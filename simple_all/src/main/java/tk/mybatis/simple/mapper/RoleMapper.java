package tk.mybatis.simple.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.session.RowBounds;
import tk.mybatis.simple.model.SysRole;

import java.util.List;

@CacheNamespaceRef(RoleMapper.class)
public interface RoleMapper {

    @Select({"select id,role_name roleName, enabled,create_by createBy,create_time createTime",
            "from sys_role",
            "where id = #{id}"})
    SysRole selectById(Long id);

    @Results(id = "roleResultMap", value = {
            @Result(property = "id", column = "id", id=true),
            @Result(property = "roleName", column = "role_name"),
            @Result(property = "enabled", column = "enabled"),
            @Result(property = "createBy", column = "create_by"),
            @Result(property = "createTime", column = "create_time")
    })
    @Select("select id,role_name,enabled,create_by,create_time from sys_role where id = #{id}")
    SysRole selectById2(Long id);

    @ResultMap("roleResultMap")
    @Select("select * from sys_role")
    List<SysRole> selectAll();

    List<SysRole> selectAll(RowBounds rowBounds);

    @Insert({"insert into sys_role(id, role_name,enabled, create_by, create_time)",
            "values(#{id}, #{roleName}, #{enabled}, #{createBy}, #{createTime, jdbcType=TIMESTAMP}})"})
    int insert(SysRole sysRole);

    @Insert({"insert into sys_role(role_name, enabled, create_by, create_time)"})
    @Options(useGeneratedKeys = true, keyProperty = "id") //这一行是用来返回自增主键
    int insert2(SysRole sysRole);

    @Insert({"insert into sys_role(role_name, enabeld, create_by, create_time)"})
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", resultType = Long.class, before = false)//返回插入的主键信息  before=false 等同于 order=after
    int insert3(SysRole sysRole);

    @Update({"update sys_role",
    "set role_name = #{role_name}",
            "enabled = #{enabled}",
            "create_by = #{createBy}",
            "craete_time = #{createTime,jdbcType=TIMESTAMP}",
            "where id = #{id}"})
    int updateById(SysRole sysRole);

    @Delete("delete from sys_role where id = #{id}")
    int deleteById(Long id);

    /*获取所有将角色和对应的权限信息*/
    List<SysRole> selectAllRoleAndPrivilege();

    /*根据用户id获取用户的角色信息*/
    List<SysRole> selectRoleByUserIdChoose(Long userId);
}
