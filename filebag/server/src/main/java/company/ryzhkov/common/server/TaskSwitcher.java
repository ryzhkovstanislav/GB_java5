package company.ryzhkov.common.server;

import company.ryzhkov.common.Message;

public class TaskSwitcher {
    private Object msg;
    private TaskDistributor td;

    public TaskSwitcher(Object msg, TaskDistributor td) {
        this.msg = msg;
        this.td = td;
    }

    public void distribute() {
        if (msg instanceof Message) {
            Messages e = Enum.valueOf(Messages.class, ((Message) msg).getHeader());
            switch (e) {
                case AUTHENTICATION:
                    td.authAction();
                    break;
                case PART_OF_FILE:
                    td.partFileAction();
                    break;
                case SERVICE:
                    td.commandAction();
                    break;
                case FILE:
                    td.fileAction();
                    break;
            }
        }
    }
}
