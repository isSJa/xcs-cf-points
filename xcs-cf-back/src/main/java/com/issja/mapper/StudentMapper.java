package com.issja.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.issja.entity.Score;
import com.issja.entity.Student;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.issja.entity.StudentContestDetail;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 社员表 Mapper 接口
 * </p>
 *
 * @author issja
 * @since 2023-01-06
 */
public interface StudentMapper extends BaseMapper<Student> {
    /**
     * 模糊查询+分页查询，获取对应页或查询的信息及总分
     * @param page
     * @param searchValue
     * @return
     */
    @MapKey("id")
    public List<Map<String, Object>> getStudentPageAndSearch(Page<Map<String,Object>>page, String searchValue);

    /**
     * 获取所有社员信息及总分
     * @return
     */
    @MapKey("id")
    public List<Map<String,Object>> getAllStudentIncludesScore();


    /**
     * 社员账号是否存在
     * @param account
     * @return
     */
    @Select("select count(*) from  student where account=#{account}")
    public Integer isStudentExist(@Param("account") String account);

    /**
     * 获取某一个社员的所有比赛记录
     * @return
     */
    public List<StudentContestDetail> getStudentScores(@Param("id")Integer id);
}
