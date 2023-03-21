# Linketinder (Beta)

O programa é uma junção do Tinder com o Linkedin, onde as empresas e os candidatos podem da um "Like" pra gostarem um do outro e em caso de match será possível com que mandem uma mensagem um para outro.

Programa feito para o processo seletivo da ZG Soluções. 

Feito por Igor Moreira Pádua.

# Tecnologias

## Front-end:
* HTML
* CSS
* TypeScript

## Back-end:
* Groovy

## Banco de dados:
* postgresql

### Modelagem:
![liketinder](https://user-images.githubusercontent.com/40117861/226634252-99506f9a-aa23-44ca-83d8-8c4cf183f489.png)
_Feito com a ferramenta dbdiagram_

### Match Explicação
Quando um candidato curti uma vaga isso é registrado na tabela curtida onde no atributo curtiu é salvo o id da vaga. Para que seja possível identificar que um candidato curtiu uma vaga existe a tabela curtida_candidato que registra as curtidas dos candidatos.
Para a empresa é basicamente a mesma coisa, a curtida da empresa é salvo na tabela curtidas, onde o id do candidato é salvo no atributo curtiu. E para a indentificação das curtidas da empresa existe a tabela curtida_empresa.

Para verificarmos o match é necessario consultar a tabela curtida e curtida_empresa é verificar se a empresa curtiu o candidato e em seguida verificar se o canidato curtiu a empresa consultando as tabelas curtida e curtida_candidato.

# Funções

1. Adicionar um novo candidato
2. Adicionar uma nova empresa
3. Listar candidatos e empresas
4. Testes para inserção de um candidato e para um empresa
5. Gráfico de pizza com as competencias dos candidatos

# Executar

## Front-end
npm start

## Back-end
gradle appRun
