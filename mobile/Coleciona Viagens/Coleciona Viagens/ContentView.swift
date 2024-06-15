import SwiftUI

struct ContentView: View {
    @State private var nome = ""
    @State private var email = ""
    @State private var senha = ""
    @State private var genero = 0
    @State private var perfil = 0
    @State private var pais = ""
    @State private var estado = ""
    @State private var cidade = ""
    @State private var showingAlert = false
    @State private var alertMessage = ""
    
    let generos = ["HOMEM_CIS", "MULHER_CIS", "HOMEM_TRANS", "MULHER_TRANS", "NAO_BINARIO"]
    let perfis = ["Refinado", "Aventureiro", "Cultural", "Gastronômico"]
    
    var body: some View {
        NavigationView {
            ScrollView {
                VStack(spacing: 20) {
                    Text("Cadastro de Usuário")
                        .font(.largeTitle)
                        .padding(.top, 40)
                    
                    Group {
                        TextField("Nome", text: $nome)
                            .textFieldStyle(RoundedBorderTextFieldStyle())
                        TextField("Email", text: $email)
                            .textFieldStyle(RoundedBorderTextFieldStyle())
                            .keyboardType(.emailAddress)
                        SecureField("Senha", text: $senha)
                            .textFieldStyle(RoundedBorderTextFieldStyle())
                    }
                    
                    HStack(spacing: 20) {
                        VStack(alignment: .leading) {
                            Text("Gênero")
                                .font(.headline)
                            Picker("Gênero", selection: $genero) {
                                ForEach(0..<generos.count) {
                                    Text(self.displayGenero(self.generos[$0]))
                                }
                            }
                            .pickerStyle(MenuPickerStyle())
                            .frame(maxWidth: .infinity)
                            .padding()
                            .background(RoundedRectangle(cornerRadius: 10).stroke(Color.gray, lineWidth: 1))
                        }
                        
                        VStack(alignment: .leading) {
                            Text("Perfil")
                                .font(.headline)
                            Picker("Perfil", selection: $perfil) {
                                ForEach(0..<perfis.count) {
                                    Text(self.perfis[$0])
                                }
                            }
                            .pickerStyle(MenuPickerStyle())
                            .frame(maxWidth: .infinity)
                            .padding()
                            .background(RoundedRectangle(cornerRadius: 10).stroke(Color.gray, lineWidth: 1))
                        }
                    }
                    
                    Group {
                        TextField("País", text: $pais)
                            .textFieldStyle(RoundedBorderTextFieldStyle())
                        TextField("Estado/Província", text: $estado)
                            .textFieldStyle(RoundedBorderTextFieldStyle())
                        TextField("Cidade", text: $cidade)
                            .textFieldStyle(RoundedBorderTextFieldStyle())
                    }
                    
                    Button(action: {
                        self.registerUser()
                    }) {
                        Text("Cadastrar")
                            .font(.headline)
                            .foregroundColor(.white)
                            .padding()
                            .frame(maxWidth: .infinity)
                            .background(Color.blue)
                            .cornerRadius(10)
                    }
                }
                .padding()
                .alert(isPresented: $showingAlert) {
                    Alert(title: Text("Mensagem"), message: Text(alertMessage), dismissButton: .default(Text("OK")))
                }
            }
            .navigationBarHidden(true)
        }
    }
    
    func displayGenero(_ genero: String) -> String {
        return genero.replacingOccurrences(of: "_", with: " ").capitalized
    }
    
    func registerUser() {
        print("Iniciando registro do usuário...")
        
        guard let url = URL(string: "http://localhost:8080/usuarios") else {
            print("URL inválida")
            self.showAlert(message: "URL inválida")
            return
        }
        
        let userData: [String: Any] = [
            "nome": nome,
            "email": email,
            "senha": senha,
            "genero": generos[genero],
            "perfil": perfis[perfil],
            "base": [
                "cidade": cidade,
                "estado": estado,
                "pais": pais
            ]
        ]
        
        print("Dados do usuário: \(userData)")
        
        var request = URLRequest(url: url)
        request.httpMethod = "POST"
        request.setValue("application/json", forHTTPHeaderField: "Content-Type")
        
        do {
            request.httpBody = try JSONSerialization.data(withJSONObject: userData, options: [])
            if let requestBody = request.httpBody {
                print("Corpo da requisição: \(String(data: requestBody, encoding: .utf8) ?? "Não foi possível converter o corpo da requisição para string")")
            }
        } catch {
            print("Erro ao serializar os dados do usuário: \(error)")
            self.showAlert(message: "Erro ao serializar os dados do usuário: \(error.localizedDescription)")
            return
        }
        
        print("Enviando requisição para \(url)...")
        
        let task = URLSession.shared.dataTask(with: request) { data, response, error in
            if let error = error {
                print("Erro na requisição: \(error)")
                self.showAlert(message: "Erro na requisição: \(error.localizedDescription)")
                return
            }
            
            if let httpResponse = response as? HTTPURLResponse {
                print("Código de resposta HTTP: \(httpResponse.statusCode)")
            }
            
            guard let data = data else {
                print("Nenhum dado recebido")
                self.showAlert(message: "Nenhum dado recebido")
                return
            }
            
            do {
                if let json = try JSONSerialization.jsonObject(with: data, options: []) as? [String: Any] {
                    print("Resposta da API: \(json)")
                    DispatchQueue.main.async {
                        if let id = json["id"] as? Int {
                            self.showAlert(message: "Usuário cadastrado com sucesso! ID: \(id)")
                        } else {
                            self.showAlert(message: "Erro ao cadastrar usuário!")
                        }
                    }
                }
            } catch {
                print("Erro ao parsear a resposta da API: \(error)")
                if let responseString = String(data: data, encoding: .utf8) {
                    print("Resposta da API (String): \(responseString)")
                }
                self.showAlert(message: "Erro ao parsear a resposta da API: \(error.localizedDescription)")
            }
        }
        
        task.resume()
    }
    
    func showAlert(message: String) {
        self.alertMessage = message
        self.showingAlert = true
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
