package com.example.demo.controller;

import com.example.demo.constant.SessionKeyConst;
import com.example.demo.constant.UrlConst;
import com.example.demo.constant.ViewNameConst;
import com.example.demo.dto.MerchandiseListInfo;
import com.example.demo.dto.MerchandiseSearchInfo;
import com.example.demo.dto.PaginatedResponse;
import com.example.demo.form.MerchandiseListForm;
import com.example.demo.service.merchandise.MerchandiseListService;
import com.example.demo.util.AppUtil;
import com.github.dozermapper.core.Mapper;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Collections;

/**
 * 商品一覧画面Controllerクラス
 *
 * @author Sam
 * @create 2024-08-09 9:24 AM
 */

@Controller
@RequiredArgsConstructor
public class MerchandiseController {

  /** 商品一覧画面Serviceクラス */
  private final MerchandiseListService service;

  /** Dozer Mapper*/
  private final Mapper mapper;

  /** セッションオブジェクト */
  private final HttpSession session;

  /** モデルキー：ユーザー情報リスト */
  private static final String KEY_MERCHANDISE_LIST = "merchandiseList";

  /** モデルキー：ユーザー情報リスト */

  /** モデルキー：商品情報リストフォーム */
  private static final String KEY_MERCHANDISE_LIST_FORM = "merchandiseListForm";

  /** モデルキー：操作種別 */
  private static final String KEY_OPERATION_KIND = "operationKind";

  /** モデルキー：PaginatedResponse */
  private static final String KEY_PAGINATEDRESPONSE = "paginatedResponse";


  /**
   * 商品情報一覧画面の初期表示を行います。
   *
   * @return
   */
  @GetMapping(UrlConst.MERCHANDISE_LIST)
  public String view(Model model, MerchandiseListForm form,
                     @RequestParam(name = "page", defaultValue = "0") Integer page,
                     @RequestParam(name = "size", defaultValue = "10") Integer size,
                     @RequestParam(name = "merchandiseId", defaultValue = "") String merchandiseId,
                     @RequestParam(name = "merchandiseName", defaultValue = "") String merchandiseName){
    session.removeAttribute(SessionKeyConst.SELECTED_MERCHANDISE_ID);

    MerchandiseSearchInfo merchandiseSearchDto = new MerchandiseSearchInfo(
            merchandiseId,
            merchandiseName,
            page,
            size
    );

    PaginatedResponse<MerchandiseListInfo> paginatedResponse = editMerchandiseListInfo(model, page, size, merchandiseSearchDto);

    model.addAttribute(KEY_MERCHANDISE_LIST, paginatedResponse.getContent());
    model.addAttribute(KEY_PAGINATEDRESPONSE, paginatedResponse);

    return ViewNameConst.MERCHANDISE_LIST;
  }

  /**
   * 画面に表示する一覧情報を作成します。
   *
   * @param model
   * @param merchandiseSearchDto
   * @return
   */

//  @SuppressWarnings("unchecked")
  private PaginatedResponse<MerchandiseListInfo> editMerchandiseListInfo(Model model,
                                                                         Integer page, Integer size, MerchandiseSearchInfo merchandiseSearchDto) {
    boolean doneSerachOrDelete = model.containsAttribute(KEY_OPERATION_KIND);
    if (doneSerachOrDelete) {
      var operationKind = (MerchandiseOperationKind) model.getAttribute(KEY_OPERATION_KIND);
      if (operationKind == MerchandiseOperationKind.SEARCH) {
        PaginatedResponse<MerchandiseListInfo> response =
                (PaginatedResponse<MerchandiseListInfo>) model.getAttribute(KEY_PAGINATEDRESPONSE);
        return response != null ? response : createEmptyPaginatedResponse(page, size);
      }
    }
    return service.showMerchandiseListByParam(merchandiseSearchDto);
  }

  /**
   * 必要に応じて空のPaginatedResponseを作成するヘルパーメソッド
   *
   * @param page
   * @param size
   * @return
   */
  private PaginatedResponse<MerchandiseListInfo> createEmptyPaginatedResponse(Integer page, Integer size) {
    return new PaginatedResponse<>(
            Collections.emptyList(),
            page,
            size,
            0L,
            0
    );
  }

  /**
   * 商品情報検索
   *
   * @param form
   * @param redirectAttributes
   * @return
   */
  @PostMapping(value = UrlConst.MERCHANDISE_LIST, params = "search")
  public String searchMerchandise(MerchandiseListForm form, RedirectAttributes redirectAttributes) {
    MerchandiseSearchInfo searchDto = mapper.map(form, MerchandiseSearchInfo.class);
//    List<MerchandiseListInfo> merchandiseListInfos = service.showMerchandiseListByParam(searchDto);
    PaginatedResponse<MerchandiseListInfo> paginatedResponse = service.showMerchandiseListByParam(searchDto);

    redirectAttributes.addFlashAttribute(KEY_PAGINATEDRESPONSE, paginatedResponse);
    redirectAttributes.addFlashAttribute(KEY_MERCHANDISE_LIST_FORM, form);
    redirectAttributes.addFlashAttribute(KEY_OPERATION_KIND, MerchandiseOperationKind.SEARCH);

    return AppUtil.doRedirect(UrlConst.MERCHANDISE_LIST);
  }

  /**
   * 選択行の商品情報を選択して、商品詳細情報画面へ進みます。
   *
   * @param form
   * @return
   */
  @PostMapping(value = UrlConst.MERCHANDISE_LIST, params = "detail")
  public String viewMerchandiseDetail (MerchandiseListForm form) {
    session.setAttribute(SessionKeyConst.SELECTED_MERCHANDISE_ID, form.getSelectedMerchandiseId());
    return AppUtil.doRedirect(UrlConst.MERCHANDISE_DETAIL);
  }

  /**
   * 操作種別Enum
   *
   * @author ys-fj
   */
  public enum MerchandiseOperationKind {
    SEARCH, DELETE
  }
}
