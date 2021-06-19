package com.lagou.controller;

import com.lagou.domain.Course;
import com.lagou.domain.CourseSection;
import com.lagou.domain.ResponseResult;
import com.lagou.service.CourseContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/courseContent")
public class CourseContentController {
    @Autowired
    private CourseContentService courseContentService;


    @RequestMapping("/findSectionAndLesson")
    public ResponseResult findSectionAndLesson(@RequestParam int courseId){
        try {
            List<CourseSection> courseSectionList = courseContentService.findSectionAndLessonByCourseId(courseId);
            ResponseResult result = new ResponseResult(true, 200, "响应成功", courseSectionList);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping("/findCourseByCourseId")
    public ResponseResult findCourseByCourseId(@RequestParam Integer courseId){
        try {
            Course course = courseContentService.findCourseByCourseId(courseId);
            ResponseResult result = new ResponseResult(true, 200, "响应成功", course);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    @RequestMapping("/saveOrUpdateSection")
    public ResponseResult saveOrUpdateSection(@RequestBody CourseSection courseSection){
        ResponseResult result=null;
        try {
            if(courseSection.getId()!=null){
                courseContentService.updateSection(courseSection);
                result = new ResponseResult(true, 200, "修改成功", null);
            }
            else{
                courseContentService.saveSection(courseSection);
                result = new ResponseResult(true, 200, "保存成功", null);
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    @RequestMapping("/updateSectionStatus")
    public ResponseResult updateSectionStatus(Integer id,int status){
        try {
            courseContentService.updateSectionStatus(id,status);
            ResponseResult result = new ResponseResult(true, 200, "响应成功", status);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
