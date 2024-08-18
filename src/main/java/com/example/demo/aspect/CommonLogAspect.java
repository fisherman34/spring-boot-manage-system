package com.example.demo.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * ログ出力共通クラス
 *
 * @author Sam
 * @create 2024-08-01 6:44 PM
 */

@Aspect
@Component
public class CommonLogAspect {

  /** ロガー（Logback） */
  private final Logger log =  LoggerFactory.getLogger(CommonLogAspect.class);


  /**
   * 指定したメソッドの開始、終了ログを出力します。
   *
   * @param jp 処理を挿入する場所の情報
   * @return 指定舌メソッドの戻り値
   */
  @Around("execution(* com.example.demo..*(..))")
  public Object writeLog(ProceedingJoinPoint jp) throws Throwable {
    Object returnObj = null;
    // 開始ログを出力
//    log.info("start: " + jp.getSignature().toString());

    try {
      // JoingPointのメッソドを実行
      returnObj = jp.proceed();
    } catch (Throwable t) {
      log.error(t.toString());
      throw t;
    }

    // 終了ログを出力
//    log.info("end: " + jp.getSignature().toString());

    // このようにしないと、Controllerクラスの場合次画面への遷移が行えない
    return returnObj;
  }
}
