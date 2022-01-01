package com.poc.sg.config;

import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Random;

@Slf4j
@Configuration
public class TestConfig {

    private static Faker faker;

    public static Faker getFaker() {
        if (Objects.isNull(faker)) {
            var seed = Timestamp.valueOf(LocalDateTime.now()).getTime();

            log.info("M=getFaker, seed={}", seed);

            final Random generate = new Random(seed);
            faker = new Faker(generate);

            return faker;
        }
        return faker;
    }
}
