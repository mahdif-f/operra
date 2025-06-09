package query;

import model.base.BaseEntity;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

/**
 * توصیف‌گر عمومی برای یک کوئری قابل اجرا، شامل پارامترها، ستون‌های نتیجه و نحوهٔ اجرا.
 * این کلاس پایه برای انواع مختلف کوئری‌ها مثل JPQL، SQL و Stored Procedure است.
 *
 * @param <T> نوع نتیجهٔ بازگشتی از اجرای کوئری.
 *           مثال: List<Product>, List<ProductSummaryDto>, List<Map<String, Object>>
 */
public abstract class QueryDescriptor<T> {

    protected final String id;
    protected final String title;
    protected final List<QueryParamDescriptor> params;
    protected final List<QueryColumnDescriptor> columns;
    protected final BiFunction<Map<String, Object>, Pageable, List<T>> executor;
    protected final boolean pageable;
    protected final int defaultPageSize;

    protected QueryDescriptor(Builder<T, ?> builder) {
        this.id = builder.id;
        this.title = builder.title;
        this.params = builder.params;
        this.columns = builder.columns;
        this.executor = builder.executor;
        this.pageable = builder.pageable;
        this.defaultPageSize = builder.defaultPageSize;
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public List<QueryParamDescriptor> getParams() { return params; }
    public List<QueryColumnDescriptor> getColumns() { return columns; }
    public BiFunction<Map<String, Object>, Pageable, List<T>> getExecutor() { return executor; }
    public boolean isPageable() { return pageable; }
    public int getDefaultPageSize() { return defaultPageSize; }

    /**
     * کلاس پایهٔ Builder برای QueryDescriptor.
     */
    public abstract static class Builder<T, SELF extends Builder<T, SELF>> {
        protected String id;
        protected String title;
        protected List<QueryParamDescriptor> params = new ArrayList<>();
        protected List<QueryColumnDescriptor> columns = new ArrayList<>();
        protected BiFunction<Map<String, Object>, Pageable, List<T>> executor;
        protected boolean pageable = false;
        protected int defaultPageSize = 50;

        public SELF id(String id) {
            this.id = id;
            return self();
        }

        public SELF title(String title) {
            this.title = title;
            return self();
        }

        public SELF addParam(QueryParamDescriptor param) {
            this.params.add(param);
            return self();
        }

        public SELF params(List<QueryParamDescriptor> params) {
            this.params.addAll(params);
            return self();
        }

        public SELF addColumn(QueryColumnDescriptor columns) {
            this.columns.add(columns);
            return self();
        }

        public SELF columns(List<QueryColumnDescriptor> columns) {
            this.columns.addAll(columns);
            return self();
        }

        public SELF executor(BiFunction<Map<String, Object>, Pageable, List<T>> executor) {
            this.executor = executor;
            return self();
        }

        public SELF pageable(boolean pageable) {
            this.pageable = pageable;
            return self();
        }

        public SELF defaultPageSize(int size) {
            this.defaultPageSize = size;
            return self();
        }

        protected abstract SELF self();
        public abstract QueryDescriptor<T> build();
    }
}