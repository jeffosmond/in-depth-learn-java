package com.jeffosmond.phase05.dao;

import com.jeffosmond.phase05.controller.output.UserOutput;

/**
 * @author JeffOsmond
 * @date 2019/10/16
 */
public interface UserDao {

    /**
     *
     * @param userId
     * @return
     */
    UserOutput queryUserById(Integer userId);
}
