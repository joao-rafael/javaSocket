# Java Sockets (Ler, caso haja dúvidas)
Fiz esse documento.md para que possas tirar dúvidas, depois servirá para apresentação do projeto e estará em Inglês(mas só depois da conclusão e atribuição da nota).

## detalhes sobre o Enunciado
Foi pedido pelo professor que o Chat suporte linha de comando:
> O servidor deve estar implementado numa classe chamada ChatServer e aceitar como argumento da linha de comando o número da porta TCP na qual ficará à escuta, por exemplo: *java ChatServer 8000*

A comunicação será toda centrada em linhas de texto, o servidor deverá aceitar comando de usuários, tais como */nick nome* e coisas do tipo, os comandos serão definidos por / mensagem, deve-se procurar por uma maneira de se implementar isto, depois.

para ver o enunciado: http://www.dcc.fc.up.pt/~rprior/1819/RC/trab/enunciado.html

### Estado:
  Até agora, o protótipo do ChatClient e do ChatServer estão feitos
  Ver documentação e comentários que inseri no código. 
  
### *TODOS*(afazeres):
  #### Client:
  * implementar o método run
  * mandar mensagem ao server
  * fazer o server suportar comandos
  * suporte a thread múltiplo
  
  #### Server:
  * suporte a comandos
  * interação com os clientes
  
### Aviso sobre funcionamento:
  Eu consegui fazer o eclipse correr o código, porém tive de modificar o argumento do método *main* da classe server
  "argv[]" para "args" tenho de perguntar ao professor se não há problema e se isso influencia na execução por linha de comando.
  
A parte de cima está em Inglês por ser um protótipo da marcação que irei usar quando o projeto estiver terminado, pois deixarei o repositório público por motivos de portfólio.
