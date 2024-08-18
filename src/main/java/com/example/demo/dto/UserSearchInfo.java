package com.example.demo.dto;

import com.example.demo.constant.db.AuthorityKind;
import com.example.demo.constant.db.UserStatusKind;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ユーザー一覧画面検索用DTOクラス
 *
 * @author Sam
 * @create 2024-08-05 8:28 PM
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSearchInfo {

  /** ログインID */
  private String loginId;

  /** ユーザー状態種別 */
  private UserStatusKind userStatusKind;

  /** ユーザー権限種別 */
  private AuthorityKind authorityKind;

  /** Paginationのpage */
  private Integer page = 0;

  /** Paginationのsize */
  private Integer size = 10;
}
