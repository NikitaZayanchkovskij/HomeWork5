/**
 * В JAVA есть интерфейс поставщик. К нему можно обратиться попросить например, дай мне студента какого-то.
 * И он такой да-да, сейчас дам.
 * Далее реализуем в интерфесе метод get, возвращающий нужный нам объект, в данном случае студента.
 */

package hw5_StudentCollection.supplier;

import hw5_StudentCollection.dto.Student;
import hw5_StudentCollection.generators.api.INamesGenerator;

import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;

public class StudentRandomSupplier implements Supplier<Student> {

    private final INamesGenerator namesGenerator;
    private final ThreadLocalRandom rnd;

    public StudentRandomSupplier(INamesGenerator namesGenerator, ThreadLocalRandom rnd){
        this.namesGenerator = namesGenerator;
        this.rnd = rnd;
    }

    @Override
    public Student get() {
        Student student = new Student();//Создаём конкретного студента с параметрами: возраст, оценка и т.д.

      //student.setNumber(i); В постащике не знаем как заполнять, поэтому можно не писать.
        student.setName(namesGenerator.getName());
        student.setAge(rnd.nextInt(7, 17));
        student.setScore(rnd.nextDouble(0, 10));
        student.setOlympic(rnd.nextBoolean());

        return student;//Возвращаем конкретного студента.
    }

}
