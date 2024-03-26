# Supermercado - Prática 18

Este é um projeto de exemplo de um supermercado com vários arquivos de código-fonte que precisam ser compilados para gerar o executável do supermercado.

## Compilação

Para compilar o código-fonte e gerar o executável, siga as etapas abaixo:

1. Abra um terminal ou prompt de comando.

2. Navegue até o diretório onde os arquivos de código-fonte estão localizados. Você pode fazer isso usando o comando `cd`:

   ```
   cd Supermercado
   ```

3. Use o compilador `g++` para compilar todos os arquivos de código-fonte juntos com os seguintes comandos:

   ```bash
   g++ -Wall -Wextra -g3 Supermercado.cpp Produto.cpp Estoque.cpp CarrinhoDeCompras.cpp -o output/Supermercado.exe
   ```

   Este comando compila os arquivos `Supermercado.cpp`, `Produto.cpp`, `Estoque.cpp` e `CarrinhoDeCompras.cpp` e gera um executável chamado `Supermercado.exe` na pasta `output`.

4. Após a conclusão da compilação, você pode executar o programa Supermercado.exe usando o seguinte comando:

   ```bash
   ./output/Supermercado.exe
   ```

   Isso executará o programa e você poderá interagir com a aplicação do supermercado.

Lembre-se de que você pode precisar ajustar os comandos de compilação e os nomes dos arquivos de código-fonte de acordo com a estrutura do seu projeto. Certifique-se de que o compilador `g++` esteja instalado e configurado corretamente em seu sistema antes de tentar compilar o código.

