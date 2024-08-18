package com.example.demo.service.merchandise;

import com.example.demo.dto.MerchandiseRegistInfo;
import com.example.demo.constant.MerchandiseRegistResult;

/**
 * 商品登録画面Serviceクラス
 *
 * @author Sam
 * @create 2024-08-12 2:20 PM
 */
public interface MerchandiseRegistService {

  /**
   * 商品情報テーブルに登録します。
   *
   * @param merchandiseRegistInfo
   * @return
   */
  public MerchandiseRegistResult registMerchandiseInfo(MerchandiseRegistInfo merchandiseRegistInfo);
}
