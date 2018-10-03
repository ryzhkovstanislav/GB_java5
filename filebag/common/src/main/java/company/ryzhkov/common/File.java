package company.ryzhkov.common;

import java.io.Serializable;

public class File extends Message implements Serializable {
    private byte[] byteArray;

    public File(byte[] byteArray) {
        this.header = "FILE";
        this.byteArray = byteArray;
    }
}
