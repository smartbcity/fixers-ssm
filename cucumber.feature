# chaincode
* fun ssmGetAdminFunction(config: SsmChaincodeConfig): SsmGetAdminFunction
* fun ssmGetQueryFunction(config: SsmChaincodeConfig): SsmGetQueryFunction
* fun ssmGetSessionLogsQueryFunction(config: SsmChaincodeConfig): SsmGetSessionLogsQueryFunction
* fun ssmGetSessionQueryFunction(config: SsmChaincodeConfig): SsmGetSessionQueryFunction
* fun ssmGetTransactionQueryFunction(config: SsmChaincodeConfig): SsmGetTransactionQueryFunction
* fun ssmGetUserFunction(config: SsmChaincodeConfig): SsmGetUserFunction
* fun ssmListAdminQueryFunction(config: SsmChaincodeConfig): SsmListAdminQueryFunction
* fun ssmListSessionQueryFunction(config: SsmChaincodeConfig): SsmListSessionQueryFunction
* fun ssmListSsmQueryFunction(config: SsmChaincodeConfig): SsmListSsmQueryFunction
* fun ssmListUserQueryFunction(config: SsmChaincodeConfig): SsmListUserQueryFunction

# couchdb
* fun couchDbDatabaseGetChangesQueryFunction(config: SsmCouchdbConfig): CouchDbDatabaseGetChangesQueryFunction
* fun couchdbDatabaseListQueryFunction(config: SsmCouchdbConfig): CouchdbDatabaseListQueryFunction
* fun couchDbDatabaseGetQueryFunction(config: SsmCouchdbConfig): CouchdbDatabaseGetQueryFunction
* fun couchdbSsmGetQueryFunction(config: SsmCouchdbConfig): CouchdbSsmGetQueryFunction
* fun couchDbSsmListQueryFunction(config: SsmCouchdbConfig): CouchdbSsmListQueryFunction
* fun couchDbSsmSessionStateListQueryFunction(config: SsmCouchdbConfig): CouchdbSsmSessionStateListQueryFunction

# SDK
* fun registerUser(agent: SsmAgent): SsmCmd
* fun create(ssm: Ssm): SsmCmd
* fun start(session: SsmSession): SsmCmd
* fun perform(action: String, context: SsmContext): SsmCmd

* fun sign(command: SsmCmd, signer: Signer): SsmCmdSigned
* fun send(ssmCommandSigned: SsmCmdSigned): InvokeReturn?

* suspend fun listAdmins(): List<SsmAgentName>
* suspend fun getAdmin(username: SsmAgentName): SsmAgent?
* suspend fun listAgent(): List<SsmAgentName>
* suspend fun getAgent(agentName: SsmAgentName): SsmAgent?
* suspend fun listSsm(): List<SsmName>
* suspend fun getSsm(name: SsmName): Ssm?
* suspend fun listSession(): List<String>
* suspend fun getSession(sessionName: SessionName): SsmSessionState?
* suspend fun log(sessionName: SessionName): List<SsmSessionStateLog>

* suspend fun getTransaction(txId: TransactionId): Transaction?
* suspend fun getBlock(blockId: BlockId): Block?

# TX
* fun ssmTxSessionPerformActionFunction(config: SsmChaincodeConfig): SsmTxSessionPerformActionFunction

* fun ssmTxCreateFunction(config: SsmChaincodeConfig): SsmTxCreateFunction
* fun ssmTxInitializeFunction(config: SsmChaincodeConfig): SsmTxInitializeFunction
* fun ssmTxSessionStartFunction(config: SsmChaincodeConfig): SsmTxSessionStartFunction

* fun ssmTxUserGrantFunction(config: SsmChaincodeConfig): SsmTxUserGrantFunction
* fun ssmTxUserRegisterFunction(config: SsmChaincodeConfig): SsmTxUserRegisterFunction

# Feature:
    Given configuration for ssm chaincode with uri "ssm:sandbox:ssm" # TX ???? FOR WHAT

# TX; SDK
    Given An admin                                                             # TX; SDK
    Given The admin "ADMIN_NAME"                                                             # TX; SDK
    Given The admin "ADMIN_NAME" with key "local/admin/ssm-admin"                            # TX; SDK
    Given The user "USER_NAME" with key "local/user/bob"                                     # TX; SDK
    Given A new user "USER_NAME"                                                             # TX; SDK

    Given A ssm "SSM_NAME" with configuration file "ssm.json"                                # TX; SDK
    Given A ssm "SSM_NAME" with transitions                                                  # TX; SDK
      | from | to | role   | action |
      | 0    | 1  | Issuer | Create |
      | 1    | 1  | Issuer | Update |
      | 1    | 2  | Issuer | Delete |
    Given A session "SESSION_NAME" started for "SSM_NAME"                                    # TX; SDK

    When I register a user "USER_NAME"                                                       # TX; SDK
    When I create a ssm "SSM_NAME" with configuration file "ssm.json"                        # TX; SDK
    When I create a ssm "SSM_NAME" with transitions                                          # TX; SDK
      | from | to | role        | action |
      | 0    | 1  | ROLE_NAME_1 | Create |
      | 1    | 1  | ROLE_NAME_2 | Update |
      | 1    | 2  | ROLE_NAME_1 | Delete |
    When I start a session "SESSION_NAME" for "SSM_NAME"                                     # TX; SDK
      | user        | role        |
      | USER_NAME   | ROLE_NAME_1 |
      | USER_NAME_2 | ROLE_NAME_2 |
      | USER_NAME_3 | ROLE_NAME_2 |
    When I perform action "ACTION_NAME"["ITERATION_NUMBER"] for "SESSION_NAME" with "USER_NAME"    # TX; SDK

# chaincode;couchdb; SDK
    When I get the list of user                                                              # data;chaincode;couchdb; SDK
    When I get the list of admin                                                             # data;chaincode;couchdb; SDK
    When I get the list of ssm                                                               # data;chaincode;couchdb; SDK
    When I get the list of session                                                           # data;chaincode;couchdb; SDK

    Then Admin "ADMIN_NAME" is in the list                                                   # data;chaincode;couchdb; SDK
    Then User "USER_NAME" is in the list                                                     # data;chaincode;couchdb; SDK
    Then Ssm "SSM_NAME" is in the list                                                       # data;chaincode;couchdb; SDK
    Then Session "SESSION_NAME" is in the list                                               # data;chaincode;couchdb; SDK
    Then Session "SESSION_NAME" have current state origin "1" current "2" iteration "3"      # data;chaincode;couchdb; SDK

    Then Admin "ADMIN_NAME" is not in the list                                               # data;chaincode;couchdb; SDK
    Then User "USER_NAME" is not in the list                                                 # data;chaincode;couchdb; SDK
    Then Ssm "SSM_NAME" is not in the list                                                   # data;chaincode;couchdb; SDK

    Then The action "ACTION_NAME" has been performed for session "SESSION_NAME"         # data;chaincode;couchdb; SDK
    Then The session "SESSION_NAME" have log with "number" entries                      # data;chaincode;couchdb; SDK
    Then The session "SESSION_NAME" have logs                                           # data;chaincode;couchdb; SDK
      | current | iteration | origin | ssm          | sessionName      |
      | 0       | 0         |        | cucumber-ssm | cucumber-session |


# chaincode

# couchdb
# fun couchDbDatabaseGetChangesQueryFunction(config: SsmCouchdbConfig): CouchDbDatabaseGetChangesQueryFunction
# fun couchdbDatabaseListQueryFunction(config: SsmCouchdbConfig): CouchdbDatabaseListQueryFunction
# fun couchDbDatabaseGetQueryFunction(config: SsmCouchdbConfig): CouchdbDatabaseGetQueryFunction

# SDK
# fun sign(command: SsmCmd, signer: Signer): SsmCmdSigned
# fun send(ssmCommandSigned: SsmCmdSigned): InvokeReturn?

# fun getTransaction(txId: TransactionId): Transaction?
# fun getBlock(blockId: BlockId): Block?

# TX
# fun ssmTxUserGrantFunction(config: SsmChaincodeConfig): SsmTxUserGrantFunction
# fun ssmTxInitializeFunction(config: SsmChaincodeConfig): SsmTxInitializeFunction
