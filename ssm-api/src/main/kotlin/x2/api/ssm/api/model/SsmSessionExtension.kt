package x2.api.ssm.api.model

import ssm.dsl.SsmSessionState
import x2.api.ssm.model.SsmSessionBase
import x2.api.ssm.model.SsmSessionStepBase

fun SsmSessionState.toSession(): SsmSessionBase {
    return SsmSessionBase(
        id = this.session,
        channel = "Not implemented",
        creationDate = 0L,
        currentStep = SsmSessionStepBase(
            id = this.current,
            date = 0,
            user = this.origin?.role ?: ""
        )
    )
}
