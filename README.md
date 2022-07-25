# Client-Server application with Sockets to a restaurant

Projeto realizado para compreensão acerca de Sockets. Basicamente, existem quatro atores para utilização: Usuário Admin, Usuário Passivo, Cliente e Servidor.

* Ações do Usuário Admin
    * Criar|Listar|Remover|Atualizar items do cardápio
    * Recusar|Confirmar|Finalizar um pedido

* Ações do Usuário Passivo
    * Listar os pedidos

* Ações do cliente
    * Listar items do cardápio
    * Realizar um pedido

* Servidor
    * Receber requisições
    * Retornar respostas

## Description

Todo o código foi escrito em java. Houve a tentativa de utilizar as melhores práticas de clean code e arquitetura, mas há bastante coisa a melhorar.

## Getting Started

Primeiramente, verifique se a porta 59091 está sendo utilizada pelo SO, caso esteja, utilize outra porta e substitua no arquivo Client.java.

### Dependencies

Versão do java utilizado:
```
openjdk 11.0.12 2021-07-20
OpenJDK Runtime Environment 18.9 (build 11.0.12+7)
OpenJDK 64-Bit Server VM 18.9 (build 11.0.12+7, mixed mode)
```

O projeto foi desenvolvido no OS Linux, portanto algumas configurações podem mudar caso o projeto seja rodado no Windows.

### Installing

O projeto pode ser clonando em qualquer pasta do sistema. Basta que essa pasta tenha acesso à variável global java do SO.

## Executing program

Caso haja algum problema para rodar o código pelo terminal, basta rodá-lo pelo VSCode.

* Você deve baixar o VSCode.
* Você deve baixar a extensão Extension Pack for Java para o VSCode. 
* Você deve ir até a classe Server.java e clicar em RUN para rodar o servidor.
* Você deve ir até a classe Client.java e clicar em RUN para rodar o cliente.

## Authors

Cristian Silva
[@cristianslv](https://github.com/cristianslv)

Gabriel Duessmann
[@gabrielduessmann](https://github.com/gabrielduessmann)