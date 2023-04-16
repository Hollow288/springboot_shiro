package com.hollow.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hollow.entity.Account;
import com.hollow.mapper.AccountMapper;
import com.hollow.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author 刘继涛
 * @version 1.0
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;
    @Override
    public Account findByUsername(String username) {
        QueryWrapper<Account> wrapper = new QueryWrapper<>();
        wrapper.eq("username",username);
        return accountMapper.selectOne(wrapper);


    }
}
