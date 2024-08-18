package com.example.demo.controller;

import com.example.demo.constant.*;
import com.example.demo.constant.db.AuthorityKind;
import com.example.demo.constant.db.UserStatusKind;
import com.example.demo.dto.PaginatedResponse;
import com.example.demo.dto.UserListInfo;
import com.example.demo.dto.UserSearchInfo;
import com.example.demo.form.UserListForm;
import com.example.demo.service.user.UserListService;
import com.example.demo.util.AppUtil;
import com.github.dozermapper.core.Mapper;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * ユーザー一覧画面Controllerクラス
 *
 * @author Sam
 * @create 2024-08-01 9:05 PM
 */

@Controller
@RequiredArgsConstructor
public class UserListController {

  /** ユーザー一覧画面Serviceクラス */
  private final UserListService service;

  /** Dozer Mapper*/
  private final Mapper mapper;

  /** メッセージソース */
  private final MessageSource messageSource;

  /** セッションオブジェクト */
  private final HttpSession session;

  /** モデルキー：ユーザー情報リストフォーム */
  private static final String KEY_USERLIST_FORM = "userListForm";

  /** モデルキー：ユーザー情報リスト */
  private static final String KEY_USERLIST = "userList";

  /** モデルキー：ユーザー情報リスト */
  private static final String KEY_USER_STATUS_KIND_OPTIONS = "userStatusKindOptions";

  /** モデルキー：ユーザー情報リスト */
  private static final String KEY_AUTHORITY_KIND_OPTIONS = "authorityKindOptions";

  /** モデルキー：操作種別 */
  private static final String KEY_OPERATION_KIND = "operationKind";

  /** モデルキー：PaginatedResponse */
  private static final String KEY_PAGINATEDRESPONSE = "paginatedResponse";

  /**
   * 画面の初期表示を行います。
   *
   * <p>またその際、画面選択項目「アカウント状態」「所有権限」の選択肢を生成して画面に渡します。
   *
   * @param model         モデル
   * @param strUserStatus
   * @param strAuthority
   * @return ユーザー一覧画面テンプレート名
   */
  @GetMapping(UrlConst.USER_LIST)
  public String view(Model model, UserListForm form,
                     @RequestParam(name = "page", defaultValue = "0") Integer page,
                     @RequestParam(name = "size", defaultValue = "10") Integer size,
                     @RequestParam(name = "loginId", defaultValue = "") String loginId,
                     @RequestParam(name = "strUserStatus", defaultValue = "") String strUserStatus,
                     @RequestParam(name = "strAuthority", defaultValue = "") String strAuthority
                     ) {
    session.removeAttribute(SessionKeyConst.SELECTED_LOGIN_ID);

    UserSearchInfo userSearchDto = new UserSearchInfo(
            loginId,
            AppUtil.UserStatusDict(strUserStatus),
            AppUtil.AuthorityStatusDict(strAuthority),
            page,
            size
    );

    PaginatedResponse<UserListInfo> paginatedResponse = editUserListInfo(model, userSearchDto);
    UserListForm userListForm = (UserListForm) model.getAttribute(KEY_USERLIST_FORM);  // search request时注入model的UserListForm
    // Use URL's query parameters to update userListForm
    if (loginId != "") {
      if (userListForm.getLoginId() == null) {
        userListForm.setLoginId(loginId);
      }
    }
    if (strUserStatus != "") {
      if(userListForm.getUserStatusKind() == null) {
        userListForm.setUserStatusKind(AppUtil.UserStatusDict(strUserStatus));
      }
    }
    if (strAuthority != "") {
      if(userListForm.getAuthorityKind() == null) {
        userListForm.setAuthorityKind(AppUtil.AuthorityStatusDict(strAuthority));
      }
    }
    model.addAttribute(KEY_USERLIST_FORM, userListForm);



    model.addAttribute(KEY_USERLIST, paginatedResponse.getContent());
    model.addAttribute(KEY_PAGINATEDRESPONSE, paginatedResponse);

    model.addAttribute(KEY_USER_STATUS_KIND_OPTIONS, UserStatusKind.values());
    model.addAttribute(KEY_AUTHORITY_KIND_OPTIONS, AuthorityKind.values());

    return ViewNameConst.USER_LIST;
  }

  /**
   * 初期表示、検索後や削除後のリダイレクトによる再表示のいずれかかを判定して画面に表示する一覧情報を作成します。
   *
   * @param model
   * @param userSearchDto
   * @return
   */
  @SuppressWarnings("unchecked")
  private PaginatedResponse<UserListInfo> editUserListInfo(Model model, UserSearchInfo userSearchDto) {
    var doneSerachOrDelete = model.containsAttribute(KEY_OPERATION_KIND);
    Integer page = userSearchDto.getPage();
    Integer size = userSearchDto.getSize();
    if (doneSerachOrDelete) {
      var operationKind = (OperationKind) model.getAttribute(KEY_OPERATION_KIND);
      if (operationKind == OperationKind.SEARCH) {

        PaginatedResponse<UserListInfo> response =
                (PaginatedResponse<UserListInfo>) model.getAttribute(KEY_PAGINATEDRESPONSE);
        return response != null ? response : AppUtil.createEmptyPaginatedResponse(page, size);
      }
      if (operationKind == OperationKind.DELETE) {
        var searchDto = mapper.map((UserListForm) model.getAttribute(KEY_USERLIST_FORM), UserSearchInfo.class);
        return service.editUserListByParam(searchDto);
      }
    }
    return service.editUserListByParam(userSearchDto);
  }

  /**
   * ユーザー情報検索
   *
   * 検索条件に合致するユーザー情報を画面に表示する
   *
   * @param form
   * @return
   */
  @PostMapping(value = UrlConst.USER_LIST, params = "search")
  public String searchUser(UserListForm form, RedirectAttributes redirectAttributes) {
    var searchDto = mapper.map(form, UserSearchInfo.class);
    PaginatedResponse<UserListInfo> paginatedResponse = service.editUserListByParam(searchDto);
    redirectAttributes.addFlashAttribute(KEY_PAGINATEDRESPONSE, paginatedResponse);
    redirectAttributes.addFlashAttribute(KEY_USERLIST_FORM, form);
    redirectAttributes.addFlashAttribute(KEY_OPERATION_KIND, OperationKind.SEARCH);

    return AppUtil.doRedirect(UrlConst.USER_LIST);
  }

  /**
   * 選択行のユーザー情報を選択して、ユーザー情報編集画面へ進みます。
   *
   * @param form
   * @return
   */
  @PostMapping(value = UrlConst.USER_LIST, params = "edit")
  public String updateUser(UserListForm form) {
    session.setAttribute(SessionKeyConst.SELECTED_LOGIN_ID, form.getSelectedLoginId());
    return AppUtil.doRedirect(UrlConst.USER_EDIT);
  }

  /**
   * 選択行のユーザー情報を削除して、最新情報で画面を再表示します。
   *
   * @param
   * @param form
   * @return
   */
  @PostMapping(value = UrlConst.USER_LIST, params = "delete")
  public String deleteuser(UserListForm form, RedirectAttributes redirectAttributes) {
    var executeResult = service.deleteUserInfoById(form.getSelectedLoginId());

    redirectAttributes.addFlashAttribute(ModelKey.IS_ERROR, executeResult == UserDeleteResult.ERROR);
    redirectAttributes.addFlashAttribute(ModelKey.MESSAGE,
            AppUtil.getMessage(messageSource, executeResult.getMessageId()));

    // 削除後、フォーム情報の「選択されたログインID」は不要になるためら、クリアします。
    redirectAttributes.addFlashAttribute(KEY_USERLIST_FORM, form.clearSelectedLoginId());
    redirectAttributes.addFlashAttribute(KEY_OPERATION_KIND, OperationKind.DELETE);
    return AppUtil.doRedirect(UrlConst.USER_LIST);
  }

  /**
   * 操作種別Enum
   *
   * @author ys-fj
   */
  public enum OperationKind {
    SEARCH, DELETE
  }

}
