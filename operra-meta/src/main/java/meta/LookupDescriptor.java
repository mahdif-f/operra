package meta;

import java.util.HashMap;
import java.util.Map;


import java.util.function.Function;
import java.util.List;

public class LookupDescriptor<T, R> {
    private final String name;
    private final Function<T, List<R>> lookupFunction;

    public LookupDescriptor(String name, Function<T, List<R>> lookupFunction) {
        this.name = name;
        this.lookupFunction = lookupFunction;
    }

    public String getName() {
        return name;
    }

    public List<R> performLookup(T context) {
        return lookupFunction.apply(context);
    }
}
