package it.Itsrizzoli;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class PizzaLoader {
    public static List<Pizza> loadPizzasFromJSON(String filename) {
        List<Pizza> pizzas = null;
        try {
            Gson gson = new Gson();
            Type listType = new TypeToken<List<Pizza>>(){}.getType();
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            pizzas = gson.fromJson(reader, listType);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pizzas;
    }
}

