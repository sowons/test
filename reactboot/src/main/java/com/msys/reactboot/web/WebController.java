package com.msys.reactboot.web;

import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {

    public WebController(ErrorAttributes errorAttributes) {
    }

    @RequestMapping("/error")
    public String handleError() {
        // 오류 처리 로직
        return "Error occurred"; // 예시 반환값
    }
}