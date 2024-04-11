package it.Itsrizzoli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ClientHandler {
    private Socket clientSocket;
    private List<Pizza> pizzaList;

    public ClientHandler(Socket clientSocket, List<Pizza> pizzaList) {
        this.clientSocket = clientSocket;
        this.pizzaList = pizzaList;
    }

    public void handle() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

            String command;
            while ((command = in.readLine()) != null) {
                String response = parse(command);
                out.println(response);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String parse(String command) {
        switch (command.toLowerCase()) {
            case "with_tomato":
                return getPizzasWithIngredient("tomato");
            case "with_cheese":
                return getPizzasWithIngredient("cheese");
            case "sorted_by_price":
                return sortPizzasByPrice();
            default:
                return "Comando non riconosciuto";
        }
    }

    private String getPizzasWithIngredient(String ingredient) {
        List<Pizza> pizzasWithIngredient = pizzaList.stream()
                .filter(pizza -> pizza.getIngredients().contains(ingredient))
                .collect(Collectors.toList());

        if (pizzasWithIngredient.isEmpty()) {
            return "Nessuna pizza con l'ingrediente " + ingredient;
        }

        StringBuilder response = new StringBuilder();
        for (Pizza pizza : pizzasWithIngredient) {
            response.append(pizza.getName()).append(": ").append(pizza.getIngredients()).append("\n");
        }
        return response.toString();
    }

    private String sortPizzasByPrice() {
        List<Pizza> sortedPizzas = pizzaList.stream()
                .sorted(Comparator.comparingDouble(Pizza::getPrice))
                .collect(Collectors.toList());

        StringBuilder response = new StringBuilder("Pizze ordinate per prezzo:\n");
        for (Pizza pizza : sortedPizzas) {
            response.append(pizza.getName()).append(": ").append(pizza.getPrice()).append("€\n");
        }
        return response.toString();
    }
}
