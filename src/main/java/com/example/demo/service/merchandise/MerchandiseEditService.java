package com.example.demo.service.merchandise;

import com.example.demo.dto.MerchandiseEditResult;
import com.example.demo.dto.MerchandiseUpdateInfo;
import com.example.demo.entity.MerchandiseInfo;

import java.util.Optional;

/**
 * 商品編集画面Serviceクラス
 *
 * @author Sam
 * @create 2024-08-12 8:48 PM
 */
public interface MerchandiseEditService {



  /**
   * 商品IDを使って商品情報テーブルを検索し、検索結果を返却します。
   *
   * @param MerchandiseId
   * @return
   */
  public Optional<MerchandiseInfo> searchMerchandiseInfo(String MerchandiseId);

  /**
   * 商品情報テーブルを更新します。
   *
   * @param merchandiseUpdateInfo
   * @return
   */
  public MerchandiseEditResult updateMerchandiseInfo(MerchandiseUpdateInfo merchandiseUpdateInfo);
}
