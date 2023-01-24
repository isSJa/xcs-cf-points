package com.issja.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.issja.entity.Student;
import com.issja.entity.StudentContestDetail;
import com.baomidou.mybatisplus.extension.service.IService;
import com.issja.entity.StudentAllContestsDetail;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author issja
 * @since 2023-01-06
 */
public interface IStudentService extends IService<Student> {
    /**
     * 分页+模糊查询
     * @param currentPage
     * @param pageSize
     * @return
     */
    public Page<Map<String,Object>> getStudentPage(int currentPage, int pageSize, String searchValue);

    /**
     * 获取所有社员信息以及总分
     * @return
     */
    public List<Map<String,Object>> getAllStudentIncludesScore();

    /**
     * 获取某一个社员的所有比赛数据
     * @param id
     * @return
     */
    public List<StudentContestDetail> getStudentScores(int id);

    /**
     * 获取所有社员的所有比赛信息
     * @return
     */
    public List<StudentAllContestsDetail> getAllStudentScores();
}
