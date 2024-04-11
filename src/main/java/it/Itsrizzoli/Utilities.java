package it.Itsrizzoli;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class Utilities {
    public static String readTextFrom(String fname) {
        InputStream is = Utilities.class.getClassLoader().getResourceAsStream(fname);
        if (is == null) {
            throw new IllegalArgumentException("Il file " + fname + " non esiste");
        }

        BufferedReader buf = new BufferedReader(new InputStreamReader(is));

        StringBuilder fileAsString = new StringBuilder();
        String line;

        try {
            while ((line = buf.readLine()) != null) {
                fileAsString.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                buf.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return fileAsString.toString();
    }

    public static <T> T loadFromJson(String filename, Class<T> classOfT) {
        try (Reader reader = new InputStreamReader(Utilities.class.getClassLoader().getResourceAsStream(filename))) {
            Gson gson = new Gson();
            return gson.fromJson(reader, classOfT);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
