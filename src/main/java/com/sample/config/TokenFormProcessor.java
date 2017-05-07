package com.sample.config;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.terasoluna.gfw.web.token.transaction.TransactionToken;
import org.terasoluna.gfw.web.token.transaction.TransactionTokenInterceptor;
import org.thymeleaf.Arguments;
import org.thymeleaf.dom.Element;
import org.thymeleaf.processor.ProcessorResult;
import org.thymeleaf.processor.element.AbstractElementProcessor;

public class TokenFormProcessor extends AbstractElementProcessor {

  static final String ELEMENT_NAME_FORM = "form";

  public TokenFormProcessor() {
    super(ELEMENT_NAME_FORM);
  }

  @Override
  protected ProcessorResult processElement(Arguments arguments, Element element) {

    HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    TransactionToken nextToken = (TransactionToken) request
        .getAttribute(TransactionTokenInterceptor.NEXT_TOKEN_REQUEST_ATTRIBUTE_NAME);

    if (nextToken != null) {
      Element input = new Element("input");
      input.setAttribute("type", "hidden");
      input.setAttribute("name", TransactionTokenInterceptor.TOKEN_REQUEST_PARAMETER);
      input.setAttribute("value", nextToken.getTokenString());
      element.addChild(input);
    }

    return ProcessorResult.OK;
  }

  @Override
  public int getPrecedence() {
    return 1;
  }

}
