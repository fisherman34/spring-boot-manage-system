package com.example.demo.entity.converter;

import com.example.demo.constant.db.UserStatusKind;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Convert;

/**
 * @author Sam
 * @create 2024-08-02 10:58 AM
 */
@Convert
public class  UserStatusConverter implements AttributeConverter<UserStatusKind, Boolean> {

  /**
   * 引数で受け取ったユーザー状態種別Enumを、利用可否のbooleanに変換します。
   *
   * @param userStatus
   * @return
   */
  @Override
  public Boolean convertToDatabaseColumn(UserStatusKind userStatus) {
    return userStatus.isDisabled();
  }

  /**
   * 引数で受け取った権限種別のコード値を、ユーザー状態種別Enumに変換します。
   *
   * @param isDisabled
   * @return
   */
  @Override
  public UserStatusKind convertToEntityAttribute(Boolean isDisabled) {
    return isDisabled? UserStatusKind.DISABLED : UserStatusKind.ENABLED;
  }
}
