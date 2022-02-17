package hw5_StudentCollection.generators;

import hw5_StudentCollection.generators.api.INamesGenerator;

import java.util.concurrent.ThreadLocalRandom;

public class NamesRandomCharsGenerator implements INamesGenerator {
    private static char[] russianChars;

    static {
        russianChars = new char[(1104 - 1072) + 1];
        for (int i = 1072, j = 0; i < 1104; i++, j++) {
            russianChars[j] = (char) i;
        }
        russianChars[russianChars.length - 1] = 'ё';
    }

    private final int from;
    private final int to;

    public NamesRandomCharsGenerator(int from, int to){
        this.from = from;
        this.to = to;
    }

    /** Параметры в скобках у метода убираем. Если не убрать:
     * public class NameRandomCharsGenerator implements INameGenerator будет подчёркивать красным. */
    public String getName() {
        return getName(from, to);
    }

    public static String getName(int from, int to){
        ThreadLocalRandom rnd = ThreadLocalRandom.current();
        int charsAmmount = rnd.nextInt(from, to);
        String name = "";
        for (int j = 0; j < charsAmmount; j++) {
            char letter = russianChars[rnd.nextInt(russianChars.length)];
            if (name.length() == 0) {
                letter = Character.toUpperCase(letter);
            }
            name = name + letter;
        }
        return name;
    }


}
