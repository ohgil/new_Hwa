package org.example.service;

import org.example.Container;
import org.example.dto.Member;
import org.example.repository.MemberRepository;

public class MemberService {
  private MemberRepository memberRepository;
  public MemberService() {
    memberRepository = Container.memberRepository;
  }

  public boolean isLoginIdDup(String member_email) {
    return memberRepository.isLoginIdDup(member_email);
  }

  public int join(String member_email, String member_pwd, String member_name, String member_birth, String member_gender) {
    return memberRepository.join(member_email, member_pwd, member_name, member_birth, member_gender);
  }

  public Member getMemberByLoginId(String member_email) {
    return memberRepository.getMemberByLoginId(member_email);
  }
}
