#include <iostream>
#include <string>
#include <vector>

using namespace std;

struct passageiro{
    string cpf;
    string nome;
    string dataNascimento;
    string numeroAutorizacao;
};

struct roteiro{
    string codigo;
    string data;
    string hora;
    string duracao;
    string origem;
    string destino;
};

struct embarque{
    char realizada;
    string data;
    string hora;
    string duracao;
    passageiro passageiroEmbarque;
    roteiro roteiroViagem;
};


int localizar_cpf(vector<passageiro> listaPassageiros){
    /*Pede do usuario o cpf e compara para ver se existe um passageiro na lista com o cpf listado,
    se estiver retorna o indice em que o passageiro está no vector, se não retorna -1*/
    string cpf;
    cout << "Digite o CPF do passageiro:" << endl;
    cin >> cpf;
    for(unsigned long int i=0;i<listaPassageiros.size();i++){
        if (listaPassageiros[i].cpf == cpf){
            cout << "Passageiro com CPF " << cpf << " encontrado" << endl;
            return i;
        }
    }
    cout << "Passageiro com CPF " << cpf << " NAO encontrado" << endl;
    return -1;
}

int localizar_passageiro(vector<passageiro> listaPassageiros){
    int i = localizar_cpf(listaPassageiros);
    if (i!=-1){
        cout << "DADOS DO PASSAGEIRO:" << endl;
        cout << "Nome: " << listaPassageiros[i].nome << endl;
        cout << "CPF: " << listaPassageiros[i].cpf << endl;
        cout << "Data de Nascimento: " << listaPassageiros[i].dataNascimento << endl;
        cout << "Numero de Autorizacao: " << listaPassageiros[i].numeroAutorizacao << endl;
        
    }
    return i;
}

void alterar_passageiro(vector<passageiro> &listaPassageiros){
    int i = localizar_passageiro(listaPassageiros);
    char resposta;
    if (i!=-1){
        cout << "Deseja alterar o nome? (s/n)";
        cin >> resposta;
        if(resposta=='s')
            getline(cin,listaPassageiros[i].nome);

        cout << "Deseja alterar o CPF? (s/n)";
        cin >> resposta;
        if(resposta=='s')
            cin >> listaPassageiros[i].cpf;

        cout << "Deseja alterar a data de nascimento? (s/n)";
        cin >> resposta;
        if(resposta=='s')
            cin >> listaPassageiros[i].dataNascimento;

        cout << "Deseja alterar o numero de autentificação? (s/n)";
        cin >> resposta;
        if(resposta=='s')
            getline(cin,listaPassageiros[i].numeroAutorizacao);
        
        cout << "Passageiro ALTERADO com sucesso" << endl;
    } 
}

void excluir_passageiro(vector<passageiro> &listaPassageiros){
    int i = localizar_cpf(listaPassageiros);
    if (i!=-1){
        listaPassageiros.erase(listaPassageiros.begin()+i);
        cout << "Passageiro EXCLUIDO com sucesso" << endl;
    }
}

void incluir_passageiro(vector<passageiro> &listaPassageiros){
    passageiro novoPassageiro;
    cout << "Digite o nome" << endl;
    getline(cin,novoPassageiro.nome);
    cout << "Digite o CPF" << endl;
    cin >> novoPassageiro.cpf;
    cout << "Digite a data de nascimento no formato (DD/MM/AAAA)" << endl;
    cin >> novoPassageiro.dataNascimento;
    char resposta = 'n';
    novoPassageiro.numeroAutorizacao = "";
    cout << "Deseja adicionar um numero de autorizacao? (s/n)" << endl;
    cin >> resposta;
    if (resposta == 's'){
        cout << "Digite o numero de autorizacao" << endl;
        getline(cin,novoPassageiro.numeroAutorizacao);
    }
    listaPassageiros.push_back(novoPassageiro);
    cout << "Passageiro INCLUIDO com sucesso" << endl;
}

void listar_passageiro(vector<passageiro> listaPassageiros){
    if (listaPassageiros.size() == 0){ //verifica se o vector de passageiros está vazio, se sim imprime uma mensagem de erro no console e sai da função
        cout << "Não existem passageiros cadastrados" << endl;
        return;
    }
    for(int i=0;i<int(listaPassageiros.size());i++){
        cout << "DADOS DO PASSAGEIRO: " << endl;
        cout << "Nome: " << listaPassageiros[i].nome << endl;
        cout << "CPF: " << listaPassageiros[i].cpf << endl;
        cout << "Data de Nascimento: " << listaPassageiros[i].dataNascimento << endl;
        cout << "Numero de Autorizacao: " << listaPassageiros[i].numeroAutorizacao << endl;
        cout << "----------------------------" << endl;
    }
}


void gestao_passageiro(vector<passageiro> &listaPassageiros){
    cout << "================================" << endl << "MENU GESTAO PASSAGEIROS:" << endl << "================================" << endl;
    cout << "1 - incluir" << endl;
    cout << "2 - excluir" << endl;
    cout << "3 - alterar" << endl;
    cout << "4 - listar" << endl;
    cout << "5 - localizar" << endl;
    cout << "0 - sair" << endl;
    int menu;
    cin >> menu;
    switch(menu){
        case 1:
            cout << "================================" << endl << "INCLUIR PASSAGEIRO" << endl << "================================" << endl;
            incluir_passageiro(listaPassageiros);
            break;
        case 2:
            cout << "================================" << endl << "EXCLUIR PASSAGEIRO" << endl << "================================" << endl;
            excluir_passageiro(listaPassageiros);
            break;
        case 3:
            cout << "================================" << endl << "ALTERAR PASSAGEIRO" << endl << "================================" << endl;
            alterar_passageiro(listaPassageiros);
            break;
        case 4:
            cout << "================================" << endl << "LISTAR PASSAGEIROS" << endl << "================================" << endl;
            listar_passageiro(listaPassageiros);
            break;
        case 5:
            cout << "================================" << endl << "LOCALIZAR PASSAGEIRO" << endl << "================================" << endl;
            localizar_passageiro(listaPassageiros);
            break;
        case 0:
            break;
        default:
            cout << "Valor invalido" << endl << endl;
        }//switch
        if (menu!=0)
            gestao_passageiro(listaPassageiros);
}


int localizar_codigo(vector<roteiro> listaRoteiros){
    /*pede do usuario o codigo do roteiro e retorna o indice do vector de rotinas que tem o mesmo codigo, 
    caso não seja encontrado um codigo de rotina dentro do vector a função retorna -1*/
    string codigo;
    cout << "Digite o codigo da rotina:" << endl;
    cin >> codigo;
    for(unsigned int i=0;i<listaRoteiros.size();i++){
        if (listaRoteiros[i].codigo == codigo){
            cout << "Rotina com codigo " << codigo << " encontrado" << endl;
            return i;
        }
    }
    cout << "Rotina com codigo " << codigo << " NAO encontrado" << endl;
    return -1;
}

int localizar_roteiro(vector<roteiro> listaRoteiros){
    int i = localizar_codigo(listaRoteiros);
    if (i>=0){
        cout << "DADOS DO ROTEIRO" << endl;
        cout << "Codigo: " << listaRoteiros[i].codigo <<  endl;
        cout << "Data da viagem: " << listaRoteiros[i].data << endl;
        cout << "Horario da viagem: " << listaRoteiros[i].hora << endl;
        cout << "Local de origem: " << listaRoteiros[i].origem << endl;
        cout << "Local de Destino: " << listaRoteiros[i].destino << endl;
    }
    return i;
}

void alterar_roteiro(vector<roteiro> &listaRoteiros){
    int i = localizar_roteiro(listaRoteiros);
    char resposta;
    if (i>=0){
        cout << "Deseja alterar o codigo? (s/n)";
        cin >> resposta;
        if(resposta=='s')
            cin >> listaRoteiros[i].codigo;

        cout << "Deseja alterar a data prevista? (s/n)";
        cin >> resposta;
        if(resposta=='s')
            cin >> listaRoteiros[i].data;

        cout << "Deseja alterar o horario previsto? (s/n)";
        cin >> resposta;
        if(resposta=='s')
            cin >> listaRoteiros[i].hora;

        cout << "Deseja alterar a duracao da viagem? (s/n)";
        cin >> resposta;
        if(resposta=='s')
            cin >> listaRoteiros[i].duracao;
        cout << "Deseja alterar o local de origem? (s/n)";
        cin >> resposta;
        if(resposta=='s')
            getline(cin,listaRoteiros[i].origem);
        cout << "Deseja alterar o local de destino? (s/n)";
        cin >> resposta;
        if(resposta=='s')
            getline(cin,listaRoteiros[i].destino);
        cout << "Roteito ALTERADO com sucesso" << endl;
    }
}

void excluir_roteiro(vector<roteiro> &listaRoteiros){
    int i = localizar_codigo(listaRoteiros);
    if (i!=-1){
        listaRoteiros.erase(listaRoteiros.begin()+i);
        cout << "Roteiro EXCLUIDO com sucesso" << endl;
    }
}

void incluir_roteiro(vector<roteiro> &listaRoteiros){
    roteiro novoRoteiro;
    cout << "Digite o codigo" << endl;
    cin >> novoRoteiro.codigo;
    cout << "Digite a data no formato (DD/MM/AAAA)" << endl;
    cin >> novoRoteiro.data;
    cout << "Digite o horario previsto no formato (HH:mm)" << endl;
    cin >> novoRoteiro.hora;
    cout << "Digite a duracao da viagem no formato (HH:mm)" << endl;
    cin >> novoRoteiro.duracao;
    cout << "Digite o local de origem" << endl;
    getline(cin,novoRoteiro.origem);
    cout << "Digite o local de destino" << endl;
    getline(cin,novoRoteiro.destino);
    listaRoteiros.push_back(novoRoteiro);
    cout << "Roteiro INCLUIDO com sucesso" << endl;
}

void listar_roteiro(vector<roteiro> listaRoteiros){
    if (listaRoteiros.size()==0){
        cout << "Nao ha roteiros cadastrados" << endl;
        return;
    }
    for(unsigned long int i=0;i<listaRoteiros.size();i++){
        cout << "DADOS DO ROTEIRO - " << i << endl;
        cout << "Codigo: " << listaRoteiros[i].codigo <<  endl;
        cout << "Data da viagem: " << listaRoteiros[i].data << endl;
        cout << "Horario da viagem: " << listaRoteiros[i].hora << endl;
        cout << "Local de origem: " << listaRoteiros[i].origem << endl;
        cout << "Local de Destino: " << listaRoteiros[i].destino << endl;
        cout << "------------------------" << endl;
    }
}


void gestao_roteiro(vector<roteiro> &listaRoteiros){
    cout << "================================" << endl << "MENU GESTAO ROTEIROS:" << endl;
    cout << "1 - incluir" << endl;
    cout << "2 - excluir" << endl;
    cout << "3 - alterar" << endl;
    cout << "4 - listar" << endl;
    cout << "5 - localizar" << endl;
    cout << "0 - sair" << endl;
    int menu;
    cin >> menu;
    switch(menu){
        case 1:
            cout << "================================" << endl << "INCLUIR ROTEIRO" << endl;
            incluir_roteiro(listaRoteiros);
            break;
        case 2:
            cout << "================================" << endl << "EXCLUIR ROTEIRO" << endl;
            excluir_roteiro(listaRoteiros);
            break;
        case 3:
            cout << "================================" << endl << "ALTERAR ROTEIRO" << endl;
            alterar_roteiro(listaRoteiros);
            break;
        case 4:
            cout << "================================" << endl << "LISTAR ROTEIROS" << endl;
            listar_roteiro(listaRoteiros);
            break;
        case 5:
            cout << "================================" << endl << "LOCALIZAR ROTEIRO" << endl;
            localizar_roteiro(listaRoteiros);
            break;
        case 0:
            break;
        default:
            cout << "Valor invalido" << endl;
        }//switch
        if (menu!=0)
            gestao_roteiro(listaRoteiros);
}




int localizar_passageiro_roteiro(vector<embarque> listaEmbarques, vector<roteiro> listaRoteiros){
    //lista os passageiros em um roteiro, localiza um passageiro expecifico e retorna o indice no vector listaEmbarque se encontra
    vector<int> iPassageiros;
    vector<passageiro> passageirosEmbarcados;
    int i = localizar_codigo(listaRoteiros); //pede o codigo do roteiro e verifica se ele existe, retorna o indice que representa o roteiro no vector listaRoteiros
    if (i<0)
        return i;
    for(unsigned long int j=0;j<listaEmbarques.size();j++){ //verifica dentro do vector de embarques quais embarques são iguais ao roteiro referente ao codigo dado
        if(listaEmbarques[j].roteiroViagem.codigo == listaRoteiros[i].codigo){
            iPassageiros.push_back(j); //insere no vector os indices em que tem passageiros no roteiro expecificado
            passageirosEmbarcados.push_back(listaEmbarques[j].passageiroEmbarque); // criar um vector com os passageiros que estão registrados no roteiro para serem listados
        }
    }
    if (iPassageiros.size()==0){
        cout << "Nao foram encontrados passageiros com embarque cadastrado nesse roteiro" << endl;
        return -1;
    }
    listar_passageiro(passageirosEmbarcados);
    int j = localizar_cpf(passageirosEmbarcados);
    return iPassageiros[j];

}

void alterar_embarque(vector<embarque> listaEmbarques,vector<roteiro> listaRoteiros){
    int i = localizar_passageiro_roteiro(listaEmbarques, listaRoteiros);
    if(i==-1)
        return;
    cout << "Digite a duração real do embarque no formato (HH:mm)" << endl;
    cin >> listaEmbarques[i].duracao;
    cout << "Embarque ALTERADO com sucesso" << endl;
    
}

void excluir_embarque(vector<embarque> &listaEmbarques, vector<roteiro> listaRoteiros){
    int i = localizar_passageiro_roteiro(listaEmbarques, listaRoteiros);
    if(i==-1)
        return;
    listaEmbarques.erase(listaEmbarques.begin()+i);
}

void incluir_embarque(vector<embarque> &listaEmbarques, vector<passageiro> listaPassageiros, vector<roteiro> listaRoteiros){
    embarque novoEmbarque;
    int i = localizar_cpf(listaPassageiros);
    if (i==-1)
        return; //erro cpf invalido
    novoEmbarque.passageiroEmbarque = listaPassageiros[i];
    int j = localizar_codigo(listaRoteiros);
    if(j==-1)
        return; //erro codigo invalido
    novoEmbarque.roteiroViagem = listaRoteiros[j];
    cout << "Digite a data real do embarque no formato (DD/MM/AAAA)" << endl;
    cin >> novoEmbarque.data;
    cout << "Digite o horario real do embarque no formato (HH:mm)" << endl;
    cin >> novoEmbarque.hora;
    cout << "O embarque ja ocorreu? (s/n)" << endl;
    cin >> novoEmbarque.realizada;
    novoEmbarque.duracao = "";
    listaEmbarques.push_back(novoEmbarque);
    cout << "Embarque INCLUIDO com sucesso" << endl;
}

void listar_embarque(vector<embarque> listaEmbarques){
    if(listaEmbarques.size() == 0){
        cout << "Nao ha embarques cadastrados" << endl;
        return;
    }
    for(unsigned long int i = 0;i<listaEmbarques.size();i++){
        cout << "Nome do Passageiro: " << listaEmbarques[i].passageiroEmbarque.nome << endl;
        cout << "CPF do Passageiro: " << listaEmbarques[i].passageiroEmbarque.cpf << endl;
        cout << "Codigo do Roteiro: " << listaEmbarques[i].roteiroViagem.codigo << endl;
        cout << "Embarque realizado? [" << listaEmbarques[i].realizada << "]" << endl;
        cout << "------------------------------------------------" << endl;
    }
}

void gestao_embarque(vector<embarque> &listaEmbarques, vector<passageiro> listaPassageiros, vector<roteiro> listaRoteiros){
    cout << "================================" << endl << "MENU GESTAO DE EMBARQUES:" << endl << "================================" << endl;
    cout << "1 - incluir" << endl;
    cout << "2 - excluir" << endl;
    cout << "3 - alterar" << endl;
    cout << "4 - listar" << endl;
    cout << "0 - sair" << endl;
    int menu;
    cin >> menu;
    switch(menu){
        case 1:
            cout << "================================" << endl << "INCLUIR EMBARQUE" << endl << "================================" << endl;
            incluir_embarque(listaEmbarques, listaPassageiros, listaRoteiros);
            break;
        case 2:
            cout << "================================" << endl << "EXCLUIR EMBARQUE" << endl << "================================" << endl;
            excluir_embarque(listaEmbarques, listaRoteiros);
            break;
        case 3:
            cout << "================================" << endl << "ALTERAR EMBARQUE" << endl << "================================" << endl;
            alterar_embarque(listaEmbarques, listaRoteiros);
            break;
        case 4:
            cout << "================================" << endl << "LISTAR EMBARQUES" << endl << "================================" << endl;
            listar_embarque(listaEmbarques);
            break;
        case 0:
            break;
        default:
            cout << "Valor invalido" << endl;
        }//switch
        if (menu!=0)
            gestao_embarque(listaEmbarques, listaPassageiros, listaRoteiros);
}



void menu_principal(vector<embarque> &listaEmbarques, vector<passageiro> listaPassageiros, vector<roteiro> listaRoteiros){
    cout << "================================" << endl << "MENU PRINCIPAL:" << endl << "================================" << endl;
    cout << "1 - Gestao de Passageiros" << endl;
    cout << "2 - Gestao de Roteiros" << endl;
    cout << "3 - Gestao de Embarques" << endl;
    cout << "0 - sair" << endl;
    int menu;
    cin >> menu;
    switch(menu){
        case 1:
            cout << "================================" << endl << "GESTAO DE PASSAGEIROS" << endl << "================================" << endl;
            gestao_passageiro(listaPassageiros);
            break;
        case 2:
            cout << "================================" << endl << "GESTAO DE ROTEIROS" << endl << "================================" << endl;
            gestao_roteiro(listaRoteiros);
            break;
        case 3:
            cout << "================================" << endl << "GESTAO DE EMBARQUE" << endl << "================================" << endl;
            gestao_embarque(listaEmbarques, listaPassageiros, listaRoteiros);
            break;
        case 0:
            break;
        default:
            cout << "Valor invalido" << endl;
        }//switch
        if (menu!=0)
        menu_principal(listaEmbarques, listaPassageiros, listaRoteiros);
}   

int main(void){
    vector<passageiro> listaPassageiros;
    vector<roteiro> listaRoteiros;
    vector<embarque> listaEmbarques;
    menu_principal(listaEmbarques, listaPassageiros, listaRoteiros);
    return 0;
}//main