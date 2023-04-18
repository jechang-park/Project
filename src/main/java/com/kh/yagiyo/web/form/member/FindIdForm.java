package com.kh.yagiyo.web.form.member;

import jakarta.validation.constraints.Email;
import lombok.Data;
@Data
public class FindIdForm {
  @Email
  private String email;

  private String id;
}
