package com.kro.business.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * locustom - com.kro.business.controller
 * <p>
 * Created by Thomas Croguennec on 04/01/17.
 * On 04/01/17
 */
@Slf4j
@Controller
public class HomeCtrl {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Model model) {
        log.info("index.html");
        model.addAttribute("fragments", "fragments");
        return "index";
    }
}
