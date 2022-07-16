FLUXO DA APLICAÇÃO:
-Ao conectar com o servidor, deve haver duas opções: Fazer o login ou realizar um pedido.
-Funcionário Adm entra com o login e recebe as funcionalidades como funcionário Adm: Confirmar e recusar pedidos de clientes e editar cardápio do restaurante.
-Funcinário passivo entra com o login e recebe as funcionalides como funcinário passivo: Visualizar pedidos.
-Cliente recebe as funcionalidades de cliente: Realizar, visualizar.

AÇÕES:
-Funcionário Adm:
	-Confirmar e recusar pedido de um cliente;
	-Haverá uma opção para digitar o id do pedido confirmando ou recusando. Esses id do pedido feito pelo cliente são viseis pela tela de Adm Passivo.
	-Finalizar o pedido pelo id. O servidor então deve enviar uma mensagem avisando o cliente. Alguns segundos depois finalizar o socket (conexão) entre eles.

-Funcionário Passivo pode visualizar os pedidos feitos pelos clientes, servidor enviar para o CMD do funcionário, sempre atualizando a tela do funcionário. 
(talvez precise de um timer para ficar fazendo request para o servidor e listar os pedidos ativos)

-Cliente: 
	-Realiza: porderá visualizar o cardápio atraves de uma opção, e fazer seu pedido. Depois de confirmado e enviado para o servidor, não poderá ser mais editado.
	-Esse pedido irá para um tela de espera até um funcionário ADM realizar uma ação.
	-Quando funcionário Adm realizar uma ação, cliente receberá uma "mensagem" de aprovado ou reprovado o pedido. 
	-Quando receber mensagem de finalização do pedido, sua conexão será finalida.


Services -  
  ADM - 
    ConfirmOrderService.java
    RefuseOrderService.java
    FinishOrderService.java
    CreateMealService.java
    DeleteMealService.java

  PASSIVO - 
    ListMealService.java

  CLIENTE -
    ListMealService.java
    RequestOrderService.java

Services: Ações listadas acima
Repositories: São acessadas pelos services e acessam o CSV_DB
Entities: Responsáveis por guardar o objeto vindo 

Forma de identificação, conectou? Informa tipo de client, client identifica (atraves de uma variavel booleana) e guarda essa informação, criar um id, e retorna esse id para o cliente, que também identifica e guarda essa informação (atraves de uma variavel booleana)

padrão para chamadas "api" -> ação/dados
ex: criar/asd,asd,asd