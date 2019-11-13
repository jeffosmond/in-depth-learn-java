package com.jeffosmond.phase03.dao;


import com.jeffosmond.phase03.entity.User;

public interface UserDao {

    User findUserById(Long id);
}
