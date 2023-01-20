package com.issja.entity;

import lombok.Data;

import java.util.List;

/**
 * 某一个用户的所有比赛数据
 * @Auth ： isSJa
 * @Time ： 2023/1/16 23:56
 **/
@Data
public class AllStudentContestsDetail {
    private Integer uid;
    private String uname;
    private List<StudentContestDetail> contestDetail;
    private List<Integer> doList;
}
