package com.kh.yagiyo.web;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 회원 세션 정보
 */
@Data
@AllArgsConstructor
public class LoginMember {
  private Long memberId;
  private String id;
  private String pw;
  private String nick;
  private String email;
  private String gender;
  private String age;
  private String gubun;
}
