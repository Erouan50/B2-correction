package com.supinfo.binaryconverter.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Antoine Rouaze <antoine.rouaze@zenika.com>
 */
public class ServerLauncher {

    private static ExecutorService service = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {
        try {

            ServerSocket listener = new ServerSocket(6666);
            while (true) {
                System.out.println("Waiting connections");
                Socket socket = listener.accept();
                System.out.println("New connection !");
                service.execute(new BinaryConverterService(socket));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
