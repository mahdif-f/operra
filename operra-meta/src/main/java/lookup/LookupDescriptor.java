package lookup;

import model.base.BaseEntity;
import query.QueryDescriptor;
import query.ResolvedQuery;

/**
 * توصیف‌گر یک lookup برای فیلدهای رفرنسی فرم‌ها به صورت type-safe.
 * این کلاس فقط وظیفه دارد منبع داده lookup را از طریق یک QueryDescriptor توصیف کند.
 */
public class LookupDescriptor<T extends BaseEntity> {

    private final ResolvedQuery<T> resolvedQuery;
    private final boolean multiSelect;

    private LookupDescriptor(Builder<T> builder) {
        this.resolvedQuery = builder.resolvedQuery;
        this.multiSelect = builder.multiSelect;
    }

    /** کوئری داده‌های lookup */
    public ResolvedQuery<T> getResolvedQuery() {
        return resolvedQuery;
    }

    /** اگر true باشد یعنی lookup به صورت multi-select ظاهر می‌شود */
    public boolean isMultiSelect() {
        return multiSelect;
    }

    // --- Builder ---
    public static class Builder<T extends BaseEntity> {
        private ResolvedQuery<T> resolvedQuery;
        private boolean multiSelect = false;

        /** تنظیم منبع داده lookup */
        public Builder<T> resolvedQuery(ResolvedQuery<T> resolvedQuery) {
            this.resolvedQuery = resolvedQuery;
            return this;
        }

        /** فعال‌سازی حالت چند‌انتخابی */
        public Builder<T> multiSelect(boolean multiSelect) {
            this.multiSelect = multiSelect;
            return this;
        }

        public LookupDescriptor<T> build() {
            return new LookupDescriptor<>(this);
        }
    }
}
