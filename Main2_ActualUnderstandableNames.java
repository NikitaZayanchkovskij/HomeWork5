/** Задача
 * 1) Создать класс Student с полями:
 *    1.1 Порядковый номер (int)
 *    1.2 Имя (Строка размером от 3 до 10 русских символов)
 *    1.3 Возраст (7 - 17)
 *    1.4 Оценка (0.0 - 10.0)
 *    1.5 Признак участия в олимпиадах (boolean)
 *
 * 2) Создать 10_000 (я изменил на 100. Зачем зря компьютер нагружать + в консоли дохера будет)
 *            объектов класса Student и поместить в коллекцию. Данные заполняются рандомно.
 * Стандартного рандома для строк в jdk нету.
 *    2.1 Заполнять имя рандомными русскими символами
 *
 *    2.2* Заполнять имя рандомными понятными именами - !!! ВАЖНО !!!
 *    Условие стоит некорректно!
 *    В решении мы придумываем массив имён и из него выбираем потом и заполняем - !!!!!ЭТО НЕ РАНДОМ!!!!!
 *    Это заранее придуманные имена из которых мы выбираем.
 *    Условие должно было звучать как:
 *    2.2* Заполнять поле имя ЗАРАНЕЕ ПРИДУМАННЫМИ именами рандомно присваивая их рандомному студенту.
 *    А НЕ РАНДОМНО ГЕНЕРИРОВАТЬ!
 *    Рандомно генерировать - это значит система сама составляет из массива чаров понятные русские имена.
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


    /**                 2.2 Заполнять имя понятными именами
     *
     * 1) На уровне класса создаём массив и заполняем его именами.
     * 2) В методе генерируем через рандом.
     */

package hw5_StudentCollection;

import hw5_StudentCollection.dto.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Main2_ActualUnderstandableNames {

    private static String[] russianNames = {"Иван", "Вася", "Петя"};

    public static void main(String[] args) {
        ThreadLocalRandom rnd = ThreadLocalRandom.current();
        List<Student> students = new ArrayList<>();//Создали пустой список, который будет принимать студентов.

        for (int i = 0; i < 100; i++) {
            Student student = new Student();//Создаём конкретного студента с параметрами: возраст, оценка и т.д.

            student.setNumber(i);//Будет порядковый номер из цикла у него т.е. номер позиции(ячейки) в массиве.
            student.setName(getName());
            student.setAge(rnd.nextInt(7, 17));
            student.setScore(rnd.nextDouble(0, 10));
            student.setOlympic(rnd.nextBoolean());

            students.add(student);//Добавляем созданного студента в список.
        }

        System.out.println(students);//Печатаем наших студентов.
    }


    /** Метод генерации какого-то из имени из заранее придуманного списка имён. */
    public static String getName() {
        ThreadLocalRandom rnd = ThreadLocalRandom.current();

        return russianNames[rnd.nextInt(russianNames.length)];
        /** Здесь рандом берёт рандомную позицию (индекс) из массива имён, а russianNames.length
         * написано чтобы рандом не вышел за пределы массива. */
    }

}
