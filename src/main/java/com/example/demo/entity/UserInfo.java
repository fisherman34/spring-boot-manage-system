package com.example.demo.entity;

import com.example.demo.constant.db.AuthorityKind;
import com.example.demo.constant.db.UserStatusKind;
import com.example.demo.entity.converter.UserAuthorityConverter;
import com.example.demo.entity.converter.UserStatusConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * ユーザー情報テーブルentity
 * @author Sam
 * @create 2024-07-29 2:53 PM
 */

@Entity
@Table(name = "user_info")
@Data
@AllArgsConstructor
public class UserInfo {

  /** ログインID */
  @Id
  @Column(name = "login_id")
  private String loginId;

  /** パスワード */
  private String password;

  /** メールアドレス */
  @Column(name = "mail_address")
  private String mailAddress;

  /** ワンクタイムコード */
  @Column(name = "one_time_code")
  private String oneTimeCode;

  /** リンクタイムコード有効期限 */
  @Column(name = "one_time_code_send_time")
  private LocalDateTime oneTimeCodeSendTime;

  /** ログイン失敗回数 */
  @Column(name = "login_failure_count")
  private Integer loginFailureCount = 0;

  /** アカウントロック日時 */
  @Column(name = "account_locked_time")
  private LocalDateTime accountLockedTime;

  /** ユーザー状態種別 */
  @Column(name = "is_disabled")
  @Convert(converter = UserStatusConverter.class)
  private UserStatusKind userStatusKind;

  /** ユーザー権限種別 */
  @Column(name = "authority")
  @Convert(converter = UserAuthorityConverter.class)
  private AuthorityKind authorityKind;

  /** 本登録完了有無(仮登録状態ならfalse)*/
  @Column(name = "is_signup_completed")
  private boolean signupCompleted;

  /** 登録日時 */
  @Column(name = "create_time")
  private LocalDateTime createTime;

  /** 最終更新日時 */
  @Column(name = "update_time")
  private LocalDateTime updateTime;

  /** 最終更新ユーザー */
  @Column(name = "update_user")
  private String updateUser;


  // spring boot data jpa需要一个空的构造器
  public UserInfo() {
  }

  /**
   * ログイン失敗回数をインクリメントする
   *
   * @return
   */
  public UserInfo incrementLoginFailureCount() {
    return new UserInfo(loginId, password, mailAddress, oneTimeCode, oneTimeCodeSendTime, ++loginFailureCount, accountLockedTime, userStatusKind,
            authorityKind, signupCompleted, createTime, updateTime, updateUser);
  }

  /**
   * ログイン失敗情報をリセットする
   *
   * @return
   */
  public UserInfo resetLoginFailureInfo() {
    return new UserInfo(loginId, password, mailAddress, oneTimeCode, oneTimeCodeSendTime, 0, null,
            userStatusKind, authorityKind, signupCompleted, createTime, updateTime, updateUser);
  }

  /**
   * アカウントロック状態に更新する
   * @return ログイン失敗回数、アカウント売ロック日時が更新されてUserInfo
   */
  public UserInfo updateAccountLocked() {
    return new UserInfo(loginId, password, mailAddress, oneTimeCode, oneTimeCodeSendTime,0, LocalDateTime.now(), userStatusKind, authorityKind,
            signupCompleted, createTime, updateTime, updateUser);
  }
}
