package com.hollow.service;

import com.hollow.entity.Account;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author 刘继涛
 * @version 1.0
 */

public interface AccountService {
    public Account findByUsername(String username);

}
