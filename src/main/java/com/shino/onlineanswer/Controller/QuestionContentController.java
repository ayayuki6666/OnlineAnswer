package com.shino.onlineanswer.Controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shino.onlineanswer.Pojo.QuestionContent;
import com.shino.onlineanswer.Pojo.User;
import com.shino.onlineanswer.Service.QuestionContentService;
import com.shino.onlineanswer.Service.QuestionService;
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
@RequestMapping("/question")
public class QuestionContentController {
    @Autowired
    QuestionContentService questionContentService;
    @Autowired
    QuestionService questionService;
    @RequestMapping(value = "/{questionNo}",method = RequestMethod.GET)
    public String QuestionTotalReturn(Model model, @PathVariable("questionNo") Integer questionNo){
        QueryWrapper<QuestionContent> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("questionNo",questionNo).orderByAsc("create_time");
        List<QuestionContent> questionContentList=questionContentService.list(queryWrapper);
        model.addAttribute("questionContentList",questionContentList);
        return "questioncontent";
    }
    @RequestMapping(method = RequestMethod.POST)
    public String QuestionTotalPost(Model model, @RequestParam("questionNo") Integer questionNo, @RequestParam("code") String code, HttpSession session)
    {
        User user= (User) session.getAttribute("user");
        QuestionContent questionContent=new QuestionContent();
        questionContent.setContent(code);
        questionContent.setUsername(user.getUsername());
        questionContent.setQuestionNo(questionNo);
        questionContentService.save(questionContent);
        QueryWrapper<QuestionContent> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("questionNo",questionNo).orderByAsc("create_time");
        List<QuestionContent> questionContentList=questionContentService.list(queryWrapper);
        model.addAttribute("questionContentList",questionContentList);
        return "questioncontent";
    }
    @RequestMapping(method = RequestMethod.DELETE)
    public String QuestionTotalDelete(Model model, @RequestParam("questionNo") Integer questionNo, @RequestParam("contentID") Long contentID)
    {
        QueryWrapper<QuestionContent> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("questionNo",questionNo).orderByAsc("create_time");
        questionContentService.removeById(contentID);
        if(questionContentService.count(queryWrapper)>0)
        {
            List<QuestionContent> questionContentList=questionContentService.list(queryWrapper);
            model.addAttribute("questionContentList",questionContentList);
            return "questioncontent";
        }
        questionService.removeById(questionNo);
        return "redirect:/asklist";
    }
}
