package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 商品一覧画面検索用DTOクラス
 *
 * @author Sam
 * @create 2024-08-09 11:31 AM
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MerchandiseSearchInfo {

  /** 商品ID */
  private String merchandiseId;

  /** 商品名 */
  private String merchandiseName;

  private Integer page = 0;

  private Integer size = 10;


}
