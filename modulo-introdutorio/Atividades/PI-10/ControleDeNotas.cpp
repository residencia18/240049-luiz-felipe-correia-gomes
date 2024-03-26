#include <iostream>
#include <string>
#include <vector>
#include <iomanip>

using namespace std;

// Função para adicionar um novo aluno
void adicionarAluno(vector<string> &nomes, vector<float> &notas1, vector<float> &notas2)
{
    string nome;
    float n1, n2;

    cout << "Digite o nome do aluno: ";
    cin >> nome;
    cout << "Digite a primeira nota do aluno: ";
    cin >> n1;
    cout << "Digite a segunda nota do aluno: ";
    cin >> n2;

    size_t pos = 0;
    while (pos < nomes.size() && nome > nomes[pos])
    {
        pos++;
    }

    nomes.insert(nomes.begin() + pos, nome);
    notas1.insert(notas1.begin() + pos, n1);
    notas2.insert(notas2.begin() + pos, n2);

    cout << endl;
}

// Função para ordenar os alunos por ordem alfabética
void ordenarAlunos(vector<string> &nomes, vector<float> &notas1, vector<float> &notas2)
{
    bool trocou;
    do
    {
        trocou = false;
        for (size_t i = 0; i < nomes.size() - 1; i++)
        {
            if (nomes[i] > nomes[i + 1])
            {
                swap(nomes[i], nomes[i + 1]);
                swap(notas1[i], notas1[i + 1]);
                swap(notas2[i], notas2[i + 1]);
                trocou = true;
            }
        }
    } while (trocou);
}

// Função para excluir um aluno por nome
void excluirAluno(vector<string> &nomes, vector<float> &notas1, vector<float> &notas2)
{
    string nome;

    cout << "Nome do aluno que vai ser removido: ";
    cin >> nome;

    bool encontrado = false;

    for (size_t i = 0; i < nomes.size(); i++)
    {
        if (nomes[i] == nome)
        {
            nomes.erase(nomes.begin() + i);
            notas1.erase(notas1.begin() + i);
            notas2.erase(notas2.begin() + i);
            cout << nome << " removido(a) com sucesso." << endl;
            cout << "---" << endl;
            encontrado = true;
            break;
        }
    }

    if (!encontrado)
    {
        cout << "Aluno inexistente." << endl;
        cout << "---" << endl;
    }

    cout << endl;
}

// Função para exibir boletins dos alunos
void exibirBoletins(const vector<string> &nomes, const vector<float> &notas1, const vector<float> &notas2)
{
    cout << "===============================" << endl;
    cout << "      Boletim dos Alunos      " << endl;
    cout << "===============================" << endl;

    cout << "Nome\t\tNota 1\tNota 2\tMédia\tSituação" << endl;

    for (size_t i = 0; i < nomes.size(); i++)
    {
        float media = (notas1[i] + notas2[i]) / 2;
        string situacao = (media >= 7) ? "Aprovado" : "Reprovado";

        cout << nomes[i] << "\t\t" << fixed << setprecision(1) << notas1[i] << "\t" << notas2[i] << "\t" << media << "\t" << situacao << endl;
    }

    cout << "===============================" << endl;
    cout << endl;
}

// Função para alterar as notas de um aluno
void alterarNotas(vector<string> &nomes, vector<float> &notas1, vector<float> &notas2)
{
    string nome;

    cout << "Nome do aluno para alterar nota: ";
    cin >> nome;

    bool encontrado = false;

    for (size_t i = 0; i < nomes.size(); i++)
    {
        if (nomes[i] == nome)
        {
            cout << "Notas do(a) " << nomes[i] << ":" << endl;
            cout << "Nota 1: " << notas1[i] << endl;
            cout << "Nota 2: " << notas2[i] << endl;
            cout << "---" << endl;

            int opcaoEscolhida;
            float novaNota;

            do
            {
                cout << "1 - Alterar a primeira nota" << endl;
                cout << "2 - Alterar a segunda nota" << endl;
                cout << "0 - Nenhuma" << endl;
                cout << "Digite o numero correspondente: ";
                cin >> opcaoEscolhida;

                switch (opcaoEscolhida)
                {
                case 1:
                    cout << "Nova nota 1 do aluno: ";
                    cin >> novaNota;
                    notas1[i] = novaNota;
                    cout << "---" << endl;
                    break;
                case 2:
                    cout << "Nova nota 2 do aluno: ";
                    cin >> novaNota;
                    notas2[i] = novaNota;
                    cout << "---" << endl;
                    break;
                default:
                    break;
                }
            } while (opcaoEscolhida != 0);

            encontrado = true;
            break;
        }
    }

    if (!encontrado)
    {
        cout << "Aluno inexistente." << endl;
        cout << "---" << endl;
    }

    cout << endl;
}

int main()
{
    vector<string> nomes;
    vector<float> notas1;
    vector<float> notas2;

    int N = 0;

    // Solicita um limite de alunos N e os nomes e notas de cada um dos alunos
    cout << "Digite o limite de alunos: ";
    cin >> N;

    char resposta1, resposta2;
    for (int i = 0; i < N; i++)
    {
        adicionarAluno(nomes, notas1, notas2);
    }

    cout << "====================" << endl;

    // Ordena alunos e suas notas em ordem alfabética
    ordenarAlunos(nomes, notas1, notas2);

    // Incluir ou excluir algum aluno
    do
    {
        cout << "Deseja incluir mais alunos? (s/n) ";
        cin >> resposta1;

        if (resposta1 == 's' && N > nomes.size())
        {
            adicionarAluno(nomes, notas1, notas2);
        }
        else if (resposta1 == 's' && N <= nomes.size())
        {
            cout << "Limite de alunos atingido." << endl;
            resposta1 = 'n';
        }

        cout << "Deseja excluir algum aluno? (s/n)";
        cin >> resposta2;

        if (resposta2 == 's')
        {
            excluirAluno(nomes, notas1, notas2);
        }
    } while (resposta1 == 's' || resposta2 == 's');

    // Exibir boletins, com médias e se estão aprovados ou reprovados (media >= 7)
    cout << endl;
    exibirBoletins(nomes, notas1, notas2);

    // Alterar notas de algum aluno
    do
    {
        cout << "Deseja alterar alguma nota? (s/n) ";
        cin >> resposta1;

        if (resposta1 == 's')
        {
            alterarNotas(nomes, notas1, notas2);
        }
    } while (resposta1 == 's');

    exibirBoletins(nomes, notas1, notas2);

    return 0;
}