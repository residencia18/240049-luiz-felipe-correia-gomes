#include <iostream>
#include <string>
#include <vector>
#include <algorithm>
#include <regex>

using namespace std;

struct PASSAGEIRO
{
    string cpf;
    string nome;
    string nascimento;
    int numAutorizacao;
};

struct ROTEIRO
{
    unsigned int codigo;
    string horarioPrevisto;
    string duracaoPrevista;
    string destino;
};

struct EMBARCA
{
    bool realizada;
    string dataHora;
    string duracao;
    PASSAGEIRO passageiro;
    ROTEIRO roteiro;
    unsigned int codigoRoteiro;
};

// Gestão de passageiros de uma empresa de transporte.

bool dataValida(string data, int opcao)
{
    // Dias entre 1 e 31, meses entre 1 a 12, anos a partir de 2023
    regex dataRegex1(R"((0[1-9]|[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2]|[1-9])/(202[3-9]|20[3-9][0-9]))");

    // Dias entre 1 e 31, meses entre 1 a 12, anos entre 1900 e 2023
    regex dataRegex2(R"((0[1-9]|[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2]|[1-9])/(19[0-9][0-9]|20[0-1][0-9]|2020|2021|2022|2023))");

    if (opcao == 1)
    {
        return regex_match(data, dataRegex1);
    }
    else if (opcao == 2)
    {
        return regex_match(data, dataRegex2);
    }
    else
    {
        return false;
    }
}

bool horaValida(string hora)
{
    // Horários válidos
    regex horarioRegex(R"((2[0-3]|[0-1]?[0-9]):([0-5]?[0-9]))");

    return regex_match(hora, horarioRegex);
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
    return -1;
}

void incluirPassageiro(vector<PASSAGEIRO> &passageiros)
{
    cout << "INCLUIR PASSAGEIRO" << endl;

    PASSAGEIRO passageiro;
    string data;

    cout << "Nome do passageiro: ";
    cin.ignore();
    getline(cin, passageiro.nome);

    cout << "CPF do passageiro: ";
    cin >> passageiro.cpf;

    cout << "Data (dd/mm/aaaa): ";
    cin >> data;

    if (!dataValida(data, 2)) // Se data estiver incorreto
    {
        cout << "Use o formato dd/mm/aaaa para a data. É permitido anos entre 1900 a 2023." << endl;
        return;
    }

    passageiro.nascimento = data;

    cout << "Número de autorização: ";
    cin >> passageiro.numAutorizacao;

    // Encontrando a posição correta para inserir o passageiro com base no CPF
    auto it = lower_bound(passageiros.begin(), passageiros.end(), passageiro, compararPorCPF);

    // Inserindo o passageiro na posição encontrada
    passageiros.insert(it, passageiro);

    cout << "Passageiro inserido com sucesso." << endl;
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
        string data;

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
                cout << "Data (dd/mm/aaaa): ";
                cin >> data;
                if (!dataValida(data, 2)) // se data estiver inválida
                {
                    cout << "Use o formato dd/mm/aaaa para a data. É permitido anos entre 1900 a 2023." << endl;
                    break;
                }
                else
                {
                    passageiros[pos].nascimento = data;
                    break;
                }
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
        cout << endl
             << "Quantidade de passageiros: " << qntPassageiros << endl;
    }
}

void menuPassageiros(vector<PASSAGEIRO> &passageiros)
{
    int resposta;

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

// Gestão de roteiros de uma empresa de transporte.

// Compara dois roteiros pelo código
bool compararPorCodigo(const ROTEIRO &a, const ROTEIRO &b)
{
    return a.codigo < b.codigo;
}

void incluirRoteiro(vector<ROTEIRO> &roteiros)
{
    ROTEIRO roteiro;
    string data, hora, duracao;

    cout << "Código do roteiro: ";
    cin >> roteiro.codigo;

    cout << "Data (dd/mm/aaaa): ";
    cin >> data;

    if (!dataValida(data, 1)) // Se data estiver incorreto
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

    cout << "Roteiro inserido com sucesso." << endl;
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
        cout << "Roteiro deletado com sucesso." << endl;
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

                if (dataValida(data, 1) && horaValida(hora))
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

void menuRoteiros(vector<ROTEIRO> &roteiros)
{
    int resposta;

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
}

// Gestão de embarques de uma empresa de transporte

bool compararPorRoteiro(const EMBARCA &a, const EMBARCA &b)
{
    return a.codigoRoteiro < b.codigoRoteiro;
}

void incluirEmbarque(vector<ROTEIRO> &roteiros, vector<PASSAGEIRO> &passageiros, vector<EMBARCA> &embarques)
{
    cout << "INCLUIR EMBARQUE" << endl;

    EMBARCA embarque;

    int posPass = encontrarPassageiro(passageiros);
    int posRot = encontrarRoteiro(roteiros);

    string data, hora;
    char resposta;

    if (posPass == -1 || posRot == -1)
    {
        cout << "CPF do passageiro ou código do roteiro inexistente." << endl;
        return;
    }
    else
    {
        embarque.passageiro = passageiros[posPass];
        embarque.roteiro = roteiros[posRot];

        embarque.codigoRoteiro = roteiros[posRot].codigo;

        cout << "O embarque foi realizado? (S/n)";
        cin >> resposta;

        if (resposta == 's' || resposta == 'S')
        {
            embarque.realizada = true;
        }

        cout << "Data real do embarque (dd/mm/aaaa): ";
        cin >> data;

        if (!dataValida(data, 1)) // Se data estiver incorreto
        {
            cout << "Use o formato dd/mm/aaaa para a data. Não informe anos anteriores a 2023." << endl;
            return;
        }

        cout << "Horário real do embarque (hh:mm): ";
        cin >> hora;

        if (!horaValida(hora)) // Se hora estiver incorreto
        {
            cout << "Use o formato hh:mm para o horário." << endl;
            return;
        }

        embarque.dataHora = "[" + data + "]" + " - " + hora + " horas";

        // Encontrando a posição correta para inserir o embarque com base no código do roteiro
        auto it = lower_bound(embarques.begin(), embarques.end(), embarque, compararPorRoteiro);

        embarques.insert(it, embarque);

        cout << "Embarque inserido com sucesso." << endl;
    }
}

int localizarPassageiroPeloRoteiro(vector<EMBARCA> &embarques, vector<ROTEIRO> &roteiros)
{
    int posRot = encontrarRoteiro(roteiros);

    vector<int> registros;
    vector<PASSAGEIRO> passageirosEmbarcados;

    if (posRot == -1) // Roteiro não está na lista
    {
        return -1;
    }
    else
    {
        for (vector<EMBARCA>::size_type pos = 0; pos < embarques.size(); pos++)
        {
            if (embarques[pos].codigoRoteiro == roteiros[posRot].codigo)
            {
                registros.push_back(pos);
                passageirosEmbarcados.push_back(embarques[pos].passageiro);
            }
        }
    }

    listarPassageiros(passageirosEmbarcados);
    int posPass = encontrarPassageiro(passageirosEmbarcados);

    if (posPass == -1)
    {
        return -1;
    }

    return registros[posPass];
}

void excluirEmbarque(vector<EMBARCA> &embarques, vector<ROTEIRO> &roteiros)
{
    cout << "EXCLUIR EMBARQUE" << endl;

    int pos = localizarPassageiroPeloRoteiro(embarques, roteiros);

    if (pos == -1)
        return;

    embarques.erase(embarques.begin() + pos);
    cout << "Embarque removido com sucesso" << endl;
}

void alterarEmbarque(vector<EMBARCA> embarques, vector<ROTEIRO> roteiros)
{
    int pos = localizarPassageiroPeloRoteiro(embarques, roteiros);

    if (pos == -1)
        return;

    cout << "Duração real do embarque: ";
    cin >> embarques[pos].duracao;

    cout << "Registro alterado com sucesso." << endl;
}

void listarEmbarques(vector<EMBARCA> embarques)
{
    cout << "LISTA DOS EMBARQUES" << endl;
    if (embarques.size() == 0)
    {
        cout << "(sem embarques registrados)" << endl;
        return;
    }

    for (vector<EMBARCA>::size_type pos = 0; pos < embarques.size(); pos++)
    {
        cout << "---" << endl;
        cout << "Nome do passageiro: " << embarques[pos].passageiro.nome << endl;
        cout << "CPF do passageiro: " << embarques[pos].passageiro.cpf << endl;
        cout << "Código do roteiro: " << embarques[pos].codigoRoteiro << endl;
        cout << "Destino: " << embarques[pos].roteiro.destino << endl;
        cout << "Data: " << embarques[pos].dataHora << endl;
        if (embarques[pos].realizada == true)
            cout << "Realizado: [sim]" << endl;
        else
            cout << "Realizado: [não]" << endl;
    }
}

void menuEmbarques(vector<EMBARCA> &embarques, vector<PASSAGEIRO> &passageiros, vector<ROTEIRO> &roteiros)
{
    int resposta;

    cout << "   -   Gestão de Embarques    -   " << endl;

    do
    {
        cout << "Selecione a opção desejada:" << endl;
        cout << "1 - Incluir embarque" << endl;
        cout << "2 - Excluir embarque" << endl;
        cout << "3 - Alterar dado do embarque" << endl;
        cout << "4 - Listar todos os embarques" << endl;
        cout << "0 - Sair" << endl;
        cout << "Opção: ";
        cin >> resposta;

        cout << endl;

        switch (resposta)
        {
        case 1:
            incluirEmbarque(roteiros, passageiros, embarques);
            break;
        case 2:
            excluirEmbarque(embarques, roteiros);
            break;
        case 3:
            alterarEmbarque(embarques, roteiros);
            break;
        case 4:
            listarEmbarques(embarques);
            break;
        case 0:
            cout << "Gestão de embarques encerrado." << endl;
            break;
        default:
            cout << "Insira o número correspondente a alguma das opções." << endl;
            break;
        }

        cout << endl;

    } while (resposta != 0);
}

int main()
{
    vector<ROTEIRO> roteiros;
    vector<PASSAGEIRO> passageiros;
    vector<EMBARCA> embarques;

    int resposta;

    cout << "   -   Vans TrasPaGente    -   " << endl;

    do
    {
        cout << "Selecione a opção desejada:" << endl;
        cout << "1 - Gestão de Passageiros" << endl;
        cout << "2 - Gestão de Roteiros" << endl;
        cout << "3 - Gestão de Embarques" << endl;
        cout << "0 - Sair do Programa" << endl;
        cout << "Opção: ";
        cin >> resposta;

        cout << endl;

        switch (resposta)
        {
        case 1:
            menuPassageiros(passageiros);
            break;
        case 2:
            menuRoteiros(roteiros);
            break;
        case 3:
            menuEmbarques(embarques, passageiros, roteiros);
            break;
        case 0:
            cout << "Programa encerrado." << endl;
            break;
        default:
            cout << "Insira o número correspondente a alguma das opções." << endl;
            break;
        }

        cout << endl;

    } while (resposta != 0);

    return 0;
}