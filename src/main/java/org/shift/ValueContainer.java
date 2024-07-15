package org.shift;

import javax.management.OperationsException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class ValueContainer<T> {
    private final List<String> tokens = new ArrayList<>();
    private final Predicate<String> validator;
    private final Function<String, T> map;

    public ValueContainer(Predicate<String> validator, Function<String, T> map) {
        this.validator = validator;
        this.map = map;
    }

    public Stream<T> values() {
        return tokens.stream()
                .map(map);
    }

    public Stream<String> tokens() {
        return tokens.stream();
    }

    public int getSize() {
        return tokens.size();
    }

    public boolean canParse(String token) {
        return validator.test(token);
    }

    public void add(String token) {
        if (!canParse(token)) {
            throw new IllegalArgumentException("can not add provided token '" + token + "'");
        }
        tokens.add(token);
    }
}
