package com.example.demo.repository;

import com.example.demo.entity.MerchandiseInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 商品情報テーブルDAO
 * @author Sam
 * @create 2024-08-09 12:55 PM
 */

@Repository
public interface MerchandiseInfoRepository extends JpaRepository<MerchandiseInfo, String> {

  /**
   * 商品IDの部分一致検索を行います。
   *
   * @param merchandiseId
   * @return
   */
  Page<MerchandiseInfo> findByMerchandiseIdLike(String merchandiseId, Pageable pageable);

  /**
   * 商品ID、商品名の項目を使って検索を行います。
   *
   * @param merchandiseId
   * @param merchandiseName
   * @return
   */
  Page<MerchandiseInfo> findByMerchandiseIdLikeAndMerchandiseNameLike(String merchandiseId, String merchandiseName,
                                                                      Pageable pageable);
}
