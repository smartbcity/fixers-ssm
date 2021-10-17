Feature:
  Background:
    When I create a ssm "SSM_NAME" with configuration file "ssm.json"
    When I start a session "SESSION_NAME" for "SSM_NAME"
    When I perform action "ACTION" for "SESSION_NAME"
