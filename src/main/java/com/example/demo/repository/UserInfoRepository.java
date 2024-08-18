package com.example.demo.repository;

import com.example.demo.constant.db.AuthorityKind;
import com.example.demo.constant.db.UserStatusKind;
import com.example.demo.entity.UserInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * ユーザー情報テーブルDAO
 *
 * @author Sam
 * @create 2024-07-29 3:07 PM
 */

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, String> {


  /**
   * ログインIDの部分一致検索を行います。
   *
   * @param loginId
   * @param pageable
   * @return
   */
  Page<UserInfo> findByLoginIdLike(String loginId, Pageable pageable);

  /**
   * ログインID、アカウント状態の項目を使って検索を行います。
   *
   * @param loginId
   * @param userStatusKind
   * @param pageable
   * @return
   */
  Page<UserInfo> findByLoginIdLikeAndUserStatusKind(String loginId, UserStatusKind userStatusKind, Pageable pageable);

  /**
   * ログインID、権限の項目を使って検索を行います。
   *
   * @param loginId
   * @param authorityKind
   * @param pageable
   * @return
   */
  Page<UserInfo> findByLoginIdLikeAndAuthorityKind(String loginId, AuthorityKind authorityKind, Pageable pageable);

  /**
   * ログインID、アカウント状態、権限の項目を使って検索を行います。
   *
   * @param loginId
   * @param userStatusKind
   * @param authorityKind
   * @param pageable
   * @return
   */
  Page<UserInfo> findByLoginIdLikeAndUserStatusKindAndAuthorityKind(String loginId, UserStatusKind userStatusKind,
                                                                    AuthorityKind authorityKind, Pageable pageable);
}
