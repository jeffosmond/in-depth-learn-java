package com.jeffosmond.phase05.dao.impl;

import com.jeffosmond.phase05.controller.output.UserOutput;
import com.jeffosmond.phase05.dao.UserDao;

import java.io.InputStream;

/**
 * @author JeffOsmond
 * @date 2019/10/16
 */
public class UserDaoImpl implements UserDao {

    @Override
    public UserOutput queryUserById(Integer id) {
//        String resource = "phase04/mybatis-config.xml";
//        InputStream inputStream = ResourcesUtils.getResourcesAsStream(resource);
//        // sqlSession被调用次数很多，而且它需要Configuration对象
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//        // 可以考虑使用工厂来屏蔽SqlSession的构造细节
//        SqlSession sqlSession = sqlSessionFactory.openSqlSession();
//        UserOutput user = sqlSession.selectOne("test.findUserById",id);
//        return user;
        return null;
    }
}
