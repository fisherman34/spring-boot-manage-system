# Spring Boot勉強用リポジトリ
Spring Bootで作られる商品管理システム、デモウェブサイトのアドレスは<a href="https://manage.651532.xyz/login"> https://manage.651532.xyz/login </a> です。デモアカウントは user0003、パスワードは abc12345 です。

## 1. 紹介
商品、顧客管理(登録/更新/削除/一覧)ができる、商品管理システムWebアプリを作成します。

### 1.1 利用技術
**Spring Boot**  
&nbsp;&nbsp;&nbsp;&nbsp;Webアプリ等を簡単に作成できるJavaのフレームワーク。  
**Thymeleaf**  
&nbsp;&nbsp;&nbsp;&nbsp;HTML画面を作成できるテンプレートエンジン。  
**Bootstrap**  
&nbsp;&nbsp;&nbsp;&nbsp;CSSのフレームワーク。デザインに使う。  
**Maven**  
&nbsp;&nbsp;&nbsp;&nbsp;ビルドツール。  
**MySQL**  
&nbsp;&nbsp;&nbsp;&nbsp;オープンソースのデータベース。

## 2. DB設計

**テーブル一覧**  

| No |        テーブル名        | 説明         | 
|----|:-------------------:|------------|
| 1  |      user_info      | ユーザー情報を管理する|
| 2  |  merchandise_info   | 商品情報を管理する  |
| 3  |   authority_kind    | ユーザー権限を説明する|

**テーブル定義(user_info)**  

| No |         列名          | 説明         | 
|----|:-------------------:|------------|
| 1  |      login_id       | ログインID     |
| 2  |      password       | パスワード      |
| 3  | account_locked_time | アカウントロック日時 |
| 4  |     is_disabled     | ユーザー状態種別   |
| 5  | login_failure_count | ログイン失敗回数   |
| 6  |      authority      | ユーザー権限種別   |
| 7  |     create_time     | 登録日時       |
| 8  |     update_time     | 最終更新日時     |
| 9  |     update_user     | 最終更新ユーザー   |
| 10 |    mail_address     | メールアドレス    |
| 11 |    one_time_code    | ワンクタイムコード  |
| 12 | is_signup_completed | 本登録完了有無    |

**テーブル定義(merchandise_info)**

| No |         列名         | 説明       | 
|----|:------------------:|----------|
| 1  |   merchandise_id   | 商品ID     |
| 2  |  merchandise_name  | 商品名      |
| 3  |     unit_price     | 単価       |
| 4  |      quantity      | 数量       |
| 5  |    create_time     | 登録日時 |
| 6  |    update_time     | 最終更新日時 |
| 7  |    update_user     | 最終更新ユーザー     |

## 3. 完成画面キャプチャ

**商品管理画面**

https://github.com/user-attachments/assets/4e2ab509-64aa-44e9-89bd-902a41166be4

**ユーザー管理画面**

https://github.com/user-attachments/assets/a8ccb588-2eb4-4a55-b011-6d65f0e1f839
