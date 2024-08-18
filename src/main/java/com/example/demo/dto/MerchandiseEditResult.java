package com.example.demo.dto;

import com.example.demo.constant.MerchandiseEditMessage;
import com.example.demo.entity.MerchandiseInfo;
import lombok.Data;

/**
 * 商品編集結果DTOクラス
 *
 * @author Sam
 * @create 2024-08-13 3:56 PM
 */

@Data
public class MerchandiseEditResult {

  /** 商品更新結果 */
  private MerchandiseInfo updateMerchandiseInfo;

  /** 商品更新結果メッセージEnum */
  private MerchandiseEditMessage updateMessage;
}
