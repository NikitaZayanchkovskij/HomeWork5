package hw5_StudentCollection.predicate;

import hw5_StudentCollection.dto.Student;

import java.util.function.Predicate;

public class StudentStartNamePredicate implements Predicate<Student> {
    private final String nameStart;

    public StudentStartNamePredicate(String nameStart){
        this.nameStart = nameStart;
    }

    @Override
    public boolean test(Student student) {
        return student.getName().startsWith(nameStart);
    }

}
