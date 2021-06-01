package x2.api.ssm.api.model

import x2.api.ssm.model.SsmBase

fun ssm.dsl.Ssm.toSsm(): SsmBase {
    return SsmBase(
        name = this.name,
        version = "Not implemented",
        transitions = this.transitions.map(ssm.dsl.SsmTransition::toTransition)
    )
}