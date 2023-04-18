package com.kh.yagiyo.web.form.member;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class JoinForm {

  private String id;

  private String pw;

  private String pw_check;

  private String nick;

  private String email;

  private String gender;

  private String age;

}
