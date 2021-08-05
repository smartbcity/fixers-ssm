

## SsmAgent  


An Agent is a user able to interact with an SSM it has been registered for. It holds a pair of public/private keys which will be used to sign transactions.



There are two kind of agents:

 - **Users** are the agents who can trigger a transition in an SSM. In effect, they are the participants in the smart contract represented by the SSM.
 - **Admins** have the rights to add new users and state machines. They are declared only once, when the chaincode is instantiated.
  
<article>

*name* `String` 

Identifier of the agent

</article>
<article>

*pub* `ByteArray` 

Public key used when signing transactions

</article>

