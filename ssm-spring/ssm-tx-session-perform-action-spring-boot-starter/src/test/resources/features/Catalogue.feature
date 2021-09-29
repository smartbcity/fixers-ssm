Feature:
  Scenario Outline: Inject couchdb function
    When I get a valid spring application context
    Then Instance of "<functionName>" is an injectable bean

    Examples:
      | functionName                      |
      | ssmSessionPerformActionFunction   |

  Scenario Outline: No inject couchdb function when config is not provided.
    When I get an empty spring application context
    Then Instance of "<functionName>" is not injectable bean

    Examples:
      | functionName                      |
      | ssmSessionPerformActionFunction   |
