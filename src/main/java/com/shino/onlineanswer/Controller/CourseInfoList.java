package com.shino.onlineanswer.Controller;

import com.shino.onlineanswer.Pojo.CourseInfo;
import com.shino.onlineanswer.Pojo.TeachCourse;
import com.shino.onlineanswer.Service.CourseInfoService;
import com.shino.onlineanswer.Service.TeachCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/courseInfo")
public class CourseInfoList {
    @Autowired
    CourseInfoService courseInfoService;
    @Autowired
    TeachCourseService teachCourseService;
    @RequestMapping(method = RequestMethod.GET)
    public String CourseInfoReturn(Model model)
    {
        List<TeachCourse> teachCourseInfoList=teachCourseService.list();
        model.addAttribute("teachCourseInfoList",teachCourseInfoList);
        return "courseInformation";
    }
    @RequestMapping(method = RequestMethod.DELETE)
    public String CourseInfoDelete(Model model, @RequestParam("courseID") Integer courseID)
    {
        courseInfoService.removeById(courseID);
        List<TeachCourse> teachCourseInfoList=teachCourseService.list();
        model.addAttribute("teachCourseInfoList",teachCourseInfoList);
        return "courseInformation";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String CourseInfoUpdate(Model model,@RequestParam(value = "courseID",required = false) Integer courseID,
                                   @RequestParam("courseName") String courseName,@RequestParam(value = "courseComment",required = false) String courseComment){

        CourseInfo courseInfo=new CourseInfo();
        if(courseID!=null)
            courseInfo.setCourseID(courseID);
        if(courseComment!=null)
            courseInfo.setCourseComment(courseComment);
        courseInfo.setCourseName(courseName);
        courseInfoService.saveOrUpdate(courseInfo);
        List<TeachCourse> teachCourseInfoList=teachCourseService.list();
        model.addAttribute("teachCourseInfoList",teachCourseInfoList);
        return "courseInformation";
    }
}
