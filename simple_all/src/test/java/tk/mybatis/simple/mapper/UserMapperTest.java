package tk.mybatis.simple.mapper;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;
import tk.mybatis.simple.model.SysUser;

import java.util.Date;

public class UserMapperTest extends BaseMapperTest {

    @Test
    public void testInsert(){
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            SysUser user = new SysUser();
            user.setUserName("test1");
            user.setUserPassword("123456");
            user.setUserEmail("test1@mybatis.hk");
            user.setUserInfo("test info");
            user.setHeadImg(new byte[]{1,2,3});
            user.setCreateTime(new Date());

            //result为执行SQL影响的行数
            int result = userMapper.insert(user);
            sqlSession.commit();
            System.out.println("result="+result);
            System.out.println("id="+user.getId());
            Assert.assertEquals(1, result);
            Assert.assertNull(user.getId());
        }finally {
            //默认的sqlsessionFactory.openSession()是不自动提交的
            //因此不手动执行commit也不会提交到数据库 //已经添加了commit
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    //@Test
    public void TestInsert2(){
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            //创建一个user对象
            SysUser user = new SysUser();
            user.setUserName("test2");
            user.setUserPassword("222222");
            user.setUserEmail("test2@mybatis.hk");
            user.setUserInfo("test2 info");
            user.setHeadImg(new byte[]{1,2,3});
            user.setCreateTime(new Date());

            int result = userMapper.insert2(user);

            System.out.println("id="+user.getId());
            Assert.assertEquals(1, result);
            //因为id写回，所以id不能为null
            Assert.assertNotNull(user.getId());
        }finally {
            sqlSession.commit();
            sqlSession.close();
        }
    }
}
