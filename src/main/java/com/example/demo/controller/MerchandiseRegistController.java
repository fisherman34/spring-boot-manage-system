package com.example.demo.controller;

import com.example.demo.constant.MerchandiseRegistResult;
import com.example.demo.constant.ModelKey;
import com.example.demo.constant.UrlConst;
import com.example.demo.constant.ViewNameConst;
import com.example.demo.dto.MerchandiseRegistInfo;
import com.example.demo.form.MerchandiseRegistForm;
import com.example.demo.service.merchandise.MerchandiseRegistService;
import com.example.demo.util.AppUtil;
import com.github.dozermapper.core.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * 商品情報登録画面Controllerクラス
 * @author Sam
 * @create 2024-08-09 9:50 PM
 */

@Controller
@RequiredArgsConstructor
public class MerchandiseRegistController {

  /** 商品登録画面Serviceクラス */
  private final MerchandiseRegistService service;

  /** Dozer Mapper*/
  private final Mapper mapper;

  /** メッセージソース */
  private final MessageSource messageSource;

  /** リダイレクトパラメータ：エラー有 */
  private static final String REDIRECT_PRAM_ERR = "err";

  /** リダイレクトパラメータ：成功 */
  private static final String REDIRECT_PRAM_SUCC = "succ";

  /**
   * 画面の初期表示を行います。
   * @param model
   * @param form
   * @return
   */
  @GetMapping(UrlConst.MERCHANDISE_REGIST)
  public String view(Model model, MerchandiseRegistForm form) {

    return ViewNameConst.MERCHANDISE_REGIST;
  }

  /**
   * 商品の登録エラー時にエラーメッセージを表示します。
   * @param model
   * @return
   */
  @GetMapping(value = UrlConst.MERCHANDISE_REGIST, params = REDIRECT_PRAM_ERR)
  public String viewWithError(Model model) {
    return ViewNameConst.MERCHANDISE_REGIST_ERROR;
  }

  /**
   * 商品の登録成功時にメッセージを表示します。
   * @param model
   * @return
   */
  @GetMapping(value = UrlConst.MERCHANDISE_REGIST, params = REDIRECT_PRAM_SUCC)
  public String viewWithSuccess(Model model) {
    return ViewNameConst.MERCHANDISE_REGIST_SUCCESS;
  }

  /**
   * 画面の入力情報を元に商品情報を登録します。
   *
   * @param form
   * @param user
   * @param redirectAttributes
   * @return
   */
  @PostMapping(UrlConst.MERCHANDISE_REGIST)
  public String regist(MerchandiseRegistForm form, @AuthenticationPrincipal User user,
                       RedirectAttributes redirectAttributes) {
    var registDto = mapper.map(form, MerchandiseRegistInfo.class);
    registDto.setUpdateUserId(user.getUsername());
    MerchandiseRegistResult registResult = service.registMerchandiseInfo(registDto);
    if(registResult == MerchandiseRegistResult.FAILURE_BY_DB_ERROR) {
      redirectAttributes.addFlashAttribute(ModelKey.MESSAGE,
              AppUtil.getMessage(messageSource, registResult.getMessageId()));
      redirectAttributes.addAttribute(REDIRECT_PRAM_ERR, "");
      return AppUtil.doRedirect(UrlConst.MERCHANDISE_REGIST);
    } else if (registResult == MerchandiseRegistResult.FAILURE_BY_ALREADY_EXISTED) {
      redirectAttributes.addFlashAttribute(ModelKey.MESSAGE,
              AppUtil.getMessage(messageSource, registResult.getMessageId()));
      redirectAttributes.addAttribute(REDIRECT_PRAM_ERR, "");
      return AppUtil.doRedirect(UrlConst.MERCHANDISE_REGIST);
    }

    redirectAttributes.addFlashAttribute(ModelKey.MESSAGE, AppUtil.getMessage(messageSource, registResult.getMessageId()));
    redirectAttributes.addAttribute(REDIRECT_PRAM_SUCC, "");
    return AppUtil.doRedirect(UrlConst.MERCHANDISE_REGIST);
  }
}
