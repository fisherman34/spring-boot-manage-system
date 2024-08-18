package com.example.demo.form;

import com.example.demo.constant.db.AuthorityKind;
import com.example.demo.constant.db.UserStatusKind;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

/**
 * @author Sam
 * @create 2024-08-01 9:36 PM
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserListForm {

  /** ログインID */
  private String loginId;

  /** アカウント状態種別 */
  private UserStatusKind userStatusKind;

  /** ユーザー権限種別 */
  private AuthorityKind authorityKind;

  private String strUserStatus;

  private String strAuthority;


  public UserListForm(String loginId, UserStatusKind userStatusKind, AuthorityKind authorityKind) {
    this.loginId = loginId;
    this.userStatusKind = userStatusKind;
    this.authorityKind = authorityKind;
    this.strUserStatus = userStatusKind != null ? userStatusKind.name() : null;
    this.strAuthority = authorityKind != null ? authorityKind.name() : null;
  }

  /** ユーザー一覧画面から選択されたログインID */
  private String selectedLoginId;

  /**
   * ユーザー一覧画面から選択されたログインIDをクリアします。
   *
   * @return
   */
  public UserListForm clearSelectedLoginId() {
    this.selectedLoginId = null;

    return this;
  }

  public void setUserStatusKind(UserStatusKind userStatusKind) {
    this.userStatusKind = userStatusKind;
    this.strUserStatus = userStatusKind != null ? userStatusKind.name() : null;
  }

  public void setAuthorityKind(AuthorityKind authorityKind) {
    this.authorityKind = authorityKind;
    this.strAuthority = authorityKind != null ? authorityKind.name() : null;
  }
}
