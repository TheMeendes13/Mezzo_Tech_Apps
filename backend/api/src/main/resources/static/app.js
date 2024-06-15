document.addEventListener('DOMContentLoaded', () => {
    let token = localStorage.getItem('token');

    // Função para exibir mensagens de alerta
    function showAlert(message) {
        const alert = document.getElementById('alert');
        if (alert) {
            alert.textContent = message;
            alert.style.display = 'block';
        }
    }

    // Função para decodificar o token JWT
    function parseJwt(token) {
        var base64Url = token.split('.')[1];
        var base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
        var jsonPayload = decodeURIComponent(atob(base64).split('').map(function(c) {
            return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
        }).join(''));

        return JSON.parse(jsonPayload);
    }

    // Função para buscar informações do usuário
    function fetchUserInfo() {
        const userNameElement = document.getElementById('nome');
        if (!userNameElement) {
            console.error('Elemento nome não encontrado no DOM.');
            return;
        }

        const decodedToken = parseJwt(token);
        console.log('Token decodificado:', decodedToken); // Log do token decodificado
        const userId = decodedToken.id;

        if (!userId) {
            console.error('ID do usuário não encontrado no token.');
            return;
        }

        console.log(`Recuperando dados do usuário com ID: ${userId}`);

        fetch(`http://localhost:8080/usuarios/${userId}`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + token
            }
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('Erro na resposta da API');
            }
            return response.json();
        })
        .then(data => {
            console.log('Dados do usuário:', data);
            if (data && data.nome) {
                userNameElement.textContent = data.nome;
            } else {
                throw new Error('Dados do usuário inválidos');
            }
        })
        .catch(error => {
            console.error('Erro ao buscar dados do usuário:', error);
            if (userNameElement) {
                userNameElement.textContent = 'Usuário';
            }
        });
    }

    // Função para buscar e exibir os roteiros do usuário
    function fetchRoteiros() {
        const decodedToken = parseJwt(token);
        console.log('Token decodificado:', decodedToken); // Log do token decodificado
        const userId = decodedToken.id;
        const roteirosList = document.getElementById('roteiros-list');

        if (!userId) {
            console.error('ID do usuário não encontrado no token.');
            return;
        }

        fetch(`http://localhost:8080/roteiros?usuarioId=${userId}`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + token
            }
        })
        .then(response => response.json())
        .then(data => {
            if (Array.isArray(data) && data.length) {
                data.forEach(roteiro => {
                    const roteiroCard = document.createElement('div');
                    roteiroCard.className = 'col-md-4 mb-3';

                    const gasto = roteiro.gasto ? roteiro.gasto.toFixed(2) : 'N/A';

                    const roteiroContent = `
                        <div class="card">
                            <div class="card-body">
                                <h5 class="card-title">${roteiro.origem} - ${roteiro.destino}</h5>
                                <p class="card-text"><strong>Data de Saída:</strong> ${roteiro.dataSaida}</p>
                                <p class="card-text"><strong>Data de Retorno:</strong> ${roteiro.dataRetorno}</p>
                                <p class="card-text"><strong>Gasto Esperado:</strong> R$ ${roteiro.gasto}</p>
                                <p class="card-text"><strong>Número do Voo:</strong> ${roteiro.voo}</p>
                            </div>
                        </div>
                    `;

                    roteiroCard.innerHTML = roteiroContent;
                    roteirosList.appendChild(roteiroCard);
                });
            } else {
                roteirosList.innerHTML = '<p>Você não tem nenhum roteiro cadastrado.</p>';
            }
        })
        .catch(error => {
            console.error('Erro ao buscar roteiros:', error);
            roteirosList.innerHTML = '<p>Erro ao buscar roteiros. Tente novamente mais tarde.</p>';
        });
    }

    // Lógica de cadastro de roteiro
    const cadastroRoteiroForm = document.getElementById('cadastroRoteiroForm');
    if (cadastroRoteiroForm) {
        cadastroRoteiroForm.addEventListener('submit', (event) => {
            event.preventDefault();

            const formData = {
                origem: document.getElementById('origem').value,
                destino: document.getElementById('destino').value,
                gasto: document.getElementById('gasto').value,
                voo: document.getElementById('voo').value,
                dataSaida: document.getElementById('dataSaida').value,
                dataRetorno: document.getElementById('dataRetorno').value
            };

            const userId = parseJwt(token).id;

            console.log('Dados de cadastro do roteiro:', formData);
            fetch(`http://localhost:8080/roteiros?usuarioId=${userId}`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': 'Bearer ' + token
                },
                body: JSON.stringify(formData)
            })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Erro ao cadastrar roteiro');
                }
                return response.json();
            })
            .then(data => {
                console.log('Roteiro cadastrado:', data);
                alert('Roteiro cadastrado com sucesso!');
                window.location.href = 'roteiros.html';
            })
            .catch(error => {
                console.error('Erro ao cadastrar roteiro:', error);
                showAlert('Erro ao cadastrar roteiro. Por favor, tente novamente.');
            });
        });
    }

    // Lógica de registro de usuário
    const registerForm = document.getElementById('registerForm');
    if (registerForm) {
        registerForm.addEventListener('submit', (event) => {
            event.preventDefault();
            const formData = {
                nome: document.getElementById('nome').value,
                email: document.getElementById('email').value,
                senha: document.getElementById('senha').value,
                genero: document.getElementById('genero').value,
                perfil: document.getElementById('perfil').value,
                base: {
                    pais: document.getElementById('pais').value,
                    estado: document.getElementById('estado').value,
                    cidade: document.getElementById('cidade').value
                }
            };
            console.log('Dados de cadastro do usuário:', formData);
            fetch('http://localhost:8080/usuarios', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(formData)
            })
            .then(response => response.json())
            .then(data => {
                console.log('Resposta da API de cadastro:', data);
                if (data.id) {
                    alert('Usuário cadastrado com sucesso!');
                    window.location.href = 'login.html'; // Redireciona para a página inicial após o login
                } else {
                    alert('Erro ao cadastrar usuário!');
                }
            })
            .catch(error => {
                console.error('Erro ao cadastrar usuário:', error);
                alert('Erro ao cadastrar usuário!');
            });
        });
    }

    // Lógica de login de usuário
    const loginForm = document.getElementById('loginForm');
    if (loginForm) {
        loginForm.addEventListener('submit', (event) => {
            event.preventDefault();

            const email = document.getElementById('email').value;
            const password = document.getElementById('senha').value;

            const loginData = {
                email: email,
                senha: password
            };

            console.log('Dados de login do usuário:', loginData);
            fetch('http://localhost:8080/login', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(loginData)
            })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Login ou senha inválidos');
                }
                return response.json();
            })
            .then(data => {
                console.log('Resposta da API de login:', data);
                if (data.token) {
                    localStorage.setItem('token', data.token);
                    alert('Login bem-sucedido!');
                    window.location.href = 'index.html'; // Redireciona para a página inicial após o login
                } else {
                    showAlert('Login ou senha inválidos!');
                }
            })
            .catch(error => {
                console.error('Erro ao fazer login:', error);
                showAlert('Erro ao fazer login. Por favor, tente novamente.');
            });
        });
    }

    // Verifica se o token está presente e busca as informações do usuário
    if (token) {
        if (window.location.pathname.includes('roteiros.html')) {
            fetchRoteiros();
        } else {
            fetchUserInfo();
        }
    } else {
        const userNameElement = document.getElementById('nome');
        if (userNameElement) {
            userNameElement.textContent = 'Usuário';
        }
    }
});
