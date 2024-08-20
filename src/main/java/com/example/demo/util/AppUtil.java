package com.example.demo.util;

import com.example.demo.constant.db.AuthorityKind;
import com.example.demo.constant.db.UserStatusKind;
import com.example.demo.dto.PaginatedResponse;
import org.springframework.context.MessageSource;

import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;

/**
 * アプリケーション共通クラス
 * @author Sam
 * @create 2024-07-29 9:23 PM
 */
public class AppUtil {

  /**
   * メッセージIDからメッセージを取得する。
   *
   * @param messageSource メッセージソース
   * @param key メッセージキー
   * @param params 置換文字群
   * @return メッセージ
   */
  public static String getMessage(MessageSource messageSource, String key, Object... params) {
    return messageSource.getMessage(key, params, Locale.JAPAN);
  }

  /**
   * DBのLIKE検索に、パラメーターにワイルドカードを付与します。
   *
   * @param param
   * @return
   */
  public static String addWildcard(String param) {
    return "%" + param + "%";
  }

  /**
   * リダイレクト先のURLを受け取って、リダイレクトURLを作成します。
   *
   * @param url
   * @return
   */
  public static String doRedirect(String url) {
    return "redirect:" + url;
  }

  /**
   * 文字列からUserStatusKind enumへの変換map
   *
   * @param status
   * @return
   */
  public static UserStatusKind UserStatusDict(String status) {
    HashMap<String, UserStatusKind> userStatusDict = new HashMap<>();

    userStatusDict.put("", null);
    userStatusDict.put("ENABLED", UserStatusKind.ENABLED);
    userStatusDict.put("DISABLED", UserStatusKind.DISABLED);

    return userStatusDict.get(status);
  }

  /**
   * 文字列からAuthorityKind enumへの変換map
   *
   * @param status
   * @return
   */
  public static AuthorityKind AuthorityStatusDict(String status) {
    HashMap<String, AuthorityKind> userStatusDict = new HashMap<>();

    userStatusDict.put("", null);
    userStatusDict.put("UNKNOWN", AuthorityKind.UNKNOWN);
    userStatusDict.put("ITEM_WATCHER", AuthorityKind.ITEM_WATCHER);
    userStatusDict.put("ITEM_MANAGER", AuthorityKind.ITEM_MANAGER);
    userStatusDict.put("ITEM_AND_USER_MANAGER", AuthorityKind.ITEM_AND_USER_MANAGER);

    return userStatusDict.get(status);
  }

  /**
   * 必要に応じて空のPaginatedResponseを作成するヘルパーメソッド
   * @param page
   * @param size
   * @return
   */
  public static <T> PaginatedResponse<T> createEmptyPaginatedResponse(Integer page, Integer size) {
    return new PaginatedResponse<>(
            Collections.emptyList(),
            page,
            size,
            0L,
            0
    );
  }
}
