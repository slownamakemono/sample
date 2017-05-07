package com.sample.web;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.terasoluna.gfw.web.token.transaction.TransactionTokenCheck;
import org.terasoluna.gfw.web.token.transaction.TransactionTokenType;


@Controller
public class PersonController extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/results").setViewName("results");
    }

    @ModelAttribute
    public PersonForm setUpPersonForm() {
    	return new PersonForm();
    }

    @TransactionTokenCheck(value = "person", type = TransactionTokenType.BEGIN)
    @GetMapping("/")
    public String showForm(PersonForm personForm) {
        return "form";
    }

    @TransactionTokenCheck(value = "person")
    @PostMapping(value = "/", params = "save")
    public String checkPersonInfo(PersonForm personForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "form";
        }

        return "redirect:/results";
    }

    @TransactionTokenCheck(value = "person")
    @PostMapping(value = "/", params = "confirm")
    public String confirmPersonInfo(@Valid PersonForm personForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "form";
        }

        return "confirm";
    }
}
