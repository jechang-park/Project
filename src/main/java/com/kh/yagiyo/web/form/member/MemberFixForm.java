package com.kh.yagiyo.web.form.member;

import com.kh.yagiyo.domain.entity.Member;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
@Data
public class MemberFixForm {

        private Long memberId;

        private String id;

        private String pw;

        private String pw_check;

        private String nick;

        private String email;

        private String gender;

        private String age;

        private String gubun;

}
