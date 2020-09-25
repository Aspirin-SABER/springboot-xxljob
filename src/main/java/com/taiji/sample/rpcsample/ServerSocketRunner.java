package com.taiji.sample.rpcsample;

import com.taiji.sample.rpcsample.entity.ServerConfig;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
@Component
public class ServerSocketRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        //需要执行的方法
        ServerSocket serverSocket =null;
        Socket socket = null;
        try {
            serverSocket = new ServerSocket(9999);
            System.out.println("启动Socket监听==============");
            ThreadPoolExecutor pool = new ThreadPoolExecutor(
                    10,
                    30,
                    20,
                    TimeUnit.SECONDS,
                    new ArrayBlockingQueue<Runnable>(10),
                    new ThreadPoolExecutor.DiscardOldestPolicy()
            );
            while(true){
                socket = serverSocket.accept();
                pool.execute(new ServerConfig(socket));
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
