package com.shino.onlineanswer.Controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shino.onlineanswer.Pojo.Student;
import com.shino.onlineanswer.Pojo.TakeCourse;
import com.shino.onlineanswer.Pojo.TeachCourse;
import com.shino.onlineanswer.Pojo.User;
import com.shino.onlineanswer.Service.StudentService;
import com.shino.onlineanswer.Service.TakeCourseService;
import com.shino.onlineanswer.Service.TeachCourseService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/courseChoose")
public class CourseChooseController {
    @Autowired
    StudentService studentService;
    @Autowired
    TeachCourseService teachCourseService;
    @Autowired
    TakeCourseService takeCourseService;
    @RequestMapping(method = RequestMethod.GET)
    public String CourseChooseReturn(Model model){
        List<TeachCourse> courselist=teachCourseService.list();
        model.addAttribute("courselist",courselist);
        return "courseChoose";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String CourseChooseSubmit(
            HttpSession session,
            @RequestParam("courseChoose")List<Integer> chooseList
    )
    {
        User user= (User) session.getAttribute("user");
        QueryWrapper<Student> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("username",user.getUsername());
        Integer temp= studentService.getOne(queryWrapper).getStudentID();
        TakeCourse takeCourse=new TakeCourse();
        takeCourse.setStudentID(temp);
        for(int i=0;i<chooseList.size();i++)
        {
            takeCourse.setCourseID(chooseList.get(i));
            takeCourseService.save(takeCourse);
        }
        return "redirect:/courselist";
    }
}
