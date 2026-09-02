let usuarioLogado = null;

function mostrarRegisto() {
    document.getElementById('loginSection').style.display = 'none';
    document.getElementById('registerSection').style.display = 'block';
}

function mostrarLogin() {
    document.getElementById('loginSection').style.display = 'block';
    document.getElementById('registerSection').style.display = 'none';
}

async function fazerLogin() {
    const email = document.getElementById('loginEmail').value;
    const senha = document.getElementById('loginSenha').value;
    const response = await fetch('/api/login', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ email, senha })
    });
    if (response.ok) {
        usuarioLogado = await response.json();
        document.getElementById('loginSection').style.display = 'none';
        document.getElementById('dashboardSection').style.display = 'block';
        document.getElementById('nomeUsuario').innerText = usuarioLogado.nome;
        document.getElementById('papelUsuario').innerText = usuarioLogado.papel ? usuarioLogado.papel.nome : 'Sem Papel';
        if (usuarioLogado.papel && (usuarioLogado.papel.nome === 'Gestor' || usuarioLogado.papel.nome === 'Administrador')) {
            document.getElementById('colunaAcao').style.display = 'table-cell';
        }
        carregarExpedientes();
    } else {
        document.getElementById('erroLogin').innerText = 'Falha no login.';
    }
}

async function criarConta() {
    const nome = document.getElementById('regNome').value;
    const email = document.getElementById('regEmail').value;
    const senha = document.getElementById('regSenha').value;
    const response = await fetch('/api/register', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ nome, email, senha })
    });
    if (response.ok) {
        document.getElementById('msgRegisto').innerText = 'Conta criada! A redirecionar...';
        setTimeout(mostrarLogin, 2000);
    } else {
        document.getElementById('msgRegisto').innerText = 'Erro ao criar conta.';
    }
}

async function carregarExpedientes() {
    const response = await fetch('/api/expedientes');
    const dados = await response.json();
    const corpoTabela = document.querySelector('#tabelaExpedientes tbody');
    corpoTabela.innerHTML = '';
    dados.forEach(exp => {
        let acaoBtn = '';
        if (usuarioLogado.papel && (usuarioLogado.papel.nome === 'Gestor' || usuarioLogado.papel.nome === 'Administrador')) {
            acaoBtn = `<td><button onclick="alert('Tramitado!')">Tramitar</button></td>`;
        }
        corpoTabela.innerHTML += `<tr>
            <td>${exp.numeroProcesso}</td>
            <td>${exp.assunto}</td>
            <td>${exp.remetente}</td>
            <td>${exp.estado}</td>
            ${acaoBtn}
        </tr>`;
    });
}

async function salvarExpediente() {
    const expediente = {
        numeroProcesso: document.getElementById('numeroProcesso').value,
        assunto: document.getElementById('assunto').value,
        remetente: document.getElementById('remetente').value
    };
    await fetch('/api/expedientes', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(expediente)
    });
    alert('Expediente registado!');
    carregarExpedientes();
}

function logout() {
    usuarioLogado = null;
    mostrarLogin();
    document.getElementById('dashboardSection').style.display = 'none';
}
