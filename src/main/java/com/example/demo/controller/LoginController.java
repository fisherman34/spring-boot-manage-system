package com.example.demo.controller;

import com.example.demo.constant.ModelKey;
import com.example.demo.constant.UrlConst;
import com.example.demo.constant.ViewNameConst;
import com.example.demo.form.LoginForm;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * ログインコントローラー
 *
 * @author Sam
 * @create 2024-07-26 9:01 PM
 */

@Controller
@RequiredArgsConstructor
public class LoginController {

//  /** ログイン画面service */
//  private final LoginService service;
//
//  /** PasswordEncoder */
//  private final PasswordEncoder passwordEncoder;
//
//  /** メッセージソース */
//  private final MessageSource messageSource;

  /** セッション情報 */
  private final HttpSession session;

  /**
   * 初期表示
   *
   * @param model
   * @param form
   * @return
   */
  @GetMapping(UrlConst.LOGIN)
  public String view(Model model, LoginForm form) {

    return ViewNameConst.LOGIN;
  }

  /**
   * ログインエラー画面表示
   *
   * @param model モデル
   * @param form 入力情報
   * @return 表示画面
   */
  @GetMapping(value = UrlConst.LOGIN, params = "error")
  public String viewWithError(Model model, LoginForm form) {
    var errorInfo =  (Exception) session.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    model.addAttribute(ModelKey.MESSAGE, errorInfo.getMessage());
    model.addAttribute(ModelKey.IS_ERROR, true);
    return ViewNameConst.LOGIN;
  }


}
