#include <iostream>
#include <string>
#include <iomanip>
using namespace std;

// Definindo a estrutura do produto
struct Produto {
    string codigo;
    string nome;
    double preco;
};

// Função para inserir um novo produto
void inserirProduto(Produto produtos[], int& numProdutos) {
    if (numProdutos >= 300) {
        cout << "Limite de produtos atingido. Impossível adicionar mais produtos." << endl;
        return;
    }

    Produto novoProduto;
    cout << "Digite o código do produto (13 caracteres numéricos): ";
    cin >> novoProduto.codigo;

    // Verificando se o código já existe
    for (int i = 0; i < numProdutos; i++) {
        if (produtos[i].codigo == novoProduto.codigo) {
            cout << "Já existe um produto com este código." << endl;
            return;
        }
    }

    cout << "Digite o nome do produto (até 20 caracteres): ";
    cin.ignore();
    getline(cin, novoProduto.nome);

    // Verificando se o nome já existe
    for (int i = 0; i < numProdutos; i++) {
        if (produtos[i].nome == novoProduto.nome) {
            cout << "Já existe um produto com este nome." << endl;
            return;
        }
    }

    cout << "Digite o preço do produto: ";
    cin >> novoProduto.preco;

    produtos[numProdutos++] = novoProduto;
    cout << "Produto cadastrado com sucesso." << endl;
}

// Função para excluir um produto cadastrado
void excluirProduto(Produto produtos[], int& numProdutos) {
    string codigoExcluir;
    cout << "Digite o código do produto a ser excluído: ";
    cin >> codigoExcluir;

    int indiceExcluir = -1;
    for (int i = 0; i < numProdutos; i++) {
        if (produtos[i].codigo == codigoExcluir) {
            indiceExcluir = i;
            break;
        }
    }

    if (indiceExcluir != -1) {
        for (int i = indiceExcluir; i < numProdutos - 1; i++) {
            produtos[i] = produtos[i + 1];
        }
        numProdutos--;
        cout << "Produto excluído com sucesso." << endl;
    } else {
        cout << "Produto não encontrado." << endl;
    }
}

// Função para listar todos os produtos
void listarProdutos(const Produto produtos[], int numProdutos) {
    if (numProdutos == 0) {
        cout << "Nenhum produto cadastrado." << endl;
        return;
    }

    cout << "Lista de Produtos:" << endl;
    cout << setw(15) << "Código" << setw(25) << "Nome" << setw(10) << "Preço" << endl;
    for (int i = 0; i < numProdutos; i++) {
        cout << setw(15) << produtos[i].codigo << setw(25) << produtos[i].nome << setw(10) << fixed << setprecision(2) << produtos[i].preco << endl;
    }
}

// Função para consultar o preço de um produto por código
void consultarPreco(const Produto produtos[], int numProdutos) {
    string codigoConsultar;
    cout << "Digite o código do produto a ser consultado: ";
    cin >> codigoConsultar;

    for (int i = 0; i < numProdutos; i++) {
        if (produtos[i].codigo == codigoConsultar) {
            cout << "Preço do produto '" << produtos[i].nome << "': " << fixed << setprecision(2) << produtos[i].preco << endl;
            return;
        }
    }

    cout << "Produto não encontrado." << endl;
}

int main() {
    Produto produtos[300];
    int numProdutos = 0;
    int opcao;

    do {
        cout << "\n===== Supermercado =====" << endl;
        cout << "1. Inserir um novo produto" << endl;
        cout << "2. Excluir um produto cadastrado" << endl;
        cout << "3. Listar todos os produtos" << endl;
        cout << "4. Consultar o preço de um produto por código" << endl;
        cout << "5. Sair" << endl;
        cout << "Escolha uma opção: ";
        cin >> opcao;

        switch (opcao) {
            case 1:
                inserirProduto(produtos, numProdutos);
                break;
            case 2:
                excluirProduto(produtos, numProdutos);
                break;
            case 3:
                listarProdutos(produtos, numProdutos);
                break;
            case 4:
                consultarPreco(produtos, numProdutos);
                break;
            case 5:
                cout << "Encerrando o programa." << endl;
                break;
            default:
                cout << "Opção inválida. Tente novamente." << endl;
                break;
        }
    } while (opcao != 5);

    return 0;
}
