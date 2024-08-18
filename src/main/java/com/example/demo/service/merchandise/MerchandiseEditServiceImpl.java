package com.example.demo.service.merchandise;

import com.example.demo.constant.MerchandiseEditMessage;
import com.example.demo.dto.MerchandiseEditResult;
import com.example.demo.dto.MerchandiseUpdateInfo;
import com.example.demo.entity.MerchandiseInfo;
import com.example.demo.repository.MerchandiseInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * {@inheritDoc}
 *
 * @author Sam
 * @create 2024-08-13 10:52 AM
 */

@Service
@RequiredArgsConstructor
public class MerchandiseEditServiceImpl implements MerchandiseEditService {

  /** 商品情報テーブルRepository */
  private final MerchandiseInfoRepository repository;

  /**
   * {@inheritDoc}
   */
  @Override
  public Optional<MerchandiseInfo> searchMerchandiseInfo(String merchandiseId) {
    return repository.findById(merchandiseId);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public MerchandiseEditResult updateMerchandiseInfo(MerchandiseUpdateInfo merchandiseUpdateInfo) {
    MerchandiseEditResult merchandiseUpdateResult = new MerchandiseEditResult();

    // 現在の登録情報を取得
    Optional<MerchandiseInfo> updateInfoOpt = repository.findById(merchandiseUpdateInfo.getMerchandiseId());
    if(updateInfoOpt.isEmpty()) {
      merchandiseUpdateResult.setUpdateMessage(MerchandiseEditMessage.FAILED);
      return merchandiseUpdateResult;
    }

    // 画面の入力情報等をセット
    MerchandiseInfo updateInfo = updateInfoOpt.get();
    updateInfo.setMerchandiseName(merchandiseUpdateInfo.getMerchandiseName());
    updateInfo.setUnitPrice(merchandiseUpdateInfo.getUnitPrice());
    updateInfo.setQuantity(merchandiseUpdateInfo.getQuantity());
    updateInfo.setUpdateUser(merchandiseUpdateInfo.getUpdateUser());
    updateInfo.setUpdateTime(LocalDateTime.now());

    try {
      repository.save(updateInfo);
    } catch (Exception e) {
      merchandiseUpdateResult.setUpdateMessage(MerchandiseEditMessage.FAILED);
      return merchandiseUpdateResult;
    }

    merchandiseUpdateResult.setUpdateMerchandiseInfo(updateInfo);
    merchandiseUpdateResult.setUpdateMessage(MerchandiseEditMessage.SUCCEED);
    return merchandiseUpdateResult;
  }
}
