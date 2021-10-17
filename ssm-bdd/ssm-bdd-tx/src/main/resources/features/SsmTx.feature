# List of Function
#



Feature:
  Background:
    Given configuration for ssm chaincode with uri "ssm:sandbox:ssm"
    Given admin agent "ssm-admin" and key "local/admin/ssm-admin"
    Given user agent "bob" and key "local/user/bob"
    Given user agent "sam" and key "local/user/sam"
    Given user agent "newsam"
    When I register a user "USER_NAME"
