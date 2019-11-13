package com.jeffosmond.phase04;

import com.jeffosmond.phase04.entity.User;
import com.jeffosmond.phase04.entity.UserExample;
import com.jeffosmond.phase04.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author JeffOsmond
 * @date 2019/11/13
 * Mybatis自动生成工具使用
 */
public class GeneratorTest {

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
     * 自动生成工具执行方法
     * @param args
     */
    public static void main(String[] args){
        try {
            List<String> warnings = new ArrayList<String>();
            //指定 逆向工程配置文件
            File configFile = new File(GeneratorTest.class.getResource("/phase04/generatorConfig.xml").getFile());
            ConfigurationParser cp = new ConfigurationParser(warnings);
            Configuration config = cp.parseConfiguration(configFile);
            DefaultShellCallback callback = new DefaultShellCallback(true);
            MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
            myBatisGenerator.generate(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试自动生成的代码进行Example查询
     * JeffNode:此种方式，一定要在pom.xml文件中配置resources打包目录，否则mapper文件夹下的xml文件不会被编译
     */
    @Test
    public void test() {
        // 创建UserMapper对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        UserExample example = new UserExample();
        List<User> list = mapper.selectByExample(example);
        System.out.println(list);
    }
}
