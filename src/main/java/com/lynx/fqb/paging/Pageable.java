package com.lynx.fqb.paging;

public interface Pageable {

    int getPageNumber();

    int getPageSize();

    int getOffset();
}
