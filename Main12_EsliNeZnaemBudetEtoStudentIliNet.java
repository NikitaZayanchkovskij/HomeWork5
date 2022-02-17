/**
 * Ниже пример как можно сделать такой код используя дженерики.
 * Например в ситуации, когда мы не знаем с каким объектом нужно будет работать:
 * Со студентами, или мэнами, или карами и т.д.
 * После задания 6 смотри.
 */

package hw5_StudentCollection;

import hw5_StudentCollection.comparators.StudentAgeComparator;
import hw5_StudentCollection.comparators.StudentNameComparator;
import hw5_StudentCollection.comparators.StudentScoreComparator;
import hw5_StudentCollection.dto.Student;
import hw5_StudentCollection.generators.NamesRandomFileGenerator;
import hw5_StudentCollection.generators.api.INamesGenerator;
import hw5_StudentCollection.predicate.StudentAgeAndScorePredicate;
import hw5_StudentCollection.supplier.StudentRandomSupplier;
import hw5_StudentCollection.top_condition.TopCondition;
import hw5_StudentCollection.top_condition.api.ITopCondition;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Main12_EsliNeZnaemBudetEtoStudentIliNet {
    public static void main(String[] args) {
        ThreadLocalRandom rnd = ThreadLocalRandom.current();
        List<Student> students = new ArrayList<>();

      //INamesGenerator nameGenerator = new NamesRandomCharsGenerator(3, 10);
      //INamesGenerator nameGenerator = new NamesRandomArrayGenerator();
        INamesGenerator nameGenerator = new NamesRandomFileGenerator("names.txt");

        Supplier<Student> supplier = new StudentRandomSupplier(nameGenerator, rnd);

        Comparator<Student> reversed = new StudentScoreComparator().reversed();

        job(supplier, new StudentAgeAndScorePredicate(12,8), new TopCondition<>(), new StudentNameComparator(),
                reversed, new StudentAgeComparator().thenComparing(reversed), new TopCondition<>());

    }
//_____________________________________________________________________________________________


    public static <T> void job(Supplier<T> supplier, Predicate<T> filterPredicate, ITopCondition<T> showerTop,
                               Comparator<T> comparatorForSort1, Comparator<T> comparatorForSort2,
                               Comparator<T> comparatorForSort3, ITopCondition<T> groupedTop){
        /** Передаём поставщика, который умеет создавать какой-то тип данных.
         * И предикат, который принимает какой-то объект и отвечает true либо false т.е.
         * подходит он нам или нет*/


        /** Есть некая работа, которую надо выполнить с каким-то типом данных.
         * Для её выполнения нужно:
         * 1) Сгенерировать какие-то объекты.
         * 2) Отфильтровать объекты.
         * 3) Вывести три ТОПа объектов.
         */

        List<T> data = new ArrayList<>();

        for (int i = 0; i < 200; i++) {
            data.add(supplier.get());
        }

        /** Задание 3 начало */
        List<T> filtered = new ArrayList<>();
/** В JAVA есть интерфейс Predicate, который принимает какой-то объект и отвечает true или false.
 * Фактически что делала наша фильтрация? Отвечала, подходит нам какой-то студент, или нет. */

        for (T item : data) {
            if (filterPredicate.test(item)) {
                filtered.add(item);
            }
        }

        System.out.println("\nСтуденты 12 лет и старше и оценка выше 8: \n" + filtered);
        /** Задание 3 конец */


        /** Задание 4 начало */
        filtered.sort(comparatorForSort1);//Передаём компаратор, который сортирует студентов.

        System.out.println("\nСтудентов отфильтровалось: " + filtered.size());//Просто проверка на всякий, можно не писать.

        showerTop.show(filtered, 10);
        /** Задание 4 конец */


        /** Задание 5 начало */
        //filteredData.sort(new StudentScoreComparator().reversed()); - Можно было и так, в одну строчку.
        filtered.sort(comparatorForSort2);

        System.out.println("\nСтуденты по оценке от большего к меньшему:");

        showerTop.show(filtered, 10);

        /** Задание 5 конец */


        /** Задание 6 начало. */
        filtered.sort(comparatorForSort3.thenComparing(comparatorForSort2));

        groupedTop.show(filtered, 10);

        System.out.println("\nСтуденты по возрасту и оценке одновременно + топ 10 в каждом возрасте:");


    }

}
