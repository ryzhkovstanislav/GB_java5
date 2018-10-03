package company.ryzhkov.common.server;

import company.ryzhkov.common.ApplicationAPI;
import company.ryzhkov.common.AuthMessage;
import company.ryzhkov.common.Message;
import company.ryzhkov.common.ServiceMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

public class AuthHandler extends ChannelInboundHandlerAdapter implements ApplicationAPI {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Message wrongRequestResponse = new ServiceMessage(WRONG_REQUEST);
        try {
            new TaskSwitcher(msg, new TaskDistributor() {
                @Override
                public void authAction() {
                    DB.getInstance().verifyUsernameAndPassword(
                            ((AuthMessage) msg).getUsername(),
                            ((AuthMessage) msg).getPassword()
                    );
                    if (DB.getInstance().isAuth()) {
                        DB.getInstance().setAuth(false);
                        Message showFiles = new ServiceMessage(SHOW_FILES);
                        ctx.fireChannelRead(showFiles);
                        ctx.channel().pipeline().remove(AuthHandler.this);
                    } else {
                        ctx.write(new ServiceMessage(WRONG_CREDENTIALS));
                        ctx.flush();
                    }
                }

                @Override
                public void fileAction() {
                    wrongRequest(wrongRequestResponse, ctx);
                }

                @Override
                public void partFileAction() {
                    wrongRequest(wrongRequestResponse, ctx);
                }

                @Override
                public void commandAction() {
                    wrongRequest(wrongRequestResponse, ctx);
                }
            }).distribute();
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

    private void wrongRequest(Message serviceMessage, ChannelHandlerContext ctx) {
        ctx.write(serviceMessage);
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
