package com.example.demo.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 商品更新結果メッセージEnumクラス
 *
 * @author Sam
 * @create 2024-08-13 3:59 PM
 */

@Getter
@AllArgsConstructor
public enum MerchandiseEditMessage {

  /** 更新成功 */
  SUCCEED(MessageConst.MERCHANDISEEDIT_UPDATE_SUCCEED),

  /** 更新失敗 */
  FAILED(MessageConst.MERCHANDISEEDIT_UPDATE_FAILED);

  /** メッセージID */
  private String messageId;

}
