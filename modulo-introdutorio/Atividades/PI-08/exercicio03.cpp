#include <stdio.h>
#include <stdlib.h>

int ehAnoBissexto(int ano) {
    return (ano % 4 == 0 && ano % 100 != 0) || (ano % 400 == 0);
}

int ehDataValida(int dia, int mes, int ano) {
    if (ano < 0 || mes < 1 || mes > 12)
        return 0;

    int diasNoMes[] = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    if (ehAnoBissexto(ano))
        diasNoMes[1] = 29;

    if (dia < 1 || dia > diasNoMes[mes - 1])
        return 0;

    return 1;
}

void imprimeData(int dia, int mes, int ano) {
    char* nomesMeses[] = {"janeiro", "fevereiro", "março", "abril", "maio", "junho", "julho", "agosto", "setembro", "outubro", "novembro", "dezembro"};

    printf("%d de %s de %d\n", dia, nomesMeses[mes - 1], ano);
}

int main() {
    int dia, mes, ano;

    printf("Digite uma data no formato dd/mm/aaaa: ");
    scanf("%d/%d/%d", &dia, &mes, &ano);

    if (ehDataValida(dia, mes, ano)) {
        printf("Dia: %d\n", dia);
        printf("Mês: %d\n", mes);
        printf("Ano: %d\n", ano);
        imprimeData(dia, mes, ano);
    } else {
        printf("Data inválida\n");
    }

    return 0;
}