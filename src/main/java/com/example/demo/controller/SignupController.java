package com.example.demo.controller;

import com.example.demo.constant.*;
import com.example.demo.dto.SignupInfo;
import com.example.demo.form.SignupForm;
import com.example.demo.service.SignupService;
import com.example.demo.util.AppUtil;
import com.github.dozermapper.core.Mapper;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

/**
 * ユーザー登録コントローラー
 *
 * @author Sam
 * @create 2024-07-26 9:01 PM
 */

@Controller
@RequiredArgsConstructor
public class SignupController {

  /** ログイン画面service */
  private final SignupService service;

  /** メッセージソース */
  private final MessageSource messageSource;

  /** オブジェクト間項目輸送クラス */
  private final Mapper mapper;

  /** セッションオブジェクト */
  private final HttpSession session;

  /** 画面で使用するファームクラス名 */
  private static final String FORM_CLASS_NAME = "signupForm";


  /**
   * 初期表示
   *
   * @param model
   * @return
   */
  @GetMapping(UrlConst.SIGNUP)
  public String view(Model model) {
    // 判断是否首次访问Signup页面，若是首次访问isInitialDisp值为true
    var isInitialDisp = !model.containsAttribute(FORM_CLASS_NAME);
    if (isInitialDisp) {
      model.addAttribute(FORM_CLASS_NAME, new SignupForm());
    }
    return ViewNameConst.SIGNUP;
  }

  /**
   * 画面の入力情報からユーザー登録処理を呼び出します。
   *
   * @param form
   * @param bdResulet 入力チェック結果
   * @return
   */
  @PostMapping(UrlConst.SIGNUP)
  public String signup(@Validated SignupForm form, BindingResult bdResulet,
                     RedirectAttributes redirectAttributes) {
    if(bdResulet.hasErrors()){
      editGuideMessage(form, bdResulet, MessageConst.FORM_ERROR, redirectAttributes);
      return AppUtil.doRedirect(UrlConst.SIGNUP);
    }

    var signupResult = service.signup(mapper.map(form, SignupInfo.class));
    var isError = signupResult != SignupResult.SUCCEED;
    if (isError) {
      editGuideMessage(form, bdResulet, signupResult.getMessageId(), redirectAttributes);
      return AppUtil.doRedirect(UrlConst.SIGNUP);
    }

    session.setAttribute(SessionKeyConst.ONE_TIME_AUTH_LOGIN_ID, form.getLoginId());
    return AppUtil.doRedirect(UrlConst.SIGNUP_CONFIRM);
  }

  /**
   * 画面に表示するガイドメッセージを設定する
   *
   * @param model モデル
   * @param messageId メッセージID
   * @param isError エラー有無
   */
  private void editGuideMessage(Model model, String messageId, boolean isError) {
    String message = AppUtil.getMessage(messageSource, messageId);
    model.addAttribute("message", message);
    model.addAttribute("isError", isError);
  }

  /**
   * メッセージIDを使ってプロパティファイルからメッセージを取得し、画面に表示します。
   *
   * <p>また、画面でメッセージを表示する際に通常メッセージとエラーメッセージとで色分けをするため、<br>
   * その判定に必要な情報も画面に渡します。
   *
   * @param form 入力情報
   * @param bdResult 入力内容の単項目チェック結果
   * @param messageId プロパティファイルから取得したいメッセージのID
   * @param redirectAttributes リダイレクト用モデル
   */
  private void editGuideMessage(SignupForm form, BindingResult bdResult, String messageId,
                                RedirectAttributes redirectAttributes) {
    redirectAttributes.addFlashAttribute(ModelKey.MESSAGE, AppUtil.getMessage(messageSource, messageId));
    redirectAttributes.addFlashAttribute(ModelKey.IS_ERROR, true);
    redirectAttributes.addFlashAttribute(form);
    redirectAttributes.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + FORM_CLASS_NAME, bdResult);
  }
}
