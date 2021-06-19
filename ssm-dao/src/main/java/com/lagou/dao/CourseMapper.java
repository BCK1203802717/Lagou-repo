package com.lagou.dao;

import com.lagou.domain.Course;
import com.lagou.domain.CourseVO;
import com.lagou.domain.Teacher;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseMapper {
    /*
    多条件课程列表查询
     */
    public List<Course> findCourseByCondition(CourseVO courseVO);

    /*
    保存课程信息
     */
    public int saveCourse(Course course);

    /*
    保存讲师信息
     */
    public int saveTeacher(Teacher teacher);

    /*
    查询课程信息
     */
    public CourseVO findCourseById(Integer id);

    /*
    保存课程信息
     */
    public int updateCourse(Course course);

    /*
    保存讲师信息
     */
    public int updateTeacher(Teacher teacher);

    /*
    更新课程状态
     */
    public int updateCourseStatus(Course course);


}
