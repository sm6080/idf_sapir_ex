package inputOutput;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Sapir Michaeli on 15.03.2017.
 */
public class Input implements InputInterface {

    public static final String EXIT = "exit";

    @Override
    public String getInput() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            return bufferedReader.readLine();
        } catch (IOException e) {

        }
        return EXIT;
    }
}
