package com.vista.agent.agentApp.socket;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

@Component
public class CommandServer {

    @PostConstruct
    public void startServer() throws IOException {
        new Thread(() -> {
            try (ServerSocket serverSocket = new ServerSocket(5555)) {
                System.out.println("Server started on port 5555");
                while (true) {
                    try (Socket clientSocket = serverSocket.accept();
                         PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                         BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
                        String inputLine;
                        while ((inputLine = in.readLine()) != null) {
                            System.out.println("Received command: " + inputLine);


                            out.println("Echo: " + inputLine);
                        }
                    } catch (IOException e) {
                        System.out.println("Exception caught when listening for a connection");
                        System.out.println(e.getMessage());
                    }
                }
            } catch (IOException e) {
                System.out.println("Exception caught when trying to listen on port 5555");
                System.out.println(e.getMessage());
            }
        }).start();
    }
}
