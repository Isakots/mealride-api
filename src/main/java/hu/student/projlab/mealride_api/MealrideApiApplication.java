package hu.student.projlab.mealride_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


@SpringBootApplication
public class MealrideApiApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(MealrideApiApplication.class, args);
    }

}
