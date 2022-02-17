package hw5_StudentCollection.generators;

import exceptions.CantReadDefaultFile;
import hw5_StudentCollection.generators.api.INamesGenerator;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class NamesRandomFileGenerator implements INamesGenerator {

    private final List<String> names = new ArrayList<>();
    private final int size;
    /** Если мы знаем, что наша коллекция больше не будет меняться -
     * лучше сохранить её размер в переменную. */

    public NamesRandomFileGenerator(String filePath){
        Reader reader;

        try {//Try catch для Reader.
            reader = new FileReader(filePath);
        } catch (FileNotFoundException e){
            try {
                reader = new FileReader("default.txt");
            } catch (FileNotFoundException e1){
                throw new CantReadDefaultFile("Произошла ошибка чтения дефолтного файла", e1);
            }
        }

        try (BufferedReader bufferedReader = new BufferedReader(reader)) {
            while(bufferedReader.ready()){
                names.add(bufferedReader.readLine());
            }
        } catch (IOException e){
            throw new RuntimeException("Некорректное чтение файла", e);
        }
        this.size = this.names.size();
    }

    public NamesRandomFileGenerator(){//Это если нам ничего не передали.
        Reader reader;

        try {//Try catch для Reader.
            reader = new FileReader("default.txt");
        } catch (FileNotFoundException e){
            throw new CantReadDefaultFile("Произошла ошибка чтения дефолтного файла", e);
        }

        try (BufferedReader bufferedReader = new BufferedReader(reader)) {
            while(bufferedReader.ready()){
                names.add(bufferedReader.readLine());
            }
        } catch (IOException e){
            throw new RuntimeException("Некорректное чтение файла", e);
        }
        this.size = this.names.size();
    }



    public String getName(){
        ThreadLocalRandom rnd = ThreadLocalRandom.current();

        return this.names.get(rnd.nextInt(this.size));
    }


}
