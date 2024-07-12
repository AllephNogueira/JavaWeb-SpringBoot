document.getElementById('cadastroForm').addEventListener('submit', function(event) {
    event.preventDefault();

    const nome = document.getElementById('nome').value;
    const email = document.getElementById('email').value;
    const crm = document.getElementById('crm').value;
    const telefone = document.getElementById('telefone').value;
    const especialidade = document.getElementById('especialidade').value;
    const logradouro = document.getElementById('logradouro').value;
    const bairro = document.getElementById('bairro').value;
    const cidade = document.getElementById('cidade').value;
    const uf = document.getElementById('uf').value;
    const cep = document.getElementById('cep').value;
    const numero = document.getElementById('numero').value;
    const complemento = document.getElementById('complemento').value;

    const data = {
        nome: nome,
        email: email,
        crm: crm,
        telefone: telefone,
        especialidade: especialidade,
        endereco: {
            logradouro: logradouro,
            bairro: bairro,
            cep: cep,
            numero: numero,
            complemento: complemento,
            cidade: cidade,
            uf: uf
        }
    };

    fetch('http://localhost:8080/medicos', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
    .then(response => response.json())
    .then(data => {
        document.getElementById('response').innerText = 'Cadastro realizado com sucesso!';
    })
    .catch(error => {
        document.getElementById('response').innerText = 'Erro ao realizar cadastro.';
        console.error('Erro:', error);
    });
});
