package query;

/**
 * توصیف‌گر یک کوئری از نوع JPQL با متن کوئری مشخص.
 */
public class JPQLQueryDescriptor<T> extends QueryDescriptor<T> {

    private final String jpql;

    private JPQLQueryDescriptor(Builder<T> builder) {
        super(builder);
        this.jpql = builder.jpql;
    }

    public String getJpql() { return jpql; }

    public static class Builder<T> extends QueryDescriptor.Builder<T, Builder<T>> {
        private String jpql;

        public Builder<T> jpql(String jpql) {
            this.jpql = jpql;
            return this;
        }

        @Override protected Builder<T> self() { return this; }

        @Override public JPQLQueryDescriptor<T> build() {
            return new JPQLQueryDescriptor<>(this);
        }
    }
}
