package com.shino.onlineanswer.Service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shino.onlineanswer.Mapper.CourseInfoMapper;
import com.shino.onlineanswer.Pojo.CourseInfo;
import com.shino.onlineanswer.Service.CourseInfoService;
import org.springframework.stereotype.Service;

@Service
public class CourseInfoServiceImpl extends ServiceImpl<CourseInfoMapper, CourseInfo> implements CourseInfoService {

}