package com.issja.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.issja.entity.*;
import com.issja.mapper.ContestsMapper;
import com.issja.mapper.StudentMapper;
import com.issja.service.IStudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author issja
 * @since 2023-01-06
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements IStudentService {

    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private ContestsMapper contestsMapper;

    @Override
    public Page<Map<String, Object>> getStudentPage(int currentPage, int pageSize, String searchValue) {
        Page<Map<String, Object>> page = new Page<>(currentPage, pageSize);
        return page.setRecords(studentMapper.getStudentPageAndSearch(page, searchValue));
    }

    @Override
    public List<Map<String, Object>> getAllStudentIncludesScore() {
        return studentMapper.getAllStudentIncludesScore();
    }

    @Override
    public List<StudentContestDetail> getStudentScores(int id) {
        List<Score> studentScores = studentMapper.getStudentScores(id);
        List<StudentContestDetail> studentContestDetails = new ArrayList<>();
        for (Score score : studentScores) {
            if (score.getANum() != null) {
                StudentContestDetail studentContestDetail = new StudentContestDetail();
                studentContestDetail.setCid(score.getCid());
                studentContestDetail.setANum(score.getANum());
                studentContestDetail.setDuring(score.getDuringscore());
                studentContestDetail.setAfter(score.getAfterscore());
                QueryWrapper<Contests> queryWrapper = new QueryWrapper<>();
                queryWrapper.select("name");
                queryWrapper.eq("id", score.getCid());
                studentContestDetail.setCname(
                        contestsMapper.selectOne(queryWrapper).getName());
                studentContestDetails.add(studentContestDetail);
            }
        }
        return studentContestDetails;
    }

    @Override
    public List<AllStudentContestsDetail> getAllStudentScores() {
        Integer studentCount = studentMapper.selectCount(null);
        Integer contestCount = contestsMapper.selectCount(null);
        List<AllStudentContestsDetail> allstudentContestsDetailList = new ArrayList<>();
        for (int i = 1; i <= studentCount; i++) {
            AllStudentContestsDetail allstudentContestsDetail = new AllStudentContestsDetail();
            List<StudentContestDetail> studentContestDetailListBefore = getStudentScores(i);
            List<StudentContestDetail> studentContestDetailList = new ArrayList<>(studentContestDetailListBefore);
            allstudentContestsDetail.setUid(i);
            QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
            queryWrapper.select("name");
            queryWrapper.eq("id", i);
            allstudentContestsDetail.setUname(studentMapper.selectOne(queryWrapper).getName());
            // 求出doList和contestDetail，没有的值附0
            List<Integer> doList = new ArrayList<>();
            int j = 1;
            for (StudentContestDetail d : studentContestDetailListBefore) {
                while (j < d.getCid()) {
                    doList.add(0);
                    studentContestDetailList.add(j-1,null);
                    j++;
                }
                doList.add(1);
                j++;
            }
            while (j <= contestCount) {
                doList.add(0);
                studentContestDetailList.add(j-1,null);
                j++;
            }
            allstudentContestsDetail.setContestDetail(studentContestDetailList);
            allstudentContestsDetail.setDoList(doList);
            allstudentContestsDetailList.add(allstudentContestsDetail);
        }
        return allstudentContestsDetailList;
    }
}
