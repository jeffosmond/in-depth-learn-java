package com.jeffosmond.phase04;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jeffosmond.phase04.entity.User;
import com.jeffosmond.phase04.entity.UserExample;
import com.jeffosmond.phase04.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

/**
 * @author JeffOsmond
 * @date 2019/11/13
 * Mybatis分页插件使用
 */
public class PageHelperTest {

    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void init() throws Exception {
        // 加载全局配置文件（同时把映射文件也加载了）
        String resource = "phase04/SqlMapConfig.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        // sqlsessionFactory需要通过sqlsessionFactoryBuilder读取全局配置文件信息之后
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    /**
     * 测试PageHelper插件分页查询
     * PageHelper有效使用三要素：
     *     1、pom文件添加pageHelper依赖
     *     2、startPage语句后紧跟查询语句
     *     3、mybatis配置文件中添加plugin标签配置
     */
    @Test
    public void test2() {
        // 创建UserMapper对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //编写分页代码
        PageHelper.startPage(2, 1);
        // 此处返回的list实现类不再是ArrayList，而是PageHelper提供的Page对象
        List<User> list = mapper.selectByExample(new UserExample());
        System.out.println(list);
        PageInfo<User> pageInfo = new PageInfo<User>(list);
        System.out.println(pageInfo.getTotal());
    }
}
