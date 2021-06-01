package x2.api.ssm.api.model

import x2.api.ssm.model.SsmTransitionBase

fun ssm.dsl.SsmTransition.toTransition(): SsmTransitionBase {
    return SsmTransitionBase(
        from = this.from,
        to = this.to,
        role = this.role,
        action = this.action,
    )
}