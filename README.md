# Sal-Cisne---Java

API SPRING

put - ( Tramsferencia de Produtos ) - http://localhost:8080/estoque/transfer?idOrigem=1&idDestino=2&qtd=18
[ http://localhost:8080/estoque/transfer?idOrigem= [ID da filial de origem] &idDestino= [ID da filial de destino] &qtd= [ Quantidade de Produtos a serem enviados ] ]
Junto com a requisição deve ser enviado um json com o corpo de um produto
  {
    "versao": 0,
    "dataUltAlteracao": "2020-09-17T16:40:08.659+00:00",
    "id": "Cod-Produto-1",
    "nome": "Sal produto 1",
    "new": false
}

Caso o estoque relacionado a esse produto e as filiais informadas existir e o valor da filial de origem
for maior que a quantidade solicitada, os valores serão alterados.

get-all produto - http://localhost:8080/produto/find-all

