Feature: Data manipulated API Reqres

  @listar-usuarios
  Scenario: Listar Usuários
    Given que acesso a API "<url>"
    When realizo uma request GET para "<endpoint>"
    Then eu valido que a resposta contém a lista de usuários
    Examples:
      | tag   | url                   | endpoint   |
      | @tag1 | https://reqres.in/api    | /users     |

  @listar-paginas
  Scenario: Listar usuários em páginas diferentes
    Given que acesso a API "<url>"
    When realizo uma request GET para "<endpoint>"
    Then eu valido a resposta da página "<page>" com a lista de usuários
    Examples:
      | tag   | url                   | endpoint             | page |
      | @tag2 | https://reqres.in/api    | /users?page=1        | 1    |
      | @tag3 | https://reqres.in/api    | /users?page=2        | 2    |
      | @tag4 | https://reqres.in/api    | /users?page=3        | 3    |
      | @tag5 | https://reqres.in/api    | /users?page=4        | 4    |
      | @tag6 | https://reqres.in/api    | /users?page=5        | 5    |

  @detalhes-usuario
  Scenario: Obter detalhes de um usuário existente
    Given que acesso a API "<url>"
    When realizo uma request GET para "<endpoint>"
    Then eu valido os dados do usuário específico
    Examples:
      | tag   | url                   | endpoint   |
      | @tag7 | https://reqres.in/api    | /users/2   |

  @usuario-inexistente
  Scenario: Obter detalhes de um usuário inexistente
    Given que acesso a API "<url>"
    When realizo uma request GET para "<endpoint>"
    Then eu valido que o erro retornado tem o status code "<status>" e a mensagem "<msg>"
    Examples:
      | tag   | url                   | endpoint         | status | msg                           |
      | @tag8 | https://reqres.in/api    | /users/9999      | 404    | "Usuário não encontrado"      |

  @criar-usuario-valido
  Scenario: Criar um usuário com dados válidos
    Given que acesso a API "<url>"
    When realizo uma request POST para "<endpoint>"
    Then eu valido que a criação foi bem-sucedida com status code "<status>" e os dados corretos
    Examples:
      | tag   | url                   | endpoint   | status |
      | @tag9 | https://reqres.in/api    | /users     | 201    |

  @criar-usuario-invalido
  Scenario Outline: Criar um usuário com dados inválidos
    Given que acesso a API "<url>"
    When realizo uma request POST para "<endpoint>"
    Then eu valido que o erro retornado tem status code "<status>" e a mensagem "<msg>"
    Examples:
      | tag   | url                   | endpoint | status | msg                                       | name            | job       |
      | @tag10| https://reqres.in/api    | /users   | 400    | O campo job é obrigatório                 | John Doe        |           |
      | @tag11| https://reqres.in/api    | /users   | 400    | O campo name não pode ser numérico        | 12345           | Developer |
      | @tag12| https://reqres.in/api    | /users   | 400    | O campo name excede o limite de caracteres| John Jacob Jingleheimer Schmidt | Developer |
      | @tag13| https://reqres.in/api    | /users   | 400    | O campo job não pode ser vazio            | John Doe        |           |

  @atualizar-usuario-valido
  Scenario: Atualizar um usuário com dados válidos
    Given que acesso a API "<url>"
    When realizo uma request PUT para "<endpoint>"
    Then eu valido que os dados do usuário foram atualizados com status code "<status>"
    Examples:
      | tag   | url                   | endpoint   | status |
      | @tag14| https://reqres.in/api    | /users/2   | 201    |

  @atualizar-usuario-faltando-dados
  Scenario: Atualizar um usuário com dados faltando
    Given que acesso a API "<url>"
    When realizo uma request PUT para "<endpoint>"
    Then eu valido que o erro retornado tem status code "<status>" e a mensagem "<msg>"
    Examples:
      | tag   | url                   | endpoint   | status | msg                                   |
      | @tag15| https://reqres.in/api    | /users/2   | 400    | "O campo 'job' é obrigatório"         |

  @excluir-usuario-existente
  Scenario: Excluir um usuário existente
    Given que acesso a API "<url>"
    When realizo uma request DELETE para "<endpoint>"
    Then eu valido que o status code é "<status>" e a resposta não contém conteúdo
    Examples:
      | tag   | url                   | endpoint     | status |
      | @tag16| https://reqres.in/api    | /users/2     | 204    |

  @excluir-usuario-inexistente
  Scenario: Excluir um usuário inexistente
    Given que acesso a API "<url>"
    When realizo uma request DELETE para "<endpoint>"
    Then eu valido que o erro retornado tem status code "<status>" e a mensagem "<msg>"
    Examples:
      | tag   | url                   | endpoint         | status | msg                           |
      | @tag17| https://reqres.in/api    | /users/9999      | 404    | "Usuário não encontrado"      |

  @login-valido
  Scenario: Testar login com credenciais válidas
    Given que acesso a API "<url>"
    When realizo uma request POST para "<endpoint>"
    Then eu valido que o status code é "<status>" e a resposta contém um token válido
    Examples:
      | tag   | url                   | endpoint   | status |
      | @tag18| https://reqres.in/api    | /login     | 200    |

  @login-sem-credenciais
  Scenario: Testar login sem fornecer credenciais
    Given que acesso a API "<url>"
    When realizo uma request POST para "<endpoint>"
    Then eu valido que o erro retornado tem status code "<status>" e a mensagem "<msg>"
    Examples:
      | tag   | url                   | endpoint   | status | msg                                  |
      | @tag19| https://reqres.in/api    | /login     | 400    | "Credenciais ausentes"              |

  @usuario-inexistente
  Scenario: Obter detalhes de um usuário inexistente
    Given que acesso a API "<url>"
    When realizo uma request GET para "<endpoint>"
    Then eu valido que o erro retornado tem status code "<status>" e a mensagem "<msg>"
    Examples:
      | tag   | url                   | endpoint       | status | msg                           |
      | @tag20| https://reqres.in/api    | /users/99999   | 404    | "Usuário não encontrado"      |

  @usuario-id-inexistente
  Scenario: Atualizar um usuário com ID inexistente
    Given que acesso a API "<url>"
    When realizo uma request PUT para "<endpoint>"
    Then eu valido que o erro retornado tem status code "<status>" e a mensagem "<msg>"
    Examples:
      | tag   | url                   | endpoint         | status | msg                           |
      | @tag21| https://reqres.in/api    | /users/99999     | 404    | "Usuário não encontrado"      |

  @criar-usuario-vazio
  Scenario: Criar um usuário com dados vazios
    Given que acesso a API "<url>"
    When realizo uma request POST para "<endpoint>"
    Then eu valido que o erro retornado tem status code "<status>" e a mensagem "<msg>"
    Examples:
      | tag   | url                   | endpoint     | status | msg                                        |
      | @tag22| https://reqres.in/api    | /users       | 400    | "Os campos 'name' e 'job' são obrigatórios"|

  @listar-usuarios-paginados
  Scenario: Listar vários usuários com paginação
    Given que acesso a API "<url>"
    When realizo uma request GET para "<endpoint>"
    Then eu valido que a lista de usuários foi retornada corretamente e paginada
    Examples:
      | tag   | url                   | endpoint               | status |
      | @tag23| https://reqres.in/api    | /users?page=1          | 200    |
      | @tag24| https://reqres.in/api    | /users?page=2          | 200    |

  @criar-multiplo-usuarios
  Scenario Outline: Criar múltiplos usuários
    Given que acesso a API "<url>"
    When realizo uma request POST para "<endpoint>"
    Then eu valido que o status code é "<status>" e os dados estão corretos
    Examples:
      | tag   | url                   | endpoint   | name    | job      | status |
      | @tag25| https://reqres.in/api    | /users     | Alice   | Developer| 201    |
      | @tag26| https://reqres.in/api    | /users     | Bob     | Designer | 201    |
      | @tag27| https://reqres.in/api    | /users     | Charlie | Tester   | 201    |

  @atualizar-usuario-faltando-job
  Scenario: Atualizar um usuário com dados faltando
    Given que acesso a API "<url>"
    When realizo uma request PUT para "<endpoint>"
    Then eu valido que o erro retornado tem status code "<status>" e a mensagem "<msg>"
    Examples:
      | tag   | url                   | endpoint     | status | msg                                   |
      | @tag28| https://reqres.in/api    | /users/2     | 400    | "O campo 'job' é obrigatório"         |
