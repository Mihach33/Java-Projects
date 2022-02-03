package zad1;

import java.util.ArrayList;
import java.util.function.Function;
import java.util.List;
import java.util.function.Predicate;

public class ListCreator<T> {

    private List<T> list;
    private List<T> listForChange;

    public ListCreator(List<T> list) {
        this.list = list;
    }

    public static <T> ListCreator<T> collectFrom(List<T> destinations) {
        ListCreator<T> listCreator = new ListCreator<T>(destinations);
        return listCreator;
    }

    public ListCreator<T> when(Predicate<T> predicate) {
        listForChange = new ArrayList<T>();

        for(int i = 0; i < list.size(); i++) {
            if(predicate.test(list.get(i))) {
                listForChange.add(list.get(i));
            }
        }
        this.list = listForChange;
        return this;
    }

    public <R> List<T> mapEvery(Function<T,R> func) {
        listForChange = new ArrayList<T>();

        for (T e : list) {
            listForChange.add((T)func.apply(e));
        }

        this.list = listForChange;
        return list;
    }

}