package query;

/**
 * توصیف‌گر یک کوئری از نوع Native SQL.
 */
public class SQLQueryDescriptor<T> extends QueryDescriptor<T> {

    private final String sql;

    private SQLQueryDescriptor(Builder<T> builder) {
        super(builder);
        this.sql = builder.sql;
    }

    public String getSql() { return sql; }

    public static class Builder<T> extends QueryDescriptor.Builder<T, Builder<T>> {
        private String sql;

        public Builder<T> sql(String sql) {
            this.sql = sql;
            return this;
        }

        @Override protected Builder<T> self() { return this; }

        @Override public SQLQueryDescriptor<T> build() {
            return new SQLQueryDescriptor<>(this);
        }
    }
}
