package rule;

import java.util.function.Function;

public record FieldDependencyRef<T>(Function<T, ?> getter, FieldDependencyCause cause) {}
