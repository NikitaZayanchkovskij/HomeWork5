package hw5_StudentCollection.generators;

import hw5_StudentCollection.generators.api.INamesGenerator;
import java.util.concurrent.ThreadLocalRandom;

public class NamesRandomArrayGenerator implements INamesGenerator {

    private final String[] russianNames;

    public NamesRandomArrayGenerator(){//Если пользователь не передал имена используем свои.
        russianNames = new String[] {"Иван", "Вася", "Петя"};
    }

    public NamesRandomArrayGenerator(String[] russianNames){//Если передал, то присваиваем их.
        this.russianNames = russianNames;
    }

    /** Параметры в скобках у метода убираем. Если не убрать:
     * public class NameRandomCharsGenerator implements INameGenerator будет подчёркивать красным. */
    public String getName() {
        ThreadLocalRandom rnd = ThreadLocalRandom.current();
        return russianNames[rnd.nextInt(russianNames.length)];
    }


}
