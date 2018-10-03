package company.ryzhkov.common.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

public class MainHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
            new TaskSwitcher(msg, new TaskDistributor() {
                @Override
                public void authAction() {
                    System.out.println(msg);
                }

                @Override
                public void fileAction() {
                    System.out.println("No");
                }

                @Override
                public void partFileAction() {
                    System.out.println("No");
                }

                @Override
                public void commandAction() {
                    System.out.println("!!!!!!");
                }
            });
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
