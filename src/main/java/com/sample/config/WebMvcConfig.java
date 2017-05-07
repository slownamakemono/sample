package com.sample.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.terasoluna.gfw.web.token.transaction.TransactionTokenInterceptor;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new TransactionTokenInterceptor());
  }

  @Bean
  @ConditionalOnMissingBean
  public TokenThymeleafDialect dialect() {
    return new TokenThymeleafDialect();
  }
}
