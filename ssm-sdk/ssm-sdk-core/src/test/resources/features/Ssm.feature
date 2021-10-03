Feature:
  Scenario: Verify admin exist
    When I fetch the list of admin
    Then Admin with name "ssm-admin" must be in the list

  Scenario: Verify user exist
    When I fetch the list of user
    Then Agent with name "bob" must not be in the list

  Scenario: Register a user
    Given An admin signer with name "ssm-admin"
    When I register a user with name "cucumber-user"
    And I fetch the list of user
    Then Agent with name "cucumber-user" must be in the list

  Scenario: Create a ssm
    Given An admin signer with name "ssm-admin"
    And A ssm schema with name "cucumber-ssm"
    When I create a ssm with name "cucumber-ssm"
    And I fetch the list of ssm
    Then Ssm with name "cucumber-ssm" must be in the list

  Scenario: Start a ssm session
    Given An admin signer with name "ssm-admin"
    And A ssm with name "cucumber-ssm"
    When I start a session with "cucumber-session" for "cucumber-ssm"
    Then A transaction must has been done for "cucumber-session"
    And details of session must be
      | current | iteration | origin | ssm          | sessionName      |
      | 0       | 0         |        | cucumber-ssm | cucumber-session |
