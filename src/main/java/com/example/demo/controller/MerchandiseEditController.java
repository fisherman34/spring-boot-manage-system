package com.example.demo.controller;

import com.example.demo.constant.*;
import com.example.demo.dto.MerchandiseEditResult;
import com.example.demo.dto.MerchandiseUpdateInfo;
import com.example.demo.entity.MerchandiseInfo;
import com.example.demo.form.MerchandiseDetailForm;
import com.example.demo.form.MerchandiseEditForm;
import com.example.demo.service.merchandise.MerchandiseEditService;
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

import javax.swing.plaf.PanelUI;
import java.util.Optional;

/**
 * 商品編集画面Controllerクラス
 * @author Sam
 * @create 2024-08-13 10:28 AM
 */


@Controller
@RequiredArgsConstructor
public class MerchandiseEditController {

  /** 商品編集画面Serviceクラス */
  private final MerchandiseEditService service;

  /** セッションオブジェクト */
  private final HttpSession session;

  /** Dozer Mapper*/
  private final Mapper mapper;

  /** メッセージソース */
  private final MessageSource messageSource;

  /** リダイレクトパラメータ：エラー有 */
  private static final String REDIRECT_PRAM_ERR = "err";

  /**
   * 前画面で選択された商品IDに紐づく商品編集画面を表示します。
   *
   * @param model
   * @param form
   * @return
   */
  @GetMapping(UrlConst.MERCHANDISE_EDIT)
  public String view(Model model, MerchandiseEditForm form) {
    var selectedMerchandiseId = (String) session.getAttribute(SessionKeyConst.SELECTED_MERCHANDISE_ID);
    Optional<MerchandiseInfo> merchandiseInfoOpt = service.searchMerchandiseInfo(selectedMerchandiseId);
    if(merchandiseInfoOpt.isEmpty()) {
      model.addAttribute(ModelKey.MESSAGE,
              AppUtil.getMessage(messageSource, MessageConst.NON_EXISTED_MERCHANDISE_ID));
      return ViewNameConst.MERCHANDISE_EDIT_ERROR;
    }
    MerchandiseInfo merchandiseInfo = merchandiseInfoOpt.get();

    MerchandiseEditForm merchandiseEditForm = mapper.map(merchandiseInfo, MerchandiseEditForm.class);
    merchandiseEditForm.setUnitPrice(String.format("%.2f", merchandiseInfo.getUnitPrice()));
    model.addAttribute("merchandiseEditForm", merchandiseEditForm);
    return ViewNameConst.MERCHANDISE_EDIT;
  }

  /**
   * 商品の更新成功時に成功画面を表示します。
   *
   * @param model
   * @return
   */
  @GetMapping(value = UrlConst.MERCHANDISE_EDIT_COMPLETION)
  public String viewWithSuccess(Model model) {
    return ViewNameConst.MERCHANDISE_EDIT_COMPLETION;
  }

  /**
   * 商品の更新エラー時にエラーメッセージを表示します。
   *
   * @param model
   * @return
   */
  @GetMapping(value = UrlConst.MERCHANDISE_EDIT, params = REDIRECT_PRAM_ERR)
  public String viewWithError(Model model) {
    return ViewNameConst.MERCHANDISE_EDIT_ERROR;
  }

  /**
   * 画面の入力情報を元に商品情報を更新します。
   *
   * @param form
   * @param user
   * @param redirectAttributes
   * @return
   */
  @PostMapping(value = UrlConst.MERCHANDISE_EDIT, params = "update")
  public String updateMerchandise(MerchandiseEditForm form, @AuthenticationPrincipal User user,
                                  RedirectAttributes redirectAttributes) {
    MerchandiseUpdateInfo updateDto = mapper.map(form, MerchandiseUpdateInfo.class);
    updateDto.setMerchandiseId((String) session.getAttribute(SessionKeyConst.SELECTED_MERCHANDISE_ID));
    updateDto.setUnitPrice(Float.parseFloat(form.getUnitPrice()));
    updateDto.setUpdateUser(user.getUsername());

    MerchandiseEditResult updateResult = service.updateMerchandiseInfo(updateDto);
    MerchandiseEditMessage updateMessage = updateResult.getUpdateMessage();
    if(updateMessage == MerchandiseEditMessage.FAILED) {
      redirectAttributes.addFlashAttribute(ModelKey.MESSAGE,
              AppUtil.getMessage(messageSource, updateMessage.getMessageId()));
      redirectAttributes.addAttribute(REDIRECT_PRAM_ERR, "");
      return AppUtil.doRedirect(UrlConst.MERCHANDISE_EDIT);
    }

    redirectAttributes.addFlashAttribute(ModelKey.IS_ERROR, false);
    redirectAttributes.addFlashAttribute(ModelKey.MESSAGE,
            AppUtil.getMessage(messageSource, updateMessage.getMessageId()));


    return AppUtil.doRedirect(UrlConst.MERCHANDISE_EDIT_COMPLETION);
  }


}
