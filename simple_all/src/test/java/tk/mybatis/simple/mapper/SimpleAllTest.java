package tk.mybatis.simple.mapper;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import tk.mybatis.simple.model.Country;
import tk.mybatis.simple.model.SysUser;

import java.util.List;

public class SimpleAllTest extends BaseMapperTest {


    @Test
    public void testSelectAll(){
        SqlSession sqlSession = getSqlSession();

        try {
            List<Country> countryList = sqlSession.selectList("tk.mybatis.simple.mapper.CountryMapper.selectAll");
        }finally {
            sqlSession.close();
        }
    }

    public void printCountryList(List<Country> countryList){
        for(Country country : countryList){
            System.out.printf("%-4d%4s%4s\n", country.getId(), country.getCountryname(), country.getCountrycode());
        }
    }

}
