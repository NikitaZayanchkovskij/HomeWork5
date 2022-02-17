package hw5_StudentCollection.top_condition;

import hw5_StudentCollection.top_condition.api.ITopCondition;

import java.util.Comparator;
import java.util.List;

public class TopCondition<T> implements ITopCondition<T> {

    @Override
    public void show(List<T> data, int count) {
        int index = 0;
        for(T item: data){
            System.out.println(item);
            count--;
            if(count <= 0){
                return;
            }
        }

    }

}
