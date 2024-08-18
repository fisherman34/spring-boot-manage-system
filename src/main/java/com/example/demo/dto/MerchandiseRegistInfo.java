package com.example.demo.dto;

import lombok.Data;

/**
 * @author Sam
 * @create 2024-08-12 2:10 PM
 */

@Data
public class MerchandiseRegistInfo {

  /** 商品ID */
  private String merchandiseId;

  /** 商品名 */
  private String merchandiseName;

  /** 単価 */
  private Float unitPrice;

  /** 在庫数量 */
  private Integer quantity;

  /** 更新ユーザーID */
  private String updateUserId;
}
