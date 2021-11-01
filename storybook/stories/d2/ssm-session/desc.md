

## Session While an  purely describes the structure of a State Machine, a session represents its instantiation. It defines which  is assigned to which role, and keeps track of every state transition it has undergone.  
  
<article>

*private* `Map<String, String>?` 

Private data attached to the session

</article>
<article>

*public* `String` 

Public data attached to the session

</article>
<article>

*roles* `Map<String, String>` 

Associate each role defined in the SSM to an agent

</article>
<article>

*session* `SessionName` 

Identifier of the session

</article>
<article>

*ssm* [`SsmName`](#ssmname) 

Identifier of the instantiated [SSM](#signing-state-machine)

</article>

