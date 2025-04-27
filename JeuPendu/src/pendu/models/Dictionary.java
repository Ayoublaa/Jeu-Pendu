package pendu.models;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Dictionary {
    private static final List<String> WORDS = Arrays.asList(
            "JAVA", "PYTHON", "KOTLIN", "RUBY", "SWIFT",
            "CHAT", "CHIEN", "LAPIN", "SOURIS", "OISEAU"
    );

    private final Random random = new Random();

    public String getRandomWord() {
        return WORDS.get(random.nextInt(WORDS.size()));
    }
}