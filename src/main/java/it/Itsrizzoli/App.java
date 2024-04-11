package it.Itsrizzoli;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class App {
    static int portNumber = 6666;
    private static List<Pizza> pizzas;

    public static void main(String[] args) {
        pizzas = PizzaLoader.loadPizzasFromJSON("pizzas.json");

        System.out.println("Server started!");

        ServerSocket serverSocket = getServerSocket();
        while (true) {
            Socket clientSocket = accept(serverSocket);
            ClientHandler ch = new ClientHandler(clientSocket, pizzas);
            ch.handle();
        }
    }

    private static Socket accept(ServerSocket serverSocket) {
        Socket clientSocket;
        try {
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return clientSocket;
    }

    private static ServerSocket getServerSocket() {
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(portNumber);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return serverSocket;
    }

}
