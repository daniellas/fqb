package com.lynx.fqb.paging;

/**
 * Defines page
 * 
 * @author daniel.las
 *
 */
public interface Pageable {

    /**
     * Gets 0 based first page number
     * 
     * @return page number
     */
    int getPageNumber();

    /**
     * Gets page size
     * 
     * @return page size
     */
    int getPageSize();

    /**
     * Gets calculated offset
     * 
     * @return offset
     */
    int getOffset();
}
