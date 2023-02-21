package com.hateoasmapper.spring.spring;

import com.hateoasmapper.spring.config.HateoasMapperConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(value = {HateoasMapperConfig.class})
public class HateoasMapperApplication {

  public static void main(String[] args) {
    SpringApplication.run(HateoasMapperApplication.class, args);
  }

}
