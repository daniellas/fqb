package com.lynx.fqb.paging;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public class PageRequest implements Pageable {

    private final int pageNumber;
    private final int pageSize;

    public PageRequest(int pageNumber, int pageSize) {
        if (pageNumber < 0) {
            throw new IllegalArgumentException("Page number must be greater or equal 0");
        }
        if (pageSize < 1) {
            throw new IllegalArgumentException("Page size must be greater than 0");
        }
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }

    @Override
    public int getOffset() {
        return pageNumber * pageSize;
    }

    /**
     * Constructs {@link Pageable} implementation
     * 
     * @param pageNumber
     *            page number
     * @param pageSize
     *            page size
     * @return new {@link Pageable}
     */
    public static Pageable of(int pageNumber, int pageSize) {
        return new PageRequest(pageNumber, pageSize);
    }

}
