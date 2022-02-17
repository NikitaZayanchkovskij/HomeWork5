package hw5_StudentCollection.comparators;

import hw5_StudentCollection.dto.Student;
import java.util.Comparator;

public class StudentScoreComparator implements Comparator<Student> {

    @Override
    public int compare(Student o1, Student o2) {
        return Double.compare(o1.getScore(), o2.getScore());
    }

}
