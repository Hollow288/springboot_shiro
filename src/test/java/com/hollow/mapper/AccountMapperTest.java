package com.hollow.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author 刘继涛
 * @version 1.0
 */

@SpringBootTest
class AccountMapperTest {

    @Autowired
    private AccountMapper mapper;

    @Test
    void test(){
        mapper.selectList(null).forEach(System.out::println);
    }


}