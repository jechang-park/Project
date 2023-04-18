package com.kh.yagiyo.domain.member.svc;

import com.kh.yagiyo.domain.entity.Member;
import com.kh.yagiyo.domain.member.dao.MemberDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor


public class MemberSVCImpl implements MemberSVC {

  private final MemberDAO memberDAO;

  @Override
  public Member save(Member member) {
    return memberDAO.save(member);
  }

  @Override
  public void update(Member member) {
   memberDAO.update(member);
  }

  @Override
  public Optional<Member> findByEmail(String email) {
    {return memberDAO.findByEmail(email);}
  }

  @Override
  public Optional<Object> findById(Long MemberId) {
    return Optional.empty();
  }

  @Override
  public List<Member> findAll() {
    return null;
  }

  @Override
  public void delete(Long memberId) {
    memberDAO.delete(memberId);
  }

  @Override
  public boolean isExist(String id) {
    return memberDAO.isExist(id);
  }

  @Override
  public Optional<Member> login(String id, String pw) {
    return memberDAO.login(id, pw);
  }

  @Override
  public Optional<String> findEmailById(String email) {
    return memberDAO.findEmailById(email);
  }

  @Override
  public String emailCheck(String email) {
    Optional<Member> byMemberEmail = memberDAO.findByEmail(email);
    if (byMemberEmail.isPresent()) {
      // 조회 결과가 있다 -> 사용할 수 없다.
      return null;
    }else {
      // 조회결과가 없다 -> 사용할 수 있다.
    }
    return "ok";
  }

  @Override
  public String idCheck(String id) {
    Optional<Member> byMemberid = memberDAO.findById(id);
    if (byMemberid.isPresent()) {
    return null;
  }else {

  }
    return "ok";
}

  @Override
  public String nickCheck(String nick) {
    Optional<Member> byMemberNick = memberDAO.findByNick(nick);
    if (byMemberNick.isPresent()) {
    return null;
  }else{
    }
    return "ok";
  }
  @Override
  public boolean isExistByEmailAndId(String email, String id) {
    return memberDAO.isExistByEmailAndId(email, id);
  }

  @Override
  public void changePasswd(String email, String pw) {
    memberDAO.changePasswd(email,pw);
  }
}

