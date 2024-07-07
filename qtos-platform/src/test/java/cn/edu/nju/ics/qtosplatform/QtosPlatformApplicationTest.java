package cn.edu.nju.ics.qtosplatform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.devtools.restart.RestartScope;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.MySQLContainer;

@SpringBootConfiguration
public class QtosPlatformApplicationTest {
    @Bean
    @RestartScope
    @ServiceConnection
    public MySQLContainer<?> mysql() {
        return new MySQLContainer<>("mysql:8");
    }

    public static void main(String[] args) {
        SpringApplication
                .from(QtosPlatformApplication::main)
                .with(QtosPlatformApplicationTest.class)
                .run(args);
    }
}