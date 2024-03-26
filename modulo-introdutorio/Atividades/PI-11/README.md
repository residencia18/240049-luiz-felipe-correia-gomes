# Prática 07
Exercícios práticos para sedimentar os conceitos de modularização, funções, passagem de parâmetro por valor e por referência, e qualidade na modularização. 

## Exercício 01
Código que recebe um vetor de números inteiros chamado vetor, uma variável inteira n contendo o tamanho do vetor, e os endereços de duas variáveis inteiras, maximo e minimo nas quais será retornado o valor do elemento de maior valor e o valor do elemento de menor valor.
## Exercício 02
A função maxmin do exercício apresenta coesão funcional, pois ela tem uma única responsabilidade clara: encontrar o valor máximo e mínimo em um vetor de números inteiros. A função não está realizando múltiplas tarefas ou manipulando dados para finalidades diversas. Quanto ao acoplamento, a função maxmin é relativamente fraca em termos de acoplamento, pois recebe o vetor, seu tamanho e as variáveis maximo e minimo por referência. Ela não está diretamente acoplada a outras partes do código além dos seus parâmetros de entrada e saída.
## Exercício 03
Código onde são criadas quatro variáveis float e são passadas em uma função para que possam ser ordenadas em ordem crescente.
## Exercício 04
Função calcula que:
- recebe como parâmetros duas variáveis inteiras, X e Y;
- retorna em X a soma de X e Y;
- retorna em Y a subtração de X e Y
## Exercício 05
O programa demonstra a inserção de um elemento no meio de um vetor e exibe o vetor resultante.
## Exercício 06
Neste código, a função intercala cria um novo vetor dinamicamente alocado para armazenar os valores intercalados dos dois vetores de entrada. Ela então intercala os elementos dos dois vetores, copiando-os para o vetor resultante, e retorna o vetor resultante. O programa principal pede ao usuário que insira os elementos dos dois vetores e, em seguida, chama a função intercala para obter o vetor intercalado, que é impresso na tela. 
## Exercício 07
Neste código, a função multiplica_por_n recebe um vetor e um multiplicador, e multiplica cada elemento do vetor pelo multiplicador em um loop. O programa principal pede ao usuário que insira os elementos do vetor, o multiplicador e, em seguida, chama a função multiplica_por_n para realizar a multiplicação. No final, os resultados são impressos na tela.
## Exercício 08
Neste código, a função eh_primo verifica se um número é primo ou não. A função conta_primos itera através do vetor e chama eh_primo para cada elemento, contando quantos são primos. O programa principal pede ao usuário que insira os elementos do vetor, chama a função conta_primos para contar os números primos e imprime o resultado na tela. 
## Exercício 09
Neste programa, a função calc_serie recebe um valor inteiro N como entrada e calcula a soma da série conforme descrito. A série é calculada com um loop que começa com o numerador em 1 e o denominador em N, incrementando o numerador e decrementando o denominador até que o numerador seja igual a N. O resultado da série é armazenado na variável soma e, em seguida, é retornado. O programa principal pede ao usuário que insira o valor de N, chama a função calc_serie e imprime o resultado na tela.
## Exercício 10
Neste código, a função encontra_posicoes percorre a string str, verifica se cada caractere é igual à letra fornecida e, se for o caso, armazena o índice no vetor posicoes. O tamanho do vetor posicoes é armazenado na variável tamanho. No programa principal, o usuário insere a string e a letra, e a função encontra_posicoes é chamada para encontrar as posições da letra na string. Os índices são então impressos na tela.
## Exercício 11
Neste código, a função codificar recebe uma string e a codifica de acordo com a regra secreta (substituindo cada letra pela próxima), enquanto a função decodificar decodifica a string (substituindo cada letra pela anterior). O programa principal pede ao usuário que insira uma string, em seguida, chama as funções codificar e decodificar, e imprime os resultados na tela. 
## Exercício 12
Neste código, a função caracteres_comuns recebe duas strings, A e B, e itera pelos caracteres de A. Para cada caractere em A, verifica se ele também está presente em B (usando a função find) e se ainda não foi adicionado à string C (para evitar duplicatas). Se um caractere for comum entre A e B, ele é adicionado à string C. O programa principal pede ao usuário que insira as duas strings, chama a função caracteres_comuns e imprime os caracteres comuns na string C. Se não houver caracteres comuns, ele informará que não há caracteres comuns entre as duas strings.

