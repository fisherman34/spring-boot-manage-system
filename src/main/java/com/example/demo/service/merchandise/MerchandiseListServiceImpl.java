package com.example.demo.service.merchandise;

import com.example.demo.dto.MerchandiseListInfo;
import com.example.demo.dto.MerchandiseSearchInfo;
import com.example.demo.dto.PaginatedResponse;
import com.example.demo.entity.MerchandiseInfo;
import com.example.demo.repository.MerchandiseInfoRepository;
import com.example.demo.util.AppUtil;
import com.github.dozermapper.core.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * {@inheritDoc}
 * @author Sam
 * @create 2024-08-09 12:54 PM
 */

@Service
@RequiredArgsConstructor
public class MerchandiseListServiceImpl implements  MerchandiseListService{

  /** 商品情報テーブルDAO */
  private final MerchandiseInfoRepository repository;

  /** Dozer Mapper */
  private final Mapper mapper;

  /**
   * {@inheritDoc}
   */
  @Override
  public PaginatedResponse<MerchandiseListInfo> showMerchandiseList(Integer page, Integer size) {
    if(page < 0) {
      page = 0;
    }
    Page<MerchandiseInfo> merchandisePage = repository.findAll(PageRequest.of(page, size));
    List<MerchandiseListInfo> merchandiseListInfos = toMerchandiseListInfo(merchandisePage);

    return new PaginatedResponse<>(
            merchandiseListInfos,
            merchandisePage.getNumber(),
            merchandisePage.getSize(),
            merchandisePage.getTotalElements(),
            merchandisePage.getTotalPages()
    );

  }


  /**
   * {@inheritDoc}
   */
  @Override
  public PaginatedResponse<MerchandiseListInfo> showMerchandiseListByParam(MerchandiseSearchInfo dto) {
    Page<MerchandiseInfo> merchandisePage = findMerchandiseInfoByParam(dto);
    List<MerchandiseListInfo> merchandiseListInfos = toMerchandiseListInfo(merchandisePage);

    return new PaginatedResponse<>(
            merchandiseListInfos,
            merchandisePage.getNumber(),
            merchandisePage.getSize(),
            merchandisePage.getTotalElements(),
            merchandisePage.getTotalPages()
    );
  }

  /**
   * 商品情報の条件検索を行い、検索結果を返却します。
   *
   * @param dto
   * @return
   */
  private Page<MerchandiseInfo> findMerchandiseInfoByParam(MerchandiseSearchInfo dto) {
    String merchandiseIdParam = AppUtil.addWildcard(dto.getMerchandiseId());
    Integer size = dto.getSize();
    Integer page = dto.getPage();
    PageRequest pr = PageRequest.of(page, size);

    if(!dto.getMerchandiseName().equals("")) {
      String merchandiseNameParam = AppUtil.addWildcard(dto.getMerchandiseName());
      return repository.findByMerchandiseIdLikeAndMerchandiseNameLike(merchandiseIdParam, merchandiseNameParam, pr);
    } else {
      return repository.findByMerchandiseIdLike(merchandiseIdParam, pr);
    }
  }


  /**
   * 商品情報EntityのListを商品一覧情報DTOのListに変換します。
   *
   * @return
   */
  private List<MerchandiseListInfo> toMerchandiseListInfo(Page<MerchandiseInfo> merchandiseInfos) {
    ArrayList<MerchandiseListInfo> merchandiseListInfos = new ArrayList<MerchandiseListInfo>();
    for (MerchandiseInfo merchandiseInfo : merchandiseInfos) {
      MerchandiseListInfo merchandiseListInfo = mapper.map(merchandiseInfo, MerchandiseListInfo.class);
      merchandiseListInfos.add(merchandiseListInfo);
    }

    return merchandiseListInfos;
  }


}
