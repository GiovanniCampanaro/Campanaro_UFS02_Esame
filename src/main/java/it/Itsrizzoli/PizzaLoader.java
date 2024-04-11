package it.Itsrizzoli;

import com.google.gson.Gson;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class PizzaLoader {
    public static List<Pizza> loadPizzasFromJSON(String filename) {
        List<Pizza> pizzas = null;
        try {
            Gson gson = new Gson();
            FileReader reader = new FileReader(filename);
            PizzaData data = gson.fromJson(reader, PizzaData.class);
            pizzas = data.sortPizzaByPrice;
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pizzas;
    }

    static class PizzaData {
        List<Pizza> sortPizzaByPrice;
    }
}

