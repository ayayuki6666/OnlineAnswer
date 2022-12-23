package com.shino.onlineanswer.Controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shino.onlineanswer.Pojo.TakeCourse;
import com.shino.onlineanswer.Service.TakeCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/student")
public class StudentListController {
    @Autowired
    TakeCourseService takeCourseService;
    @RequestMapping(method = RequestMethod.GET)
    public String StudentListReturn(Model model, @RequestParam("courseID") Integer courseID){
        QueryWrapper queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("courseID",courseID);
        List<TakeCourse> studentList=takeCourseService.list(queryWrapper);
        model.addAttribute("studentList",studentList);
        model.addAttribute("courseID",courseID);
        return "student";
    }
    @RequestMapping(method = RequestMethod.PUT)
    public String StudentBan(Model model,@RequestParam("courseID") Integer courseID,
                             @RequestParam("type") String type,@RequestParam("studentID") Integer studentID)
    {
        TakeCourse takeCourse=new TakeCourse();
        Map<String,String> map=new HashMap<>();
        map.put("banned","allowed");
        map.put("allowed","banned");
        takeCourse.setType(map.get(type));
        QueryWrapper<TakeCourse> takeCourseQueryWrapper=new QueryWrapper<>();
        takeCourseQueryWrapper.eq("studentID",studentID).eq("courseID",courseID);
        takeCourseService.update(takeCourse,takeCourseQueryWrapper);
        QueryWrapper queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("courseID",courseID);
        List<TakeCourse> studentList=takeCourseService.list(queryWrapper);
        model.addAttribute("studentList",studentList);
        model.addAttribute("courseID",courseID);
        return "student";
    }
}
