package com.example.demo.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 商品削除結果種別
 *
 * @author Sam
 * @create 2024-08-13 8:14 PM
 */

@Getter
@AllArgsConstructor
public enum MerchandiseDeleteResult {

  /** エラーなし */
  SUCCEED(MessageConst.MERCHANDISEDETAIL_DELETE_SUCCEED),

  /** エラーあり */
  ERROR(MessageConst.MERCHANDISEDETAIL_NON_EXISTED_LOGIN_ID);

  /** メッセージID */
  private String messageId;
}
