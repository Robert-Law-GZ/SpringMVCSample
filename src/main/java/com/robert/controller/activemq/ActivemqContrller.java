package com.robert.controller.activemq;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/amq")
public class ActivemqContrller {

    @RequestMapping(value = "/activemq", method = RequestMethod.GET)
    public ModelAndView welcome() {
        System.out.println("-------------welcome-----------");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("activemq");
        return mv;
    }

}
