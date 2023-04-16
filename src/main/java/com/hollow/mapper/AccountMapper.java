package com.hollow.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hollow.entity.Account;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @author 刘继涛
 * @version 1.0
 */
@Mapper
public interface AccountMapper extends BaseMapper<Account> {

}
