# Gestão de Passageiros de uma Empresa de Transporte

Este programa em C++ foi desenvolvido para permitir a gestão de passageiros de uma empresa de transporte. Ele oferece as seguintes funcionalidades:

1. Incluir Passageiro: Permite adicionar informações sobre um novo passageiro, incluindo nome, CPF, data de nascimento e número de autorização.

2. Excluir Passageiro: Permite remover um passageiro da lista com base no CPF fornecido.

3. Alterar Dado do Passageiro: Permite alterar informações de um passageiro, como nome, CPF, data de nascimento ou número de autorização.

4. Listar Todos os Passageiros: Exibe uma lista de todos os passageiros cadastrados, incluindo nome, CPF, data de nascimento e número de autorização.

5. Localizar Passageiro: Permite buscar um passageiro específico pelo CPF e exibir suas informações.

O programa utiliza um vetor de estruturas `PASSAGEIRO` para armazenar as informações dos passageiros. Ele também inclui funções auxiliares para formatar datas, validar datas e ordenar os passageiros por CPF.

## Como Usar o Programa

1. Ao iniciar o programa, você será apresentado a um menu de opções numeradas. Escolha a opção desejada digitando o número correspondente.

2. Para incluir um novo passageiro, escolha a opção 1 e siga as instruções fornecidas pelo programa.

3. Para excluir um passageiro, escolha a opção 2 e siga as instruções fornecidas pelo programa.

4. Para alterar informações de um passageiro, escolha a opção 3 e siga as instruções fornecidas pelo programa. Você pode escolher qual campo deseja alterar.

5. Para listar todos os passageiros cadastrados, escolha a opção 4. O programa exibirá as informações de todos os passageiros ou informará se a lista está vazia.

6. Para localizar um passageiro pelo CPF, escolha a opção 5 e siga as instruções fornecidas pelo programa.

7. Para sair do programa, escolha a opção 0.

# Gestão de Roteiros de uma Empresa de Transporte

Este programa em C++ foi desenvolvido para permitir a gestão de roteiros de uma empresa de transporte. Ele oferece as seguintes funcionalidades:

1. Incluir Roteiro: Permite adicionar informações sobre um novo roteiro, incluindo código, data e hora prevista, duração prevista e destino.

2. Excluir Roteiro: Permite remover um roteiro da lista com base no código fornecido.

3. Alterar Dado do Roteiro: Permite alterar informações de um roteiro, como código, data e hora prevista, duração prevista ou destino.

4. Listar Todos os Roteiros: Exibe uma lista de todos os roteiros cadastrados, incluindo código, data e hora prevista, duração prevista e destino.

5. Localizar Roteiro: Permite buscar um roteiro específico pelo código e exibir suas informações.

O programa utiliza uma estrutura `ROTEIRO` para armazenar as informações dos roteiros e um vetor para manter uma lista de roteiros. Além disso, ele inclui funções de validação de data e hora usando expressões regulares para garantir que os dados inseridos estejam no formato correto.

## Como Usar o Programa

1. Ao iniciar o programa, você será apresentado a um menu de opções numeradas. Escolha a opção desejada digitando o número correspondente.

2. Para incluir um novo roteiro, escolha a opção 1 e siga as instruções fornecidas pelo programa. Certifique-se de inserir datas e horas válidas no formato correto.

3. Para excluir um roteiro, escolha a opção 2 e siga as instruções fornecidas pelo programa.

4. Para alterar informações de um roteiro, escolha a opção 3 e siga as instruções fornecidas pelo programa. Você pode escolher qual campo deseja alterar.

5. Para listar todos os roteiros cadastrados, escolha a opção 4. O programa exibirá as informações de todos os roteiros ou informará se a lista está vazia.

6. Para localizar um roteiro pelo código, escolha a opção 5 e siga as instruções fornecidas pelo programa.

7. Para sair do programa, escolha a opção 0.

Lembre-se de que o programa utiliza formatação de data no formato "dd/mm/aaaa" e formatação de hora no formato "hh:mm". Ele também realiza validações para garantir a entrada correta de dados.