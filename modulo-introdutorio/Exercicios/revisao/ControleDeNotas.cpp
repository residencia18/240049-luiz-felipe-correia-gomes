#include <iostream>
#include <vector>
#include <string>

using namespace std;

int main()
{
    vector<string> nomes;
    vector<float> notas1;
    vector<float> notas2;
    vector<float> medias;
    int N, i;

    // ETAPA 1 - INSERINDO ALUNOS E NOTAS
    cout << "Digite o limite de alunos (N): ";
    cin >> N;

    i = 0;
    while (i < N)
    {
        string nome;
        float n1, n2;
        cout << "Digite o nome do aluno: ";
        cin >> nome;
        cout << "Digite a primeira nota do aluno: ";
        cin >> n1;
        cout << "Digite a segunda nota do aluno: ";
        cin >> n2;
        nomes.push_back(nome);
        notas1.push_back(n1);
        notas2.push_back(n2);
        i++;

        cout << "---" << endl;

        if (nomes.size() > N)
        {
            cout << "Limite de alunos atingido." << endl;
            break;
        }
    }

    cout << "====================" << endl;
    vector<string>::iterator it_nomes;
    vector<float>::iterator it_notas1;
    vector<float>::iterator it_notas2;

    // ETAPA 2 - ORDENAÇÃO DOS ALUNOS PELO CRITÉRIO LEXICOGRÁFICO
    // Bubble Sort
    bool trocou;
    do
    {
        trocou = false;
        it_notas1 = notas1.begin();
        it_notas2 = notas2.begin();
        for (it_nomes = nomes.begin(); it_nomes != nomes.end() - 1; it_nomes++, it_notas1++, it_notas2++)
        {
            if (*it_nomes > *(it_nomes + 1))
            {
                swap(*it_nomes, *(it_nomes + 1));
                swap(*it_notas1, *(it_notas1 + 1));
                swap(*it_notas2, *(it_notas2 + 1));
                trocou = true;
            }
        }
    } while (trocou);

    for (int i = 0; i < nomes.size(); i++)
    {
        cout << nomes[i] << ": " << notas1[i] << "  " << notas2[i] << endl;
    }

    cout << "====================" << endl;

    // ETAPA 3 - INCLUSÃO DE NOVOS ALUNOS OU EXCLUSÃO DE ALUNOS DO SISTEMA.
    char resposta1, resposta2;
    do
    {
        string nome;
        float n1, n2;
        cout << "Deseja incluir mais alunos? (s/n) ";
        cin >> resposta1;

        if (resposta1 == 's' && nomes.size() < N) // se a resposta for sim e tiver vaga
        {
            cout << "Nome do novo aluno: ";
            cin >> nome;

            cout << "Digite a primeira nota do aluno: ";
            cin >> n1;
            cout << "Digite a segunda nota do aluno: ";
            cin >> n2;

            cout << "---" << endl;

            size_t pos = 0;
            while (pos < nomes.size() && nome > nomes[pos])
            {
                pos++;
            }

            nomes.insert(nomes.begin() + pos, nome);
            notas1.insert(notas1.begin() + pos, n1);
            notas2.insert(notas2.begin() + pos, n2);
        } else if (resposta1 == 's' && nomes.size() >= N) { // se a resposta for sim mas não tiver vaga
            cout << "Limite de alunos atingido." << endl;
            resposta1 = 'n';
        }
 
        cout << "Deseja excluir algum aluno? (s/n)";
        cin >> resposta2;

        if (resposta2 == 's')
        {
            cout << "Nome do aluno que vai ser removido: ";
            cin >> nome;

            bool encontrado = false; // Variável de sinalização

            for (int i = 0; i < nomes.size(); i++)
            {
                if (nomes[i] == nome)
                {
                    nomes.erase(nomes.begin() + i);
                    notas1.erase(notas1.begin() + i);
                    notas2.erase(notas2.begin() + i);
                    cout << nome << " removido(a) com sucesso." << endl;
                    cout << "---" << endl;
                    encontrado = true; // o aluno foi encontrado e removido
                    break;
                }
            }

            if (!encontrado)
            {
                cout << "Aluno inexistente." << endl;
                cout << "---" << endl;
            }
        }
    } while (resposta1 == 's' || resposta2 == 's');

    cout << "====================" << endl;

    float media;

    // exibir boletins com medias do alunos e se estão aprovados ou reprovados
    for (int i = 0; i < nomes.size(); i++)
    {
        cout << "Boletim de " << nomes[i] << endl;
        cout << "Nota 1: " << notas1[i] << endl;
        cout << "Nota 2: " << notas2[i] << endl;
        media = (notas1[i] + notas2[i]) / 2;
        // medias
        medias.push_back(media);

        cout << "Media: " << medias[i] << endl;

        if (medias[i] >= 7)
        {
            cout << "Aluno aprovado!" << endl;
        }
        else
        {
            cout << "Aluno reprovado!" << endl;
        }

        cout << "---" << endl;
    }

    // ETAPA 3 - ALTERAR NOTAS
    do
    {
        cout << "Deseja alterar alguma nota? (s/n)";
        cin >> resposta1;

        string nome;
        if (resposta1 == 's')
        {
            cout << "Nome do aluno para alterar nota: ";
            cin >> nome;

            bool encontrado = false; // Variável de sinalização
            float novaNota;          // nova nota do aluno
            int opcaoEscolhida;      // opção escolhida no menu

            for (int i = 0; i < nomes.size(); i++)
            {
                if (nomes[i] == nome)
                {
                    cout << "Notas do(a) " << nomes[i] << ":" << endl;
                    cout << "Nota 1: " << notas1[i] << endl;
                    cout << "Nota 2: " << notas2[i] << endl;

                    cout << "---" << endl;

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

                    encontrado = true; // o aluno foi encontrado
                    break;
                }
            }

            if (!encontrado)
            {
                cout << "Aluno inexistente." << endl;
                cout << "---" << endl;
            }
        }
    } while (resposta1 == 's');

    cout << "====================" << endl;

    // ETAPA 4 - EXIBINDO O BOLETIM FINAL
    cout << "BOLETIM FINAL:" << endl;
    for (int i = 0; i < nomes.size(); i++)
    {
        cout << "Boletim de " << nomes[i] << endl;
        cout << "Nota 1: " << notas1[i] << endl;
        cout << "Nota 2: " << notas2[i] << endl;

        media = (notas1[i] + notas2[i]) / 2;
        medias[i] = media;

        // medias
        cout << "Media: " << medias[i] << endl;

        if (medias[i] >= 7)
        {
            cout << "Aluno aprovado!" << endl;
        }
        else
        {
            cout << "Aluno reprovado!" << endl;
        }

        cout << "---" << endl;
    }

    return 0;
}