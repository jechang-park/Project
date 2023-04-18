package com.kh.yagiyo.web.form.member;

import lombok.Data;

@Data
public class MemberForm {

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
