package com.example.demo.service.merchandise;

import com.example.demo.constant.MerchandiseDeleteResult;
import com.example.demo.entity.MerchandiseInfo;
import com.example.demo.repository.MerchandiseInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


/**
 * {@inheritDoc}
 */

@Service
@RequiredArgsConstructor
public class MerchandiseDetailServiceImpl implements MerchandiseDetailService {

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
  public MerchandiseDeleteResult deleteMerchandiseInfoById(String merchandiseId) {
    Optional<MerchandiseInfo> merchandiseInfo = repository.findById(merchandiseId);
    if(merchandiseInfo.isEmpty()) {
      return MerchandiseDeleteResult.ERROR;
    }

    repository.deleteById(merchandiseId);

    return MerchandiseDeleteResult.SUCCEED;
  }
}
