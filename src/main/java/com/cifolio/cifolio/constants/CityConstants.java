package com.cifolio.cifolio.constants;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public final class CityConstants {
    public static final int DEFAULT_PAGE = 0;
    public static final int DEFAULT_PAGE_SIZE = 12;
    public static final Pageable DEFAULT_PAGING_DATA = PageRequest.of(DEFAULT_PAGE, DEFAULT_PAGE_SIZE);

    private CityConstants() {}
}
