package com.lagou.service;

import com.lagou.domain.Course;
import com.lagou.domain.CourseSection;

import java.util.List;

public interface CourseContentService {
    /*
    查询课程内容
     */
    public List<CourseSection> findSectionAndLessonByCourseId(int courseId);

    /*
    回显章节的课程信息
     */
    public Course findCourseByCourseId(Integer courseId);

    /*
    保存和新建章节信息
     */
    public void saveSection(CourseSection courseSection);

    /*
    修改章节信息
     */
    public void updateSection(CourseSection courseSection);
    /*
    修改课程状态
     */
    public void updateSectionStatus(Integer id,int status);
}
