=====================================================================
 AGENCIA DE RELACIONAMENTO
 CEFET-MG - Campus Timoteo | Lab. AED I | Prof. Luciano Moreira
 Trabalho 1
 Alunos: Isabella Senra Menezes e João Vitor Alves Silva
=====================================================================
 
1. O QUE E O PROGRAMA
---------------------
Ferramenta de linha de comando que le o cadastro de candidatos de uma
agencia de relacionamento e identifica os potenciais companheiros de
cada um, considerando:
  - mesmo estado;
  - preferencia de sexo do candidato;
  - grau de interesse em comum (soma dos pesos das areas em comum)
    maior ou igual ao grau desejado, definido pelo usuario.
 
Pesos das areas:
  1 ESPORTES     = 1      5 TECNOLOGIA  = 3
  2 ARTES        = 2      6 ANIMAIS     = 2
  3 MUSICA       = 1      7 GASTRONOMIA = 2
  4 CINEMA       = 1      8 CIENCIAS    = 3
 
 
2. REQUISITOS
-------------
  - Terminal com suporte a UTF-8 (para exibir acentos corretamente).

 
3. ESTRUTURA DE ARQUIVOS
------------------------
Os arquivos-fonte ficam na pasta "TrabalhoCandidato" (nome do pacote):
 
  src/
    TrabalhoCandidato/
      Main.java
      LeitorArquivo.java
      GeradorCandidatos.java
      TADCandidato.java
      TADListaIdentificadores.java
      TADListaInteresses.java
      SexoCandidato.java
      Excecoes/
        ArquivoInvalidoException.java
 
Arquivos de dados (devem estar na pasta de onde o programa e executado):
  nomes.txt       - dicionario de nomes usado pelo gerador
  candidatos.txt  - cadastro lido pelo programa principal
 
 
4. GERANDO O ARQUIVO DE CANDIDATOS (programa auxiliar)
------------------------------------------------------
O GeradorCandidatos cria o arquivo candidatos.txt com dados aleatorios.
 
5.1 Prepare o arquivo nomes.txt na pasta de execucao, um nome por linha
    no formato "Nome;Sexo", onde Sexo e F ou M. Exemplo:
 
      Ana Souza;F
      Joao Pereira;M
      Maria Oliveira;F
      Carlos Lima;M
 
    (Salve o arquivo em UTF-8. Todas as linhas devem ter o ";".)
 
5.2 Sobre a execucao:

    Cada execucao sobrescreve o candidatos.txt existente.
    Estados sorteados: MG, SP, RJ, ES.
  Obs: Se desejar acrescentar mais estados no sorteio, acrescente tambem no vetor de String de estados que contem no arquivo Main

 
6. FORMATO DO ARQUIVO candidatos.txt
------------------------------------
  N                      (numero de candidatos)
  ****
  nome
  sigla_estado           (2 letras, ex.: MG)
  sexo                   (F ou M)
  sexo_interesse         (F, M ou I = indiferente)
  numero_areas
  area_1                 (codigo de 1 a 8)
  area_2
  ...
  ****
  (proximo candidato)
 
Exemplo:
 
  2
  ****
  Ana Souza
  MG
  F
  M
  2
  1
  5
  ****
  Joao Pereira
  MG
  M
  F
  1
  5
 
Linhas em branco sao ignoradas. O arquivo deve estar em UTF-8.
 
 
7. EXECUTANDO O PROGRAMA PRINCIPAL
----------------------------------
Com o candidatos.txt na pasta de execucao:
 
  Projeto/candidatos.txt (deve ficar na mesma pasta onde contem a pasta src
 
Ao iniciar, o programa carrega o arquivo e calcula os potenciais
companheiros com grau de interesse desejado igual a 0. Se o arquivo
estiver ausente ou malformado, uma mensagem de erro e exibida e o
programa encerra.
 
 
8. USO DO MENU
--------------
Digite o numero da opcao e pressione ENTER.
 
  1 - Inserir/atualizar o grau de interesse desejado
      Informe um inteiro de 0 a 15. Todas as listas de potenciais
      companheiros sao recalculadas com o novo grau.
 
  2 - Imprimir a lista dos potenciais companheiros de um candidato
      O programa mostra todos os candidatos (id - nome - estado).
      Informe o id desejado. Sao impressos o numero e a lista de
      potenciais companheiros.
 
  3 - Imprimir id/nome dos K candidatos com maior numero de potenciais
      companheiros
      Informe K (entre 1 e o total de candidatos).
 
  4 - Imprimir id/nome dos candidatos sem nenhum companheiro encontrado
 
  5 - Imprimir id/nome de candidatos com interesse em uma area
      Informe o codigo da area (1 a 8). Ao final e impresso o total.
 
  6 - Imprimir id/nome de candidatos que residem em um estado
      Informe a sigla (MG, SP, RJ ou ES; maiusculas ou minusculas).
      Ao final e impresso o total.
 
  0 - Sair do programa
 
Entradas invalidas (texto no lugar de numero, id inexistente, area ou
estado invalido, grau fora de 0 a 15) geram uma mensagem e o menu volta.
 
 
9. EXEMPLO DE SESSAO
--------------------
  Candidatos carregados com sucesso do arquivo: candidatos.txt
 
  -- MENU --
  ...
  1
  Qual o grau de interesse desejado?
  4
  Grau de interesse desejado atual: 4
 
  2
  [ lista de candidatos ]
  Informe o id de qual dos candidatos acima voce deseja saber os
  potenciais companheiros:
  0
  Candidato: 0 - Ana Souza - MG
  Numero de potenciais companheiros: 3
  [ 7 - Joao Pereira - Numero de potenciais companheiros: 5 ]
  ...
 
 
10. OBSERVACOES SOBRE AS REGRAS
-------------------------------
  - Apenas a preferencia de sexo do candidato u e considerada ao decidir
    se v e seu potencial companheiro (conforme o enunciado). Por isso a
    relacao nao e simetrica: v pode estar na lista de u sem que u esteja
    na lista de v.
  - O sexo cadastrado do candidato deve ser F ou M. O valor I e valido
    somente para o sexo de interesse.
  - Os identificadores sao gerados pelo programa, em sequencia a partir
    de 0, na ordem em que os candidatos aparecem no arquivo.
  - A opcao 6 aceita apenas os estados MG, SP, RJ e ES (a nao ser que sejam acrescentadas mais opcoes no vetor de estados na Main).
  - As estruturas de dados (listas encadeadas com tail e sentinela) foram
    implementadas pelo grupo, sem uso de ArrayList, LinkedList e similares.
 
 
11. PROBLEMAS COMUNS
--------------------
  - "Erro ao carregar o arquivo: arquivo nao encontrado"
      O candidatos.txt nao esta na pasta de onde o java foi executado.
      Rode o gerador ou copie o arquivo para essa pasta.
 
  - Acentos aparecendo errados
      Confira se nomes.txt e candidatos.txt estao em UTF-8 e se o
      terminal esta configurado para UTF-8.
 
  - Gerador falha com ArrayIndexOutOfBoundsException
      Alguma linha de nomes.txt nao tem o formato "Nome;Sexo".
