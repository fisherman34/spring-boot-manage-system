package com.example.demo.service.user;

import com.example.demo.dto.UserEditResult;
import com.example.demo.dto.UserUpdateInfo;
import com.example.demo.entity.UserInfo;

import java.util.Optional;

/**
 * ユーザー編集画面Serviceクラス
 *
 * @author Sam
 * @create 2024-08-06 7:55 PM
 */
public interface UserEditService {

  /**
   * ログインIDを使ってユーザー情報テーブルを検索し、検索結果を返却します。
   *
   * @param loginId ログインID
   * @return 該当のユーザー情報テーブル登録情報
   */
  public Optional<UserInfo> searchUserInfo(String loginId);

  /**
   * ユーザー情報テーブルを更新します。
   *
   * @param userUpdateInfo ユーザー更新情報
   * @return 更新結果
   */
  public UserEditResult updateUserInfo(UserUpdateInfo userUpdateInfo);
}
