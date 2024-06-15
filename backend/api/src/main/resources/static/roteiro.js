document.addEventListener('DOMContentLoaded', () => {
    const roteirosList = document.getElementById('roteiros-list');
    const token = localStorage.getItem('token');
    const userId = localStorage.getItem('userId');

    if (!token || !userId) {
        alert('Você precisa estar logado para ver seus roteiros.');
        window.location.href = 'login.html'; // Redireciona para a página de login se não estiver logado
        return;
    }

    fetch(`http://localhost:8080/roteiros?usuarioId=3`, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`
        }
    })
    .then(response => response.json())
    .then(data => {
        if (Array.isArray(data) && data.length) {
            data.forEach(roteiro => {
                const roteiroCard = document.createElement('div');
                roteiroCard.className = 'roteiro-card';

                const roteiroContent = `
                    <h2>${roteiro.origem} - ${roteiro.destino}</h2>
                    <p><strong>Data de Saída:</strong> ${roteiro.dataSaida}</p>
                    <p><strong>Data de Retorno:</strong> ${roteiro.dataRetorno}</p>
                    <p><strong>Total de Dias:</strong> ${roteiro.totalDias}</p>
                    <p><strong>Gasto Esperado:</strong> R$ ${roteiro.gastoEsperado.toFixed(2)}</p>
                    <p><strong>Número do Voo:</strong> ${roteiro.numeroVoo}</p>
                    <p><strong>Lugares a Visitar:</strong> ${roteiro.lugaresAVisitar.join(', ')}</p>
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
});
