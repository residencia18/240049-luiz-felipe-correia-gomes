# Exercícios Teóricos

1. **O que é uma exceção em Java e qual é o propósito de usá-las em programas?**
   
   Em Java, uma exceção é um evento que ocorre durante a execução de um programa e interrompe o fluxo normal de instruções. Essas situações excepcionais podem ser causadas por erros no código, condições imprevistas ou situações de erro durante a execução do programa. O propósito de usar exceções é lidar com essas condições de erro de maneira estruturada, permitindo que o programa responda apropriadamente a essas situações anômalas, evitando a interrupção abrupta e incontrolada da execução.

2. **Pesquise sobre a diferença entre exceções verificadas e não verificadas em Java. Dê exemplos de cada uma.**

   - **Exceções Verificadas (Checked Exceptions):**
     São exceções que o compilador exige que sejam tratadas ou declaradas no método que as lança. Elas são subclasses da classe `Exception`, excluindo `RuntimeException` e suas subclasses. Exemplos:

     ```java
     import java.io.File;
     import java.io.FileNotFoundException;
     import java.io.FileReader;

     public class ExemploExcecaoVerificada {
         public static void main(String[] args) {
             try {
                 FileReader arquivo = new FileReader(new File("arquivo.txt"));
             } catch (FileNotFoundException e) {
                 e.printStackTrace();
             }
         }
     }
     ```

   - **Exceções Não Verificadas (Unchecked Exceptions):**
     São exceções que o compilador não obriga a serem tratadas ou declaradas. Elas são subclasses de `RuntimeException`. Exemplos:

     ```java
     public class ExemploExcecaoNaoVerificada {
         public static void main(String[] args) {
             int[] array = new int[5];
             System.out.println(array[10]); // Isso lançará ArrayIndexOutOfBoundsException em tempo de execução
         }
     }
     ```

3. **Como você pode lidar com exceções em Java? Quais são as palavras-chave e as práticas comuns para o tratamento de exceções?**

   - **Palavras-chave:**
     - `try`: Define um bloco de código onde podem ocorrer exceções.
     - `catch`: Captura exceções específicas e define o bloco de código a ser executado quando essas exceções ocorrerem.
     - `finally`: Define um bloco de código que é sempre executado, independentemente de ocorrer uma exceção ou não.
     - `throw`: Lança uma exceção manualmente.
     - `throws`: Indica que um método pode lançar exceções específicas e delega a responsabilidade de tratamento para o chamador.

   - **Práticas comuns:**
     - Sempre trate ou declare exceções verificadas.
     - Use `try-catch` para capturar exceções e fornecer um comportamento alternativo ou mensagem de erro adequada.
     - Utilize o bloco `finally` para garantir que determinadas operações sejam executadas, independentemente de ocorrerem exceções ou não.

4. **O que é o bloco "try-catch" em Java? Como ele funciona e por que é importante usá-lo ao lidar com exceções?**

   O bloco `try-catch` em Java é usado para envolver um bloco de código onde podem ocorrer exceções. O bloco `try` contém o código que pode gerar exceções, e o bloco `catch` contém o código a ser executado quando uma exceção específica é lançada. Se uma exceção ocorrer no bloco `try`, o controle é transferido para o bloco `catch` apropriado.

   Exemplo:

   ```java
   try {
       // Código que pode gerar uma exceção
   } catch (TipoDeExcecao e) {
       // Código a ser executado em caso de exceção
   }
   ```

   É importante usar o bloco `try-catch` para lidar com exceções porque isso ajuda a manter o controle do fluxo do programa, evitando que ele seja encerrado abruptamente por erros não tratados.

5. **Quando é apropriado criar suas próprias exceções personalizadas em Java e como você pode fazer isso? Dê um exemplo de quando e por que você criaria uma exceção personalizada.**

   É apropriado criar suas próprias exceções personalizadas em Java quando você precisa lidar com situações específicas no seu domínio de aplicação que não são adequadamente representadas por exceções padrão da linguagem.

   Exemplo:

   ```java
   public class SaldoInsuficienteException extends Exception {
       public SaldoInsuficienteException(String mensagem) {
           super(mensagem);
       }
   }

   public class ContaBancaria {
       private double saldo;

       public void sacar(double valor) throws SaldoInsuficienteException {
           if (valor > saldo) {
               throw new SaldoInsuficienteException("Saldo insuficiente para realizar o saque.");
           }
           // Lógica para realizar o saque se o saldo for suficiente
       }
   }
   ```

   Neste exemplo, uma exceção personalizada `SaldoInsuficienteException` é criada para lidar com casos específicos em que uma operação de saque em uma conta bancária não pode ser concluída devido a saldo insuficiente. Isso proporciona uma maneira mais clara e semântica de lidar com essa condição específica no código.