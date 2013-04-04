package com.supinfo.binaryconverter.server;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.Socket;

/**
 * @author Antoine Rouaze <antoine.rouaze@zenika.com>
 */
public class BinaryConverterService implements Runnable {

    private Socket socket;

    public BinaryConverterService(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (Reader reader = new InputStreamReader(socket.getInputStream());
             PrintWriter writer = new PrintWriter(socket.getOutputStream())) {
            int number = reader.read();
            System.out.println("Number: " + number);
            writer.write(Integer.toBinaryString(number));
            writer.flush();
            System.out.println("Result sent");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
