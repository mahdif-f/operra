package query;

import java.util.List;

// -- Page<T> --
public class Page<T> {
    private final List<T> content;
    private final long totalElements;

    public Page(List<T> content, long totalElements) {
        this.content = content;
        this.totalElements = totalElements;
    }

    public List<T> getContent() { return content; }
    public long getTotalElements() { return totalElements; }
}