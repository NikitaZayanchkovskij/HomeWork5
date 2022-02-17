/** В JAVA есть интерфейс Predicate, который принимает какой-то объект и отвечает true или false.
 * Фактически что делала наша фильтрация? Отвечала, подходит нам какой-то студент, или нет. */

package hw5_StudentCollection.predicate;


import hw5_StudentCollection.dto.Student;

import java.util.function.Predicate;

public class StudentAgeAndScorePredicate implements Predicate<Student> {

    private final int age;
    private final double score;

    public StudentAgeAndScorePredicate(int age, double score){
        this.age = age;
        this.score = score;
    }

    @Override
    public boolean test(Student student) {
        return student.getAge() >= this.age && student.getScore() > this.score;
    }

}
