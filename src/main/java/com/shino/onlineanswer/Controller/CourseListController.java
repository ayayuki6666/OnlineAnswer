package com.shino.onlineanswer.Controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shino.onlineanswer.Pojo.TakeCourse;
import com.shino.onlineanswer.Pojo.User;
import com.shino.onlineanswer.Service.TakeCourseService;
import com.shino.onlineanswer.Service.TeachCourseService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.List;

@Controller
@RequestMapping("/courselist")
public class CourseListController {
    @Autowired
    TakeCourseService takeCourseService;
    @Autowired
    TeachCourseService teachCourseService;
        @RequestMapping(method = RequestMethod.GET)
        public String CourseListReturn(Model model, HttpServletRequest request)
        {
            HttpSession session=request.getSession();
            User user= (User) session.getAttribute("user");
            QueryWrapper queryWrapper=new QueryWrapper<>();
            List<TakeCourse> takeCourseList;
            queryWrapper.eq("username",user.getUsername());
            if(user.getPermission().equals("student")) {
                takeCourseList=takeCourseService.list(queryWrapper);
                if (takeCourseList == null) {
                    return "redirect:/courseChoose";
                }
                model.addAttribute("takecourselist", takeCourseList);
            }
            if(user.getPermission().equals("teacher"))
            {
                takeCourseList=teachCourseService.list(queryWrapper);
                model.addAttribute("takecourselist", takeCourseList);
            }
            return "courselist";
        }
    }

