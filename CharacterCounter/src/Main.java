import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Antoine Rouaze <antoine.rouaze@zenika.com>
 */
public class Main {

    public static void main(String[] args) {
        try (Reader reader = new FileReader("src/Main.java")) {
            Map<Character, Integer> result = new HashMap<>();
            int current;
            while (-1 != (current = reader.read())) {
                if (result.get((char) current) == null) {
                    result.put((char) current, 1);
                } else {
                    result.put((char) current, result.get((char) current) + 1);
                }
            }
            for (Map.Entry<Character, Integer> entry : result.entrySet()) {
                System.out.println("Key: '" + entry.getKey() + "', Value: " + entry.getValue());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
