#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

class ItemSet {
private:
    vector<string> itens;

public:
    ItemSet() {}

    // Construtor que recebe outro conjunto como parâmetro
    ItemSet(const ItemSet& conjunto) {
        itens = conjunto.itens;
    }

    // Método para inserir um item no conjunto, evitando repetições
    void inserir(const string& s) {
        if (find(itens.begin(), itens.end(), s) == itens.end()) {
            itens.push_back(s);
            cout << "Item '" << s << "' inserido com sucesso." << endl;
        } else {
            cout << "Item '" << s << "' já existe no conjunto." << endl;
        }
    }

    // Método para excluir um item do conjunto, se ele existir
    void excluir(const string& s) {
        auto it = find(itens.begin(), itens.end(), s);
        if (it != itens.end()) {
            itens.erase(it);
            cout << "Item '" << s << "' excluído com sucesso." << endl;
        } else {
            cout << "Item '" << s << "' não encontrado no conjunto." << endl;
        }
    }

    // Sobrecarga do operador de atribuição (=)
    ItemSet& operator=(const ItemSet& conjunto) {
        if (this == &conjunto) {
            return *this;
        }
        itens = conjunto.itens;
        return *this;
    }

    // Sobrecarga do operador de união (+)
    ItemSet operator+(const ItemSet& conjunto) const {
        ItemSet result(*this);
        for (const string& item : conjunto.itens) {
            result.inserir(item);
        }
        return result;
    }

    // Sobrecarga do operador de intersecção (*)
    ItemSet operator*(const ItemSet& conjunto) const {
        ItemSet result;
        for (const string& item : itens) {
            if (conjunto.contem(item)) {
                result.inserir(item);
            }
        }
        return result;
    }

    // Sobrecarga do operador de diferença (-)
    ItemSet operator-(const ItemSet& conjunto) const {
        ItemSet result;
        for (const string& item : itens) {
            if (!conjunto.contem(item)) {
                result.inserir(item);
            }
        }
        return result;
    }

    // Sobrecarga do operador de delta (<>)
    ItemSet operatordelta(const ItemSet& conjunto) const {
        ItemSet result = (*this - conjunto) + (conjunto - *this);
        return result;
    }

    // Sobrecarga do operador de comparação (==)
    bool operator==(const ItemSet& conjunto) const {
        return itens == conjunto.itens;
    }

    // Método para verificar se um item está no conjunto
    bool contem(const string& s) const {
        return find(itens.begin(), itens.end(), s) != itens.end();
    }

    // Método para obter os itens do conjunto (para fins de impressão)
    vector<string> getItens() const {
        return itens;
    }
};

int main() {
    ItemSet conjuntoA;
    ItemSet conjuntoB;

    conjuntoA.inserir("Arroz");
    conjuntoA.inserir("Macarrão");
    conjuntoA.inserir("Feijão");

    conjuntoB.inserir("Feijão");
    conjuntoB.inserir("Biscoito Cream Cracker");

    // Teste das sobrecargas dos operadores
    ItemSet conjuntoC = conjuntoA + conjuntoB;
    ItemSet conjuntoD = conjuntoA * conjuntoB;
    ItemSet conjuntoE = conjuntoA - conjuntoB;
    ItemSet conjuntoF = (conjuntoB - conjuntoC) + (conjuntoC - conjuntoB);
    //ItemSet conjuntoF = conjuntoA != conjuntoB;

    cout << "Conjunto C (união): ";
    for (const string& item : conjuntoC.getItens()) {
        cout << item << " ";
    }
    cout << endl;

    cout << "Conjunto D (intersecção): ";
    for (const string& item : conjuntoD.getItens()) {
        cout << item << " ";
    }
    cout << endl;

    cout << "Conjunto E (diferença): ";
    for (const string& item : conjuntoE.getItens()) {
        cout << item << " ";
    }
    cout << endl;

    cout << "Conjunto F (delta): ";
    for (const string& item : conjuntoF.getItens()) {
        cout << item << " ";
    }
    cout << endl;

    // Teste da sobrecarga do operador ==
    if (conjuntoA == conjuntoB) {
        cout << "Conjunto A é igual ao Conjunto B." << endl;
    } else {
        cout << "Conjunto A não é igual ao Conjunto B." << endl;
    }

    return 0;
}
