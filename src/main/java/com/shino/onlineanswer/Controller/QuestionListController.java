package com.shino.onlineanswer.Controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shino.onlineanswer.Pojo.Question;
import com.shino.onlineanswer.Pojo.QuestionContent;
import com.shino.onlineanswer.Pojo.TakeCourse;
import com.shino.onlineanswer.Pojo.User;
import com.shino.onlineanswer.Service.QuestionContentService;
import com.shino.onlineanswer.Service.QuestionService;
import com.shino.onlineanswer.Service.TakeCourseService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/asklist")
public class QuestionListController {
    @Autowired
    QuestionService questionService;
    @Autowired
    QuestionContentService questionContentService;
    @Autowired
    TakeCourseService takeCourseService;
    @RequestMapping()
    public String askListNull(Model model,@RequestParam(value = "courseID",required = false) Integer courseID,
                              HttpSession session)
    {
        User user= (User) session.getAttribute("user");
        String stuType=null;
        if(user.getPermission().equals("student"))
        {
            QueryWrapper<TakeCourse> takeCourseQueryWrapper = new QueryWrapper<>();
            takeCourseQueryWrapper.eq("courseID", courseID).eq("username", user.getUsername());
            stuType = takeCourseService.getOne(takeCourseQueryWrapper).getType();
        }
        model.addAttribute("stuType",stuType);


        QueryWrapper queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("courseID",courseID);
        List<Question> questionList=questionService.list();
        model.addAttribute("questionList",questionList);
        return "questionlist";
    }


    @RequestMapping(value = ("/{courseID}"),method = RequestMethod.GET)
    public String AskListReturn(Model model, @PathVariable("courseID") Integer courseID,HttpSession session)
    {
        User user= (User) session.getAttribute("user");
        String stuType=null;
        if(user.getPermission().equals("student"))
        {
            QueryWrapper<TakeCourse> takeCourseQueryWrapper = new QueryWrapper<>();
            takeCourseQueryWrapper.eq("courseID", courseID).eq("username", user.getUsername());
            stuType = takeCourseService.getOne(takeCourseQueryWrapper).getType();
        }

        model.addAttribute("stuType",stuType);

        QueryWrapper queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("courseID",courseID);
        List<Question> questionList=questionService.list(queryWrapper);
        model.addAttribute("questionList",questionList);
        return "questionlist";
    }
    @RequestMapping(method = RequestMethod.POST)
    public String AskListAdd(Model model, @RequestParam("questionTitle") String questionTitle,
                             @RequestParam("code") String code,@RequestParam("courseID") Integer courseID,
                             @RequestParam("type") String type,HttpSession session)
    {
        User user= (User) session.getAttribute("user");


        String stuType=null;
        if(user.getPermission().equals("student"))
        {
            QueryWrapper<TakeCourse> takeCourseQueryWrapper = new QueryWrapper<>();
            takeCourseQueryWrapper.eq("courseID", courseID).eq("username", user.getUsername());
            stuType = takeCourseService.getOne(takeCourseQueryWrapper).getType();
        }
        model.addAttribute("stuType",stuType);



        QuestionContent questionContent=new QuestionContent();
        Question question=new Question();
        question.setQuestionTitle(questionTitle);
        question.setCourseID(courseID);
        question.setType(type);
        question.setUsername(user.getUsername());
        questionService.save(question);
        questionContent.setQuestionNo(question.getQuestionNO());
        questionContent.setUsername(user.getUsername());
        questionContent.setContent(code);
        questionContentService.save(questionContent);
        QueryWrapper queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("courseID",courseID);
        List<Question> questionList=questionService.list(queryWrapper);
        model.addAttribute("questionList",questionList);
        return "questionlist";
    }
    @RequestMapping(method = RequestMethod.DELETE)
    public String questionDelete(Model model,@RequestParam("questionNo") Integer questionNO,
                                 @RequestParam("courseID") Integer courseID,HttpSession session)
    {
        User user= (User) session.getAttribute("user");


        String stuType=null;
        if(user.getPermission().equals("student"))
        {
            QueryWrapper<TakeCourse> takeCourseQueryWrapper = new QueryWrapper<>();
            takeCourseQueryWrapper.eq("courseID", courseID).eq("username", user.getUsername());
            stuType = takeCourseService.getOne(takeCourseQueryWrapper).getType();
        }
        model.addAttribute("stuType",stuType);

        questionService.removeById(questionNO);
        QueryWrapper queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("courseID",courseID);
        List<Question> questionList=questionService.list(queryWrapper);
        model.addAttribute("questionList",questionList);
        return "questionlist";
    }
}
