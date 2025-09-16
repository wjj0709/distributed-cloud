package com.phoenix.distributed.common.lang;

import io.swagger.v3.oas.annotations.Parameter;

import java.util.List;

/**
 * @author wjj-phoenix
 * @since 2025-09-16
 * 分页对象
 */
public final class IPage<T> {
    @Parameter(description = "当前页码")
    private final long page;

    @Parameter(description = "每页数据量大小")
    private final long size;

    @Parameter(description = "总页码")
    private final long pages;

    @Parameter(description = "总的数据量")
    private final long total;

    @Parameter(description = "数据")
    private final List<T> rows;

    public IPage(long page, long size, long pages, long total, List<T> rows) {
        this.page = page;
        this.size = size;
        this.pages = pages;
        this.total = total;
        this.rows = rows;
    }

    public static <T> IPage<T> of(long page, long size, long pages, long total, List<T> rows) {
        return new IPage<>(page, size, pages, total, rows);
    }

    public static <T> IPage<T> empty() {
        return of(0, 0, 0, 0, null);
    }

    public long getPage() {
        return page;
    }

    public long getSize() {
        return size;
    }

    public long getPages() {
        return pages;
    }

    public long getTotal() {
        return total;
    }

    public List<T> getRows() {
        return rows;
    }
}

