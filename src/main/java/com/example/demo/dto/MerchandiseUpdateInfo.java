package com.example.demo.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 商品更新情報DTOクラス
 *
 * @author Sam
 * @create 2024-08-13 3:40 PM
 */

@Data
public class MerchandiseUpdateInfo {

  /** 商品ID */
  private String merchandiseId;

  /** 商品名 */
  private String merchandiseName;

  /** 単価 */
  private Float unitPrice;

  /** 在庫数量 */
  private Integer quantity;


  /** 最終更新ユーザー */
  private String updateUser;
}
