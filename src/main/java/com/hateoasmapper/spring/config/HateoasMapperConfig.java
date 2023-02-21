package com.hateoasmapper.spring.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@ComponentScan(basePackages = "com.hateoasmapper")
@EnableMongoRepositories(value = "com.hateoasmapper.repository")
@EntityScan(value = "com.hateoasmapper.entity")
public class HateoasMapperConfig {
}
