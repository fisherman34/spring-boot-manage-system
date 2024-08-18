package com.example.demo.form;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * 商品一覧画面Formクラス
 *
 * @author Sam
 * @create 2024-08-09 2:18 PM
 */

@Data
public class MerchandiseListForm {

  /** 商品ID */
  @Length(min = 3, max = 20)
  private String merchandiseId;

  /** 商品名 */
  @Length(min = 3, max = 20)
  private String merchandiseName;

  /** 商品一覧画面から選択されたログインID */
  private String selectedMerchandiseId;
}
