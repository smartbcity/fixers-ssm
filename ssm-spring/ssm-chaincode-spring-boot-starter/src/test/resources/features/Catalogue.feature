Feature:
  Scenario Outline: Inject couchdb function
    When I get a valid spring application context
    Then Instance of "<functionName>" is an injectable bean

    Examples:
      | functionName                    |
      | ssmGetAdminFunction             |
      | ssmGetQueryFunction             |
      | ssmGetSessionLogsQueryFunction  |
      | ssmGetSessionQueryFunction      |
      | ssmGetTransactionQueryFunction  |
      | ssmGetUserFunction              |
      | ssmListAdminQueryFunction       |
      | ssmListSessionQueryFunction     |
      | ssmListSsmQueryFunction         |
      | ssmListUserQueryFunction        |
