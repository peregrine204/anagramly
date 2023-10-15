package org.anagramly;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
@SpringBootApplication
public class AnagramlyRunner {
    private static final List<String> ALLOWED_INPUTS = List.of("1", "2", "0");

    public static void main(String[] args) {
        SpringApplication.run(AnagramlyRunner.class);
    }
}