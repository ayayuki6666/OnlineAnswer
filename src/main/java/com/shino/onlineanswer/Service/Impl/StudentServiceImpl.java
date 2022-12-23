package com.shino.onlineanswer.Service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shino.onlineanswer.Mapper.StudentMapper;
import com.shino.onlineanswer.Pojo.Student;
import com.shino.onlineanswer.Service.StudentService;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

}
