package com.msys.reactboot.web;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * WebController
 */
@Controller
public class WebController implements ErrorController {

    @GetMapping(value = { "", "/error" })
    public String index() {
        return "index.html";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }

}