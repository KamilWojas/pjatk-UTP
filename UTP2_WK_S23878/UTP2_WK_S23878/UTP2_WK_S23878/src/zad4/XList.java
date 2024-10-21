package zad4;

import java.util.*;
import java.util.stream.Collectors;
import java.util.function.*;

class XList <T> extends ArrayList<T> {

    private XList() {

    }

    public XList(Object... objects) {
        this.addAll(XList.of(objects));
    }

    public static <T> XList<T> of(Object... objects) {
        XList xList = new XList<>();
        boolean combine = false;
        if (objects.length > 1) {
            combine = true;
            for (Object object : objects) {
                if (!(object instanceof Collection)) {
                    combine = false;
                    break;
                } else if (object.getClass().isArray()) {
                    combine = false;
                    break;
                }
            }
        }
        for (Object object : objects)
            if (object instanceof Collection && !combine)
                ((Collection) object).forEach(o -> xList.addAll(XList.of(o)));
            else if (object.getClass().isArray() && !combine)
                Arrays.stream(((Object[]) object)).forEach(o -> xList.addAll(XList.of(o)));
            else if (combine)
                xList.add(XList.of(object));
            else
                xList.add(object);

        return xList;
    }

    public static XList<String> charsOf(String s) {
        List<String> chars = new ArrayList<>();
        for (char c : s.toCharArray()) chars.add(String.valueOf(c));
        return XList.of(chars);
    }

    public static XList<String> tokensOf(String s) {
        return XList.tokensOf(s, "\\s+");
    }

    public static XList<String> tokensOf(String s, String separator) {
        return XList.of(s.split(separator));
    }

    public XList<Integer> union(Object... objects) {
        XList xList = new XList(this);
        xList.addAll(XList.of(objects));
        return xList;
    }

    public XList<T> unique() {
        XList xList = new XList();
        this.forEach(t -> {
            if (!xList.contains(t)) xList.add(t);
        });
        return xList;
    }

    public XList diff(Object... objects) {
        XList xList = new XList(this);
        xList.removeAll(XList.of(objects));
        return xList;
    }

    public XList<XList<T>> combine() {
        XList<XList<T>> xList = combine(0, (XList<XList<T>>) this);
        xList.forEach(Collections::reverse);
        return xList;
    }

    private static <T> XList<XList<T>> combine(int i, XList<XList<T>> collections) {
        XList<XList<T>> xList = new XList<>();
        if (i == collections.size()) {
            xList.add(new XList<>());
        } else {
            for (Object obj : collections.get(i)) {
                for (XList<T> collection : combine(i + 1, collections)) {
                    collection.add((T) obj);
                    xList.add(collection);
                }
            }
        }

        return xList;
    }


    public String join() {
        return join("");
    }

    public String join(String separator) {
        return this.stream()
                .map(o ->
                        o.toString()
                ).collect(
                        Collectors.joining(separator)
                );
    }

    public <S> XList<String> collect(Function<XList<S>, String> fun) {
        XList xList = new XList();

        for (XList<S> t : ((XList<XList<S>>) this)) {
            xList.add(fun.apply(t));
        }

        return xList;
    }

    public void forEachWithIndex(BiConsumer<T, Integer> consumer) {
        for (int i = 0; i < this.size(); i = i + 1) {
            consumer.accept(this.get(i), i);
        }
    }
}