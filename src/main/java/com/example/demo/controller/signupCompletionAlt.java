package com.example.demo.controller;

import com.example.demo.constant.UrlConst;
import com.example.demo.constant.ViewNameConst;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Sam
 * @create 2024-08-20 7:11 PM
 */

@Controller
public class signupCompletionAlt {

  @GetMapping(UrlConst.SIGNUP_COMPLETION_ALT)
  public String view() {
    return ViewNameConst.SIGNUP_COMPLETION_ALT;
  }
}
