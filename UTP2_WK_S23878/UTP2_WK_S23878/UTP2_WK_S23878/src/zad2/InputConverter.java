package zad2;

import java.util.function.Function;

public class InputConverter <T> {
    private T input;

    public InputConverter(T t) {
        this.input = t;
    }

    public <S> S convertBy(Function... functions) {
        Object input = this.input;
        Object result = null;

        for (Function f : functions) {
            result = f.apply(input);
            input = result;
        }
        return (S) result;
    }
}