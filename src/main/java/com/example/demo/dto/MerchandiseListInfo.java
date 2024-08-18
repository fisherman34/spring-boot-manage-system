package com.example.demo.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.Data;

/**
 * 商品一覧画面DTOクラス
 *
 * @author Sam
 * @create 2024-08-09 11:31 AM
 */

@Data
public class MerchandiseListInfo {

  /** 商品ID */
  private String merchandiseId;

  /** 商品名 */
  private String merchandiseName;

  /** 単価 */
  private Float unitPrice;

  /** 在庫数量 */
  private Integer quantity;
}
