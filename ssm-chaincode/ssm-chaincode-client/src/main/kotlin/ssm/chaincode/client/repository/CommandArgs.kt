package ssm.chaincode.client.repository

import ssm.chaincode.client.invoke.command.InvokeArgs
import java.util.*

class CommandArgs {
    var cmd: String? = null
        private set
    var fcn: String? = null
        private set
    var args: List<String>? = null
        private set

    fun setCmd(cmd: String?): CommandArgs {
        this.cmd = cmd
        return this
    }

    fun setFcn(fcn: String?): CommandArgs {
        this.fcn = fcn
        return this
    }

    fun setArgs(args: List<String>?): CommandArgs {
        this.args = args
        return this
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o !is CommandArgs) return false
        val commandArgs = o
        return cmd == commandArgs.cmd &&
                fcn == commandArgs.fcn &&
                args == commandArgs.args
    }

    override fun hashCode(): Int {
        return Objects.hash(cmd, fcn, args)
    }

    override fun toString(): String {
        return "cmd: ${cmd}, fcn: ${fcn}, args: ${args}"
    }

    companion object {
        fun from(cmd: String?, invokeArgs: InvokeArgs, channelId: String?, chaincodeId: String?): CommandArgs {
            return CommandArgs()
                .setCmd(cmd)
                .setFcn(invokeArgs.fcn)
                .setArgs(invokeArgs.args)
        }
    }
}