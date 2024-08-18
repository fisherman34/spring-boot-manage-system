package com.example.demo.form;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 商品編集画面Formクラス
 *
 * @author Sam
 * @create 2024-08-06 7:53 PM
 */

@Data
public class MerchandiseEditForm {

  /** 商品ID */
  private String merchandiseId;

  /** 商品名 */
  private String merchandiseName;

  /** 単価 */

  private String unitPrice;

  /** 在庫数量 */
  private Integer quantity;

}
