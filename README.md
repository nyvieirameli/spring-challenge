# spring-challenge

Desafio prático do bootcamp, módulo 8, SocialMeli. O objetivo é: testar tudo aprendido sobre POO, GIT, Java, Stream, Spring e JPA 


## Como rodar o projeto
1. Clonar o projeto
```
 git clone git@github.com:nyvieirameli/spring-challenge.git
```
2. rodar o projeto pelo IntelliJ IDEA

## Casos de Uso

### US0001 - Ser capaz de realizar a ação de “Follow” (seguir) a um determinado vendedor

```
POST /users/buyers/{{followerId}}/follow/{{followedId}}
```
Parâmetros:

| Parâmetro | Tipo | Descrição |
| ----------- | ---- | --------- |
| followerId | UUID | Identificação do usuário que irá seguir |
| followedId | UUID | Identificação do usuário a ser seguido |

Retorno:

| Código | nome | Descrição |
| ----------- | ---- | --------- |
| 204 | NO-CONTENT | Sucesso ao Seguir Vendedor |
| 400 | BAD REQUEST | UUID inválida: mal formatada ou usuários inválidos |

Body Retorno 400:

````json
{
  "httpStatus": "BAD_REQUEST",
  "hasError": true,
  "errorMessage": "User is invalid",
  "data": null
}
````
Paramêtros De retorno:

| Parâmetro | Tipo | Descrição |
| ----------- | ---- | --------- |
| httpStatus | String | Nome do status passado |
| hasError | Boolean | Verificação de erro |
| errorMessage | String | Mensagem de erro retornada |
| data | null | valor vindo apenas caso 200 (neste caso, sempre nula) |

### US0002 - Obter o resultado do número de usuários que seguem um determinado vendedor

```
GET /users/sellers/{{sellerId}}/followers/count
```
Parâmetros:

| Parâmetro | Tipo | Descrição |
| ----------- | ---- | --------- |
| sellerId | UUID | Identificação do vendedor |

Retorno:

| Código | nome | Descrição |
| ----------- | ---- | --------- |
| 200 | OK | Sucesso na requisição retornando o  json |
| 400 | BAD REQUEST | UUID inválida: mal formatada ou usuário inválido |

Body Retorno 200:

````json
{
  "httpStatus": "OK",
  "hasError": false,
  "errorMessage": null,
  "data": 1
}
````

Body Retorno 400:

````json
{
  "httpStatus": "BAD_REQUEST",
  "hasError": true,
  "errorMessage": "User is invalid",
  "data": null
}
````

Paramêtros De retorno:

| Parâmetro | Tipo | Descrição |
| ----------- | ---- | --------- |
| httpStatus | String | Nome do status passado |
| hasError | Boolean | Verificação de erro |
| errorMessage | String | Mensagem de erro retornada |
| data | Long | Quantidade de usuários seguindo |

### US0003 - Obter uma lista de todos os usuários que seguem um determinado vendedor (quem me segue?)

```
GET  /users/sellers/{{sellerId}}/followers/list
```
Parâmetros:

| Parâmetro | Tipo | Descrição |
| ----------- | ---- | --------- |
| sellerId | UUID | Identificação do vendedor |

Retorno:

| Código | nome | Descrição |
| ----------- | ---- | --------- |
| 200 | OK | Sucesso na requisição retornando o  json |
| 400 | BAD REQUEST | UUID inválida: mal formatada ou usuário inválido |

Body Retorno 200:

````json
{
  "httpStatus": "OK",
  "hasError": false,
  "errorMessage": null,
  "data": [
    {
      "id": "2c276ff9-6b1c-4e13-a71f-1ee830a1d75e",
      "name": "Nycolas",
      "cpf": "12345678911",
      "birthdate": "2000-05-27",
      "type": "BUYER"
    }
  ]
}
````

````json
{
  "httpStatus": "BAD_REQUEST",
  "hasError": true,
  "errorMessage": "User is invalid",
  "data": null
}
````

Paramêtros De retorno:

| Parâmetro | Tipo | Descrição |
| ----------- | ---- | --------- |
| httpStatus | String | Nome do status passado |
| hasError | Boolean | Verificação de erro |
| errorMessage | String | Mensagem de erro retornada |
| data | List<Followers> | Lista com todos os seguidores daquele vendedor |

### US0004 - Obter uma lista de todos os vendedores que um determinado usuário segue (quem estou seguindo?)

```
GET /users/buyers/{{userId}}/following
```
Parâmetros:

| Parâmetro | Tipo | Descrição |
| ----------- | ---- | --------- |
| userId | UUID | Identificação do usuário |

Retorno:

| Código | nome | Descrição |
| ----------- | ---- | --------- |
| 200 | OK | Sucesso na requisição retornando o  json |
| 400 | BAD REQUEST | UUID inválida: mal formatada ou usuário inválido |

Body Retorno 200:

````json
{
  "httpStatus": "OK",
  "hasError": false,
  "errorMessage": null,
  "data": [
    {
      "id": "e9d8f0bf-00aa-4af3-bdec-40404c80b301",
      "name": "Onias",
      "cpf": "12345678911",
      "birthdate": "2000-05-27",
      "type": "SELLER"
    }
  ]
}
````

Body Retorno 400:

````json
{
  "httpStatus": "BAD_REQUEST",
  "hasError": true,
  "errorMessage": "User is invalid",
  "data": null
}
````

Paramêtros De retorno:

| Parâmetro | Tipo | Descrição |
| ----------- | ---- | --------- |
| httpStatus | String | Nome do status passado |
| hasError | Boolean | Verificação de erro |
| errorMessage | String | Mensagem de erro retornada |
| data | List<Followeds> | Lista com todos a quem o usuário segue |

### US0005 - Cadastrar uma nova publicação

```
POST /products/posts
```

Exemplo de Corpo do Envio:

````json
{
  "category":100,
  "price":5500.00,
  "quantity":13,
  "productId":"{{productId}}"
}
````

Parâmetros:

| Parâmetro | Tipo | Descrição |
| ----------- | ---- | --------- |
| category | Integer | Identificador usado para saber a categoria à qual um produto pertence. Por exemplo: 100: cadeiras, 58: teclados |
| price | Double | Preço do produto |
| quantity | Integer | Quantidade de itens daquele produto |
| productId | UUID | Identificação do produto associado a publicação |

Retorno:

| Código | nome | Descrição |
| ----------- | ---- | --------- |
| 200 | CREATED | Sucesso na criação |
| 400 | BAD REQUEST | UUID mal formatado ou parâmetros incorretos |

Body Retorno 200:

````json
{
  "httpStatus": "OK",
  "hasError": false,
  "errorMessage": null,
  "data": {
    "id": "269c8aa8-f0a2-4fe2-8d1b-1406eda75276",
    "date": "2021-06-08T02:13:00.064046",
    "category": 100,
    "price": 5500.0,
    "quantity": 13,
    "hasPromo": false,
    "discount": 0.0,
    "product": {
      "id": "445d58fa-ccb5-4bfa-a8af-bd19066ff5c4",
      "name": "Macbook",
      "color": "Cinza",
      "notes": "Um macbook cinza bem legal",
      "user": {
        "id": "e9d8f0bf-00aa-4af3-bdec-40404c80b301",
        "name": "Onias",
        "cpf": "12345678911",
        "birthdate": "2000-05-27",
        "type": "SELLER"
      },
      "brand": {
        "id": "56a3c3b3-0ed9-4f39-ae61-4396a1e21dfd",
        "name": "Apple"
      },
      "type": {
        "id": "e9347f17-0358-4a41-8a33-7cbf8629b972",
        "name": "Notebook"
      }
    }
  }
}
````

Body Retorno 400:

````json
{
  "httpStatus": "BAD_REQUEST",
  "hasError": true,
  "errorMessage": "Product is null",
  "data": null
}
````

Paramêtros De retorno:

| Parâmetro | Tipo | Descrição |
| ----------- | ---- | --------- |
| httpStatus | String | Nome do status passado |
| hasError | Boolean | Verificação de erro |
| errorMessage | String | Mensagem de erro retornada |
| data | Post | Corpo do post criado |

### US0006 - Obter uma lista das publicações feitas pelos vendedores que um usuário segue nas últimas duas semanas (para isso, ter em conta ordenação por data, a maioria das publicações recentes primeiro).

```
GET /products/posts/followed/{{userId}}/list
```
Parâmetros:

| Parâmetro | Tipo | Descrição |
| ----------- | ---- | --------- |
| userId | UUID | Identificação do usuário |

Retorno:

| Código | nome | Descrição |
| ----------- | ---- | --------- |
| 200 | CREATED | Lista encontrada com sucesso |
| 404 | NOT FOUND | Lista vazia ou usuário não encontrado |

Body Retorno 200:

```json
{
  "httpStatus": "OK",
  "hasError": false,
  "errorMessage": null,
  "data": [
    {
      "id": "269c8aa8-f0a2-4fe2-8d1b-1406eda75276",
      "date": "2021-06-08T02:13:00.064046",
      "category": 100,
      "price": 5500.0,
      "quantity": 13,
      "hasPromo": false,
      "discount": 0.0,
      "product": {
        "id": "445d58fa-ccb5-4bfa-a8af-bd19066ff5c4",
        "name": "Macbook",
        "color": "Cinza",
        "notes": "Um macbook cinza bem legal",
        "user": {
          "id": "e9d8f0bf-00aa-4af3-bdec-40404c80b301",
          "name": "Onias",
          "cpf": "12345678911",
          "birthdate": "2000-05-27",
          "type": "SELLER"
        },
        "brand": {
          "id": "56a3c3b3-0ed9-4f39-ae61-4396a1e21dfd",
          "name": "Apple"
        },
        "type": {
          "id": "e9347f17-0358-4a41-8a33-7cbf8629b972",
          "name": "Notebook"
        }
      }
    },
    {
      "id": "9f0c7ecb-2fe5-4d6a-b38c-0557e1f03259",
      "date": "2021-06-08T01:47:39.837793",
      "category": 100,
      "price": 5500.0,
      "quantity": 13,
      "hasPromo": true,
      "discount": 32.0,
      "product": {
        "id": "445d58fa-ccb5-4bfa-a8af-bd19066ff5c4",
        "name": "Macbook",
        "color": "Cinza",
        "notes": "Um macbook cinza bem legal",
        "user": {
          "id": "e9d8f0bf-00aa-4af3-bdec-40404c80b301",
          "name": "Onias",
          "cpf": "12345678911",
          "birthdate": "2000-05-27",
          "type": "SELLER"
        },
        "brand": {
          "id": "56a3c3b3-0ed9-4f39-ae61-4396a1e21dfd",
          "name": "Apple"
        },
        "type": {
          "id": "e9347f17-0358-4a41-8a33-7cbf8629b972",
          "name": "Notebook"
        }
      }
    }
  ]
}
````

Body Retorno 404:

```json
{
    "httpStatus": "NOT_FOUND",
    "hasError": true,
    "errorMessage": "User not found",
    "data": null
}
````

Paramêtros De retorno:

| Parâmetro | Tipo | Descrição |
| ----------- | ---- | --------- |
| httpStatus | String | Nome do status passado |
| hasError | Boolean | Verificação de erro |
| errorMessage | String | Mensagem de erro retornada |
| data | List<Post> | Todos os posts das pessoas seguidas pelo usuário das últimoas duas semanas |

### US0007 - Ser capaz de realizar a ação de “Deixar de seguir” (parar de seguir) um determinado vendedor.

```
POST /users/buyers/{{followerId}}/unfollow/{{followedId}}
```
Parâmetros:

| Parâmetro | Tipo | Descrição |
| ----------- | ---- | --------- |
| followerId | UUID | Identificação do usuário que irá deixar de seguir |
| followedId | UUID | Identificação do usuário que irá deixar de ser seguido |

Retorno:

| Código | nome | Descrição |
| ----------- | ---- | --------- |
| 204 | NO-CONTENT | Sucesso ao Deseguir Vendedor |
| 400 | BAD REQUEST | UUID inválida: mal formatada ou usuários inválidos |

Body Retorno 400:

````json
{
  "httpStatus": "BAD_REQUEST",
  "hasError": true,
  "errorMessage": "User is invalid",
  "data": null
}
````
Paramêtros De retorno:

| Parâmetro | Tipo | Descrição |
| ----------- | ---- | --------- |
| httpStatus | String | Nome do status passado |
| hasError | Boolean | Verificação de erro |
| errorMessage | String | Mensagem de erro retornada |
| data | null | valor vindo apenas caso 200 (neste caso, sempre nula) |

### US0008 - Ordem alfabética crescente e decrescente
Habilitado em :
```
GET
/users/buyers/{{buyerId}}/following
/users/sellers/{{sellerId}}/followers/list
/users/sellers/{{sellerId}}/following
```

GET Exemplos:
```
/users/buyers/{{buyerId}}/following?order=name-asc
/users/buyers/{{buyerId}}/following?order=name-desc
```

Parâmetros da Query :

| Tipo | Valor | Descrição |
| ----------- | ---- | --------- |
| order | name-asc |  Alfabético crescente |
| order | name-desc |  Alfabético decrescente |

Ordem padrão: crescente (name-asc)

### US0009 - Classificar por data crescente e decrescente

Habilitado em :
```
GET
/products/posts/followed/{{buyerId}}/list
```

GET Exemplos:
```
/products/posts/followed/{{buyerId}}/list?order=date-asc
/products/posts/followed/{{buyerId}}/list?order=date-desc
```

Parâmetros da Query:

| Tipo | Valor | Descrição |
| ----------- | ---- | --------- |
| order | date-asc |   crescente (do mais antigo para o mais novo |
| order | date-desc |  decrescente (do mais novo ao mais antigo |

Ordem padrão: descrescente (date-desc)

### US0010 - Realizar a publicação de um novo produto promocional

```
POST /products/posts/new-promo
```
Exemplo de Corpo do Envio :

```json
{
  "category":100,
  "price":5500.00,
  "quantity":13,
  "productId":"{{productId}}",
  "discount":32.0
}
```

Parâmetros:

| Parâmetro | Tipo | Descrição |
| ----------- | ---- | --------- |
| category | Integer | Identificador usado para saber a categoria à qual um produto pertence. Por exemplo: 100: cadeiras, 58: teclados |
| price | Double | Preço do produto |
| quantity | Integer | Quantidade de itens daquele produto |
| productId | UUID | Identificação do produto associado a publicação |
| discount | Double | Valor de desconto do produto na publicação |

Retorno:

| Código | nome | Descrição |
| ----------- | ---- | --------- |
| 200 | CREATED | Sucesso na criação |
| 400 | BAD REQUEST | UUID mal formatado ou parâmetros incorretos |

Body Retorno 200:

```json
{
    "httpStatus": "OK",
    "hasError": false,
    "errorMessage": null,
    "data": {
        "id": "13b0cf2b-efd1-4c03-b667-18cba9f8b204",
        "date": "2021-06-08T02:50:21.629637",
        "category": 100,
        "price": 5500.0,
        "quantity": 13,
        "hasPromo": true,
        "discount": 32.0,
        "product": {
            "id": "445d58fa-ccb5-4bfa-a8af-bd19066ff5c4",
            "name": "Macbook",
            "color": "Cinza",
            "notes": "Um macbook cinza bem legal",
            "user": {
                "id": "e9d8f0bf-00aa-4af3-bdec-40404c80b301",
                "name": "Onias",
                "cpf": "12345678911",
                "birthdate": "2000-05-27",
                "type": "SELLER"
            },
            "brand": {
                "id": "56a3c3b3-0ed9-4f39-ae61-4396a1e21dfd",
                "name": "Apple"
            },
            "type": {
                "id": "e9347f17-0358-4a41-8a33-7cbf8629b972",
                "name": "Notebook"
            }
        }
    }
}
```

Body Retorno 400:

````json
{
  "httpStatus": "BAD_REQUEST",
  "hasError": true,
  "errorMessage": "Product is null",
  "data": null
}
````

Paramêtros De retorno:

| Parâmetro | Tipo | Descrição |
| ----------- | ---- | --------- |
| httpStatus | String | Nome do status passado |
| hasError | Boolean | Verificação de erro |
| errorMessage | String | Mensagem de erro retornada |
| data | Post | Corpo do post criado |

### US0011 - Obtenha o quantidade de produtos promocionais de um vendedor específico
```
GET /products/posts/{{sellerId}}/promo/count
```

Parâmetros:

| Parâmetro | Tipo | Descrição |
| ----------- | ---- | --------- |
| sellerId | UUID | Identificação do vendedor |

Retorno:

| Código | nome | Descrição |
| ----------- | ---- | --------- |
| 200 | OK | Sucesso na requisição retornando o  json |
| 400 | BAD REQUEST | UUID inválida: mal formatada ou usuário inválido |

Body Retorno 200:

````json
{
  "httpStatus": "OK",
  "hasError": false,
  "errorMessage": null,
  "data": 2
}
````

Body Retorno 400:

````json
{
  "httpStatus": "BAD_REQUEST",
  "hasError": true,
  "errorMessage": "User is invalid",
  "data": null
}
````

Paramêtros De retorno:

| Parâmetro | Tipo | Descrição |
| ----------- | ---- | --------- |
| httpStatus | String | Nome do status passado |
| hasError | Boolean | Verificação de erro |
| errorMessage | String | Mensagem de erro retornada |
| data | Long | Quantidade de posts com promoção daquele vendedor |

### US0012 - Obter uma lista de todos os produtos promocionais de um vendedor específico

```
GET /products/posts/{{sellerId}}/list/promo
```

Parâmetros:

| Parâmetro | Tipo | Descrição |
| ----------- | ---- | --------- |
| sellerId | UUID | Identificação do vendedor |

Retorno:

| Código | nome | Descrição |
| ----------- | ---- | --------- |
| 200 | OK | Sucesso na requisição retornando o  json |
| 404 | BAD REQUEST | UUID inválida: mal formatada ou usuário inválido |

Body Retorno 200:

````json
{
  "httpStatus": "OK",
  "hasError": false,
  "errorMessage": null,
  "data": [
    {
      "id": "9f0c7ecb-2fe5-4d6a-b38c-0557e1f03259",
      "date": "2021-06-08T01:47:39.837793",
      "category": 100,
      "price": 5500.0,
      "quantity": 13,
      "hasPromo": true,
      "discount": 32.0,
      "product": {
        "id": "445d58fa-ccb5-4bfa-a8af-bd19066ff5c4",
        "name": "Macbook",
        "color": "Cinza",
        "notes": "Um macbook cinza bem legal",
        "user": {
          "id": "e9d8f0bf-00aa-4af3-bdec-40404c80b301",
          "name": "Onias",
          "cpf": "12345678911",
          "birthdate": "2000-05-27",
          "type": "SELLER"
        },
        "brand": {
          "id": "56a3c3b3-0ed9-4f39-ae61-4396a1e21dfd",
          "name": "Apple"
        },
        "type": {
          "id": "e9347f17-0358-4a41-8a33-7cbf8629b972",
          "name": "Notebook"
        }
      }
    },
    {
      "id": "13b0cf2b-efd1-4c03-b667-18cba9f8b204",
      "date": "2021-06-08T02:50:21.629637",
      "category": 100,
      "price": 5500.0,
      "quantity": 13,
      "hasPromo": true,
      "discount": 32.0,
      "product": {
        "id": "445d58fa-ccb5-4bfa-a8af-bd19066ff5c4",
        "name": "Macbook",
        "color": "Cinza",
        "notes": "Um macbook cinza bem legal",
        "user": {
          "id": "e9d8f0bf-00aa-4af3-bdec-40404c80b301",
          "name": "Onias",
          "cpf": "12345678911",
          "birthdate": "2000-05-27",
          "type": "SELLER"
        },
        "brand": {
          "id": "56a3c3b3-0ed9-4f39-ae61-4396a1e21dfd",
          "name": "Apple"
        },
        "type": {
          "id": "e9347f17-0358-4a41-8a33-7cbf8629b972",
          "name": "Notebook"
        }
      }
    }
  ]
}
````

Body Retorno 404:

````json
{
  "httpStatus": "NOT_FOUND",
  "hasError": true,
  "errorMessage": "User not found",
  "data": null
}
````

Paramêtros De retorno:

| Parâmetro | Tipo | Descrição |
| ----------- | ---- | --------- |
| httpStatus | String | Nome do status passado |
| hasError | Boolean | Verificação de erro |
| errorMessage | String | Mensagem de erro retornada |
| data | List<Post> | Lista com os posts promocionais daquele vendedor |