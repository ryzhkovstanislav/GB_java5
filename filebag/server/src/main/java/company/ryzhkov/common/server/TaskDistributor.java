package company.ryzhkov.common.server;

public interface TaskDistributor {
    void authAction();

    void fileAction();

    void partFileAction();

    void commandAction();
}
