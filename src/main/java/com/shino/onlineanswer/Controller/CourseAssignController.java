package com.shino.onlineanswer.Controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shino.onlineanswer.Pojo.CourseInfo;
import com.shino.onlineanswer.Pojo.Teacher;
import com.shino.onlineanswer.Service.CourseInfoService;
import com.shino.onlineanswer.Service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/assign")
public class CourseAssignController {
    @Autowired
    CourseInfoService courseInfoService;
    @Autowired
    TeacherService teacherService;
    @RequestMapping(method = RequestMethod.GET)
    public String assignReturn(Model model){
        QueryWrapper<CourseInfo> queryWrapper=new QueryWrapper<>();
        queryWrapper.isNull("teacherID");
        List<CourseInfo> courseInfoList= courseInfoService.list(queryWrapper);
        List<Teacher> teacherList=teacherService.list();
        if(courseInfoList==null)
        {
            return "redirect:/courseInfo";
        }
        model.addAttribute("courseInfoList",courseInfoList);
        model.addAttribute("teacherList",teacherList);
        return "courseAssign";
    }
    @RequestMapping(method = RequestMethod.PUT)
    public String assignPut(
            @RequestParam("courseID") List<Integer> courseIDList,
            @RequestParam("teacherID") List<Integer> teacherIDLIst
    )
    {
        List<CourseInfo> courseInfoList=new ArrayList<>();
        for(int i=0;i<courseIDList.size();i++) {
            CourseInfo courseInfo = new CourseInfo();
            courseInfo.setCourseID(courseIDList.get(i));
            courseInfo.setTeacherID(teacherIDLIst.get(i));
            courseInfoList.add(courseInfo);
        }
        courseInfoService.saveOrUpdateBatch(courseInfoList);
        return "redirect:/courseInfo";
    }
}
