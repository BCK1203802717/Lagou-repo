package com.lagou.dao;

import com.lagou.domain.Course;
import com.lagou.domain.CourseSection;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseContentMapper {
    /*
    查询课程信息
     */
    public List<CourseSection> findSectionAndLessonByCourseId(int courseId);

    /*
    回显章节的课程信息
     */

    public Course findCourseByCourseId(Integer courseId);


    /*
    保存章节信息
     */
    public int saveSection(CourseSection courseSection);
    /*
    修改章节信息
     */
    public int updateSection(CourseSection courseSection);

    /*
    修改课程状态
     */
    public int updateSectionStatus(CourseSection courseSection);
}
