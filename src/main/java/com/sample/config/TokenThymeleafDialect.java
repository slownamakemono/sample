package com.sample.config;

import java.util.LinkedHashSet;
import java.util.Set;

import org.thymeleaf.dialect.AbstractDialect;
import org.thymeleaf.processor.IProcessor;

public class TokenThymeleafDialect extends AbstractDialect {

  public static final String PREFIX = "";

  public TokenThymeleafDialect() {
    super();
  }

  @Override
  public String getPrefix() {
    return PREFIX;
  }

  @Override
  public Set<IProcessor> getProcessors() {
    final Set<IProcessor> processors = new LinkedHashSet<IProcessor>();
    processors.add(new TokenFormProcessor());
    return new LinkedHashSet<IProcessor>(processors);
  }

}
