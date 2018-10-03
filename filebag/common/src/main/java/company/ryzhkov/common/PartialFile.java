package company.ryzhkov.common;

import java.io.Serializable;

public class PartialFile extends File implements Serializable {
    public PartialFile(byte[] byteArray) {
        super(byteArray);
        this.header = "PART_OF_FILE";
    }
}
