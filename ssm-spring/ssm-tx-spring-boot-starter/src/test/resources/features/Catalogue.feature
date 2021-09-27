Feature:
  Scenario Outline: Inject couchdb function
    When I get a valid spring application context
    Then Instance of "<functionName>" is an injectable bean

    Examples:
      | functionName                      |
      | txSsmListQueryFunction            |
      | txSsmGetQueryFunction             |
      | txSsmSessionListQueryFunction     |
      | txSsmSessionGetQueryFunction      |
      | txSsmSessionLogListQueryFunction  |
      | txSsmSessionLogGetQueryFunction   |
