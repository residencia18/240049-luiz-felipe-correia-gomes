#include <iostream>
#include <string>
#include <vector>
#include <algorithm>
#include <regex>

// Gestão de roteiros de uma empresa de transporte.

using namespace std;

struct ROTEIRO
{
    unsigned int codigo;
    string horarioPrevisto;
    string duracaoPrevista;
    string destino;
};

// Compara dois roteiros pelo código
bool compararPorCodigo(const ROTEIRO &a, const ROTEIRO &b)
{
    return a.codigo < b.codigo;
}

bool dataValida(string data)
{
    // Dias entre 1 e 31, meses entre 1 a 12, anos a partir de 2023
    regex dataRegex(R"((0[1-9]|[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2]|[1-9])/(202[3-9]|20[3-9][0-9]))");

    return regex_match(data, dataRegex);
}

bool horaValida(string hora)
{
    // Horários válidos
    regex horarioRegex(R"((2[0-3]|[0-1]?[0-9]):([0-5]?[0-9]))");

    return regex_match(hora, horarioRegex);
}

void incluirRoteiro(vector<ROTEIRO> &roteiros)
{
    ROTEIRO roteiro;
    string data, hora, duracao;

    cout << "Código do roteiro: ";
    cin >> roteiro.codigo;

    cout << "Data (dd/mm/aaaa): ";
    cin >> data;

    if (!dataValida(data)) // Se data estiver incorreto
    {
        cout << "Use o formato dd/mm/aaaa para a data. Não informe anos anteriores a 2023." << endl;
        return;
    }

    cout << "Horário previsto (hh:mm): ";
    cin >> hora;

    if (!horaValida(hora)) // Se hora estiver incorreto
    {
        cout << "Use o formato hh:mm para o horário." << endl;
        return;
    }

    roteiro.horarioPrevisto = "[" + data + "]" + " - " + hora + " horas";

    cout << "Duração prevista: ";
    cin >> duracao;

    if (horaValida(duracao))
    {
        roteiro.duracaoPrevista = duracao + " horas";
    }
    else
    {
        cout << "Informações inválidas: " << endl
             << "Use o formato hh:mm para a duração." << endl;
        return;
    }

    cout << "Destino: ";
    cin.ignore();
    getline(cin, roteiro.destino);

    // Encontrando a posição correta para inserir o roteiro com base no código
    auto it = lower_bound(roteiros.begin(), roteiros.end(), roteiro, compararPorCodigo);

    roteiros.insert(it, roteiro);
}

int encontrarRoteiro(vector<ROTEIRO> roteiros)
{
    unsigned int referencia;

    cout << "Código do roteiro procurado: ";
    cin >> referencia;

    for (vector<ROTEIRO>::size_type i = 0; i < roteiros.size(); i++)
    {
        if (roteiros[i].codigo == referencia)
        {
            // Encontrou o roteiro
            cout << "Roteiro com código " << referencia << " encontrado." << endl;
            return i;
        }
        else if (roteiros[i].codigo > referencia)
        {
            // Chegou a um roteiro com código maior que o pesquisado
            cout << "Roteiro com código " << referencia << " não encontrado." << endl;
            return -1;
        }
    }

    // Loop terminou sem encontrar o roteiro
    cout << "Roteiro com código " << referencia << " não encontrado" << endl;
    return -1;
}

void excluirRoteiro(vector<ROTEIRO> &roteiros)
{
    cout << "EXCLUIR ROTEIRO" << endl;

    int pos = encontrarRoteiro(roteiros);

    if (pos == -1) // Roteiro não está na lista
    {
        return;
    }
    else
    {
        roteiros.erase(roteiros.begin() + pos);
        cout << "Roteiro excluído." << endl;
        return;
    }
}

void alterarRoteiro(vector<ROTEIRO> &roteiros)
{
    cout << "ALTERAR DADO DO ROTEIRO" << endl;

    int pos = encontrarRoteiro(roteiros);
    char resposta;

    if (pos == -1) // Roteiro não está na lista
    {
        return;
    }
    else
    {
        int campo;
        string data, hora, duracao;

        cout << "Insira o número correspondente ao campo a ser alterado, qualquer outro para voltar: " << endl;

        do
        {
            cout << "---" << endl;

            cout << "1 - Código" << endl;
            cout << "2 - Data e hora prevista" << endl;
            cout << "3 - Duração prevista" << endl;
            cout << "4 - Destino" << endl;
            cout << "Opção: ";
            cin >> campo;

            switch (campo)
            {
            case 1:
                cout << "Novo código: ";
                cin >> roteiros[pos].codigo;
                cout << "Código alterado com sucesso." << endl;
                break;
            case 2:
                cout << "Nova data: ";
                cin >> data;
                cout << "Novo horário: ";
                cin >> hora;

                if (dataValida(data) && horaValida(hora))
                {
                    roteiros[pos].horarioPrevisto = "[" + data + "]" + " - " + hora + " horas";
                    cout << "Data e horário alterados com sucesso." << endl;
                }
                else
                {
                    cout << "Informações inválidas: " << endl
                         << "Use o formato dd/mm/aaaa para a data. Não informe anos anteriores a 2023." << endl
                         << "Use o formato hh:mm para o horário." << endl;
                    break;
                }
                break;
            case 3:
                cout << "Nova duração prevista: ";
                cin >> duracao;

                if (horaValida(duracao))
                {
                    roteiros[pos].duracaoPrevista = duracao + " horas";
                    cout << "Duração alterada com sucesso." << endl;
                }
                else
                {
                    cout << "Informações inválidas: " << endl
                         << "Use o formato hh:mm para a duração.";
                    break;
                }
                break;
            case 4:
                cout << "Novo destino: ";
                cin.ignore();
                getline(cin, roteiros[pos].destino);
                cout << "Destino alterado com sucesso." << endl;
                break;
            default:
                cout << "Campo não reconhecido. Nenhuma alteração foi feita." << endl;
                break;
            }

            cout << "Deseja alterar outro dado? (S/n)";
            cin >> resposta;

        } while (resposta == 's' || resposta == 'S');
    }
}

void listarRoteiros(vector<ROTEIRO> roteiros)
{
    cout << "LISTA DOS ROTEIROS" << endl;

    for (vector<ROTEIRO>::size_type pos = 0; pos < roteiros.size(); pos++)
    {
        cout << "---" << endl;
        cout << "Código: " << roteiros[pos].codigo << endl;
        cout << "Agendado para: " << roteiros[pos].horarioPrevisto << endl;
        cout << "Duração prevista: " << roteiros[pos].duracaoPrevista << endl;
        cout << "Destino: " << roteiros[pos].destino << endl;
    }
}

void localizarRoteiro(vector<ROTEIRO> roteiros)
{
    cout << "LOCALIZAR ROTEIRO" << endl;

    int pos = encontrarRoteiro(roteiros);

    if (pos == -1)
    {
        return;
    }
    else
    {
        cout << "Código: " << roteiros[pos].codigo << endl;
        cout << "Agendado para: " << roteiros[pos].horarioPrevisto << endl;
        cout << "Duração prevista: " << roteiros[pos].duracaoPrevista << endl;
        cout << "Destino: " << roteiros[pos].destino << endl;
    }
}

int main()
{
    int resposta;
    vector<ROTEIRO> roteiros;

    cout << "   -   Módulo de Gestão de Roteiros    -   " << endl;

    do
    {
        cout << "Selecione a opção desejada:" << endl;
        cout << "1 - Incluir roteiro" << endl;
        cout << "2 - Excluir roteiro" << endl;
        cout << "3 - Alterar dado do roteiro" << endl;
        cout << "4 - Listar todos os roteiros" << endl;
        cout << "5 - Localizar roteiro" << endl;
        cout << "0 - Sair" << endl;
        cout << "Opção: ";
        cin >> resposta;

        cout << endl;

        switch (resposta)
        {
        case 1:
            incluirRoteiro(roteiros);
            break;
        case 2:
            excluirRoteiro(roteiros);
            break;
        case 3:
            alterarRoteiro(roteiros);
            break;
        case 4:
            listarRoteiros(roteiros);
            break;
        case 5:
            localizarRoteiro(roteiros);
            break;
        case 0:
            cout << "Gestão de Roteiros encerrado." << endl;
            break;
        default:
            cout << "Insira o número correspondente a alguma das opções." << endl;
            break;
        }

        cout << endl;

    } while (resposta != 0);

    return 1;
}