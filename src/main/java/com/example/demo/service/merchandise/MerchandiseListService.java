package com.example.demo.service.merchandise;

import com.example.demo.dto.MerchandiseListInfo;
import com.example.demo.dto.MerchandiseSearchInfo;
import com.example.demo.dto.PaginatedResponse;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 商品一覧画面Serviceクラス
 * @author Sam
 * @create 2024-08-09 12:51 PM
 */
public interface MerchandiseListService {



  /**
   * 商品情報テーブルを全件検索し、商品一覧画面に必要な情報へ変換しては返却します。
   *
   * @return
   */
  public PaginatedResponse<MerchandiseListInfo> showMerchandiseList(Integer page, Integer size);

  /**
   * 商品情報を条件検索した結果を画面の表示用に変換して返却します。
   *
   * @param dto
   * @return
   */
  public PaginatedResponse<MerchandiseListInfo> showMerchandiseListByParam(MerchandiseSearchInfo dto);
}
