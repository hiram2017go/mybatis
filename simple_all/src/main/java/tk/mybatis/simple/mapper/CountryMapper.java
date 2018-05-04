package tk.mybatis.simple.mapper;

import tk.mybatis.simple.model.Country;

import java.util.List;

public interface CountryMapper {

    public List<Country> selectAll();
}
