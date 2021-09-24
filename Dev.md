# Kotlin lib for blockchain-ssm

[Blockchain-ssm](https://github.com/civis-blockchain/blockchain-ssm) is a signing state machines chaincode developped for Hyperleger Fabric.  
This sdk has been tested with [fabric bclan configuration](https://github.com/civis-blockchain/blockchain-coop/tree/master/bclan) fabric network configuration

## Configuration example

```
ssm.rest.url=http://peer0.pr-bc1.civis-blockchain.org:9090
```

Ex: `ssm-rest-client/src/test/resources/ssm-client.properties`

## Usage example
``` 
sdk-core/src/test/java/io/civis/ssm/sdk/client/SsmClientItTest.java
```

### F2 Function

* `ssm-f2-start-session` -> Function to start ssm session

## SSM CouchDb
* counting
```
http://localhost:5984/sandbox_ssm/_design/indexTypeDoc/_view/indexType?group_level=1&key=[%22ssm%22]
{
  "rows":[{
    "key":["ssm"],
    "value":8
  }]
}
```

## Gradle

* Start local blockchain
```
docker-compose -f docker-compose-it.yaml up -d
```

* Test
```bash
make test 
```

* Test
```bash
make package 
```

* Publish
```bash
make push -e VERSION=0.0.0-SNAPSHOT
```

## SSM Chaincode API

### Operations

objects: ssm, session, agent, transaction, block

request types: query, invoke \
queries: "list {object}", "{object} id" \
invokes: "{object} {action}" + sign

#### Chaincode instanciation

* **init:** creates a new chaincode instance, with a list of admins.

```text
Command
-------
  "init", admins: <Agent>*

Result
------
  Chaincode is instantiated, DB stores a list of admins.
```


#### User management

* **register:** A new user is registered by an admin, Agent structure is signed with the admin private key.

```text
Command
-------
  "register", user:Agent, admin_name:string, signature:b64

Result
------
  User is stored in the DB.
```

* **grant:** A user is granted / revoked API rights by an admin, Grant structure is signed with the admin private key.

```text
Command
-------
  "grant", rights:Grant, admin_name:string, signature:b64

Result
------
  Grant is stored / updated in the DB.
```

#### SSM management

* **create:** A new SSM is registered by an admin, SSM structure is signed with the admin private key.

```text
Command
-------
  "create", ssm:SigningStateMachine, admin_name:string, signature:b64

Result
------
  SSM is stored in the DB.
```

* **start:** A new SSM session is initiated by an admin, SSM init state is signed with the admin private key.

```text
Command
-------
  "start", init:State, admin_name:string, signature:b64

Result
------
  State is stored in the DB.
```

* **limit:** Sets or removes a limit to the maximum iterations count of an existing SSM session. The uppdated session state is signed with the admin private key.

```text
Command
-------
  "limit", context:State, admin_name:string, signature:b64

Result
------
  Session state is updated. 
```

**Note**: In the `State` structure of the `context` parameter, `session` and `iteration` fields are mandatory, `limit` field is optional, other fields are read-only.


#### SSM execution

* **perform:** A user performs an action on a SSM session. The action to perform and the new session state are signed with the user private key.

```
Command
-------
  "perform", action:string, context:State, user_name:string, signature:b64

Result
------
  Session state is updated. 
```

**Note**: In the `State` structure of the `context` parameter, `session` and `iteration` fields are mandatory, `public` and `private` data fields are optional, other fields are read-only.

#### SSM queries

* **session:** Get a SSM session state.

```text
Query
-------
  "session", <session id>
 
  curl -x GET '$BASE_URL/?cmd=query&fcn=session&args=$SESSION_ID'

Result
------
  Returns current session State structure.
```

* **ssm:** Get a SigningStateMachine transitions list.

```text
Query
-------
  "ssm", <ssm name> 
  
  curl -x GET '$BASE_URL/?cmd=query&fcn=ssm&args=$SSM_NAME'

Result
------
  Returns corresponding SigningStateMachine structure.
```

* **user:** Get a user's public key.

```text
QueryComma
-------
  "user", <user name> 
  
  curl -x GET '$BASE_URL/?cmd=query&fcn=user&args=$USER_NAME'

Result
------
  Returns corresponding Agent structure.
```

* **credits:** Get a user's API credits aka grants.

```text
Query
-------
  "credits", <user name> 
  
  curl -x GET '$BASE_URL/?cmd=query&fcn=user&args=$USER_NAME'

Result
------
  Returns corresponding Grant structure.
```

* **admin:** Get an administrator's public key.

```text
Query
-------
  "admin", <admin name> 
  
  curl -x GET '$BASE_URL/?cmd=query&fcn=admin&args=$ADMIN_NAME'

Result
------
  Returns corresponding Agent structure.
```

* **list:** List all Sessions, SigningStateMachines, Users, Admins, Transactions or Blocks

```text
Query
-------
  "list", <session|ssm|user|admin|transaction|block> 
  
  curl -x GET '$BASE_URL/?cmd=query&fcn=list&args=session'
  curl -x GET '$BASE_URL/?cmd=query&fcn=list&args=ssm'
  curl -x GET '$BASE_URL/?cmd=query&fcn=list&args=user'
  curl -x GET '$BASE_URL/?cmd=query&fcn=list&args=admin'
  curl -x GET '$BASE_URL/?cmd=query&fcn=list&args=transaction'
  curl -x GET '$BASE_URL/?cmd=query&fcn=list&args=block'

Result
------
  Returns the list of corresponding identifiers.
```

* **log:** Log a session history

```text
Query
-------
  "log", <session id> 
  
  curl -x GET '$BASE_URL/?cmd=query&fcn=log&args=$SESSION_ID'

Result
------
  Returns the successive session states with the corresponding transaction ids.
```

* **transaction:** Get a transaction

```text
Query
-------
  "transaction", <transaction id> 
  
  curl -x GET '$BASE_URL/?cmd=query&fcn=transaction&args=TRANSACTION_ID'

Result
------
  Returns corresponding transaction.
```

* **block:** Get a block

```text
Query
-------
  "block", <block id> 
  
  curl -x GET '$BASE_URL/?cmd=query&fcn=block&args=$BLOCK_ID'

Result
------
  Returns corresponding block.
```

## SSM Chaincode F2

#### Chaincode Queries

* **SsmGetAdminQuery:** Get an admin

```text
Query
-------
    adminName

Result
------
  Returns corresponding admin
```

* **SsmListAdminQuery:** List all admins

```text
Query
-------

Result
------
  Returns all admins
```

* **SsmGetUserQuery:** Get a user

```text
Query
-------
    userName

Result
------
  Returns corresponding user
```

* **SsmListUserQuery:** List all users

```text
Query
-------

Result
------
  Returns all users
```

* **SsmGetQuery:** Get an SSM structure

```text
Query
-------
   ssmId

Result
------
  Returns corresponding SSM structure
```

* **SsmListSsmQuery:** List all SSM structures

```text
Query
-------

Result
------
  Returns all SSM structure
```

* **SsmGetSessionQuery:** Get a session

```
Query
-------
   sessionId

Result
------
  Returns corresponding session
```

* **SsmListSessionQuery:** List all sessions

```
Query
-------

Result
------
  Returns all sessions
```

* **SsmGetSessionLogsQuery:** Get the current and previous states of a session, along with their associated transaction ids

```
Query
-------
  sessionId

Result
------
  Returns all sessions logs
```

* **SsmGetTransactionQuery:** Get a transaction

```
Query
-------
  transactionId
 
Result
------
  Returns corresponding transaction
```

## SSM Couchdb F2

#### Couchdb Queries

* **CouchdbGetSsmListQuery:** List all SSM

```
Query
-------
    dbName

Result
------
  Returns all SSM in corresponding database
```


* **CouchdbGetSsmSessionListQuery:** List all sessions of a given SSM

```
Query
-------
    dbName, ssmId

Result
------
  Returns all sessions of corresponding SSM in given database
```

