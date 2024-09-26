package cn.edu.nju.ics.qtosbase;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class QtosBaseApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(QtosBaseApplication.class)
                .headless(false)
                .run(args);
    }

}
