package com.issja.entity;

import io.swagger.models.auth.In;
import lombok.Data;

import java.util.List;

/**
 * 某一个用户的所有比赛数据
 * @Auth ： isSJa
 * @Time ： 2023/1/16 23:56
 **/
@Data
public class AllUserContestsDetail {
    private Integer uid;
    private String uname;
    private List<UserContestDetail> contestDetail;
    private List<Integer> doList;
}
