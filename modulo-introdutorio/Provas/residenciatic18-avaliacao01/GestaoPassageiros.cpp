#include <iostream>
#include <string>
#include <vector>
#include <algorithm>
#include <locale>

// Gestão de passageiros de uma empresa de transporte.

using namespace std;

struct PASSAGEIRO
{
    string cpf;
    string nome;
    string nascimento;
    int numAutorizacao;
};

// dd/mm/aaaa
string formatarData(int dia, int mes, int ano)
{
    string diaString = to_string(dia),
           mesString = to_string(mes),
           anoString = to_string(ano);

    if (dia < 10)
    {
        diaString = "0" + to_string(dia);
    }

    if (mes < 10)
    {
        mesString = "0" + to_string(mes);
    }

    return diaString + "/" + mesString + "/" + anoString;
}

bool validarData(int dia, int mes, int ano)
{
    if (dia < 1 || dia > 31)
    {
        cout << "Dia inválido. O dia deve estar entre 1 e 31." << endl;
        return false;
    }

    if (mes < 1 || mes > 12)
    {
        cout << "Mês inválido. O mês deve estar entre 1 e 12." << endl;
        return false;
    }

    if (ano < 1900 || ano > 2023) // Ano atual: 2023
    {
        cout << "Ano inválido. O ano deve estar entre 1900 e 2023." << endl;
        return false;
    }

    return true;
}

// Compara dois passageiros pelo CPF
bool compararPorCPF(const PASSAGEIRO &a, const PASSAGEIRO &b)
{
    return a.cpf < b.cpf;
}

int encontrarPassageiro(vector<PASSAGEIRO> passageiros)
{
    string referencia;

    cout << "CPF do passageiro procurado: ";
    cin >> referencia;

    for (vector<PASSAGEIRO>::size_type i = 0; i < passageiros.size(); i++)
    {
        if (passageiros[i].cpf == referencia)
        {
            // Encontrou o passageiro com o CPF especificado
            cout << "Passageiro com CPF " << referencia << " encontrado." << endl;
            return i; // Retorna o índice dele na lista
        }
        else if (passageiros[i].cpf > referencia)
        {
            // Chegou a um passageiro com CPF maior, o passageiro não está na lista
            cout << "Passageiro com CPF " << referencia << " não encontrado." << endl;
            return -1; // Passageiro não encontrado
        }
    }

    // Se o loop terminar sem encontrar o passageiro, significa que ele não está na lista
    cout << "Passageiro com CPF " << referencia << " não encontrado." << endl;
    return -1; // Passageiro não encontrado
}

bool incluirPassageiro(vector<PASSAGEIRO> &passageiros)
{
    cout << "INCLUIR PASSAGEIRO" << endl;

    PASSAGEIRO passageiro;
    int dia, mes, ano;

    cout << "Nome do passageiro: ";
    cin.ignore();                  
    getline(cin, passageiro.nome); 

    cout << "CPF do passageiro: ";
    cin >> passageiro.cpf;

    cout << "Dia de nascimento: ";
    cin >> dia;

    cout << "Mes de nascimento: ";
    cin >> mes;

    cout << "Ano de nascimento: ";
    cin >> ano;

    if (!validarData(dia, mes, ano)) // se data estiver inválida
    {
        return false;
    }

    passageiro.nascimento = formatarData(dia, mes, ano);

    cout << "Número de autorização: ";
    cin >> passageiro.numAutorizacao;

    // Encontrando a posição correta para inserir o passageiro com base no CPF
    auto it = lower_bound(passageiros.begin(), passageiros.end(), passageiro, compararPorCPF);

    // Inserindo o passageiro na posição encontrada
    passageiros.insert(it, passageiro);

    return true; // O passageiro foi adicionado sem problemas
}

bool excluirPassageiro(vector<PASSAGEIRO> &passageiros)
{
    cout << "EXCLUIR PASSAGEIRO" << endl;

    int pos = encontrarPassageiro(passageiros);

    if (pos == -1) // CPF informado não está na lista
    {
        return false;
    }
    else
    {
        passageiros.erase(passageiros.begin() + pos);
        cout << "Passageiro excluído." << endl;
        return true;
    }

    return false; // O passageiro informado não existe
}

bool localizarPassageiro(vector<PASSAGEIRO> passageiros)
{
    cout << "LOCALIZAR PASSAGEIRO" << endl;

    int pos = encontrarPassageiro(passageiros);

    if (pos == -1) // CPF informado não está na lista
    {
        return false;
    }
    else
    {
        cout << "Nome: " << passageiros[pos].nome << endl;
        cout << "CPF: " << passageiros[pos].cpf << endl;
        cout << "Data de nascimento: " << passageiros[pos].nascimento << endl;
        cout << "Número de autorização: " << passageiros[pos].numAutorizacao << endl;

        return true; // Passageiro encontrado e informações impressas na tela
    }
}

void alterarDado(vector<PASSAGEIRO> &passageiros)
{
    cout << "ALTERAR DADO DO PASSAGEIRO" << endl;

    int pos = encontrarPassageiro(passageiros);
    char resposta;

    if (pos == -1) // CPF informado não está na lista
    {
        return;
    }
    else
    {
        int campo;

        cout << "Insira o número correspondente ao campo a ser alterado, qualquer outro para voltar: " << endl;

        do
        {
            cout << "---" << endl;

            cout << "1 - Nome" << endl;
            cout << "2 - CPF" << endl;
            cout << "3 - Data de nascimento" << endl;
            cout << "4 - Número de autorização" << endl;
            cout << "Opção: ";
            cin >> campo;

            switch (campo)
            {
            case 1:
                cin.ignore();                        
                getline(cin, passageiros[pos].nome); 
                break;
            case 2:
                cout << "Novo CPF: ";
                cin >> passageiros[pos].cpf;
                break;
            case 3:
                int dia, mes, ano;
                cout << "Novo dia de nascimento: ";
                cin >> dia;
                cout << "Novo mês de nascimento: ";
                cin >> mes;
                cout << "Novo ano de nascimento: ";
                cin >> ano;
                if (!validarData(dia, mes, ano)) // se data estiver inválida
                {
                    break;
                }
                else
                {
                    passageiros[pos].nascimento = formatarData(dia, mes, ano);
                }
                break;
            case 4:
                cout << "Novo número de autorização: ";
                cin >> passageiros[pos].numAutorizacao;
                break;
            default:
                cout << "Campo não reconhecido. Nenhuma alteração foi feita." << endl;
                break;
            }

            cout << "Deseja altera outro dado? (S/n)";
            cin >> resposta;

        } while (resposta == 's' || resposta == 'S');
    }
}

void listarPassageiros(vector<PASSAGEIRO> passageiros)
{
    cout << "LISTA DOS PASSAGEIROS" << endl;
    int qntPassageiros = 0;

    for (vector<PASSAGEIRO>::size_type pos = 0; pos < passageiros.size(); pos++)
    {
        cout << "---" << endl;
        cout << "Nome: " << passageiros[pos].nome << endl;
        cout << "CPF: " << passageiros[pos].cpf << endl;
        cout << "Data de nascimento: " << passageiros[pos].nascimento << endl;
        cout << "Número de autorização: " << passageiros[pos].numAutorizacao << endl;

        qntPassageiros++;
    }

    if (qntPassageiros == 0)
    {
        cout << "(sem passageiros na lista)" << endl;
    }
    else
    {   
        cout << endl << "Quantidade de passageiros: " << qntPassageiros << endl;
    }
}

// FUNÇÃO PRINCIPAL
int main()
{
    int resposta;
    vector<PASSAGEIRO> passageiros;

    cout << "   -  Módulo de Gestão de Passageiros  -   " << endl;

    do
    {
        cout << "Selecione a opção desejada:" << endl;
        cout << "1 - Incluir passageiro" << endl;
        cout << "2 - Excluir passageiro" << endl;
        cout << "3 - Alterar dado do passageiro" << endl;
        cout << "4 - Listar todos os passageiros" << endl;
        cout << "5 - Localizar passageiro" << endl;
        cout << "0 - Sair" << endl;
        cout << "Opção: ";
        cin >> resposta;

        cout << endl;

        switch (resposta)
        {
        case 1:
            incluirPassageiro(passageiros);
            break;
        case 2:
            excluirPassageiro(passageiros);
            break;
        case 3:
            alterarDado(passageiros);
            break;
        case 4:
            listarPassageiros(passageiros);
            break;
        case 5:
            localizarPassageiro(passageiros);
            break;
        case 0:
            cout << "Gestão de passageiros encerrado." << endl;
            break;
        default:
            cout << "Insira o número correspondente a alguma das opções." << endl;
            break;
        }

        cout << endl;

    } while (resposta != 0);
}