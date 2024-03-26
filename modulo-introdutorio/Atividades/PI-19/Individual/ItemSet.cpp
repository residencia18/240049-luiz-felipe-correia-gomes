#include <iostream>
#include <vector>
#include <string>
#include <algorithm>

using namespace std;

class ItemSet {
private:
    vector<string> items;

public:
    // Método para inserir um item no conjunto, caso ele não exista
    void inserir(string s) {
        // Verifica se o item já está no conjunto
        if (find(items.begin(), items.end(), s) == items.end()) {
            // Se não estiver, insere o item
            items.push_back(s);
            cout << "Item \"" << s << "\" inserido no conjunto." << endl;
        } else {
            cout << "Item \"" << s << "\" já existe no conjunto." << endl;
        }
    }

    // Método para excluir um item do conjunto, caso ele exista
    void excluir(string s) {
        auto it = find(items.begin(), items.end(), s);
        if (it != items.end()) {
            // Se o item existe, exclui-o
            items.erase(it);
            cout << "Item \"" << s << "\" excluído do conjunto." << endl;
        } else {
            cout << "Item \"" << s << "\" não encontrado no conjunto." << endl;
        }
    }
};

int main() {
    ItemSet conjunto;

    conjunto.inserir("Item1");
    conjunto.inserir("Item2");
    conjunto.inserir("Item3");
    conjunto.inserir("Item2"); // Tentativa de inserir um item repetido

    conjunto.excluir("Item2");
    conjunto.excluir("Item4"); // Tentativa de excluir um item que não existe

    return 0;
}
