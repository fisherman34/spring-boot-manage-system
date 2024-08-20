package com.example.demo.constant;

/**
 * URL定数クラス
 *
 * @author Sam
 * @create 2024-07-31 10:44 AM
 */
public class UrlConst {

  /** ルートディレクトリ */
  public static final String ROOT = "/";

  /** ログイン画面 */
  public static final String LOGIN = "/login";

  /** ユーザー登録画面 */
  public static final String SIGNUP = "/signup";

  /** ユーザー登録情報確認画面 */
  public static final String SIGNUP_CONFIRM = "/signupConfirm";

  /** ユーザー登録情報確認結果画面 */
  public static final String SIGNUP_COMPLETION = "/signupCompletion";

  public static final String SIGNUP_COMPLETION_ALT = "/signupCompletionAlt";

  /** メニュー画面 */
  public static final String MENU = "/menu";

  /** ユーザー一覧画面 */
  public static final String USER_LIST = "/userList";

  /** ユーザー編集画面 */
  public static final String USER_EDIT = "/userEdit";

  /** 商品一覧画面 */
  public static final String MERCHANDISE_LIST = "/merchandiseList";

  /** 商品情報登録画面 */
  public static final String MERCHANDISE_REGIST = "/merchandiseRegist";

  /** 商品情報詳細画面 */
  public static final String MERCHANDISE_DETAIL = "/merchandiseDetail";

  /** 商品情報編集画面 */
  public static final String MERCHANDISE_EDIT = "/merchandiseEdit";

  /** 商品編集画面_成功時 */
  public static final String MERCHANDISE_EDIT_COMPLETION = "/merchandiseEditCompletion";

  /** 商品編集画面（削除）_エラー時 */
  public static final String MERCHANDISE_DELETE_ERROR = "/merchandiseDeleteError";

  /** 商品編集画面（削除）_成功時 */
  public static final String MERCHANDISE_DELETE_COMPLETION = "/merchandiseDeleteCompletion";

  /** 認証不要画面 */
  public static final String[] NO_AUTHENTICATION = { LOGIN, SIGNUP, SIGNUP_CONFIRM, SIGNUP_COMPLETION,
          SIGNUP_COMPLETION_ALT, "/webjars/**", "/css/**", "/js/**"};
}
