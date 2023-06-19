package ru.home.proj.tasklist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TaskListSpringBootAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaskListSpringBootAppApplication.class, args);

//        ConfigurableApplicationContext context = SpringApplication.run(TaskListSpringBootAppApplication.class, args);
//        var encoder = context.getBean("passwordEncoder", PasswordEncoder.class);
//        System.out.println(encoder.encode("admin"));
    }

}
