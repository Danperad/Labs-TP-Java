package com.vyatsu.lab.repositories;

import lombok.Getter;
import lombok.Setter;

public class Filter {
    @Getter
    @Setter
    private int minPrice = 0;
    @Getter
    @Setter
    private int maxPrice = 0;
    @Getter
    @Setter
    private String text = "";
}
