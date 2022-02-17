/**
 * Переходим наконец непосредственно к решению, как считать целый файл с именами.
 * До этого Илья объяснял что такое try catch и exceptions.
 *
 *    2.3** Заполнять имя рандомными понятными именами, загруженными из файла.
 *
 * 3) Отфильтровать студентов по нескольким признакам. По возрасту (тех, кому 12 и выше),
 * по оценке (выше 8). Отфильтрованных студентов поместить в отдельный список.
 * Старый список должен остаться неизменным.
 * 4) Отсортировать отфильтрованных студентов по имени, от меньшего к большему. Вывести топ 10.
 * 5) Отсортировать отфильтрованных студентов по оценке, от большего к меньшему. Вывести топ 10.
 * 6) Отсортировать отфильтрованных студентов по возрасту и оценке одновременно.
 * Вывести топ 10 в каждом возрасте.
 */


    /**              2.3 Заполнять имя рандомными понятными именами, загруженными из файла.
     *
     *  1) Объявляем класс BufferReader. Он считывает не символы уже, а строки.
     * (Илья нам ещё рассказывал про загрузку видео например в интернете, что такое буферизация,
     * интересно. Видео 14 занятие 2:46:00)
     *  2) Передаём ему в () Reader класс, который читает из файла. В нашем случае мы его в
     * переменную сохранили. Строка 69.
     * Передать Reader нужно потому, что Reader умеет читать информацию из файла, но посимвольно.
     * А этот умеет накапливать информацию из других ридеров. Собственно поэтому буфер ридер называется.
     * Если в него провалиться (Ctrl + ЛКМ) он наследует класс Reader.
     *  3) Делаем цикл, пока. Пока есть что-то для чтения - читать файл.
     *  4) Методы ready и readLine вызывают ексепшены - обрабатываем. Делаем try catch.
     *  5) Создаём файл, откуда читать.
     *  6) Передаём в student.setName имя файла. Можно с большой буквы, можно с маленькой. В Windows.
     * Но например на маке, или на линукс не заработает, там важно какая буква.
     *  7) После того, как воспользовались ридером - закрываем его, чтобы не тратить ресурсы.
     * Если провалиться в класс Reader увидим implements Readable, Closeable. Везде где так написано -
     * нужно закрывать после использования.
     * Иначе будет ситуация, как например когда мы хотим удалить файл, а компьютер пишет, что
     * файл ещё используется и нельзя с ним ничего сделать.
     * Пишем bufferedReader.close() и делаем try catch т.к. у него тоже может быть експешн.
     */

package hw5_StudentCollection;

import exceptions.CantReadDefaultFile;
import hw5_StudentCollection.dto.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Main7_NamesFromFile {
    public static void main(String[] args) {
        ThreadLocalRandom rnd = ThreadLocalRandom.current();
        List<Student> students = new ArrayList<>();//Создали пустой список, который будет принимать студентов.

        for (int i = 0; i < 100; i++) {
            Student student = new Student();//Создаём конкретного студента с параметрами: возраст, оценка и т.д.

            student.setNumber(i);//Будет порядковый номер из цикла у него т.е. номер позиции(ячейки) в массиве.
            student.setName(getName("names.txt"));
            student.setAge(rnd.nextInt(7, 17));
            student.setScore(rnd.nextDouble(0, 10));
            student.setOlympic(rnd.nextBoolean());

            students.add(student);//Добавляем созданного студента в список.
        }

        System.out.println(students);//Печатаем наших студентов.
    }


    /** Метод генерации какого-то из имени из заранее придуманного списка имён, но из файла. */
    public static String getName(String filePath){

        ThreadLocalRandom rnd = ThreadLocalRandom.current();
        List<String> names = new ArrayList<>();

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

        /** Try catch для Buffered Reader через try с ресурсами.
         * Здесь нам самим не нужно вызывать метод close. За нас это делает JAVA.
         * И теперь достаточно написать всего один catch для того, чтобы обработать
         * и ошибку закрытия файла, и ошибку чтения файла.
         * Также, если мы закрыли BufferedReader - (здесь он закрывается автоматически),
         * закроется и обычный reader выше.
         */
        try (BufferedReader bufferedReader = new BufferedReader(reader)) {//Объявили здесь BufferedReader.
            while (bufferedReader.ready()){//Также можно сделать здесь чуть красивее. Смотри Main8_NamesFromFile.
                names.add(bufferedReader.readLine());
            }
        } catch (IOException e){
            throw new RuntimeException("Некорректное чтение файла", e);
        }

        return names.get(rnd.nextInt(names.size()));
    }

}
