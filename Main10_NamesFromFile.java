/**
 * 3) Отфильтровать студентов по нескольким признакам. По возрасту (тех, кому 12 и выше),
 * по оценке (выше 8). Отфильтрованных студентов поместить в отдельный список.
 * Старый список должен остаться неизменным.
 * 4) Отсортировать отфильтрованных студентов по имени, от меньшего к большему. Вывести топ 10.
 * 5) Отсортировать отфильтрованных студентов по оценке, от большего к меньшему. Вывести топ 10.
 * 6) Отсортировать отфильтрованных студентов по возрасту и оценке одновременно.
 *    Вывести топ 10 в каждом возрасте.
 */

package hw5_StudentCollection;

import exceptions.CantReadDefaultFile;
import hw5_StudentCollection.comparators.StudentAgeComparator;
import hw5_StudentCollection.comparators.StudentNameComparator;
import hw5_StudentCollection.comparators.StudentScoreComparator;
import hw5_StudentCollection.dto.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Main10_NamesFromFile {
    public static void main(String[] args) {
        ThreadLocalRandom rnd = ThreadLocalRandom.current();
        List<Student> students = new ArrayList<>();//Создали пустой список, который будет принимать студентов.

        for (int i = 0; i < 200; i++) {
            Student student = new Student();//Создаём конкретного студента с параметрами: возраст, оценка и т.д.

            student.setNumber(i);//Будет порядковый номер из цикла у него т.е. номер позиции(ячейки) в массиве.
            student.setName(getName("names.txt"));
            student.setAge(rnd.nextInt(7, 17));
            student.setScore(rnd.nextDouble(0, 10));
            student.setOlympic(rnd.nextBoolean());

            students.add(student);//Добавляем созданного студента в список.
        }

        /** Задание 3 начало */
        List<Student> filteredStudents = new ArrayList<>();

      //Нужно перебрать всех студентов (сравнить каждого), поэтому делаем for each.
      //for each Илья говорил лучше тогда, когда нам не нужно модифицировать список, для которого
      //мы делаем этот цикл. А если нам нужно что-то добавлять или удалть из списка - тогда for i.
        for (Student student : students) {
            if(student.getAge() >= 12 && student.getScore() > 8){
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
        for (Student filteredStudents2 : filteredStudents){
            System.out.print(filteredStudents2);
            index++;
            if(index >= 10){
                break;
            }
        }
        /** Задание 4 конец */


        /** Задание 5 начало */
        Comparator<Student> studentScoreComparator = new StudentScoreComparator().reversed();

      //filteredStudents.sort(new StudentScoreComparator().reversed()); - Можно было и так, в одну строчку.
        filteredStudents.sort(studentScoreComparator);
        /** Т.к. по условию нам нужно вывести от Большего к меньшему можно сделать 2 способами:
         * 1) В компараторе поменять местами o1 и o2.
         * 2) Как здесь: у компаратора вызвать метод reversed.
         */

        System.out.println("\nСтуденты по оценке от большего к меньшему:");
        index = 0;
        for (Student filteredStudents2 : filteredStudents){
            System.out.print(filteredStudents2);
            index++;
            if(index >= 10){
                break;
            }
        }
        /** Задание 5 конец */


        /** Задание 6 начало.
         * Приводился пример табличек в excel. Нужно отсортировать и по возрасту, и по оценке.
         * Нужно понимать, какая сортировка первая, какая вторая. Потому что вторая сортировка
         * происходит внутри первой сортировки. Сделаем например сначала по возрасту, потом по оценке.
         * Здесь тоже можно упростить.
         * У компаратора есть метод thenComparing, в который можно передать тоже компаратор.
         * P.S. По оценке у нас здесь от большей оценке к меньшей. Чтобы было от меньшей к большей -
         * у компаратора нужно убрать метод reversed.
         */
      //StudentAgeComparator studentAgeComparator = new StudentAgeComparator(); - Можно было и так, вместо след. строчки.
        Comparator<Student> studentAgeComparator = new StudentAgeComparator();
        Comparator<Student> studentAgeAndScoreComparator = studentAgeComparator.thenComparing(studentScoreComparator);

        filteredStudents.sort(studentAgeAndScoreComparator);

        System.out.println("\nСтуденты по возрасту и оценке одновременно + топ 10 в каждом возрасте:");

        index = 0;
        int currentAge = -1;//Это как флажок того, что студенты не отфильтрованы ещё.
                                                        //В видео 15 занятие 19.10.2021 время 1:00:00
        for (Student filteredStudents2 : filteredStudents){
            if (currentAge == -1 || currentAge != filteredStudents2.getAge()){
                index = 0;
                currentAge = filteredStudents2.getAge();
            }

            if(index >= 10 && currentAge == filteredStudents2.getAge()){
                continue;//Заканчивает выполнение текущей итерации цикла, а break полностью выходит из цикла.
            }

            index++;

            System.out.print(filteredStudents2);
        }
        /** Задание 6 конец */

    }



    /** Метод генерации какого-то из имени из заранее придуманного списка имён, но из файла. */
    public static String getName(String filePath){
        List<String> names = new ArrayList<>();

        ThreadLocalRandom rnd = ThreadLocalRandom.current();

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
         * Здесь нам самим не нужно вызывать метод clos. За нас это делает JAVA.
         * И теперь достаточно написать всего один catch для того, чтобы обработать
         * и ошибку закрытия файла, и ошибку чтения файла.
         * Также, если мы закрыли BufferedReader - (здесь он закрывается автоматически),
         * закроется и обычный reader выше.
         */
        try (BufferedReader bufferedReader = new BufferedReader(reader)) {//Объявили здесь BufferedReader.
            names = read(bufferedReader);
        } catch (IOException e){
            throw new RuntimeException("Некорректное чтение файла", e);
        }

        return names.get(rnd.nextInt(names.size()));
    }

    
    public static List<String> read(BufferedReader bufferedReader) throws IOException{
        List<String> names = new ArrayList<>();

        while (bufferedReader.ready()){
            names.add(bufferedReader.readLine());
        }

        return names;
    }

}
