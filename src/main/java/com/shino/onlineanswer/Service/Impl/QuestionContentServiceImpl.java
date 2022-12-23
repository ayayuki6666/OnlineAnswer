package com.shino.onlineanswer.Service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shino.onlineanswer.Mapper.QuestionContentMapper;
import com.shino.onlineanswer.Pojo.QuestionContent;
import com.shino.onlineanswer.Service.QuestionContentService;
import org.springframework.stereotype.Service;

@Service
public class QuestionContentServiceImpl extends ServiceImpl<QuestionContentMapper, QuestionContent> implements QuestionContentService{
}
