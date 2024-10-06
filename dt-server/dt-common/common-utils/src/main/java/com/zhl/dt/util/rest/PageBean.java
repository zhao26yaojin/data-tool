package com.zhl.dt.util.rest;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PageBean<T> {

    private int current;

    private int size;

    private long total;

    private List<T> records;

}
