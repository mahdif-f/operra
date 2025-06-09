package query;

/**
 * توصیف‌گر اجرای Stored Procedure.
 */
public class StoredProcedureQueryDescriptor<T> extends QueryDescriptor<T> {

    private final String procedureName;

    private StoredProcedureQueryDescriptor(Builder<T> builder) {
        super(builder);
        this.procedureName = builder.procedureName;
    }

    public String getProcedureName() { return procedureName; }

    public static class Builder<T> extends QueryDescriptor.Builder<T, Builder<T>> {
        private String procedureName;

        public Builder<T> procedureName(String procedureName) {
            this.procedureName = procedureName;
            return this;
        }

        @Override protected Builder<T> self() { return this; }

        @Override public StoredProcedureQueryDescriptor<T> build() {
            return new StoredProcedureQueryDescriptor<>(this);
        }
    }
}
