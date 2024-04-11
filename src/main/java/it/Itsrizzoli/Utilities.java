package it.Itsrizzoli;

import com.google.gson.Gson;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

public class Utilities {
    public static String readTextFrom(String fname) {
        InputStream is;
        is = Utilities.class.getClassLoader().getResourceAsStream(fname);
        if (is == null) {
            throw new IllegalArgumentException("Il file " + fname + " non esiste");
        }

        StringBuilder fileAsString = new StringBuilder();
        try (Reader reader = new InputStreamReader(is)) {
            int character;
            while ((character = reader.read()) != -1) {
                fileAsString.append((char) character);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return fileAsString.toString();
    }

    public static <T> T loadFromJson(String filename, Class<T> classOfT) {
        String json = readTextFrom(filename);
        Gson gson = new Gson();
        return gson.fromJson(json, classOfT);
    }
}
