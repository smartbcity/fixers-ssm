Feature:

  Background:
    Given The admin "ADMIN_NAME" with key "local/admin/ssm-admin"
    Given The user "USER_NAME" with key "local/user/bob"
    Given A new user "USER_NAME"

    Given An admin signer "ADMIN_NAME"
    Given A ssm "SSM_NAME" with configuration file "ssm.json"
    Given A ssm "SSM_NAME" with transitions
      | from | to | role   | action |
      | 0    | 1  | Issuer | Create |
      | 1    | 1  | Issuer | Update |
      | 1    | 2  | Issuer | Delete |
    Given A session "SESSION_NAME" started for "SSM_NAME"

    When I register a user "USER_NAME"
    When I create a ssm "SSM_NAME" with configuration file "ssm.json"
    When I create a ssm "SSM_NAME" with transitions
      | from | to | role        | action |
      | 0    | 1  | ROLE_NAME_1 | Create |
      | 1    | 1  | ROLE_NAME_2 | Update |
      | 1    | 2  | ROLE_NAME_1 | Delete |
    When I start a session "SESSION_NAME" for "SSM_NAME"
      | user        | role        |
      | USER_NAME   | ROLE_NAME_1 |
      | USER_NAME_2 | ROLE_NAME_2 |
      | USER_NAME_3 | ROLE_NAME_2 |
    When I perform action "ACTION_NAME" for "SESSION_NAME" with "USER_NAME"



## Use higher function for those
#Feature:
#  Scenario: Verify admin exist
#    When I fetch the list of admin
#    Then Admin with name "ssm-admin" must be in the list
#
#  Scenario: Verify user exist
#    When I fetch the list of user
#    Then Agent with name "bob" must not be in the list
#
#  Scenario: Register a user
#    Given An admin signer "ssm-admin"
#    When I register a user with name "cucumber-user"
#    And I fetch the list of user
#    Then Agent with name "cucumber-user" must be in the list
#
#  Scenario: Create a ssm
#    Given An admin signer "ssm-admin"
#    And A ssm schema with name "cucumber-ssm"
#    When I create a ssm with name "cucumber-ssm"
#    And I fetch the list of ssm
#    Then Ssm with name "cucumber-ssm" must be in the list
#
#  Scenario: Start a ssm session
#    Given An admin signer "ssm-admin"
#    And A ssm with name "cucumber-ssm"
#    When I start a session with "cucumber-session" for "cucumber-ssm"
#    Then A transaction must has been done for "cucumber-session"
#    And details of session must be
#      | current | iteration | origin | ssm          | sessionName      |
#      | 0       | 0         |        | cucumber-ssm | cucumber-session |
