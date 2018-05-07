mybatis 执行插入数据时
sqlSessionFactory.openSession()是不会自动提交的

所以将数据提交到数据库的时候，需要执行commit()。

### 如果需要获取主键值
通过useGeneratedKeys设置为true,mybaits会使用jdbc的getGeneratedKeys方法获取数据库内部生产的主键。
获得主键后将其赋值给keyProperty配置的id属性。有多个列时用逗号个改，并且设置keyColumn属性，按顺序一一列出。

```$xslt
<insert id="insert2" useGeneratedKeys="true" keyProperty="id">
```
即可以在插入数据库后通过user.getId()获取主键的值。如果没有设置上述属性，返回结果则为null.