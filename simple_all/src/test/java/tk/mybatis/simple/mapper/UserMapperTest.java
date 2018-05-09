package tk.mybatis.simple.mapper;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;
import tk.mybatis.simple.model.SysRole;
import tk.mybatis.simple.model.SysUser;

import java.util.Date;
import java.util.List;

public class UserMapperTest extends BaseMapperTest {

    //@Test
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

    //@Test
    public void TestInsert3(){
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            //创建一个user对象
            SysUser user = new SysUser();
            user.setUserName("test3");
            user.setUserPassword("3333");
            user.setUserEmail("test3@mybatis.hk");
            user.setUserInfo("test3 info");
            user.setHeadImg(new byte[]{1,2,3});
            user.setCreateTime(new Date());

            int result = userMapper.insert3(user);

            System.out.println("id="+user.getId());
            Assert.assertEquals(1, result);
            //因为id写回，所以id不能为null
            Assert.assertNotNull(user.getId());
        }finally {
            sqlSession.commit();
            sqlSession.close();
        }
    }

    //@Test
    public  void testUpdateById(){
        SqlSession sqlSession = getSqlSession();
        try{
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            SysUser user = userMapper.selectById(1L);
            Assert.assertNotNull(user);
            Assert.assertEquals("admin", user.getUserName());

            //修改用户名
            user.setUserName("public");
            user.setUserEmail("public@mybait.com");

            int result = userMapper.updateById(user);
            Assert.assertEquals(1, result);

            user = userMapper.selectById(1L);
            System.out.println("user.getUserName():"+user.getUserName());

        }finally {
            sqlSession.commit();
            sqlSession.close();
        }
    }

    //@Test
    public void testDeleteById(){

        SqlSession sqlSession = getSqlSession();

        try{
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

            SysUser user = userMapper.selectById(10L);

            Assert.assertNotNull(user);

            int delResult = userMapper.deleteById(10L);
            Assert.assertEquals(1, delResult);

        }finally {
            sqlSession.commit();
            sqlSession.close();
        }
    }

    //@Test
    public void testSelectRoleByUserIdAndRoleEnabled(){
        SqlSession sqlSession = getSqlSession();

        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<SysRole> userList = userMapper.selectRolesByUserIdAndRoleEnabled(1L, 1);

            Assert.assertNotNull(userList);

            Assert.assertTrue(userList.size() > 0);

        }finally {
            sqlSession.close();
        }
    }

    //@Test
    public void testselectRolesByUserAndRole(){
        SqlSession sqlSession = getSqlSession();

        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            SysUser user = userMapper.selectById(1L);
            SysRole role = sqlSession.getMapper(RoleMapper.class).selectById(1L);
            List<SysRole> userList = userMapper.selectRolesByUserAndRole(user, role);

            Assert.assertNotNull(userList);

            Assert.assertTrue(userList.size() > 0);

        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectByIdOrUserName(){
        SqlSession sqlSession = getSqlSession();

        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            SysUser user = new SysUser();
            //两个参数均存在，只判断第一个
            user.setId(1L);
            user.setUserName("admin");

            SysUser sysUser = userMapper.selectByIdOrUserName(user);

            Assert.assertNotNull(sysUser);


        }finally {
            sqlSession.close();
        }
    }
}
