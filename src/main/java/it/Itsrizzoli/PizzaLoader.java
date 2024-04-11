package it.Itsrizzoli;

import java.util.List;

public class PizzaLoader {
    public static List<Pizza> loadPizzasFromJSON(String filename) {
        return Utilities.loadFromJson(filename, PizzaLoader.PizzaData.class).sortPizzaByPrice;
    }

    static class PizzaData {
        List<Pizza> sortPizzaByPrice;
    }
}
