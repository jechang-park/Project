package com.kh.yagiyo.domain.member.dao;

import com.kh.yagiyo.domain.entity.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor

public class MemberDAOImpl implements MemberDAO {
  private final NamedParameterJdbcTemplate template;
  private final JdbcTemplate jdbcTemplate;

  @Override
  //등록

  public Member save(Member member) {
    StringBuffer sql = new StringBuffer();
    sql.append("insert into member ( ");
    sql.append("    Memberid, ");
    sql.append("    id, ");
    sql.append("    pw, ");
    sql.append("    nick, ");
    sql.append("    email, ");
    sql.append("    gender, ");
    sql.append("    age ");
    sql.append(") values( ");
    sql.append("    Memberid_seq.nextval, ");
    sql.append("    :id, ");
    sql.append("    :pw, ");
    sql.append("    :nick, ");
    sql.append("    :email, ");
    sql.append("    :gender, ");
    sql.append("    :age ");
    sql.append(") ");

    SqlParameterSource param = new BeanPropertySqlParameterSource(member);
    KeyHolder keyHolder = new GeneratedKeyHolder(); //insert된 레코드에서 컬럼값추출
    template.update(sql.toString(), param, keyHolder, new String[]{"memberid"});

    long memberId = keyHolder.getKey().longValue();

    member.setMemberId(memberId);
    return member;
  }

  @Override
  public void update(Member member) {
    log.info("member", member);

    StringBuffer sql = new StringBuffer();
    sql.append("update member ");
    sql.append("   set id = :id, ");
    sql.append("       pw = :pw, ");
    sql.append("       nick = :nick, ");
    sql.append("       email = :email ");
    sql.append(" where memberid = :memberId ");

    SqlParameterSource param = new MapSqlParameterSource()
        .addValue("id", member.getId())
        .addValue("pw", member.getPw())
        .addValue("nick", member.getNick())
        .addValue("email", member.getEmail())
        .addValue("memberId", member.getMemberId());

    template.update(sql.toString(), param);
  }

  @Override
  public Optional<Member> findByEmail(String email) {
    StringBuffer sql = new StringBuffer();
    sql.append("select memberid, ");
    sql.append("       id ");
    sql.append("       pw, ");
    sql.append("       nick, ");
    sql.append("       email, ");
    sql.append("       gender, ");
    sql.append("       age ");
    sql.append("  from member ");
    sql.append(" where email = :email ");

    try {
      Map<String, String> param = Map.of("email", email);
      Member member = template.queryForObject(
          sql.toString(),
          param,
          BeanPropertyRowMapper.newInstance(Member.class)
      );
      return Optional.of(member);
    } catch (EmptyResultDataAccessException e) {
      //조회결과가 없는 경우
      return Optional.empty();
    }
  }

  /**
   * @param
   * @return
   */
  @Override
  public Optional<Member> findById(String id) {
    StringBuffer sql = new StringBuffer();
    sql.append("select memberid, ");
    sql.append("       id ");
    sql.append("       pw, ");
    sql.append("       nick, ");
    sql.append("       email, ");
    sql.append("       gender, ");
    sql.append("       age ");
    sql.append("  from member ");
    sql.append(" where id = :id ");

    try {
      Map<String, String> param = Map.of("id", id);
      Member member = template.queryForObject(
          sql.toString(),
          param,
          BeanPropertyRowMapper.newInstance(Member.class)
      );
      return Optional.of(member);
    } catch (EmptyResultDataAccessException e) {
      return Optional.empty();
    }
  }

  /**
   * @return
   */
  @Override
  public List<Member> findAll() {
    StringBuffer sql = new StringBuffer();

    sql.append("select memberId, ");
    sql.append("       id, ");
    sql.append("       pw, ");
    sql.append("       nick, ");
    sql.append("       email, ");
    sql.append("       gender, ");
    sql.append("       age ");
    sql.append("  from member ");
    sql.append(" order by memberid desc ");

    List<Member> list = template.query(
        sql.toString(),
        BeanPropertyRowMapper.newInstance(Member.class)
    );

    return list;
  }

  /**
   * @param memberId
   */
  @Override
  public void delete(Long memberId) {
    StringBuffer sql = new StringBuffer();
    sql.append(" delete from member ");
    sql.append(" where memberid = :memberid ");

    Map<String, Long> param = Map.of("memberid", memberId);
    template.update(sql.toString(), param);
  }

  /**
   * @param
   * @return
   */
  @Override
  public boolean isExist(String id) {
    boolean flag = false;
    String sql = "select count(id) from member where id = :id ";

    Map<String, String> param = Map.of("id", id);

    Integer cnt = template.queryForObject(sql, param, Integer.class);

    return cnt == 1 ? true : false;
  }

  /**
   * @param
   * @param
   * @return
   */
  @Override
  public Optional<Member> login(String id, String pw) {
    StringBuffer sql = new StringBuffer();
    sql.append(" select memberid, ");
    sql.append("       id, ");
    sql.append("       pw, ");
    sql.append("       email, ");
    sql.append("       nick, ");
    sql.append("       gubun ");
    sql.append("  from member ");
    sql.append(" where id = :id and pw = :pw ");

    Map<String, String> param = Map.of("id", id, "pw", pw);
    // 레코드1개를 반환할경우 query로 list를 반환받고 list.size() == 1 ? list.get(0) : null 처리하자!!
    List<Member> list = template.query(
        sql.toString(),
        param,
        BeanPropertyRowMapper.newInstance(Member.class) //자바객체 <=> 테이블 레코드 자동 매핑
    );

    return list.size() == 1 ? Optional.of(list.get(0)) : Optional.empty();
  }

  /**
   * @param
   * @return
   */
  @Override
  public Optional<String> findEmailById(String email) {
    StringBuffer sql = new StringBuffer();
    sql.append("select id ");
    sql.append("  from member ");
    sql.append(" where email = :email ");

    Map<String, String> param = Map.of("email", email);
    List<String> result = template.query(
        sql.toString(),
        param,
        (rs, rowNum) -> rs.getNString("id")
    );

    return (result.size() == 1) ? Optional.of((String)result.get(0)) : Optional.empty();
  }

  @Override
  public Optional<Member> findByNick(String nick) {
    StringBuffer sql = new StringBuffer();
    sql.append("select memberid, ");
    sql.append("       id ");
    sql.append("       pw, ");
    sql.append("       nick, ");
    sql.append("       email, ");
    sql.append("       gender, ");
    sql.append("       age ");
    sql.append("  from member ");
    sql.append(" where nick = :nick ");

    try {
      Map<String, String> param = Map.of("nick", nick);
      Member member = template.queryForObject(
          sql.toString(),
          param,
          BeanPropertyRowMapper.newInstance(Member.class)
      );
      return Optional.of(member);
    } catch (EmptyResultDataAccessException e) {
      return Optional.empty();
    }
  }
//  이메일 닉네임 존재확인
  @Override
  public boolean isExistByEmailAndId(String email, String id) {
    boolean isExist = false;

    StringBuffer sql = new StringBuffer();
    sql.append("select count(*) ");
    sql.append("  from member ");
    sql.append(" where email = :email ");
    sql.append("   and id = :id ");

    Map<String, String> param = Map.of("email",email,"id",id);

    Integer integer = template.queryForObject(sql.toString(), param, Integer.class);
    if (integer == 1) isExist = true;

    return isExist;
  }

  //비밀번호변경
  @Override
  public void changePasswd(String email, String pw) {
    StringBuffer sql = new StringBuffer();
    sql.append("update member ");
    sql.append("   set pw = :pw ");
    sql.append(" where email = :email ");

    Map<String, String> param = Map.of("email",email,"pw",pw);
    template.update(sql.toString(),param);
  }
}
