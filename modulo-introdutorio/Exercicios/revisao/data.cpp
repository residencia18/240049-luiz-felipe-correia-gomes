#include <iostream>
#include <string>

using namespace std;

int main(void)
{

    string data = "**/**/****";
    string dia = "", mes = "", ano = "";

    int aux = 0;

    cout << "Data: ";
    getline(cin, data);

    for (aux; aux < data.length(); aux++)
    {
        if (data.at(aux) != '/')
        {
            dia += data.at(aux);
        }
        else
        {
            break;
        }
    }

    for (aux++; aux < data.length(); aux++)
    {
        if (data.at(aux) != '/')
        {
            mes += data.at(aux);
        }
        else
        {
            break;
        }
    }

    for (aux++; aux < data.length(); aux++)
    {
        if (data.at(aux) != '\0')
        {
            ano += data.at(aux);
        }
        else
        {
            break;
        }
    }

    int data_dia = stoi(dia);
    int data_mes = stoi(mes);
    int data_ano = stoi(ano);

    bool dias31 = data_dia > 0 || data_dia < 32;
    bool dias30 = data_dia > 0 || data_dia < 31;
    bool ano_bissexto = data_ano % 400 == 0 || data_ano % 4 == 0 && data_ano % 100 != 0;
    
    switch (data_mes)
    {
    case 1: // janeiro
        if (!dias31)
        {
            cout << "Dia inválido" << endl;
            return 0;
        }
            mes = "janeiro";
        break;
    case 2: // fevereiro
        if (ano_bissexto)
        {
            // é bissexto
            if (data_dia <= 0 || data_dia > 29)
            {
                cout << "Dia inválido" << endl;
                return 0;
            }
        }
        else
        {
            // não é bissexto
            if (data_dia <= 0 || data_dia > 28)
            {
                cout << "Dia inválido" << endl;
                return 0;
            }
        }
        mes = "fevereiro";
        break;
    case 3: // março
        if (!dias30)
        {
            cout << "Dia inválido" << endl;
            return 0;
        }
        mes = "março";
        break;

    case 4: // abril
        if (!dias30)
        {
            cout << "Dia Inválido" << endl;
        }
        mes = "abril";
        break;
    case 5: // maio
        if (!dias31)
        {
            cout << "Dia inválido" << endl;
            return 0;
        }
        mes = "maio";
        break;

    case 6: // junho
        if (!dias30)
        {
            cout << "Dia Inválido" << endl;
        }
        mes = "junho";
        break;
    case 7: // julho
        if (!dias31)
        {
            cout << "Dia inválido" << endl;
            return 0;
        }
        mes = "julho";
        break;

    case 8: // agosto
        if (!dias31)
        {
            cout << "Dia Inválido" << endl;
        }
        mes = "agosto";
        break;
    case 9: // setembro
        if (!dias30)
        {
            cout << "Dia inválido" << endl;
            return 0;
        }
        mes = "setembro";
        break;
    case 10: // outubro
        if (!dias31)
        {
            cout << "Dia Inválido" << endl;
        }
        mes = "outubro";
        break;
    case 11: // novembro
        if (!dias30)
        {
            cout << "Dia inválido" << endl;
            return 0;
        }
        mes = "novembro";
        break;
    case 12: // dezembro
        if (!dias31)
        {
            cout << "Dia Inválido" << endl;
        }
        mes = "dezembro";
        break;
    default:
        cout << "Mês Inválido" << endl;
        return 0;
    }

    cout << "Dia " << dia << " de " << mes << " de " << ano << endl;

    return 0;
}