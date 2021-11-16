Feature:
  Scenario: Verify admin exist
    When I get the list of admin
    Then Admin "ssm-admin" is in the list

  Scenario: Verify user exist
    When I get the list of user
    Then User "bob" is not in the list

#  Scenario: Register a user
#    Given An admin signer "ssm-admin"
#    When I register a user with name "cucumber-user"
#    And I get the list of user
#    Then User "cucumber-user" is in the list
#
#  Scenario: Create a ssm
#    Given An admin signer "ssm-admin"
#    And A ssm schema "cucumber-ssm"
#    When I create a ssm with name "cucumber-ssm"
#    And I get the list of ssm
#    Then Ssm "cucumber-ssm" is in the list
#
#  Scenario: Start a ssm session
#    Given An admin signer "ssm-admin"
#    And A ssm "cucumber-ssm"
#    When I start a session with "cucumber-session" for "cucumber-ssm"
#    Then A transaction must has been done for "cucumber-session"
#    And details of session must be
#      | current | iteration | origin | ssm          | sessionName      |
#      | 0       | 0         |        | cucumber-ssm | cucumber-session |
