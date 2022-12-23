package com.shino.onlineanswer.Controller;

import com.shino.onlineanswer.Pojo.Teacher;
import com.shino.onlineanswer.Pojo.User;
import com.shino.onlineanswer.Service.TeacherService;
import com.shino.onlineanswer.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/teacher")
public class TeacherInfoController {
    @Autowired
    TeacherService teacherService;
    @Autowired
    UserService userService;
    @RequestMapping(method = RequestMethod.GET)
    public String teacherReturn(Model model){
        List<Teacher> teacherList=teacherService.list();
        model.addAttribute("teacherList",teacherList);
        return "teacher";
    }
    @RequestMapping(method = RequestMethod.DELETE)
    public String teacherDelete(Model model, @RequestParam("teacherID") Integer teacherID)
    {
        teacherService.removeById(teacherID);
        List<Teacher> teacherList=teacherService.list();
        model.addAttribute("teacherList",teacherList);
        return "teacher";
    }
    @RequestMapping(method = RequestMethod.POST)
    public String teacherAlter(Model model,@RequestParam(value = "teacherID",required = false) Integer teacherID,
                               @RequestParam("teacherName") String teacherName,
                               @RequestParam(value = "teacherComment",required = false) String teacherComment,
                               @RequestParam(value = "teacherTitle",required = false) String teacherTitle){
        Teacher teacher=new Teacher();
        if(teacherID!=null)
            teacher.setTeacherID(teacherID);
        if(teacherComment!=null)
            teacher.setTeacherComment(teacherComment);
        if(teacherTitle!=null)
            teacher.setTeacherTitle(teacherTitle);
        teacher.setTeacherName(teacherName);
        teacherService.saveOrUpdate(teacher);
        List<Teacher> teacherList=teacherService.list();
        User user= new User();
        user.setPermission("teacher");
        user.setUsername(String.valueOf(teacherID));
        userService.saveOrUpdate(user);
        model.addAttribute("teacherList",teacherList);
        return "teacher";
    }
}
