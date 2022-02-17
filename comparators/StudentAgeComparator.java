package hw5_StudentCollection.comparators;

import hw5_StudentCollection.dto.Student;
import java.util.Comparator;

public class StudentAgeComparator implements Comparator<Student> {

    @Override
    public int compare(Student o1, Student o2) {
      //return o1.getAge() - o2.getAge(); - Можно так.
        return Integer.compare(o1.getAge(), o2.getAge());//Можно так.
    }

}
