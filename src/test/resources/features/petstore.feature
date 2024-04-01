Feature: Login Functionality
  @createUser
  Scenario: Create user request submitted with valid request body
    Given Post User API
    When Provide "valid" request body
    Then Status code equals 200
    # Examples:
    #   |id     		|username    | firstName |  lastName  | email          | password  | phone   | userStatus
    #   | 102         	|sdutta102   |   s102    |  d102      | s102@gmail.com |  test102  | 444444  |   0
    #   | 103         	|sdutta103   |   s103    |  d103      | s103@gmail.com |  test103  | 555555  |   1


  @invalidRequest
  Scenario: Create user request submitted with invalid request body
    Given Post User API
    When Provide "invalid" request body
    Then Status code equals 500


  @retrieveUser
  Scenario: Retrieve User with the valid User Id
    Given GET User API
    When Retrieve user with username "sdutta"
    Then Status code equals 200

  @retrieveUser
  Scenario: Retrieve User with the Invalid User Id
    Given GET User API
    When Retrieve user with username "545erer"
    Then Get Status code equals 404