package com.jeffosmond.phase05;

import com.jeffosmond.phase05.controller.output.UserOutput;
import com.jeffosmond.phase05.dao.UserDao;
import com.jeffosmond.phase05.dao.impl.UserDaoImpl;
import org.junit.Test;

import java.io.InputStream;

/**
 * @author JeffOsmond
 * @date 2019/11/13
 * 手写Mybatis框架测试
 */
public class MybatisHandwriteTest {

    /**
     * 测试主配置文件加载：此处做的事情和SqlSessionFactoryBuilder中做的事情是一样的
     */
    @Test
    public void testInitConfigurarion() {
//        //定义全局配置文件路径
//        String resource = "phase05/mybatis-config.xml";
//        //获取文件数据流
//        InputStream inputStream = ResourcesUtils.getResourcesAsStream(resource);
//        //转换Element对象
//        Document element = DocumentUtils.readDocument(inputStream);
//        //解析Element对象为Configuration对象
//        Configuration configuration = new ConfigXMLParser().parseConfiguration(element.getRootElement());
//        System.out.println(configuration);
    }

    @Test
    public void testOwnMybatis(){
        UserDao userDao = new UserDaoImpl();
        UserOutput user = userDao.queryUserById(1);
        System.out.println(user);
    }
}
