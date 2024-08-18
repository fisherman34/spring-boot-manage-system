package com.example.demo.controller;

import com.example.demo.constant.*;
import com.example.demo.entity.MerchandiseInfo;
import com.example.demo.form.MerchandiseDetailForm;
import com.example.demo.service.merchandise.MerchandiseDetailService;
import com.example.demo.util.AppUtil;
import com.github.dozermapper.core.Mapper;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

/**
 * 商品詳細画面Controllerクラス
 *
 * @author Sam
 * @create 2024-08-12 8:13 PM
 */

@Controller
@RequiredArgsConstructor
public class MerchandiseDetailController {

  /** ユーザー詳細画面Serviceクラス */
  private final MerchandiseDetailService service;

  /** Dozer Mapper*/
  private final Mapper mapper;

  /** セッションオブジェクト */
  private final HttpSession session;

  /** メッセージソース */
  private final MessageSource messageSource;

  /** リダイレクトパラメータ：エラー有 */
  private static final String REDIRECT_PRAM_ERR = "err";

  /**
   * 前画面で選択された商品IDに紐づく商品情報詳細を画面に表示します。
   *
   * @param model
   * @return
   */
  @GetMapping(UrlConst.MERCHANDISE_DETAIL)
  public String view(Model model, MerchandiseDetailForm form) {
    var merchandiseId = (String) session.getAttribute(SessionKeyConst.SELECTED_MERCHANDISE_ID);
    Optional<MerchandiseInfo> merchandiseInfoOpt = service.searchMerchandiseInfo(merchandiseId);
    if(merchandiseInfoOpt.isEmpty()) {
      model.addAttribute(ModelKey.MESSAGE,
              AppUtil.getMessage(messageSource, MessageConst.NON_EXISTED_MERCHANDISE_ID));
      return ViewNameConst.MERCHANDISE_EDIT_ERROR;
    }

    MerchandiseInfo merchandiseInfo = merchandiseInfoOpt.get();
    model.addAttribute("merchDetailForm", mapper.map(merchandiseInfo, MerchandiseDetailForm.class));

    return ViewNameConst.MERCHANDISE_DETAIL;
  }

  /**
   * 商品の削除成功時に成功画面を表示します。
   *
   * @param model
   * @return
   */
  @GetMapping(UrlConst.MERCHANDISE_DELETE_COMPLETION)
  public String viewWithSuccess(Model model) {
    return ViewNameConst.MERCHANDISE_DELETE_COMPLETION;
  }

  /**
   * 商品の削除エラー時にエラーメッセージを表示します。
   *
   * @param model
   * @return
   */
  @GetMapping(value = UrlConst.MERCHANDISE_DETAIL, params = REDIRECT_PRAM_ERR)
  public String viewWithError(Model model) {
    return ViewNameConst.MERCHANDISE_DELETE_ERROR;
  }

  /**
   * 表示された商品IDに紐づく商品編集画面へ進みます。
   * @param form
   * @param user
   * @param redirectAttributes
   * @return
   */
  @PostMapping(value = UrlConst.MERCHANDISE_DETAIL, params = "edit")
  public String editMerchandise(MerchandiseDetailForm form, @AuthenticationPrincipal User user,
                                RedirectAttributes redirectAttributes, Model model) {
    var merchandiseId = (String) session.getAttribute(SessionKeyConst.SELECTED_MERCHANDISE_ID);
    Optional<MerchandiseInfo> merchandiseInfoOpt = service.searchMerchandiseInfo(merchandiseId);
    if(merchandiseInfoOpt.isEmpty()) {
      model.addAttribute(ModelKey.MESSAGE,
              AppUtil.getMessage(messageSource, MessageConst.NON_EXISTED_MERCHANDISE_ID));
      return ViewNameConst.MERCHANDISE_EDIT_ERROR;
    }

    return AppUtil.doRedirect(UrlConst.MERCHANDISE_EDIT);
  }

  /**
   * 表示されている商品情報を削除して、商品の削除成功時に成功画面へリダイレクトします。
   *
   * @param form
   * @param redirectAttributes
   * @return
   */
  @PostMapping(value = UrlConst.MERCHANDISE_DETAIL, params = "delete")
  public String deleteMerchandise(MerchandiseDetailForm form, RedirectAttributes redirectAttributes) {
    var selectedMerchandiseId = (String) session.getAttribute(SessionKeyConst.SELECTED_MERCHANDISE_ID);
    MerchandiseDeleteResult executeResult = service.deleteMerchandiseInfoById(selectedMerchandiseId);
    if(executeResult == MerchandiseDeleteResult.ERROR) {
      redirectAttributes.addFlashAttribute(ModelKey.MESSAGE,
              AppUtil.getMessage(messageSource, executeResult.getMessageId()));
      redirectAttributes.addAttribute(REDIRECT_PRAM_ERR, "");
      return AppUtil.doRedirect(UrlConst.MERCHANDISE_DETAIL);
    }

    redirectAttributes.addFlashAttribute(ModelKey.IS_ERROR, false);
    redirectAttributes.addFlashAttribute(ModelKey.MESSAGE,
            AppUtil.getMessage(messageSource, executeResult.getMessageId()));

    return AppUtil.doRedirect(UrlConst.MERCHANDISE_DELETE_COMPLETION);
  }


}
