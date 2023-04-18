package com.kh.yagiyo.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member {
  private Long memberId;
      private String id;
  private String pw;
  private String nick;
  private String email;
  private String gender;
  private String age;
  private String gubun;

  public Member(Long memberId, String id, String pw) {
    this.memberId = memberId;
    this.id = id;
    this.pw = pw;
  }
}
