package com.example.demo.controller;

import com.example.demo.constant.UrlConst;
import com.example.demo.constant.ViewNameConst;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * ユーザー登録情報確認結果画面Controllerクラス
 *
 * @author Sam
 * @create 2024-08-08 9:36 AM
 */

@Controller
public class SignupCompletionController {


  @GetMapping(UrlConst.SIGNUP_COMPLETION)
  public String view(Module module) {
    return ViewNameConst.SIGNUP_COMPLETION;
  }
}
