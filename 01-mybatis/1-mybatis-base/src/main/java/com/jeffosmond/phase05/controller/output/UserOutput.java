package com.jeffosmond.phase05.controller.output;

import lombok.Data;

import java.util.Date;

/**
 * @function   :
 * @version    : v1.0
 * @author     : JeffOsmond
 * @createTime : 2019/9/27 14:45
 */
@Data
public class UserOutput {

    private String user_name;
    private Integer user_sex;
    private String address;
    private Date birthday;
}
