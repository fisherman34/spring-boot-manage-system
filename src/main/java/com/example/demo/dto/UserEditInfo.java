package com.example.demo.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * ユーザー編集情報DTOクラス
 *
 * @author Sam
 * @create 2024-08-06 8:06 PM
 */

@Data
public class UserEditInfo {

  /** ログインID */
  private String loginId;

  /** ログイン失敗回数 */
  private int loginFailureCount;

  /** アカウントロック日時 */
  private LocalDateTime accountLockedTime;
}

