// Objeto para viagem
class Viagem {
    constructor(destino, transporte, diarias, alimentacao, preco, taxa, parcelas)
    {
        this.destino = destino;
        this.transporte = transporte;
        this.diarias = diarias;
        this.alimentacao = alimentacao;
        this.preco = preco;
        this.taxa = taxa;
        this.parcelas = parcelas;
    }
}

document.addEventListener("DOMContentLoaded", function () {
    // Espera que o DOM esteja totalmente carregado antes de executar o script

    // Referências aos elementos do DOM
    const btns = document.querySelectorAll(".comprar-viagem");

    // Loop para adicionar os ouvintes de evento a todos os botões
    btns.forEach(function (btn) {
        btn.addEventListener("click", function (event) {
            event.preventDefault(); // Evita o comportamento padrão do link
            const destinoId = this.closest(".item-plans").id;
            capturarInformacoes(destinoId);      
        });
    });
});

function capturarInformacoes(destinoId) {
    const element = document.getElementById(destinoId);

    const destino = element.querySelector(".destino").textContent;
    const transporte = element.querySelector(".transporte").textContent;
    const diarias = element.querySelector(".diarias").textContent;
    const alimentacao = element.querySelector(".alimentacao").textContent;
    const preco = element.querySelector(".plan-price").textContent;
    const taxa = element.querySelector(".taxas").textContent;
    const parcelas = element.querySelector(".parcelas").textContent;

    const viagem = new Viagem(destino, transporte, diarias, alimentacao, preco, taxa, parcelas);

    console.log(viagem)
}


