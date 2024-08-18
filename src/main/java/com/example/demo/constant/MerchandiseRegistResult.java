package com.example.demo.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 商品登録結果種別
 *
 * @author Sam
 * @create 2024-08-12 2:45 PM
 */

@Getter
@AllArgsConstructor
public enum MerchandiseRegistResult {

  /* エラーなし */
  SUCCEED(MessageConst.MERCHANDISE_REGIST_SUCCEED),

  /* 既に登録済み */
  FAILURE_BY_ALREADY_EXISTED(MessageConst.MERCHANDISE_ALREADY_EXISTED),


  /* 商品登録エラー */
  FAILURE_BY_DB_ERROR(MessageConst.MERCHANDISE_REGIST_FAILED);


  String messageId;

}
