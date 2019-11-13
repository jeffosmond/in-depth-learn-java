package com.jeffosmond.phase03.dao;


import com.jeffosmond.phase03.entity.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

/**
 * @function   : 用户Dao实现类
 * @version    : v1.0
 * @author     : JeffOsmond
 * @createTime : 2019/10/6 18:17
 */
public class UserDaoImpl implements UserDao {

    private SqlSessionFactory sqlSessionFactory;

    public UserDaoImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public User findUserById(Long id) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        return sqlSession.selectOne("test.findUserById",id);
    }
}
