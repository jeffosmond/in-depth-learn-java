package com.jeffosmond.phase05.entity;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @function   : 用户表
 * @version    : v1.0
 * @author     : JeffOsmond
 * @createTime : 2019/9/27 14:45
 */
@Data
@ToString
public class User {
    private Long user_id;
    private String user_name;
    private Date birthday;
    private Integer user_sex;
    private String address;
}
