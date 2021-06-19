package com.lagou.service;

import com.lagou.domain.Course;
import com.lagou.domain.CourseVO;

import java.util.List;

public interface CourseService {
    /*
多条件课程列表查询
 */
    public List<Course> findCourseByCondition(CourseVO courseVo);

    /*
    保存课程信息
     */
    public void saveCourseOrTeacher(CourseVO courseVO);

    /*
    查询课程信息
     */
    public CourseVO findCourseById(Integer id);
    /*
    更新课程状态
     */
    public void updateCourseStatus(Integer id,int status);
}
