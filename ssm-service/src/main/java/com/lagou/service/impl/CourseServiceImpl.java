package com.lagou.service.impl;

import com.lagou.dao.CourseMapper;
import com.lagou.domain.Course;
import com.lagou.domain.CourseVO;
import com.lagou.domain.Teacher;
import com.lagou.service.CourseService;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseMapper courseMapper;
    @Override
    public List<Course> findCourseByCondition(CourseVO courseVo) {
        List<Course> courseList = courseMapper.findCourseByCondition(courseVo);
        for(Course course:courseList){
            System.out.println(course.getCourseName());
        }
        return courseList;
    }

    @Override
    public void saveCourseOrTeacher(CourseVO courseVO) {
        try {
            System.out.println(courseVO.toString());
            if(courseVO.getId()!=null){
                Course course=new Course();
                BeanUtils.copyProperties(course,courseVO);
                Date date = new Date();
                course.setCreateTime(date);
                course.setUpdateTime(date);
                courseMapper.updateCourse(course);

                Teacher teacher=new Teacher();
                BeanUtils.copyProperties(teacher,courseVO);
                teacher.setCreateTime(date);
                teacher.setUpdateTime(date);
                teacher.setCourseId(course.getId());
                courseMapper.updateTeacher(teacher);
            }
            else {
                Course course=new Course();
                BeanUtils.copyProperties(course,courseVO);
                Date date = new Date();
                course.setCreateTime(date);
                course.setUpdateTime(date);
                System.out.println(course.toString());
                courseMapper.saveCourse(course);

                Teacher teacher=new Teacher();
                BeanUtils.copyProperties(teacher,courseVO);
                teacher.setCreateTime(date);
                teacher.setUpdateTime(date);
                teacher.setCourseId(course.getId());
                System.out.println(teacher);
                courseMapper.saveTeacher(teacher);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public CourseVO findCourseById(Integer id) {
        CourseVO courseVO = courseMapper.findCourseById(id);
        return courseVO;
    }
    @Override
    public void updateCourseStatus(Integer id, int status) {
        try {
            Course course=new Course();
            course.setId(id);
            course.setStatus(status);
            course.setUpdateTime(new Date());
            courseMapper.updateCourseStatus(course);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
