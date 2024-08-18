package com.example.demo.service.common;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

/**
 * メール送信Service実装クラス
 *
 * @author Sam
 * @create 2024-08-07 9:07 PM
 */

@Service
@RequiredArgsConstructor
public class MailSendServiceImpl implements MailSendService {

  /** メール送信用クラス */
  private final MailSender mailSender;

  /** 送信者のメールアドレス */
  @Value("${spring.mail.username}")
  private String mailFromAddress = "";

  @Override
  public boolean sendMail(String mailTo, String mailSubject, String mailText) {
    var smm = new SimpleMailMessage();
    smm.setFrom(mailFromAddress);
    smm.setTo(mailTo);
    smm.setSubject(mailSubject);
    smm.setText(mailText);

    try{
      mailSender.send(smm);
    } catch (Exception e) {
      return false;
    }

    return true;
  }
}
