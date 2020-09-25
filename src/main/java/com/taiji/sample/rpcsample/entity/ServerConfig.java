package com.taiji.sample.rpcsample.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.time.LocalDateTime;

public class ServerConfig extends Thread {



    private Socket socket;

    public ServerConfig(Socket socket) {
        this.socket = socket;
    }

    // 获取spring容器管理的类，可以获取到sevrice的类
//    private EnvironmentService service = SpringUtil.getBean(EnvironmentServiceImpl.class);

    private String handle(InputStream inputStream) throws IOException{
        return null;
    }

    @Override
    public void run() {
        BufferedWriter writer = null;
        try {

            // 设置连接超时9秒
            socket.setSoTimeout(9000);
            System.out.println("客户 - " + socket.getRemoteSocketAddress() + " -> 机连接成功");
            InputStream inputStream = socket.getInputStream();
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            String result = null;
            try {
//                result = handle(inputStream);
                writer.write("Hello,Saber11111111111111111111111111111" +
                        "111111111111111111111111111111111111111111111111111111" +
                        "1111111111111111111111111111111111111111111111111" +
                        "" +
                        "11111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111" +
                        "1111111111111111111111111111111111111111111111111111111111111");
                writer.newLine();
                writer.flush();
            } catch (IOException  e) {
                writer.write("error");
                writer.newLine();
                writer.flush();
                System.out.println("发生异常");
                try {
                    System.out.println("再次接受!");
                    result = handle(inputStream);
                    writer.write(result);
                    writer.newLine();
                    writer.flush();
                } catch (SocketTimeoutException ex) {
                    System.out.println("再次接受, 发生异常,连接关闭");
                }
            }
        } catch (SocketException socketException) {
            socketException.printStackTrace();
            try {
                writer.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
