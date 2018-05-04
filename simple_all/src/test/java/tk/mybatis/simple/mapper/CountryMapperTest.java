package tk.mybatis.simple.mapper;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;
import tk.mybatis.simple.model.Country;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class CountryMapperTest {

    private static SqlSessionFactory sqlSessionFactory;

    @BeforeClass
    public  static void init(){

        try {
            //通过Resources工具类将mybatis-config.xml配置文件读入Reader
            Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
            /*通过SqlSessionFactoryBuilder建造类使用Reader创建SqlSessionFactory工厂对象。在创建SqlSEssionFactory对象的过程中,*/
            /*首先解析mysqlbatis-config.xml配置文件，读取配置文件中的mapper配置后会读取全部的Mapper.xml进行具体方法的解析*/
            /*解析完成后，SqlSessionFactory就包含了所有的属性配置和执行SQL的信息*/
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSelectAll(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            List<Country> countryList = sqlSession.selectList("tk.mybatis.simple.mapper.CountryMapper.selectAll");
            printCountryList(countryList);
        }finally {
            sqlSession.close();
        }
    }

    private void printCountryList(List<Country> countryList){
        for(Country country : countryList){
            System.out.printf("%-4d%4s%4s\n", country.getId(), country.getCountryname(), country.getCountrycode());
        }
    }
}
