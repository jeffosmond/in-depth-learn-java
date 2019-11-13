package com.jeffosmond.phase03;

import com.jeffosmond.phase03.dao.UserDao;
import com.jeffosmond.phase03.dao.UserDaoImpl;
import com.jeffosmond.phase03.entity.Role;
import com.jeffosmond.phase03.entity.User;
import com.jeffosmond.phase03.mapper.RoleMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;

/**
 * @author JeffOsmond
 * @date 2019/11/13
 * Apache Mybatis 普通使用方法
 */
public class ApacheMybatisTest {

    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void init() throws Exception{
        // 加载全局配置文件（同时把映射文件也加载了）
        String resource = "phase03/SqlMapConfig.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        // sqlsessionFactory需要通过sqlsessionFactoryBuilder读取全局配置文件信息之后
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    /**
     * 普通模式(指定命名空间调用方法)
     */
    @Test
    public void testFindUserById() {
        UserDao dao = new UserDaoImpl(sqlSessionFactory);
        User user = dao.findUserById(1L);
        System.out.println(user);
    }

    /**
     * Mapper代理开发方式（基于XML）
     */
    @Test
    public void testFindRoleById() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        RoleMapper mapper = sqlSession.getMapper(RoleMapper.class);
        Role role = mapper.findRoleById(1L);
        System.out.println(role);
    }
}
