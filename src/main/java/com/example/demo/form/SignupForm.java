package com.example.demo.form;

import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * ユーザー登録画面form
 *
 * @author Sam
 * @create 2024-07-26 9:15 PM
 */

@Data
public class SignupForm {

  /** ログインID */
  @Length(min = 8, max = 20)
  private String loginId;

  /** パスワード */
  @Length(min = 8, max = 20)
  private String password;

  /** メールアドレス */
  @Length(max = 100)
  @Pattern(regexp = "^[A-Za-z0-9+_.-]+@([A-Za-z0-9][A-Za-z0-9-]*[A-Za-z0-9]*\\.)+[A-Za-z]{2,}$", message = "{signup.invalidMailAddress}")
  private String mailAddress;
}
