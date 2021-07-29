package ssm.chaincode.dsl

import f2.dsl.cqrs.Command

expect interface SsmCommandDTO: Command {
    /**
     * URL of the peer to contact
     * @example "http://peer.sandbox.smartb.network:9000"
     */
    val baseUrl: String

    /**
     * Identifier of the channel on which the command or query is executed
     * @example "channel-smartb"
     */
    val channelId: String?

    /**
     * Identifier of the chaincode on which the command or query is executed
     * @example "ssm-smartb"
     */
    val chaincodeId: String?

    /**
     * Authentication token
     * @example "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6ImhvTnZUSXpDZ3pCa2lkazNaQlZ1NW5TekJrYTQtQkFORGpVNF82TEpzSFUifQ.eyJ2YWx1ZSI6ImJsYmxibCIsImZpc3J0bmFtZSI6IkpvaG4iLCJsYXN0bmFtZSI6IkRldWYifQ.df8lUW26IQclW2T8uP-CQllithvr1rZAF19WFh3enmA6P-ZPHJZa6WVapzbkISxu2PsnLUBkoLFEuJQYozbBfLtpysUpGNv8qSuegDqjsysbWB8x6jZiZoozSoe-5KugKoI8Y3yqQmzFZ-MtgdK0rq-_ojsWVYqwQxxmAknQuow"
     */
    val bearerToken: String?
}
