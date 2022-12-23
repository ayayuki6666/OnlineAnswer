package com.shino.onlineanswer.Service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shino.onlineanswer.Mapper.QuestionMapper;
import com.shino.onlineanswer.Pojo.Question;
import com.shino.onlineanswer.Service.QuestionService;
import org.springframework.stereotype.Service;

@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question> implements QuestionService {
}
