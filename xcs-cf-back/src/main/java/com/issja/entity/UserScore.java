package com.issja.entity;

import lombok.Data;

import java.util.List;

/**
 * @Auth ： isSJa
 * @Time ： 2023/1/16 23:56
 **/
@Data
public class UserScore {
    private Integer id;
    private String name;
    private List<Integer> isDo;
    private Integer during=0;
    private Integer after=0;
}
