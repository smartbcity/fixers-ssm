Feature:

  Scenario Outline: Inject couchdb function
    Given The application parameters
      | ssm.data.chaincode.url       | http://localhost:9090 |
      | ssm.data.couchdb.url         | http://localhost:5000 |
      | ssm.data.couchdb.username    | couchdb               |
      | ssm.data.couchdb.password    | couchdb               |
      | ssm.data.couchdb.serviceName | ssm-couchdb-test      |
      | ssm.data.channelId           | chaincode             |
      | ssm.data.chaincodeId         | ssm                   |
      | ssm.data.ssmVersion          | 1.0.0                 |
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

  Scenario Outline: No inject couchdb function when config is not provided.
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
