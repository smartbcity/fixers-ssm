Feature:

  Scenario Outline: Inject data ssm function
    Given The application parameters
      | ssm.data.chaincode.url          | http://localhost:9090  |
      | ssm.data.couchdb.url            | http://localhost:5000  |
      | ssm.data.couchdb.username       | couchdb                |
      | ssm.data.couchdb.password       | couchdb                |
      | ssm.data.couchdb.serviceName    | ssm-couchdb-test       |
    When I build a valid spring application context
    Then Instance of "<functionName>" is an injectable bean

    Examples:
      | functionName                       |
      | dataSsmListQueryFunction           |
      | dataSsmGetQueryFunction            |
      | dataSsmSessionListQueryFunction    |
      | dataSsmSessionGetQueryFunction     |
      | dataSsmSessionLogListQueryFunction |
      | dataSsmSessionLogGetQueryFunction  |

  Scenario Outline: No inject data ssm function when config is not provided.
    When I get an empty spring application context
    Then Instance of "<functionName>" is not injectable bean

    Examples:
      | functionName                       |
      | dataSsmListQueryFunction           |
      | dataSsmGetQueryFunction            |
      | dataSsmSessionListQueryFunction    |
      | dataSsmSessionGetQueryFunction     |
      | dataSsmSessionLogListQueryFunction |
      | dataSsmSessionLogGetQueryFunction  |
