1. Para o desenvolvimento do projeto foi criado um banco MYSQL local.
2. O desenvolvimento da API foi realizado usando springboot

3. As linhas a seguir estão contidas no arquivo application.properties e representam a base de dados e acesso ao banco MYSQL:
   spring.datasource.url=jdbc:mysql://localhost/colec
   spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
   spring.datasource.username=root
   spring.datasource.password=root
   
4. A criação das senhas dos usuários foi feita a partir do gerador de hash BCrypt:
https://bcrypt-generator.com/
Para fins de teste todas as senhas foram codificadas antes de serem salvas.
Para novos testes, codifique a senha no link acima e use-a como senha.
Para logar na ferramenta deve-se usar o e-mail de cadastro e a senha antes do hash

Exemplo: joao@email.com
        senha antes do hash: 123456 (usar para login)
        senha após o hash: $2a$12$GqECkuV11oCb4sUtrFzowu6uRPFGVL7XMu5Wtuv.2ypT6YneuVjAS (usar para cadastro)


6. O json abaixo representa a instancia da tabela "usuarios" no banco
   [
  {
    "id": 1,
    "nome": "Wesley Mendes de Oliveira",
    "email": "fabricio.mendes@email.com",
    "genero": "HOMEM_CIS",
    "cidade": "São Paulo",
    "estado": "São Paulo",
    "pais": "Brasil",
    "perfil": "Aventureiro",
    "ativo": 1,
    "senha": "$2a$12$OwFUDLhsoUoKhaPUng8VxuTr18Cq9P19UHA0ltZaJDZJjE9iAkgDW"
  },
  {
    "id": 2,
    "nome": "Ana Luiza Assis",
    "email": "ana.luiza@email.com",
    "genero": "MULHER_CIS",
    "cidade": "Belo Horizonte",
    "estado": "Minas Gerais",
    "pais": "Brasil",
    "perfil": "Urbano",
    "ativo": 1,
    "senha": "$2a$12$4OE4EOqWvFXr11oluoE5fe6QYwgM463jRp67nweqz0YByWOIFt3xK"
  },
  {
    "id": 3,
    "nome": "Mario de Andrade",
    "email": "mario@email.com",
    "genero": "HOMEM_CIS",
    "cidade": "Vitória",
    "estado": "Espírito Santo",
    "pais": "Brasil",
    "perfil": "Aventureiro",
    "ativo": 1,
    "senha": "$2a$12$GqECkuV11oCb4sUtrFzowu6uRPFGVL7XMu5Wtuv.2ypT6YneuVjAS"
  },
  {
    "id": 4,
    "nome": "Roberto Fulano",
    "email": "fulano@email.com",
    "genero": "HOMEM_CIS",
    "cidade": "Rio de Janeiro",
    "estado": "Rio de Janeiro",
    "pais": "Brasil",
    "perfil": "Refinado",
    "ativo": 1,
    "senha": "$2a$12$4OE4EOqWvFXr11oluoE5fe6QYwgM463jRp67nweqz0YByWOIFt3xK"
  },
  {
    "id": 5,
    "nome": "Maria da Graça",
    "email": "maria@email.com",
    "genero": "MULHER_CIS",
    "cidade": "São Paulo",
    "estado": "São Paulo",
    "pais": "Brasil",
    "perfil": "Urbano",
    "ativo": 1,
    "senha": "frgfdgdf"
  },
  {
    "id": 6,
    "nome": "Ronaldo Fenômeno",
    "email": "Ronaldo.fenomeno@email.com",
    "genero": "HOMEM_CIS",
    "cidade": "São Paulo",
    "estado": "São Paulo",
    "pais": "Brasil",
    "perfil": "Aventureiro",
    "ativo": 1,
    "senha": "$2a$12$eC7lMAAN1e7gW.kSTRIYVedbS8Uw23j/6ICHy/0j/ALgjhziMIt/6"
  },
  {
    "id": 7,
    "nome": "Pablo Vittar",
    "email": "Pablo@email.com",
    "genero": "MULHER_TRANS",
    "cidade": "Curitiba",
    "estado": "Paraná",
    "pais": "Brasil",
    "perfil": "Cultural",
    "ativo": 1,
    "senha": "5148654fgdhfrd"
  },
  {
    "id": 8,
    "nome": "Noemia",
    "email": "nono@email.com",
    "genero": "HOMEM_CIS",
    "cidade": "Curicica",
    "estado": "Rio de Janeiro",
    "pais": "Brasil",
    "perfil": "Refinado",
    "ativo": 1,
    "senha": "hgjkmhkjhk"
  },
  {
    "id": 9,
    "nome": "Ananias Alves",
    "email": "ananias@gmail.com",
    "genero": "HOMEM_CIS",
    "cidade": "Vitória da Conquista",
    "estado": "Bahia",
    "pais": "Brasil",
    "perfil": "Cultural",
    "ativo": 1,
    "senha": "544sd6fsdgfdegr"
  }
]

7. o json a seguir representa a tabela "roteiros" da base de dados
   [
  {
    "id": 1,
    "origem": "São Paulo",
    "destino": "Rio de Janeiro",
    "gasto": 1500.00,
    "voo": "GOL1234",
    "data_saida": "2023-12-20",
    "data_retorno": "2023-12-25",
    "usuario_id": 1,
    "ativo": 1
  },
  {
    "id": 2,
    "origem": "Recife",
    "destino": "Salvador",
    "gasto": 2000.00,
    "voo": "AZUL123",
    "data_saida": "2024-09-13",
    "data_retorno": "2024-09-20",
    "usuario_id": 3,
    "ativo": 1
  },
  {
    "id": 3,
    "origem": "Curitiba",
    "destino": "São Paulo",
    "gasto": 1000.00,
    "voo": "GOL789",
    "data_saida": "2024-10-13",
    "data_retorno": "2024-10-20",
    "usuario_id": 3,
    "ativo": 1
  },
  {
    "id": 4,
    "origem": "São Paulo",
    "destino": "Madrid",
    "gasto": 5000.00,
    "voo": "LTM123",
    "data_saida": "2024-07-22",
    "data_retorno": "2024-08-11",
    "usuario_id": 6,
    "ativo": 1
  },
  {
    "id": 5,
    "origem": "São Paulo",
    "destino": "Barcelona",
    "gasto": 6000.00,
    "voo": "LTM456",
    "data_saida": "2024-08-13",
    "data_retorno": "2024-08-25",
    "usuario_id": 6,
    "ativo": 1
  }
]

