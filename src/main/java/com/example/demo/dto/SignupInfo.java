package com.example.demo.dto;

import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * ユーザー登録用のDTOクラス
 *
 * @author Sam
 * @create 2024-08-07 5:22 PM
 */

@Data
public class SignupInfo {

  /** ログインID */
  private String loginId;

  /** パスワード */
  private String password;

  /** メールアドレス */
  private String mailAddress;
}
