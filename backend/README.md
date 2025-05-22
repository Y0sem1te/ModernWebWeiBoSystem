后端是SpringBoot项目在IDEA里面搭建。

1. 配置自己的maven：

可以借鉴下面博主的配置方法：

https://blog.csdn.net/rej177/article/details/131565196

2. 搭建自己的数据库：

在src/main/resource文件夹底下的application.properties里配置自己的数据库信息，把url，username, driver，password都改成自己的。

```properties
spring.datasource.type=com.alibaba.druid.pool.DruidDataSource
spring.datasource.driver-class-name=com.mysql.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/weibo?useSSL=false
spring.datasource.username=root
spring.datasource.password=root
mybatis-plus.configuration.log-impl=org.apache.ibatis.logging.stdout.StdOutImpl
```

3. 重构maven项目:

![image](https://github.com/Y0sem1te/ModernWebWeiBoSystem/tree/main/backend/readme_file/img1.png)

4. 搭建数据库：

找到backend/readme_file/database_dump.sql文件，用mysqlworkbench或navicat等软件打开，这些是所有创建和我一样的数据库的要用全部sql语句，执行一遍创建数据库。



后端就搭好了。