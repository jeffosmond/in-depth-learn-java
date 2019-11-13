package com.jeffosmond.phase03.entity;

import lombok.*;

import java.util.Date;

/**
 * @author JeffOsmond
 * @date 2019/11/13
 * 用户表
 */
@Data
public class User {
    private Long user_id;
    private String user_name;
    private Date birthday;
    private Integer user_sex;
    private String address;
}
