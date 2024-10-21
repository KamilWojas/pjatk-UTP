package zad3;

import java.util.NoSuchElementException;
import java.util.function.*;


public class Maybe<T> {
    T value;

    public Maybe(T value) {
        this.value = value;
    }

    public static <U> Maybe<U> of(U value) {
        return new Maybe<>(value);
    }

    public void ifPresent(Consumer c) {
        if (value != null)
            c.accept(value);
    }

    public <U> Maybe<U> map(Function<T, U> f) {
        return (value == null) ? new Maybe<>(null) : new Maybe((U) f.apply(value));
    }

    public T get() throws NoSuchElementException {
        if (value == null) throw new NoSuchElementException(" maybe is empty");
        return value;
    }

    public T orElse(T t) {
        return (value == null) ? t : value;
    }

    public boolean isPresent() {
        return (value != null);
    }

    public Maybe<T> filter(Predicate<T> p) {
        return (p.test((T) this.value) || value == null) ? this : Maybe.of(null);
    }

    public String toString() {
        return (value == null) ? "Maybe is empty " : "Maybe has value " + value;
    }
}