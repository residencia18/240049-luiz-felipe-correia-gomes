#include <iostream>
#include <vector>
#include <string>
#include <iomanip>
#include <map>
#include <algorithm>
#include <cmath>

using namespace std;

// Estrutura para armazenar informações da passagem
struct Passagem
{
    int numeroAssento;
    string data;
    int hora;
    string cpf;
    string nome;
    int idade;
    double preco;

    Passagem(int _numeroAssento, string _data, int _hora, string _cpf, string _nome, int _idade, double _preco)
    {
        numeroAssento = _numeroAssento;
        data = _data;
        hora = _hora;
        cpf = _cpf;
        nome = _nome;
        idade = _idade;
        preco = _preco;
    }
};

// Estrutura para armazenar informações de uma viagem
struct Viagem
{
    int numeroViagem;
    string cidadeOrigem;
    string cidadeDestino;
    int horario;
    vector<Passagem> passagens;

    Viagem(int _numeroViagem, string _cidadeOrigem, string _cidadeDestino, int _horario)
    {
        numeroViagem = _numeroViagem;
        cidadeOrigem = _cidadeOrigem;
        cidadeDestino = _cidadeDestino;
        horario = _horario;
    }
};

// Função para calcular o total arrecadado em uma viagem
double calcularTotalArrecadadoViagem(const Viagem &viagem)
{
    double totalArrecadado = 0.0;
    for (const Passagem &passagem : viagem.passagens)
    {
        totalArrecadado += passagem.preco;
    }
    return totalArrecadado;
}

// Função para calcular o total arrecadado em um determinado mês
double calcularTotalArrecadadoMes(const vector<Viagem> &viagens, int mes)
{
    double totalArrecadado = 0.0;
    for (const Viagem &viagem : viagens)
    {
        for (const Passagem &passagem : viagem.passagens)
        {
            // Supomos que a data está no formato "dd/mm/aaaa", para extrair o mes
            int viagemMes = stoi(passagem.data.substr(3, 2));
            if (viagemMes == mes)
            {
                totalArrecadado += passagem.preco;
            }
        }
    }
    return totalArrecadado;
}

// Função para encontrar o horário de viagem mais rentável
int encontrarHorarioMaisRentavel(const vector<Viagem> &viagens)
{
    map<int, double> horarioArrecadado;
    for (const Viagem &viagem : viagens)
    {
        horarioArrecadado[viagem.horario] += calcularTotalArrecadadoViagem(viagem);
    }

    int horarioMaisRentavel = -1;
    double maxArrecadado = -1.0;

    for (const auto &pair : horarioArrecadado)
    {
        if (pair.second > maxArrecadado)
        {
            maxArrecadado = pair.second;
            horarioMaisRentavel = pair.first;
        }
    }

    return horarioMaisRentavel;
}

// Função para calcular a média de idade dos passageiros
double calcularMediaIdadePassageiros(const Viagem &viagem)
{
    if (viagem.passagens.empty())
    {
        return 0.0;
    }

    int somaIdades = 0;
    for (const Passagem &passagem : viagem.passagens)
    {
        somaIdades += passagem.idade;
    }

    return static_cast<double>(somaIdades) / viagem.passagens.size();
}

// Função para retornar o nome do passageiro de uma determinada poltrona P de uma determinada viagem.
string obterNomePassageiro(const vector<Viagem> &viagens)
{
    int numeroViagem, numeroAssento;
    string dataViagem;

    cout << "Digite a data da viagem: ";
    cin >> dataViagem;

    bool encontrou = false;

    // Encontra a data da passagem
    for (const Viagem &viagem : viagens)
    {
        for (const Passagem &passagem : viagem.passagens)
        {
            if (passagem.data == dataViagem)
            {
                encontrou = true;
            }
        }
    }

    if (encontrou)
    {
        cout << "Encontrado viagens nessa data" << endl;
    }
    else
    {
        return "Não encontrado viagens na data especificada";
    }

    cout << "Digite o número da viagem: ";
    cin >> numeroViagem;

    if (numeroViagem < 1 || numeroViagem > 10)
    {
        return "Número de viagem inválido.";
    }

    cout << "Digite o número da poltrona: ";
    cin >> numeroAssento;

    for (const Viagem &viagem : viagens)
    {
        if (viagem.numeroViagem == numeroViagem)
        {
            for (const Passagem &passagem : viagem.passagens)
            {
                if (passagem.numeroAssento == numeroAssento)
                {
                    return "Passageiro encontrado: " + passagem.nome;
                }
            }
        }
    }
    return "Poltrona vazia"; // Se a poltrona estiver vazia
}

int main()
{
    // Criar 10 viagens (5 de ida e 5 de volta)
    vector<Viagem> viagens;
    for (int i = 1; i <= 5; ++i)
    {
        // Horários aleatorios de 1 a 5
        viagens.push_back(Viagem(i, "Rio de Janeiro", "São Paulo", rand() % 5 + 1));
        viagens.push_back(Viagem(i + 5, "São Paulo", "Rio de Janeiro", rand() % 5 + 1));
    }

    // Simular a venda de passagens (você pode adicionar seus próprios dados)
    viagens[0].passagens.push_back(Passagem(1, "01/09/2023", 2, "12345678901", "João", 30, 80.0));
    viagens[0].passagens.push_back(Passagem(2, "01/09/2023", 2, "98765432109", "Maria", 25, 80.0));
    viagens[0].passagens.push_back(Passagem(3, "01/09/2023", 2, "45678901234", "Pedro", 35, 80.0));

    int escolha;
    do
    {
        cout << "Escolha uma opção:" << endl;
        cout << "1. Calcular o total arrecadado para uma viagem" << endl;
        cout << "2. Calcular o total arrecadado em um determinado mês" << endl;
        cout << "3. Encontrar o horário de viagem mais rentável" << endl;
        cout << "4. Calcular a média de idade dos passageiros" << endl;
        cout << "5. Encontrar o nome do passageiro de uma determinada poltrona" << endl;
        cout << "0. Sair do programa" << endl;

        cin >> escolha;

        switch (escolha)
        {
        case 1:
        {
            int numeroViagem;
            cout << "Digite o número da viagem: ";
            cin >> numeroViagem;

            if (numeroViagem >= 1 && numeroViagem <= 10)
            {
                cout << "Total arrecadado para a viagem " << numeroViagem << ": " << calcularTotalArrecadadoViagem(viagens[numeroViagem - 1]) << " reais" << endl;
            }
            else
            {
                cout << "Número de viagem inválido." << endl;
            }
            break;
        }
        case 2:
        {
            int mes;
            cout << "Digite o número do mês (1 a 12): ";
            cin >> mes;

            if (mes >= 1 && mes <= 12)
            {
                cout << "Total arrecadado em " << mes << ": " << calcularTotalArrecadadoMes(viagens, mes) << " reais" << endl;
            }
            else
            {
                cout << "Mês inválido." << endl;
            }
            break;
        }
        case 3:
        {
            int horarioMaisRentavel = encontrarHorarioMaisRentavel(viagens);
            cout << "Horário mais rentável: " << horarioMaisRentavel << endl;
            break;
        }
        case 4:
        {
            int numeroViagem;
            cout << "Digite o número da viagem: ";
            cin >> numeroViagem;

            if (numeroViagem >= 1 && numeroViagem <= 10)
            {
                cout << "Média de idade dos passageiros na viagem " << numeroViagem << ": " << fixed << setprecision(2) << calcularMediaIdadePassageiros(viagens[numeroViagem - 1]) << " anos" << endl;
            }
            else
            {
                cout << "Número de viagem inválido." << endl;
            }
            break;
        }
        case 5:
            cout << obterNomePassageiro(viagens) << endl;
            break;
        case 0:
            cout << "Encerrando o programa." << endl;
            break;
        default:
            cout << "Opção inválida. Tente novamente." << endl;
        }
    } while (escolha != 0);

    return 0;
}
