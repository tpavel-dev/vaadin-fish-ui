package kz.kcell.apps.fish.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
        scanBasePackages = {"kz.kcell"}
)
public class VaadinBootApp {// extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(VaadinBootApp.class, args);
    }

}