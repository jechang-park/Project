package com.kh.yagiyo.web;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class NutrientsController {
  @GetMapping("/nutrients")
  public String nutrients() {
    return "nutrients";
  }

  @GetMapping("/nutrients/오메가3")
  public String item1() {
    return "ingredient/오메가3";
  }

  @GetMapping("/nutrients/프로바이오틱스유산균")
  public String item2() {
    return "ingredient/프로바이오틱스유산균";
  }

  @GetMapping("/nutrients/비타민C")
  public String item3() {
    return "ingredient/비타민C";
  }

  @GetMapping("/nutrients/크릴오일")
  public String item4() {
    return "ingredient/크릴오일";
  }

  @GetMapping("/nutrients/레시틴")
  public String item5() {
    return "ingredient/레시틴";
  }

  @GetMapping("/nutrients/코엔자임Q10")
  public String item6() {
    return "ingredient/코엔자임Q10";
  }

  @GetMapping("/nutrients/비타민B1")
  public String item7() {
    return "ingredient/비타민B1";
  }

  @GetMapping("/nutrients/비타민A")
  public String item8() {
    return "ingredient/비타민A";
  }

  @GetMapping("/nutrients/칼슘")
  public String item9() {
    return "ingredient/칼슘";
  }

  @GetMapping("/nutrients/루테인")
  public String item10() {
    return "ingredient/루테인";
  }

  @GetMapping("/nutrients/비타민D")
  public String item11() {
    return "ingredient/비타민D";
  }

  @GetMapping("/nutrients/글루코사민")
  public String item12() {
    return "ingredient/글루코사민";
  }


  @GetMapping("/nutrients/쏘팔메토")
  public String item13() {
    return "ingredient/쏘팔메토";
  }

  @GetMapping("/nutrients/공액리놀레산")
  public String item14() {
    return "ingredient/공액리놀레산";
  }

  @GetMapping("/nutrients/엽산")
  public String item15() {
    return "ingredient/엽산";
  }

  @GetMapping("/nutrients/셀레늄(셀렌)")
  public String item16() {
    return "ingredient/셀레늄(셀렌)";
  }

  @GetMapping("/nutrients/폴리코사놀")
  public String item17() {
    return "ingredient/폴리코사놀";
  }

  @GetMapping("/nutrients/비타민B6")
  public String item18() {
    return "ingredient/비타민B6";
  }

  @GetMapping("/nutrients/비타민K")
  public String item19() {
    return "ingredient/비타민K";
  }

  @GetMapping("/nutrients/스페인감초추출물")
  public String item20() {
    return "ingredient/스페인감초추출물";
  }

  @GetMapping("/nutrients/크롬")
  public String item21() {
    return "ingredient/크롬";
  }

  @GetMapping("/nutrients/녹차추출물")
  public String item22() {
    return "ingredient/녹차추출물";
  }

  @GetMapping("/nutrients/비타민E")
  public String item23() {
    return "ingredient/비타민E";
  }

  @GetMapping("/nutrients/비타민B12")
  public String item24() {
    return "ingredient/비타민B12";
  }

  @GetMapping("/nutrients/아연")
  public String item25() {
    return "ingredient/아연";
  }

  @GetMapping("/nutrients/홍삼")
  public String item26() {
    return "ingredient/홍삼";
  }

  @GetMapping("/nutrients/밀크씨슬")
  public String item27() {
    return "ingredient/밀크씨슬";
  }

  @GetMapping("/nutrients/베타카로틴")
  public String item28() {
    return "ingredient/베타카로틴";
  }

  @GetMapping("/nutrients/마그네슘")
  public String item29() {
    return "ingredient/마그네슘";
  }

  @GetMapping("/nutrients/히알루론산")
  public String item30() {
    return "ingredient/히알루론산";
  }

  @GetMapping("/nutrients/비타민B2")
  public String item31() {
    return "ingredient/비타민B2";
  }

  @GetMapping("/nutrients/칼륨")
  public String item32() {
    return "ingredient/칼륨";
  }

  @GetMapping("/nutrients/프로폴리스")
  public String item33() {
    return "ingredient/프로폴리스";
  }

  @GetMapping("/nutrients/지아잔틴")
  public String item34() {
    return "ingredient/지아잔틴";
  }

  @GetMapping("/nutrients/아스타잔틴")
  public String item35() {
    return "ingredient/아스타잔틴";
  }

  @GetMapping("/nutrients/빌베리추출물")
  public String item36() {
    return "ingredient/빌베리추출물";
  }
}
