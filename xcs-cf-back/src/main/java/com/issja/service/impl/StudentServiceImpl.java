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
import java.util.Objects;

/**
 * <p>
 * 社员表 服务实现类
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
        return studentMapper.getStudentScores(id);
    }

    @Override
    public List<StudentAllContestsDetail> getAllStudentScores() {
        // 所有社员信息
        List<Student> studentList = studentMapper.selectList(null);
        Integer contestCount = contestsMapper.selectCount(null);
        // 所有比赛信息
        List<Contests> contestsList = contestsMapper.selectList(null);
        // 需要返回的社员竞赛详情集合
        List<StudentAllContestsDetail> allstudentAllContestsDetailList = new ArrayList<>();
        for (Student student:studentList) {
            // 一个社员的所有竞赛数据
            StudentAllContestsDetail allstudentAllContestsDetail = new StudentAllContestsDetail();
            // 获取这个社员的所有竞赛数据（只含A过的竞赛）
            List<StudentContestDetail> studentContestDetailListBefore = getStudentScores(student.getId());
            // 未A过的竞赛赋值为null，要用foreach遍历，因此再开一个list存竞赛数据
            List<StudentContestDetail> studentContestDetailList = new ArrayList<>();
            // 赋值id和uname
            allstudentAllContestsDetail.setUid(student.getId());
            allstudentAllContestsDetail.setUname(student.getName());
            // 求出doList和contestDetail，没有的值附null和0
            List<Integer> doList = new ArrayList<>();
            int index=0;
            for(Contests contest:contestsList){
                if(index==studentContestDetailListBefore.size())break;
                if(Objects.equals(contest.getId(), studentContestDetailListBefore.get(index).getCid())){
                    doList.add(1);
                    studentContestDetailList.add(studentContestDetailListBefore.get(index));
                    index++;
                }else{
                    doList.add(0);
                    studentContestDetailList.add(null);
                }
            }
            while(studentContestDetailList.size()<contestsList.size()){
                doList.add(0);
                studentContestDetailList.add(null);
            }
            // 赋值doList和contestDetail
            allstudentAllContestsDetail.setContestDetail(studentContestDetailList);
            allstudentAllContestsDetail.setDoList(doList);
            allstudentAllContestsDetailList.add(allstudentAllContestsDetail);
        }
        return allstudentAllContestsDetailList;
    }
}
