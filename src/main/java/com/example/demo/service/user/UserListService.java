package com.example.demo.service.user;

import com.example.demo.constant.UserDeleteResult;
import com.example.demo.dto.PaginatedResponse;
import com.example.demo.dto.UserListInfo;
import com.example.demo.dto.UserSearchInfo;

/**
 *  ユーザー一覧画面Serviceクラス
 * @author Sam
 * @create 2024-08-02 2:06 PM
 */
public interface UserListService {

  /**
   * ユーザー情報テーブルを全件検索し、ユーザー一覧画面に必要な情報へ変換しては返却します。
   *
   * @return ユーザー情報テーブルの全登録情報
   */
  public PaginatedResponse<UserListInfo> editUserList(Integer page, Integer size);

  /**
   * ユーザー情報を条件検索した結果を画面の表示用に変換して返却します。
   *
   * @param dto
   * @return
   */
  public PaginatedResponse<UserListInfo> editUserListByParam(UserSearchInfo dto);

  /**
   * 指定されたIDのユーザー情報を削除します。
   *
   * @param loginId
   * @return
   */
  public UserDeleteResult deleteUserInfoById(String loginId);
}
