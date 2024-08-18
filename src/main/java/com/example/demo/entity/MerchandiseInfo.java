package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 商品情報テーブルentity
 *
 * @author Sam
 * @create 2024-08-09 9:53 AM
 */

@Entity
@Table(name = "merchandise_info")
@Data
@AllArgsConstructor
public class MerchandiseInfo {

  /** 商品ID */
  @Id
  @Column(name = "merchandise_id")
  private String merchandiseId;

  /** 商品名 */
  @Column(name = "merchandise_name")
  private String merchandiseName;

  /** 単価 */
  @Column(name = "unit_price")
  private Float unitPrice;

  /** 在庫数量 */
  private Integer quantity;

  /** 登録日時 */
  @Column(name = "create_time")
  private LocalDateTime createTime;

  /** 最終更新日時 */
  @Column(name = "update_time")
  private LocalDateTime updateTime;

  /** 最終更新ユーザー */
  @Column(name = "update_user")
  private String updateUser;


  public MerchandiseInfo() {
  }
}
