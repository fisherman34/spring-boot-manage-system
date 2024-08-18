package com.example.demo.form;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.Data;

/**
 * 商品情報登録画面form
 *
 * @author Sam
 * @create 2024-08-09 9:57 PM
 */

@Data
public class MerchandiseRegistForm {

  /** 商品ID */
  private String merchandiseId;

  /** 商品名 */
  private String merchandiseName;

  /** 単価 */
  private Float unitPrice;

  /** 在庫数量 */
  private Integer quantity;
}
