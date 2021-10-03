Feature:
  Scenario Outline: Inject couchdb function
    Given The application parameters
      | ssm.chaincode.url | http://localhost:9090 |
    When I build a valid spring application context
    Then Instance of "<functionName>" is an injectable bean

    Examples:
      | functionName        |
      | ssmCreateFunction   |

  Scenario Outline: No inject couchdb function when config is not provided.
    When I get an empty spring application context
    Then Instance of "<functionName>" is not injectable bean

    Examples:
      | functionName        |
      | ssmCreateFunction   |
