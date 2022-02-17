package hw5_StudentCollection.top_condition;

import hw5_StudentCollection.top_condition.api.ITopCondition;
import objects.students.Student;

import java.util.List;

public class TopConditionGroupStudent implements ITopCondition<Student> {

    @Override
    public void show(List<Student> data, int count) {
        int index = 0;
        int currentAge = -1;//Это как флажок того, что студенты не отфильтрованы ещё.
        //В видео 15 занятие 19.10.2021 время 1:00:00
        for (Student student : data) {
            if (currentAge == -1 || currentAge != student.getAge()) {
                index = 0;
                currentAge = student.getAge();
            }

            if (index >= count && currentAge == student.getAge()) {
                continue;//Заканчивает выполнение текущей итерации цикла, а break полностью выходит из цикла.
            }

            index++;

            System.out.print(student);
        }

    }
}
