/**
 * Здесь делаем более структурированный код. Более красивый. Без повторов кода.
 *
 * P.S.
 *  Т.к. я создавал отдельные вкладки для каждого задания получения имени:
 *  из символов, из массива и из файла - у меня один метод getName. А на видео было всё в одном майне
 *  т.е. в одном мейне 3 метода getName.
 *  Поэтому Илья сказал это повод сделать Интерфейс, и сделать несколько реализаций.
 *  Здесь так и делаем.
 */

package hw5_StudentCollection;

import exceptions.CantReadDefaultFile;
import hw5_StudentCollection.comparators.StudentAgeComparator;
import hw5_StudentCollection.comparators.StudentNameComparator;
import hw5_StudentCollection.comparators.StudentScoreComparator;
import hw5_StudentCollection.dto.Student;
import hw5_StudentCollection.generators.NamesRandomArrayGenerator;
import hw5_StudentCollection.generators.NamesRandomCharsGenerator;
import hw5_StudentCollection.generators.NamesRandomFileGenerator;
import hw5_StudentCollection.generators.api.INamesGenerator;
import hw5_StudentCollection.supplier.StudentRandomSupplier;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;

public class Main11_NamesFromFile {
    public static void main(String[] args) {
        ThreadLocalRandom rnd = ThreadLocalRandom.current();
        List<Student> students = new ArrayList<>();//Создали пустой список, который будет принимать студентов.

      //INamesGenerator nameGenerator = new NamesRandomCharsGenerator(3, 10);
      //INamesGenerator nameGenerator = new NamesRandomArrayGenerator();
        INamesGenerator nameGenerator = new NamesRandomFileGenerator("names.txt");
        Supplier<Student> supplier = new StudentRandomSupplier(nameGenerator, rnd);

        for (int i = 0; i < 200; i++) {
            Student student = supplier.get();
            student.setNumber(i);//Будет порядковый номер из цикла у него т.е. номер позиции(ячейки) в массиве.
            students.add(student);//Добавляем созданного студента в список.
        }

        /** Задание 3 начало */
        List<Student> filteredStudents = new ArrayList<>();

        for (Student student : students) {
            if (student.getAge() >= 12 && student.getScore() > 8) {
                filteredStudents.add(student);
            }
        }

        System.out.println("\nСтуденты 12 лет и старше и оценка выше 8: \n" + filteredStudents);
        /** Задание 3 конец */


        /** Задание 4 начало */
        filteredStudents.sort(new StudentNameComparator());//Передаём компаратор, который сортирует студентов.

        System.out.println("\nСтудентов отфильтровалось: " + filteredStudents.size());//Просто проверка на всякий, можно не писать.

        System.out.println("\nСтуденты по алфавиту от раннего к позднему:");
        int index = 0;//Топ 10, чтобы вывести. В принципе выше тоже нужно было так, если топ 10 хотим.
        for (Student filteredStudents2 : filteredStudents) {
            System.out.print(filteredStudents2);
            index++;
            if (index >= 10) {
                break;
            }
        }
        /** Задание 4 конец */


        /** Задание 5 начало */
        Comparator<Student> studentScoreComparator = new StudentScoreComparator().reversed();

      //filteredStudents.sort(new StudentScoreComparator().reversed()); - Можно было и так, в одну строчку.
        filteredStudents.sort(studentScoreComparator);

        System.out.println("\nСтуденты по оценке от большего к меньшему:");
        index = 0;
        for (Student filteredStudents2 : filteredStudents) {
            System.out.print(filteredStudents2);
            index++;
            if (index >= 10) {
                break;
            }
        }
        /** Задание 5 конец */


        /** Задание 6 начало. */
        //StudentAgeComparator studentAgeComparator = new StudentAgeComparator(); - Можно было и так, вместо след. строчки.
        Comparator<Student> studentAgeComparator = new StudentAgeComparator();
        Comparator<Student> studentAgeAndScoreComparator = studentAgeComparator.thenComparing(studentScoreComparator);

        filteredStudents.sort(studentAgeAndScoreComparator);

        System.out.println("\nСтуденты по возрасту и оценке одновременно + топ 10 в каждом возрасте:");

        index = 0;
        int currentAge = -1;//Это как флажок того, что студенты не отфильтрованы ещё.
        //В видео 15 занятие 19.10.2021 время 1:00:00
        for (Student filteredStudents2 : filteredStudents) {
            if (currentAge == -1 || currentAge != filteredStudents2.getAge()) {
                index = 0;
                currentAge = filteredStudents2.getAge();
            }

            if (index >= 10 && currentAge == filteredStudents2.getAge()) {
                continue;//Заканчивает выполнение текущей итерации цикла, а break полностью выходит из цикла.
            }

            index++;

            System.out.print(filteredStudents2);
        }
        /** Задание 6 конец */

    }
}
