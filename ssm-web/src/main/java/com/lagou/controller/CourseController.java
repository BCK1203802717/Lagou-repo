package com.lagou.controller;

import com.lagou.domain.Course;
import com.lagou.domain.CourseSection;
import com.lagou.domain.CourseVO;
import com.lagou.domain.ResponseResult;
import com.lagou.service.CourseContentService;
import com.lagou.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/course")
public class CourseController {
    //    @Autowired
    //    private CourseService courseService;
    //
    //    @RequestMapping("/findCourseByCondition ")
    //    public ResponseResult findCourseByCondition(@RequestBody CourseVO courseVo){
    //        List<Course> courseList = courseService.findCourseByCondition(courseVo);
    //        ResponseResult responseResult = new ResponseResult(true, 200, "响应成功",courseList);
    //        return responseResult;
    //    }
    @Autowired
    private CourseService courseService;

    /*
        多条件课程列表查询
     */
    @RequestMapping("/findCourseByCondition")
    public ResponseResult findCourseByCondition(@RequestBody CourseVO courseVO){
        System.out.println("文件：");
        //调用service
        List<Course> list = courseService.findCourseByCondition(courseVO);

        ResponseResult responseResult = new ResponseResult(true, 200, "响应成功", list);
        return  responseResult;
    }

    /*
        课程图片上传
     */
    @RequestMapping("/courseUpload")
    public ResponseResult fileUpload(@RequestParam("file")MultipartFile file, HttpServletRequest request) throws IOException {


        //1.判断接收到的上传文件是否为空
        if(file.isEmpty()){
            throw  new RuntimeException();
        }

        //2.获取项目部署路径

        // D:\apache-tomcat-8.5.56\webapps\ssm-web\
        String realPath = request.getServletContext().getRealPath("/");
        // D:\apache-tomcat-8.5.56\webapp
        String substring = realPath.substring(0, realPath.indexOf("ssm_web"));


        //3.获取原文件名
        //lagou.jpg
        String originalFilename = file.getOriginalFilename();

        //4.生成新文件名
        //12421321.jpg
        String newFileName = System.currentTimeMillis() + originalFilename.substring(originalFilename.lastIndexOf("."));

        //5.文件上传

        String uploadPath = substring + "upload\\";
        File filePath = new File(uploadPath, newFileName);

        // 如果目录不存在就创建目录
        if(!filePath.getParentFile().exists()){
            filePath.getParentFile().mkdirs();
            System.out.println("创建目录：" + filePath);
        }

        // 图片就进行了真正的上传
        file.transferTo(filePath);

        // 6. 将文件名和文件路径返回，进行响应
        Map<String, String> map = new HashMap<>();
        map.put("fileName",newFileName);

        map.put("filePath","http://localhost:8080/upload/" + newFileName);

        ResponseResult responseResult = new ResponseResult(true, 200, "图片上传成功", map);

        return responseResult;

    }
    /*** 保存&修改课程信息接口 * */
    @RequestMapping("/saveOrUpdateCourse ")
    public ResponseResult saveOrUpdateCourse(@RequestBody CourseVO courseVO){
        try {
            courseService.saveCourseOrTeacher(courseVO);
            ResponseResult responseResult=null;
            if(courseVO.getId()!=null){
               responseResult = new ResponseResult(true, 200, "修改成功", null);
            }
            else{
                responseResult = new ResponseResult(true, 200, "保存成功", null);
            }

            return responseResult;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /*
    根据id查询课程信息
     */
    @RequestMapping("/findCourseById")
    public ResponseResult findCourseById(Integer id){
        try {
            CourseVO courseVO = courseService.findCourseById(id);
            ResponseResult result = new ResponseResult(true, 200, "查询成功", courseVO);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    @RequestMapping("updateCourseStatus")
    public ResponseResult updateCourseStatus(Integer id,int status){
        try {
            courseService.updateCourseStatus(id,status);
            ResponseResult result = new ResponseResult(true, 200, "响应成功", status);
            return  result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
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
}
