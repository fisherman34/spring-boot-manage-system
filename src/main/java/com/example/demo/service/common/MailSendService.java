package com.example.demo.service.common;

/**
 * メール送信Serviceクラス
 *
 * @author Sam
 * @create 2024-08-07 9:06 PM
 */
public interface MailSendService {

  /**
   * メールを送信します。
   *
   * @param mailTo 宛先
   * @param mailSubject 件名
   * @param mailText 本文
   * @return 送信結果(成功ならtrue)
   */
  public boolean sendMail(String mailTo, String mailSubject, String mailText);
}
