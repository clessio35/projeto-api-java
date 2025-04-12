Feature: Data manipulated API Reqres

  @listar-usuarios @all
  Scenario: Listar Usuários
    Given que acesso a API "<url>"
    When realizo uma request GET para "<endpoint>"
    Then eu valido que a resposta contém a lista de usuários
    Examples:
      | tag   | url                   | endpoint   |
      | @tag1 | https://reqres.in/api | /users     |

  @listar-paginas @all
  Scenario: Listar usuários em páginas diferentes
    Given que acesso a API "<url>"
    When realizo uma request GET para "<endpoint>"
    Then eu valido a resposta da página "<page>" com a lista de usuários
    Examples:
      | tag  		| url                   | endpoint             | page |
      | @tag2 	| https://reqres.in/api | /users?page=1        | 1    |
      | @tag3 	| https://reqres.in/api | /users?page=2        | 2    |
      | @tag4 	| https://reqres.in/api | /users?page=3        | 3    |
      | @tag5 	| https://reqres.in/api | /users?page=4        | 4    |
      | @tag6 	| https://reqres.in/api | /users?page=5        | 5    |

	@detalhes-usuario @all
	Scenario: Obter detalhes de um usuário existente
	  Given que acesso a API "<url>"
	  When realizo uma request GET para "<endpoint>"
	  Then eu valido os dados do usuário específico "<user>"
	  Examples:
	    | tag   | url                   | endpoint   | user |
	    | @tag7 | https://reqres.in/api | /users/2   | 2    |
	    | @tag8 | https://reqres.in/api | /users/3   | 3    |
	    | @tag9 | https://reqres.in/api | /users/4   | 4    |
	    | @tag10| https://reqres.in/api | /users/5   | 5    |
	    | @tag11| https://reqres.in/api | /users/6   | 6    |
	    | @tag12| https://reqres.in/api | /users/7   | 7    |
	    | @tag13| https://reqres.in/api | /users/8   | 8    |
	    | @tag14| https://reqres.in/api | /users/9   | 9    |
	    | @tag15| https://reqres.in/api | /users/10  | 10   |
	    | @tag16| https://reqres.in/api | /users/11  | 11   |
	
	@usuario-inexistente @all
	Scenario: Obter detalhes de um usuário inexistente
	  Given que acesso a API "<url>"
	  When realizo uma request GET para "<endpoint>"
	  Then eu valido que o erro retornado tem o status code "<status>"
	  Examples:
	    | tag   | url                   | endpoint         | status | msg |
	    | @tag17| https://reqres.in/api | /users/9999      | 404    |     |
	
	@criar-usuario-valido @all
	Scenario: Criar um usuário com dados válidos
	  Given que acesso a API "<url>"
	  When realizo uma request POST para "<endpoint>"
	  Then eu valido que a criação foi bem-sucedida com status code "<status>" e os dados corretos
	  Examples:
	    | tag   | url                   | endpoint   | status |
	    | @tag18| https://reqres.in/api | /users     | 201    |
	
	@criar-usuario-invalido @all
	Scenario Outline: Criar um usuário com dados inválidos
	  Given que acesso a API "<url>"
	  When realizo uma request POST com dados incorretos "<endpoint>" "<name>" "<job>"
	  Then eu valido resultado retornado com status code "<status>" "<name>" "<job>"
	  Examples:
	    | tag   | url                   | endpoint | status |  name           							  | job       |
	    | @tag19| https://reqres.in/api | /users   | 201    |  John Doe     							    |           |
	    | @tag20| https://reqres.in/api | /users   | 201    |  12345       								    | Developer |
	    | @tag21| https://reqres.in/api | /users   | 201    | John Jacob Jingleheimer Schmidt | Developer |
	    | @tag22| https://reqres.in/api | /users   | 201    |  John Doe        								|           |
	
	@atualizar-usuario-valido @all
	Scenario: Atualizar um usuário com dados válidos
	  Given que acesso a API "<url>"
	  When realizo uma request PUT para "<endpoint>"
	  Then eu valido que os dados do usuário foram atualizados
	  Examples:
	    | tag   | url                   | endpoint   |
	    | @tag23| https://reqres.in/api | /users/2   |
	
	@atualizar-usuario-faltando-dados @all
	Scenario: Atualizar um usuário com dados faltando
	  Given que acesso a API "<url>"
	  When realizo uma request PUT para "<endpoint>" faltando informacoes
	  Then eu valido a alteracao com ausencia de informacao
	  Examples:
	    | tag   | url                   | endpoint   | 
	    | @tag24| https://reqres.in/api | /users/2   | 
	
	@excluir-usuario-existente @all
	Scenario: Excluir um usuário existente
	  Given que acesso a API "<url>"
	  When realizo uma request DELETE para "<endpoint>"
	  Then eu valido que o status code é "<status>" e a resposta não contém conteúdo
	  Examples:
	    | tag   | url                   | endpoint     | status |
	    | @tag25| https://reqres.in/api | /users/2     | 204    |
	
	@excluir-usuario-inexistente @all
	Scenario: Excluir um usuário inexistente
	  Given que acesso a API "<url>"
	  When realizo uma request DELETE para "<endpoint>"
	  Then eu valido que o status code é "<status>" e a resposta não contém conteúdo
	  Examples:
	    | tag   | url                  		 | endpoint         | status | 
	    | @tag26| https://reqres.in/api    | /users/9999      | 204    |
	
	@login-valido @all
	Scenario: Testar login com credenciais válidas
	  Given que acesso a API "<url>"
	  When realizo uma request POST com as informacoes de login para "<endpoint>"
	  Then eu valido que a resposta contém um token
	  Examples:
	    | tag   | url                  		 | endpoint   |
	    | @tag27| https://reqres.in/api    | /login     |
	
	@login-sem-credenciais @all
	Scenario: Testar login sem fornecer credenciais
	  Given que acesso a API "<url>"
	  When realizo uma request POST para "<endpoint>"
	  Then eu valido que erro retornado e a mensagem "<status>" "<msg>"
	  Examples:
	    | tag   | url                   | endpoint   | status | msg                         |
	    | @tag28| https://reqres.in/api | /login     | 400    | user not found              |
	
	@usuario-inexistente @all
	Scenario: Obter detalhes de um usuário inexistente
	  Given que acesso a API "<url>"
	  When realizo uma request GET para "<endpoint>"
	  Then eu valido que o erro retornado tem o status code "<status>"
	  Examples:
	    | tag   | url                   | endpoint       | status |
	    | @tag29| https://reqres.in/api | /users/99999   | 404    | 
	
	
	@criar-usuario-vazio @all
	Scenario: Criar um usuário com dados vazios
	  Given que acesso a API "<url>"
	  When realizo uma request POST sem dados para "<endpoint>"
	  Then eu valido o response gerado
	  Examples:
	    | tag   | url                  		 | endpoint     | 
	    | @tag31| https://reqres.in/api    | /users       |
	
	@listar-usuarios-paginados @all
	Scenario: Listar vários usuários com paginação
	  Given que acesso a API "<url>"
	  When realizo uma request GET para "<endpoint>"
	  Then eu valido a resposta da página "<page>" com a lista de usuários
	  Examples:
	    | tag   | url                  	   | endpoint               | status | page |
	    | @tag32| https://reqres.in/api    | /users?page=1          | 200    |1			|
	    | @tag33| https://reqres.in/api    | /users?page=2          | 200    |2			|	
	
	@criar-multiplo-usuarios @all
	Scenario Outline: Criar múltiplos usuários
	  Given que acesso a API "<url>"
	  When realizo uma request POST com dados incorretos "<endpoint>" "<name>" "<job>"
	  Then eu valido resultado retornado com status code "<status>" "<name>" "<job>"
	  Examples:
	    | tag   | url                   	 | endpoint   | name    | job      | status |
	    | @tag34| https://reqres.in/api    | /users     | Alice   | Developer| 201    |
	    | @tag35| https://reqres.in/api    | /users     | Bob     | Designer | 201    |
	    | @tag36| https://reqres.in/api    | /users     | Charlie | Tester   | 201    |
	
	@atualizar-usuario-faltando-job @all
	Scenario: Atualizar um usuário com dados faltando
	  Given que acesso a API "<url>"
	  When realizo uma request PUT para "<endpoint>" faltando informacoes
	  Then eu valido a alteracao com ausencia de informacao
	  Examples:
	    | tag   | url                   | endpoint     | 
	    | @tag37| https://reqres.in/api | /users/2     |
