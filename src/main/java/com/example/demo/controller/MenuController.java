package com.example.demo.controller;

import com.example.demo.constant.db.AuthorityKind;
import com.example.demo.constant.UrlConst;
import com.example.demo.constant.ViewNameConst;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * メニューコントローラー
 *
 * @author Sam
 * @create 2024-07-29 12:01 PM
 */

@Controller
public class MenuController {

  /**
   * 初期表示
   *
   * @return 表示画面
   */
  @GetMapping(UrlConst.MENU)
  public String view(@AuthenticationPrincipal User user, Model model) {
    var hasUserManageAuth = user.getAuthorities().stream()
            .allMatch(authority -> authority.getAuthority()
                    .equals(AuthorityKind.ITEM_AND_USER_MANAGER.getCode()));
    model.addAttribute("hasUserManageAuth", hasUserManageAuth);

    return ViewNameConst.MENU;
  }
}
