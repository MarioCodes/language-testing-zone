package es.msanchez.kotlin.skeleton.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = { "es.msanchez.kotlin.playzone.**.*" })
public class SpringConfig {
}