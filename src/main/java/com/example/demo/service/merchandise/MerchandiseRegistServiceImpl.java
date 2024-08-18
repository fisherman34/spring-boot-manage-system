package com.example.demo.service.merchandise;

import com.example.demo.dto.MerchandiseRegistInfo;
import com.example.demo.constant.MerchandiseRegistResult;
import com.example.demo.entity.MerchandiseInfo;
import com.example.demo.repository.MerchandiseInfoRepository;
import com.github.dozermapper.core.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @author Sam
 * @create 2024-08-12 2:58 PM
 */

@Service
@RequiredArgsConstructor
public class MerchandiseRegistServiceImpl implements MerchandiseRegistService {

  /** 商品情報テーブルRepository */
  private final MerchandiseInfoRepository repository;

  /** Dozer Mapper*/
  private final Mapper mapper;

  /**
   * {@inheritDoc}
   */
  @Override
  public MerchandiseRegistResult registMerchandiseInfo(MerchandiseRegistInfo merchandiseRegistInfo) {

    Optional<MerchandiseInfo> registInfoOpt = repository.findById(merchandiseRegistInfo.getMerchandiseId());
    if(registInfoOpt.isPresent()) {
      return MerchandiseRegistResult.FAILURE_BY_ALREADY_EXISTED;
    }

    MerchandiseInfo merchandiseInfo = mapper.map(merchandiseRegistInfo, MerchandiseInfo.class);
    merchandiseInfo.setCreateTime(LocalDateTime.now());
    merchandiseInfo.setUpdateTime(LocalDateTime.now());
    merchandiseInfo.setUpdateUser(merchandiseRegistInfo.getUpdateUserId());

    try {
      repository.save(merchandiseInfo);
    } catch (Exception e) {
      return MerchandiseRegistResult.FAILURE_BY_DB_ERROR;
    }

    return MerchandiseRegistResult.SUCCEED;
  }
}
