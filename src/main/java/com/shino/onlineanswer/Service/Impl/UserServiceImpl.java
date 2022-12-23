package com.shino.onlineanswer.Service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shino.onlineanswer.Mapper.UserMapper;
import com.shino.onlineanswer.Pojo.User;
import com.shino.onlineanswer.Service.UserService;
import org.springframework.stereotype.Service;
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{

}
