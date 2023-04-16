package com.hollow.entity;

import lombok.Data;

/**
 * @author 刘继涛
 * @version 1.0
 */

@Data
public class Account {
    private Integer id;
    private String username;
    private String password;
    private String perms;
    private String role;
}
