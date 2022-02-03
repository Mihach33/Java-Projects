package zad5;

import java.util.NoSuchElementException;
import java.util.function.*;

public class Maybe<T> {
    private T t;

    public Maybe(T t) {
        this.t = t;
    }


    public static <T> Maybe<T> of(T k) {
        return new Maybe<T>(k);
    }

    public void ifPresent(Consumer cons) {
        if (isPresent()){
            cons.accept(t);
        }
    }

    public boolean isPresent() {
        if(t != null){
            return true;
        }
            return false;

    }

    public <R> Maybe<R> map(Function<T, R> func) {
        if (isPresent()){
            R r = func.apply(t);
            return new Maybe<R>(r);
        }
        return new Maybe<R>(null);
    }

    public T get() {
        if (!isPresent()){
            throw new NoSuchElementException(" maybe is empty");
        }
        return t;
    }

    public T orElse(T defVal) {
        if (isPresent()){
            return t;
        }
        return defVal;
    }

    public Maybe<T> filter(Predicate<T> pred) {
        if (isPresent()){
            if (pred.test(t)){
                return this;
            }
        }
        return this;
    }

    @Override

    public String toString() {
        if (!isPresent()){
            return "Maybe is empty";
        } else{
            try {
                this.get();
            }catch(NoSuchElementException e) {
            }
        }
        return "Maybe has value " + t;
    }


}
