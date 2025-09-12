// src/main/java/com/stackflow/oms/support/Pagination.java
package com.stackflow.oms.support;

public record Pagination(int page, int size) {
    public static Pagination of(int page, int size) {
        if (page < 0) page = 0;
        if (size <= 0 || size > 200) size = 20;
        return new Pagination(page, size);
    }
}