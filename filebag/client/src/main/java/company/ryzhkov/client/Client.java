package company.ryzhkov.client;

import company.ryzhkov.common.Message;
import io.netty.handler.codec.serialization.ObjectDecoderInputStream;
import io.netty.handler.codec.serialization.ObjectEncoderOutputStream;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    private Socket socket = null;
    private ObjectDecoderInputStream in = null;
    private ObjectEncoderOutputStream out = null;

    public Client() {
        try {
            socket = new Socket("localhost", 8189);
            in = new ObjectDecoderInputStream(socket.getInputStream());
            out = new ObjectEncoderOutputStream(socket.getOutputStream());
        } catch (UnknownHostException e) {
            System.out.println("Unknown host");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void runServer() {
        new Thread(() -> {
            try {
                while (true) {
                    Message msg = (Message) in.readObject();
                    System.out.println(msg.getHeader());
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                System.out.println("Class not found");
            } finally {
                try {
                    out.close();
                    in.close();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void senMessage(Object object) {
        try {
            out.writeObject(object);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
