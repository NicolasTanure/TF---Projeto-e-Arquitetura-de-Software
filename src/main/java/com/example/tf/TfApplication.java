package com.example.tf;

import java.util.HashMap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@SpringBootApplication
public class TfApplication {

    public static void main(String[] args) {
        SpringApplication.run(TfApplication.class, args);
    }

    @PostMapping("/")
	public String getStatus(){
		return "oi";
	}

}
