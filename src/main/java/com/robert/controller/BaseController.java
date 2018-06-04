package com.robert.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@ControllerAdvice
public class BaseController {

    @ModelAttribute
    private void init(){
//        System.err.println("-----------------1------------");
    }

}
