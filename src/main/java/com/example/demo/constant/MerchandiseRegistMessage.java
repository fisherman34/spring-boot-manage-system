package com.example.demo.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 商品登録結果メッセージEnumクラス
 * @author Sam
 * @create 2024-08-12 2:52 PM
 */

@Getter
@AllArgsConstructor
public enum MerchandiseRegistMessage {

  /** 更新成功 */
  SUCCEED(MessageConst.MERCHANDISE_REGIST_SUCCEED),

  /** 更新失敗 */
  FAILED(MessageConst.MERCHANDISE_REGIST_FAILED);

  /** メッセージID */
  private String messageId;
}
