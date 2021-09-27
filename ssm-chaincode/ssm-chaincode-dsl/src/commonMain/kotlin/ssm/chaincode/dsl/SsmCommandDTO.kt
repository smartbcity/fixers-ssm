package ssm.chaincode.dsl

import f2.dsl.cqrs.Command

expect interface SsmCommandDTO : Command {
	/**
	 * Authentication token
	 * @example "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6ImhvTnZUSXpDZ3pCa2lkazNaQlZ1NW5TekJrYTQtQkFORGpVNF82TEpzSFUifQ.eyJ2YWx1ZSI6ImJsYmxibCIsImZpc3J0bmFtZSI6IkpvaG4iLCJsYXN0bmFtZSI6IkRldWYifQ.df8lUW26IQclW2T8uP-CQllithvr1rZAF19WFh3enmA6P-ZPHJZa6WVapzbkISxu2PsnLUBkoLFEuJQYozbBfLtpysUpGNv8qSuegDqjsysbWB8x6jZiZoozSoe-5KugKoI8Y3yqQmzFZ-MtgdK0rq-_ojsWVYqwQxxmAknQuow"
	 */
	val bearerToken: String?
}
