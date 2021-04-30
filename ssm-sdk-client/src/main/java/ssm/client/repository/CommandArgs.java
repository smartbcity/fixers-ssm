package ssm.client.repository;

import java.util.List;
import java.util.Objects;

import com.google.common.base.MoreObjects;

import ssm.client.invoke.command.InvokeArgs;

public class CommandArgs {

    private String cmd;
    private String fcn;
    private String channelid;
    private String chaincodeid;
    private List<String> args;

    public static CommandArgs from(String cmd, InvokeArgs invokeArgs, String channelId, String chaincodeId) {
        return new CommandArgs()
            .setCmd(cmd)
            .setFcn(invokeArgs.getFcn())
            .setArgs(invokeArgs.getArgs())
            .setChannelid(channelId)
            .setChaincodeid(chaincodeId);
    }

    public String getCmd() {
        return cmd;
    }

    public CommandArgs setCmd(String cmd) {
        this.cmd = cmd;
        return this;
    }

    public String getFcn() {
        return fcn;
    }

    public CommandArgs setFcn(String fcn) {
        this.fcn = fcn;
        return this;
    }

    public String getChannelid() {
        return channelid;
    }

    public CommandArgs setChannelid(String channelid) {
        this.channelid = channelid;
        return this;
    }

    public String getChaincodeid() {
        return chaincodeid;
    }

    public CommandArgs setChaincodeid(String chaincodeid) {
        this.chaincodeid = chaincodeid;
        return this;
    }

    public List<String> getArgs() {
        return args;
    }

    public CommandArgs setArgs(List<String> args) {
        this.args = args;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CommandArgs)) return false;
        CommandArgs commandArgs = (CommandArgs) o;
        return Objects.equals(cmd, commandArgs.cmd) &&
                Objects.equals(fcn, commandArgs.fcn) &&
                Objects.equals(args, commandArgs.args);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cmd, fcn, args);
    }

    @Override
    public String toString() {
        MoreObjects.ToStringHelper stringHelper = MoreObjects.toStringHelper(this)
            .add("cmd", cmd)
            .add("fcn", fcn)
            .add("args", args);

        if (this.channelid != null) {
            stringHelper.add("channelid", channelid);
        }

        if (this.chaincodeid != null) {
            stringHelper.add("chaincodeid", chaincodeid);
        }

        return stringHelper.toString();
    }
}
