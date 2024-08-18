package com.example.demo.form;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 商品詳細情報画面form
 *
 * @author Sam
 * @create 2024-08-12 10:11 PM
 */

@Data
public class MerchandiseDetailForm {

  /** 商品ID */
  private String merchandiseId;

  /** 商品名 */
  private String merchandiseName;

  /** 単価 */
  private Float unitPrice;

  /** 在庫数量 */
  private Integer quantity;


  /** 最終更新日時 */
  private LocalDateTime updateTime;

  /** 最終更新ユーザー */
  private String updateUser;
}
