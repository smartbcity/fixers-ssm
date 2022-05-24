

## SsmSessionState



The Session State represents a snapshot of a session on a given state machine. It holds the current state index, public and private data relevant to the SSM session. The iteration is incremented at every transition. The originating transition allows to track the session history in the ledger.





  
<article>

***current*** `Int` 

Current state identifier

</article>
<article>

***iteration*** `Int` 

Number of iterations the session has undergone before attaining the current state

</article>
<article>

***origin*** [`SsmTransition?`](#ssmtransition) 

Transition that lead to the current state

</article>
<article>

***private*** `Map<String, String>?` 

Private data attached to the session

</article>
<article>

***public*** `Any?` 

Public data attached to the session

</article>
<article>

***roles*** `Map<String, String>?` 

Associate each role defined in the SSM to an agent

</article>
<article>

***session*** `SessionName` 

Identifier of the session

</article>
<article>

***ssm*** [`SsmName?`](#ssmname) 

Identifier of the instantiated [SSM](#signing-state-machine)

</article>

