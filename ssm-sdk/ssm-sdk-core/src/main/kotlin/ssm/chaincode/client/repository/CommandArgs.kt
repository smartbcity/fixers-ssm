package ssm.chaincode.client.repository

import java.util.Objects
import ssm.chaincode.client.model.InvokeArgs

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

	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (other !is CommandArgs) return false
		val commandArgs = other
		return cmd == commandArgs.cmd &&
				fcn == commandArgs.fcn &&
				args == commandArgs.args
	}

	override fun hashCode(): Int {
		return Objects.hash(cmd, fcn, args)
	}

	override fun toString() = "cmd: $cmd, fcn: $fcn, args: $args"

	companion object {
		fun from(cmd: String?, invokeArgs: InvokeArgs, channelId: String?, chaincodeId: String?): CommandArgs {
			return CommandArgs()
				.setCmd(cmd)
				.setFcn(invokeArgs.fcn)
				.setArgs(invokeArgs.args)
		}
	}
}
