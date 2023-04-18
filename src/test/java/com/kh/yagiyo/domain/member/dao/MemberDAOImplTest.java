package com.kh.yagiyo.domain.member.dao;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@Slf4j
@SpringBootTest
public class MemberDAOImplTest {

  @Autowired
  private MemberDAO memberDAO;

  @Test
  void findId(){
    String email = "test1@gmail.com";
    Optional<String> id = memberDAO.findEmailById(email);
    Assertions.assertThat(id.get()).isEqualTo("testtest");
  }


}
