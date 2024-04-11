package it.Itsrizzoli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Utilities {
    static String readTextFrom(String fname) {
        InputStream is;
        is = Utilities.class.getClassLoader().getResourceAsStream(fname);
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
}
