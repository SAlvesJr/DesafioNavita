# ⚡ Avaliação Técnica – Desenvolvimento | Navita ⚡
---

## Gerenciamento de patrimônios de uma empresa.
 

### Requisitos técnicos útilizados
* Java 11; 
* Spring Boot
* Os dados foram salvos no PostgreSQL
* Testes unitários 
* API Restful
* Autenticaçã JWT

## Solução 
*  Inicialmente estruturei o projeto, e após isso comecei a desenvolver os testes, juntamente com a implementação do service para por fim fazer os controllers.

---

## Endpoints

 
 *Usuário*
~~~
° GET usuarios/list – Listar os usuários
° GET usuarios/{id} – Busca os usuários pelo ID
° POST usuarios – Criar um usuário
° GET usuarios/email?value= – Busca usúario pelo email
° PUT usuários/{id} – Editiar um usuário
° DELETE usuários/{id} – Excluir um usuário
~~~

*Patrimônio*
~~~
° GET patrimonios - Obter todos os patrimônios
° GET patrimonios/{id} - Obter um patrimônio por ID
° POST patrimonios - Inserir um novo patrimônio
° PUT patrimonios/{id} - Alterar os dados de um patrimônio
° DELETE patrimonios/{id} - Excluir um patrimônio
~~~

*Marca*
~~~
° GET marcas - Obter todas as marcas
° GET marcas/{id} - Obter uma marca por ID
° GET marcas/{id}/patrimônios - Obter todos os patrimônios de uma marca
° POST marcas - Inserir uma nova marca
° PUT marca/{id} - Alterar os dados de uma marca
° DELETE marca/{id} - Excluir uma marca
~~~

## Autenticação:

* **URL:** 
http://localhost:8080/login

* **Body:**
{
    "name" : "admin",
    "senha" : "password"
}

* **Authorization Type:** Bearer Token


## *</>* **by [Sérgio Alves da Fonseca Júnior](https://github.com/SAlvesJr)**