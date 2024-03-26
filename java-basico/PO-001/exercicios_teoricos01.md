# Exercícios Teóricos

## Parte Teórica:

### Questão 1
> O que é uma classe em Java e qual é a diferença entre uma classe e um objeto? Dê 5 exemplos mostrando-os em C++ e em Java.

Resposta: A classe serve para definir um tipo. Um tipo é uma estrutura de dados. Em outras palavras, a classe é uma estrutura que define o comportamento e as propriedades de objetos. Os objetos, por sua vez, são instâncias de uma classe e representam uma entidade do mundo real.

Cinco exemplos mostrando o uso de classes em C++ e Java:
#### Classe pessoa 
- Em Java
```java
public class Pessoa {
    String nome;
    int idade;

    void apresentar() {
        System.out.println("Meu nome é " + nome + " e eu tenho " + idade + " anos.");
    }
}
```
- Em C++
```cpp
#include <iostream>
#include <string>

class Pessoa {
public:
    std::string nome;
    int idade;

    void apresentar() {
        std::cout << "Meu nome é " << nome << " e eu tenho " << idade << " anos." << std::endl;
    }
};
```
#### Classe retângulo
- Em Java
```java
public class Retangulo {
    double comprimento;
    double largura;

    double calcularArea() {
        return comprimento * largura;
    }
}
```
- Em C++
```cpp
#include <iostream>

class Retangulo {
public:
    double comprimento;
    double largura;

    double calcularArea() {
        return comprimento * largura;
    }
};
```
### Classe livro
- Em Java
```java
public class Livro {
    String titulo;
    String autor;
    int anoPublicacao;

    void exibirDetalhes() {
        System.out.println("Livro: " + titulo + "\nAutor: " + autor + "\nAno de Publicação: " + anoPublicacao);
    }
}
```
- Em C++
```cpp
#include <iostream>
#include <string>

class Livro {
public:
    std::string titulo;
    std::string autor;
    int anoPublicacao;

    void exibirDetalhes() {
        std::cout << "Livro: " << titulo << "\nAutor: " << autor << "\nAno de Publicação: " << anoPublicacao << std::endl;
    }
};
```
### Classe carro
- Em Java
```java
public class Carro {
    String marca;
    String modelo;
    int anoFabricacao;

    void exibirInformacoes() {
        System.out.println("Carro: " + marca + " " + modelo + "\nAno de Fabricação: " + anoFabricacao);
    }
}
```
- Em C++
```cpp
#include <iostream>
#include <string>

class Carro {
public:
    std::string marca;
    std::string modelo;
    int anoFabricacao;

    void exibirInformacoes() {
        std::cout << "Carro: " << marca << " " << modelo << "\nAno de Fabricação: " << anoFabricacao << std::endl;
    }
}
```
### Classe animal de estimação
- Em Java
```java
public class AnimalEstimacao {
    String nome;
    String tipo;
    int idade;

    void exibirDetalhes() {
        System.out.println("Nome: " + nome + "\nTipo: " + tipo + "\nIdade: " + idade + " anos");
    }
}
```
- Em C++
```cpp
#include <iostream>
#include <string>

class AnimalEstimacao {
public:
    std::string nome;
    std::string tipo;
    int idade;

    void exibirDetalhes() {
        std::cout << "Nome: " << nome << "\nTipo: " << tipo << "\nIdade: " << idade << " anos" << std::endl;
    }
};
```

### Questão 2
> Como você declara uma variável em Java e quais são os tipos de dados primitivos mais comuns? Faça um paralelo entre isso e a mesma coisa na linguagem C++.

Resposta: Para declararmos uma variável em Java, seguimos três passos:
- Definir o tipo dela
- Definir o nome dela
- Definir o valor dela (opcional)

Exemplo:
```java
//Vamos supor o cálculo da área de uma cirfurência, precisaríamos de dois dados:
double raio = 3.4;
double pi = 3.14159;	
```
No entanto, bora lembrar que o valor de pi (*π*) é um valor CONSTANTE, que não pode ser trocado durante o código.  Para casos parecidos com esse, a gente pode declarar ele como uma constante escrevendo “final” antes da tipagem e, dessa forma, impedindo qualquer tentativa de alteração desse valor.

Exemplo:
```java
double raio;
final double PI = 3.14159;
//É uma boa prática escrever o nome de uma constante toda maiúscula.
```

Em Java, nós temos 8 tipos primitivos.

Destes, 6 tipos representam valores numéricos, sendo 4 para números inteiros e 2 para números com ponto flutuante. São eles:
- Inteiros:
    - byte → 1 byte (-128 até +127)
    - short → 2 bytes (+- 32.767)
    - int → 4 bytes (+- 2 bilhões)
    - long → 8 bytes
- Ponto flutuante:
    - float → 4 bytes
    - double → 8 bytes

Os demais 2 tipos, são o char e o boolean.
- char
    - Para representar caracteres;
    - Delimitado por aspas simples;
- Boolean
    - false;
    - true;

Em C++, a declaração de variáveis é semelhante, mas existem algumas nuances de cada linguagem que devem ser consideradas: 

1. **Inteiros:**
   - *Java:* `byte`, `short`, `int`, `long`
   - *C++:* `char`, `short`, `int`, `long`, `long long`

2. **Ponto flutuante:**
   - *Java:* `float`, `double`
   - *C++:* `float`, `double`

3. **Caracteres:**
   - *Java:* `char`
   - *C++:* `char`

4. **Booleanos:**
   - *Java:* `boolean`
   - *C++:* `bool`

5. **Tamanho dos tipos:**
   - *Java:* O tamanho dos tipos é independente da máquina, definido pelas especificações da linguagem.
   - *C++:* O tamanho dos tipos pode variar dependendo da máquina (ex: `int` pode ter 4 ou 8 bytes).

6. **Padrões de inicialização:**
   - *Java:* As variáveis locais devem ser inicializadas antes de serem usadas.
   - *C++:* As variáveis locais podem não ser inicializadas, mas é uma boa prática fazê-lo para evitar comportamentos indefinidos.

7. **Limites e Faixas:**
   - *Java:* Fornece constantes na classe `Integer` e `Long` para representar limites, como `Integer.MAX_VALUE` e `Integer.MIN_VALUE`.
   - *C++:* Pode depender da implementação, mas geralmente usa constantes do cabeçalho `limits`, como `INT_MAX` e `INT_MIN`.

8. **Overflows e Underflows:**
   - *Java:* Gera uma exceção em caso de overflow ou underflow (por exemplo, ao ultrapassar o valor máximo ou mínimo para um tipo).
   - *C++:* O comportamento pode ser indefinido em caso de overflow ou underflow.

Essas são algumas das principais semelhanças e diferenças entre os tipos de dados primitivos em Java e C++.

### Questão 3
> Explique o conceito de herança em Java e como você pode criar uma 
subclasse a partir de uma classe existente. Faça um paralelo com C++, apresentando 5 exemplos.

Resposta: Em Java, a herança é um conceito fundamental da programação orientada a objetos que permite a criação de uma nova classe baseada em uma classe existente. A classe existente é chamada de classe pai ou superclasse, enquanto a nova classe é chamada de classe filha ou subclasse. A herança permite que a subclasse herde atributos e comportamentos da superclasse, promovendo a reutilização de código e facilitando a criação de hierarquias de classes.

A seguir, apresento um exemplo simples para ilustrar como criar uma subclasse em Java:

Suponha que temos uma classe chamada `Veiculo`:

```java
public class Veiculo {
    private String marca;
    private String modelo;

    public Veiculo(String marca, String modelo) {
        this.marca = marca;
        this.modelo = modelo;
    }

    public void exibirInformacoes() {
        System.out.println("Marca: " + marca);
        System.out.println("Modelo: " + modelo);
    }
}
```

Agora, podemos criar uma subclasse chamada `Carro` que herda de `Veiculo`:

```java
public class Carro extends Veiculo {
    private int numeroPortas;

    public Carro(String marca, String modelo, int numeroPortas) {
        super(marca, modelo); // Chama o construtor da superclasse (Veiculo)
        this.numeroPortas = numeroPortas;
    }

    // Podemos adicionar métodos específicos da subclasse, se necessário

    public void exibirInformacoesCarro() {
        exibirInformacoes(); // Podemos chamar métodos da superclasse
        System.out.println("Número de portas: " + numeroPortas);
    }
}
```

Neste exemplo:

- A classe `Carro` estende a classe `Veiculo` usando a palavra-chave `extends`.
- O construtor da subclasse (`Carro`) chama o construtor da superclasse (`Veiculo`) usando `super(marca, modelo)`.
- A subclasse (`Carro`) herda os métodos e atributos públicos ou protegidos da superclasse (`Veiculo`).
- A subclasse pode ter seus próprios métodos e atributos específicos.

Com essa hierarquia de classes, você pode criar instâncias tanto de `Veiculo` quanto de `Carro`:

```java
Veiculo meuVeiculo = new Veiculo("Toyota", "Corolla");
meuVeiculo.exibirInformacoes();

Carro meuCarro = new Carro("Honda", "Civic", 4);
meuCarro.exibirInformacoesCarro();
```

Isso resultaria na exibição das informações do veículo e do carro no console.

Em C++, o conceito de herança é semelhante ao de Java, mas a sintaxe e algumas características podem diferir. Aqui está um exemplo de como você poderia implementar herança em C++:

Suponha que temos uma classe base chamada `Veiculo`:

```cpp
#include <iostream>
#include <string>

class Veiculo {
protected:
    std::string marca;
    std::string modelo;

public:
    Veiculo(const std::string& marca, const std::string& modelo)
        : marca(marca), modelo(modelo) {}

    void exibirInformacoes() {
        std::cout << "Marca: " << marca << std::endl;
        std::cout << "Modelo: " << modelo << std::endl;
    }
};
```

Agora, podemos criar uma subclasse chamada `Carro` que herda de `Veiculo`:

```cpp
class Carro : public Veiculo {
private:
    int numeroPortas;

public:
    Carro(const std::string& marca, const std::string& modelo, int numeroPortas)
        : Veiculo(marca, modelo), numeroPortas(numeroPortas) {}

    // Podemos adicionar métodos específicos da subclasse, se necessário

    void exibirInformacoesCarro() {
        exibirInformacoes(); // Podemos chamar métodos da superclasse
        std::cout << "Número de portas: " << numeroPortas << std::endl;
    }
};
```

Aqui estão algumas diferenças notáveis em C++:

- Usamos `#include <iostream>` para usar as funcionalidades de entrada e saída padrão.
- O acesso aos membros da classe base (`Veiculo`) é especificado usando `public Veiculo` na declaração da classe `Carro`. Isso define a visibilidade dos membros herdados na subclasse.

A criação de instâncias e o uso das classes seriam semelhantes em C++:

```cpp
int main() {
    Veiculo meuVeiculo("Toyota", "Corolla");
    meuVeiculo.exibirInformacoes();

    Carro meuCarro("Honda", "Civic", 4);
    meuCarro.exibirInformacoesCarro();

    return 0;
}
```

Este é um exemplo básico que demonstra a herança em C++. O entendimento do modificador de acesso (`public`, `private`, `protected`) é crucial para compreender como a herança funciona em C++.

Vamos expandir os exemplos para incluir mais situações comuns em herança.
#### Em C++

- **Exemplo: Adicionando Método à Subclasse**

```cpp
#include <iostream>
#include <string>

class Animal {
protected:
    std::string nome;

public:
    Animal(const std::string& nome) : nome(nome) {}

    void emitirSom() {
        std::cout << "Algum som de animal" << std::endl;
    }
};

class Cachorro : public Animal {
public:
    Cachorro(const std::string& nome) : Animal(nome) {}

    void latir() {
        std::cout << nome << " está latindo!" << std::endl;
    }
};

int main() {
    Cachorro meuCachorro("Buddy");
    meuCachorro.emitirSom();  // Método da classe base
    meuCachorro.latir();      // Método da subclasse

    return 0;
}
```

- **Exemplo: Utilizando Construtores e Destrutores**

```cpp
#include <iostream>
#include <string>

class Pessoa {
protected:
    std::string nome;

public:
    Pessoa(const std::string& nome) : nome(nome) {
        std::cout << "Construindo Pessoa: " << nome << std::endl;
    }

    ~Pessoa() {
        std::cout << "Destruindo Pessoa: " << nome << std::endl;
    }
};

class Estudante : public Pessoa {
public:
    Estudante(const std::string& nome) : Pessoa(nome) {
        std::cout << "Construindo Estudante: " << nome << std::endl;
    }

    ~Estudante() {
        std::cout << "Destruindo Estudante: " << nome << std::endl;
    }
};

int main() {
    Estudante estudante("Alice");
    // A destruição ocorre automaticamente quando o objeto sai do escopo

    return 0;
}
```

- **Exemplo: Acesso a Membros Protegidos**

```cpp
#include <iostream>
#include <string>

class Animal {
protected:
    std::string tipo;

public:
    Animal(const std::string& tipo) : tipo(tipo) {}

    void mostrarTipo() {
        std::cout << "Tipo de Animal: " << tipo << std::endl;
    }
};

class Passaro : public Animal {
public:
    Passaro(const std::string& tipo) : Animal(tipo) {}

    void voar() {
        std::cout << tipo << " está voando!" << std::endl;
    }
};

int main() {
    Passaro meuPassaro("Canário");
    meuPassaro.mostrarTipo();  // Método da classe base
    meuPassaro.voar();         // Método da subclasse

    return 0;
}
```

- **Exemplo: Herança Múltipla**

```cpp
#include <iostream>
#include <string>

class Animal {
public:
    void comer() {
        std::cout << "Animal está comendo." << std::endl;
    }
};

class Voador {
public:
    void voar() {
        std::cout << "Está voando." << std::endl;
    }
};

class Pato : public Animal, public Voador {
public:
    void grasnar() {
        std::cout << "Quack! Quack!" << std::endl;
    }
};

int main() {
    Pato meuPato;
    meuPato.comer();   // Método da classe base Animal
    meuPato.voar();    // Método da classe base Voador
    meuPato.grasnar();  // Método da subclasse Pato

    return 0;
}
```

Esses exemplos abordam diferentes aspectos da herança em C++, incluindo adição de métodos, utilização de construtores/destrutores, acesso a membros protegidos e herança múltipla.

#### Em Java

- **Exemplo: Adicionando Método à Subclasse**

```java
class Animal {
    protected String nome;

    public Animal(String nome) {
        this.nome = nome;
    }

    public void emitirSom() {
        System.out.println("Algum som de animal");
    }
}

class Cachorro extends Animal {
    public Cachorro(String nome) {
        super(nome);
    }

    public void latir() {
        System.out.println(nome + " está latindo!");
    }
}

public class Main {
    public static void main(String[] args) {
        Cachorro meuCachorro = new Cachorro("Buddy");
        meuCachorro.emitirSom();  // Método da classe base
        meuCachorro.latir();      // Método da subclasse
    }
}
```

- **Exemplo: Utilizando Construtores e Destrutores**

```java
class Pessoa {
    protected String nome;

    public Pessoa(String nome) {
        this.nome = nome;
        System.out.println("Construindo Pessoa: " + nome);
    }

    /* Em Java, não há o conceito direto de destrutores como em algumas outras linguagens, como C++. 
    Em Java, a máquina virtual (JVM) gerencia a coleta de lixo (garbage collection) para liberar a memória de objetos que não têm mais referências válidas */
}

class Estudante extends Pessoa {
    public Estudante(String nome) {
        super(nome);
        System.out.println("Construindo Estudante: " + nome);
    }

}

public class Main {
    public static void main(String[] args) {
        Estudante estudante = new Estudante("Alice");
    }
}
```

- **Exemplo: Acesso a Membros Protegidos**

```java
class Animal {
    protected String tipo;

    public Animal(String tipo) {
        this.tipo = tipo;
    }

    public void mostrarTipo() {
        System.out.println("Tipo de Animal: " + tipo);
    }
}

class Passaro extends Animal {
    public Passaro(String tipo) {
        super(tipo);
    }

    public void voar() {
        System.out.println(tipo + " está voando!");
    }
}

public class Main {
    public static void main(String[] args) {
        Passaro meuPassaro = new Passaro("Canário");
        meuPassaro.mostrarTipo();  // Método da classe base
        meuPassaro.voar();         // Método da subclasse
    }
}
```

- **Exemplo: Herança Múltipla**

Em Java, a herança múltipla direta não é permitida. No entanto, você pode alcançar funcionalidades semelhantes usando interfaces.

```java
interface Animal {
    void comer();
}

interface Voador {
    void voar();
}

class Pato implements Animal, Voador {
    @Override
    public void comer() {
        System.out.println("Pato está comendo.");
    }

    @Override
    public void voar() {
        System.out.println("Pato está voando.");
    }

    public void grasnar() {
        System.out.println("Quack! Quack!");
    }
}

public class Main {
    public static void main(String[] args) {
        Pato meuPato = new Pato();
        meuPato.comer();   // Método da interface Animal
        meuPato.voar();    // Método da interface Voador
        meuPato.grasnar();  // Método da classe Pato
    }
}
```

Estes são apenas exemplos adicionais para ilustrar diferentes aspectos da herança em Java. Cada exemplo enfatiza um aspecto específico, como métodos adicionados, construtores/destrutores, acesso a membros protegidos e simulação de herança múltipla usando interfaces.

### Questão 4
>Quando declaramos uma variável em Java, temos, na verdade, um ponteiro. Em C++ é diferente. Discorra sobre esse aspecto. 

Em Java, quando você declara uma variável de um tipo primitivo (como int, float, char, etc.), você está armazenando o valor real da variável, não um ponteiro para o valor. Por exemplo:

```java
int x = 10; // Em Java, x armazena diretamente o valor 10, não um ponteiro para 10.
```

No entanto, quando você trabalha com objetos em Java, você está lidando com referências. A variável armazena uma referência ao objeto na memória, não o objeto em si. Por exemplo:

```java
String str = new String("Hello"); // 'str' armazena uma referência ao objeto String na memória.
```

Agora, em relação ao C++, é verdade que o comportamento é diferente. Em C++, a declaração de variáveis pode ser mais flexível e oferecer maior controle sobre a alocação de memória. Por exemplo:

```cpp
int x = 10; // Em C++, x armazena diretamente o valor 10, assim como em Java para tipos primitivos.

int* ptr = new int; // Em C++, usando ponteiros para alocar dinamicamente memória.
*ptr = 10; // O ponteiro 'ptr' armazena o endereço de memória onde está o valor 10.
```

No exemplo acima, o operador `new` é usado para alocar dinamicamente um inteiro na memória e retorna o endereço desse espaço alocado, que é então armazenado no ponteiro `ptr`. A alocação dinâmica de memória em C++ permite maior controle, mas também exige responsabilidade na gestão de memória, pois é necessário liberar a memória alocada quando não for mais necessária.

Em resumo, enquanto em Java as variáveis de tipos primitivos armazenam diretamente os valores, em C++ você tem mais flexibilidade com ponteiros e alocação de memória, mas isso também traz a responsabilidade de gerenciar a memória manualmente.
