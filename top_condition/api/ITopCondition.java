/**
 * Этот интерфейс мы сделали для вывода некоего топа по определённым правилам.
 * Для вкладки Main12_EsliNeZnaemBudetEtoStudentIliNet, где пишем абстракцию через дженерики.
 */

package hw5_StudentCollection.top_condition.api;

import java.util.Comparator;
import java.util.List;

public interface ITopCondition<T> {
    void show (List<T> data, int count);

}
