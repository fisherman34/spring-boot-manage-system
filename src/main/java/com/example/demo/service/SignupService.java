package com.example.demo.service;

import com.example.demo.constant.SignupResult;
import com.example.demo.dto.SignupInfo;

/**
 * ユーザー登録画面 interface
 * @author Sam
 * @create 2024-07-29 3:19 PM
 */

public interface SignupService {


  /**
   * 画面の入力情報を元にユーザー情報テーブルの新規登録を行います。
   *
   * @param dto 入力情報
   * @return 登録情報（ユーザー情報Entity）、すでに同じユーザーがある場合はEmpty
   */
  public SignupResult signup(SignupInfo dto);
}
