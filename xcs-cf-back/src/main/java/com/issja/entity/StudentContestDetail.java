package com.issja.entity;

import lombok.Data;

/**
 * 某一个用户参加某一个比赛的数据
 * @Auth ： isSJa
 * @Time ： 2023/1/17 16:44
 **/
@Data
public class StudentContestDetail {
    //比赛的id
    private Integer cid;
    //比赛的名字
    private String cname;
    // 一共A题数
    private Integer aNum;
    // 正式比赛积分
    private Integer during;
    //赛后补题积分
    private Integer after;
}
