package com.example.demo.form;

import lombok.Data;

/**
 * ログイン画面form
 *
 * @author Sam
 * @create 2024-07-26 9:15 PM
 */

@Data
public class LoginForm {

  /** ログインID */
  private String loginId;

  /** パスワード */
  private String password;
}
