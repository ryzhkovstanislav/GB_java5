package company.ryzhkov.common;

import java.io.Serializable;

public class ServiceMessage extends Message implements Serializable {
    private String command;

    public ServiceMessage(String command) {
        this.header = "SERVICE";
        this.command = command;
    }

    public String getCommand() {
        return command;
    }
}
