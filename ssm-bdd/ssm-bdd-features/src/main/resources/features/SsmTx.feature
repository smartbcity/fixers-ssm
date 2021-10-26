Feature:
  Scenario: Verify admin exist
    When I get the list of admin
    Then Admin "ssm-admin" is in the list

  Scenario: Verify user exist
    When I get the list of user
    Then User "bob" is not in the list

  Scenario: Register a user
    Given An admin
    When I register a user "cucumber-user"
    And I get the list of user
    Then User "cucumber-user" is in the list

  Scenario: Create a ssm
    Given An admin
    When I create a ssm "loop-ssm" with transitions
      | from | to | role   | action |
      | 0    | 0  | Looper | Loop   |
    And I get the list of ssm
    Then Ssm "loop-ssm" is in the list

  Scenario: Start a ssm session
    Given An admin
    And A ssm "crud-ssm" with transitions
      | from | to | role   | action |
      | 0    | 1  | Issuer | Create |
      | 1    | 1  | Issuer | Update |
      | 1    | 2  | Issuer | Delete |
    When I start a session "crud-ssm-session" for "crud-ssm"
    Then The session "crud-ssm-session" have logs
      | current | iteration | origin | ssm      | sessionName      |
      | 0       | 0         |        | crud-ssm | crud-ssm-session |