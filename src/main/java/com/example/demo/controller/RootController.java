package com.example.demo.controller;

import com.example.demo.constant.UrlConst;
import com.example.demo.util.AppUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.swing.plaf.PanelUI;

/**
 * ルートディレクトリコントローラー
 * @author Sam
 * @create 2024-08-09 9:14 AM
 */

@Controller
public class RootController {

  @GetMapping (UrlConst.ROOT)
  public String index(){
    return AppUtil.doRedirect(UrlConst.MENU);
  }
}
