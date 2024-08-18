package com.example.demo.service.user;

import com.example.demo.constant.UserDeleteResult;
import com.example.demo.dto.PaginatedResponse;
import com.example.demo.dto.UserListInfo;
import com.example.demo.dto.UserSearchInfo;
import com.example.demo.entity.UserInfo;
import com.example.demo.repository.UserInfoRepository;
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
 * @create 2024-08-02 2:14 PM
 */

@Service
@RequiredArgsConstructor
public class UserListServiceImpl implements UserListService {

  /** ユーザー情報テーブルDAO */
  private final UserInfoRepository repository;

  /** Dozer Mapper */
  private final Mapper mapper;

  /**
   * {@inheritDoc}
   *
   * @return
   */
  @Override
  public PaginatedResponse<UserListInfo> editUserList(Integer page, Integer size) {
    if(page < 0) {
      page = 0;
    }

    Page<UserInfo> userPaged = repository.findAll(PageRequest.of(page, size));
    List<UserListInfo> userListInfos = toUserListInfos(userPaged);

    return new PaginatedResponse<>(
            userListInfos,
            userPaged.getNumber(),
            userPaged.getSize(),
            userPaged.getTotalElements(),
            userPaged.getTotalPages()
    );
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public PaginatedResponse<UserListInfo> editUserListByParam(UserSearchInfo dto) {
    Page<UserInfo> userPaged = findUserInfoByParam(dto);
    List<UserListInfo> userListInfos = toUserListInfos(userPaged);

    return new PaginatedResponse<>(
            userListInfos,
            userPaged.getNumber(),
            userPaged.getSize(),
            userPaged.getTotalElements(),
            userPaged.getTotalPages()
    );
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public UserDeleteResult deleteUserInfoById(String loginId) {
    var userInfo = repository.findById(loginId);
    if (userInfo.isEmpty()) {
      return UserDeleteResult.ERROR;
    }

    repository.deleteById(loginId);

    return UserDeleteResult.SUCCEED;
  }

  /**
   * ユーザー情報取得（条件付き）
   *
   * @param dto
   * @return
   */
  private Page<UserInfo> findUserInfoByParam(UserSearchInfo dto) {
    var loginIdParam = AppUtil.addWildcard(dto.getLoginId());
    Integer page = dto.getPage();
    Integer size = dto.getSize();
    PageRequest pr = PageRequest.of(page, size);

    if (dto.getUserStatusKind() != null && dto.getAuthorityKind() !=null) {
      return repository.findByLoginIdLikeAndUserStatusKindAndAuthorityKind(loginIdParam,
              dto.getUserStatusKind(), dto.getAuthorityKind(), pr);
    } else if (dto.getUserStatusKind() != null) {
      return repository.findByLoginIdLikeAndUserStatusKind(loginIdParam, dto.getUserStatusKind(), pr);
    } else if (dto.getAuthorityKind() != null) {
      return repository.findByLoginIdLikeAndAuthorityKind(loginIdParam, dto.getAuthorityKind(), pr);
    } else {
      return repository.findByLoginIdLike(loginIdParam, pr);
    }
  }

  /**
   * ユーザー情報EntityのListをユーザー一覧情報DTOのListに変換します。
   *
   * @param userInfos
   * @return
   */
  private List<UserListInfo> toUserListInfos(Page<UserInfo> userInfos) {
    var userListInfos = new ArrayList<UserListInfo>();
    for (UserInfo userInfo : userInfos) {
      var userListInfo = mapper.map(userInfo, UserListInfo.class);
      userListInfo.setStatus(userInfo.getUserStatusKind().getDisplayValue());
      userListInfo.setAuthority(userInfo.getAuthorityKind().getDisplayValue());
      userListInfos.add(userListInfo);
    }

    return userListInfos;
  }
}
