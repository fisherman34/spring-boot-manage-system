package com.example.demo.entity.converter;

import com.example.demo.constant.db.AuthorityKind;
import jakarta.persistence.AttributeConverter;

/**
 * @author Sam
 * @create 2024-08-02 11:51 AM
 */
public class UserAuthorityConverter implements AttributeConverter<AuthorityKind, String> {

  /**
   * 引数で受け取った権限種別Enumを、権限種別のコード値に変換します
   *
   * @param authorityKind
   * @return
   */
  @Override
  public String convertToDatabaseColumn(AuthorityKind authorityKind) {
    return authorityKind.getCode();
  }

  /**
   * 引数で受け取った権限種別のコード値を、権限種別Enumに変換します。
   *
   * @param value
   * @return
   */
  @Override
  public AuthorityKind convertToEntityAttribute(String value) {
    return AuthorityKind.from(value);
  }
}
