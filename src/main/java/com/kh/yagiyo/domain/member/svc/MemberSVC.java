package com.kh.yagiyo.domain.member.svc;

import com.kh.yagiyo.domain.entity.Member;

import java.util.List;
import java.util.Optional;

public interface MemberSVC {

  Member save(Member member);

  //수정
  void update(Member member);

  //조회 by mail
  Optional<Member> findByEmail(String email);

  //조회 by member_id
  Optional<Object> findById(Long MemberId);

  //전체조회
  List<Member> findAll();

  //탈퇴
  void delete(Long memberId);

  //회원유무
  boolean isExist(String id);

  //로그인
  Optional<Member> login(String id, String pw);

  //아이디찾기
  Optional<String> findEmailById(String email);

  String emailCheck(String email);

  String idCheck(String id);

  String nickCheck(String nick);

  boolean isExistByEmailAndId(String email, String id);

  void changePasswd(String email, String pw);

}
