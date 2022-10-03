package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EchoServer {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                    BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    String get = in.readLine();
                    if (get.contains("Exit")) {
                        server.close();
                        break;
                    } else if (get.contains("Hello")) {
                        out.write("Hello.".getBytes());
                    } else if (get.contains("Any")) {
                        out.write("What.".getBytes());
                    }
                    System.out.println(get);
                    for (String str = in.readLine(); str != null && !str.isEmpty(); str = in.readLine()) {
                        System.out.println(str);
                    }
                    out.flush();
                } catch (IOException e) {
                    LOG.error("Exception in log example", e);
                }
            }
        } catch (IOException e) {
            LOG.error("Exception in log example", e);
        }
    }
}