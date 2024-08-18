package com.example.demo.service.merchandise;

import com.example.demo.constant.MerchandiseDeleteResult;
import com.example.demo.entity.MerchandiseInfo;

import java.util.Optional;

/**
 * ユーザー詳細画面Serviceクラス
 *
 * @author Sam
 * @create 2024-08-12 8:48 PM
 */
public interface MerchandiseDetailService {



  /**
   * 商品IDを使って商品情報テーブルを検索し、検索結果を返却します。
   *
   * @param merchandiseId
   * @return
   */
  public Optional<MerchandiseInfo> searchMerchandiseInfo(String merchandiseId);

  /**
   * 指定されたIDの商品情報を削除します。
   *
   * @param merchandiseId
   * @return
   */
  public MerchandiseDeleteResult deleteMerchandiseInfoById(String merchandiseId);


}
