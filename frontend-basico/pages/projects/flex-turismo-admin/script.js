// Objeto para viagem
class Viagem {
    constructor(destino, imagem, transporte, diarias, alimentacao, preco, taxas, parcelas) {
        this.destino = destino;
        this.imagem = imagem;
        this.transporte = transporte;
        this.diarias = diarias;
        this.alimentacao = alimentacao;
        this.preco = preco;
        this.taxas = taxas;
        this.parcelas = parcelas;
    }
}

document.addEventListener("DOMContentLoaded", function () {
    // Espera que o DOM esteja totalmente carregado antes de executar o script

    // Referências aos elementos do DOM
    const btnsCompra = document.querySelectorAll(".comprar-viagem");
    const btnAbrirForm = document.getElementById("abrir-formulario");
    const btnInserir = document.getElementById("inserir-formulario");
    const btnFechar = document.getElementById("fechar-formulario");

    btnAbrirForm.addEventListener("click", function () {
        document.getElementById("overlay").style.display = "flex";
    })

    btnFechar.addEventListener("click", function () {
        document.getElementById("overlay").style.display = "none";
    })
    
    btnInserir.addEventListener("click", function (event) {
        event.preventDefault(); // Evita o comportamento padrão do link

        const destino = document.getElementById("destino").value;
        const imagem = document.getElementById("imagem").value;
        const transporte = document.getElementById("transporte").value;
        const diarias = document.getElementById("diarias").value;
        const alimentacao = document.getElementById("alimentacao").value;
        const preco = document.getElementById("preco").value;
        const taxas = document.getElementById("taxas").value;
        const parcelas = document.getElementById("parcelas").value;

        const novaViagem = new Viagem(destino, imagem, transporte, diarias, alimentacao, preco, taxas, parcelas);

        adicionarViagem(novaViagem);
    });

    // Loop para adicionar os ouvintes de evento a todos os botões
    btnsCompra.forEach(function (btn) {
        btn.addEventListener("click", function (event) {
            event.preventDefault(); // Evita o comportamento padrão do link

            const destinoId = this.closest(".item-plans").id;
            capturarInformacoes(destinoId);
        });
    });
});

function adicionarViagem(novaViagem) {
    const novaViagemHTML = `
    <div class="item-plans" id="${novaViagem.destino}">
        <h3 class="destino">${novaViagem.destino}</h3>
        <img src="${novaViagem.imagem}" alt="Imagem de ${novaViagem.destino}">
        <ul class="list-informations">
            <li class="transporte">${novaViagem.transporte}</li>
            <li class="diarias">${novaViagem.diarias} diárias</li>
            <li class="alimentacao">${novaViagem.alimentacao}</li>
        </ul>
        <div class="info-item-plans">
            <h2 class="plan-price">R$${novaViagem.preco}</h2>
            <p class="taxas">${novaViagem.taxas === "sim" ? "Taxas Inclusas" : "Taxas Não Inclusas"}</p>
            <p class="parcelas">Em até ${novaViagem.parcelas}x sem juros</p>
        </div>
        <button class="comprar-viagem">Comprar</button>
    </div>
`;

    const listaViagens = document.querySelector(".list-plans");
    listaViagens.insertAdjacentHTML("beforeend", novaViagemHTML);

    const btnsCompra = document.querySelectorAll(".comprar-viagem");

    btnsCompra.forEach(function (btn) {
        btn.addEventListener("click", function (event) {
            event.preventDefault(); // Evita o comportamento padrão do link

            const destinoId = this.closest(".item-plans").id;
            capturarInformacoes(destinoId);
        });
    });

    fecharFormulario();
}

function fecharFormulario() {
    document.getElementById('overlay').style.display = 'none';
}

function capturarInformacoes(destinoId) {
    const element = document.getElementById(destinoId);

    const destino = element.querySelector(".destino").textContent;
    const transporte = element.querySelector(".transporte").textContent;
    const diarias = element.querySelector(".diarias").textContent;
    const alimentacao = element.querySelector(".alimentacao").textContent;
    const preco = element.querySelector(".plan-price").textContent;
    const taxas = element.querySelector(".taxas").textContent;
    const parcelas = element.querySelector(".parcelas").textContent;

    const viagem = new Viagem(destino, transporte, diarias, alimentacao, preco, taxas, parcelas);

    console.log(viagem);
}
