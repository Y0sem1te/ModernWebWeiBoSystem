package org.example.weibo_site;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("org.example.weibo_site.mapper")
public class WeiboSiteApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeiboSiteApplication.class, args);
    }

}
