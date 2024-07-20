package org.shift;

import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexpFilter implements Predicate<String> {
    private final Pattern pattern;

    public RegexpFilter(Pattern pattern) {
        this.pattern = pattern;
    }

    @Override
    public boolean test(String s) {
        Matcher matcher = pattern.matcher(s);
        return matcher.find() && matcher.group().equals(s);
    }
}
