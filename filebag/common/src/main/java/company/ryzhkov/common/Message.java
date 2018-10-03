package company.ryzhkov.common;

import java.io.Serializable;

public abstract class Message implements Serializable {
    protected String header;

    protected Message() {
    }

    protected Message(String header) {
        this.header = header;
    }

    public String getHeader() {
        return header;
    }
}
