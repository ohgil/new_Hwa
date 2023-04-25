package org.example.controller;

import org.example.Container;
import org.example.dto.Member;
import org.example.service.MemberService;

public class MemberController {
  private MemberService memberService;

  public MemberController() {
    memberService = Container.memberService;
  }

  public void join() {
    String member_email;
    String member_pwd;
    String member_pwd_confirm;
    String member_name;
    String member_birth;
    String member_gender;

    System.out.println("== 회원 가입 ==");

    // 로그인 아이디 입력
    while (true) {
      System.out.printf("로그인 아이디(이메일): ");
      member_email = Container.scanner.nextLine().trim();

      if (member_email.length() == 0) {
        System.out.println("로그인 아이디(이메일)를 입력해주세요.");
        continue;
      }

      boolean isLoginIdDup = memberService.isLoginIdDup(member_email);

      if (isLoginIdDup) {
        System.out.printf("%s(은)는 이미 사용중인 로그인 아이디(이메일)입니다.\n", member_email);
        continue;
      }

      break;
    }

    // 로그인 비밀번호 입력
    while (true) {
      System.out.printf("로그인 비밀번호 : ");
      member_pwd = Container.scanner.nextLine().trim();

      if (member_pwd.length() == 0) {
        System.out.println("로그인 비밀번호를 입력해주세요.");
        continue;
      }

      // 로그인 비밀번호 확인 입력
      boolean member_pwd_confirmIsSame = true;

      while (true) {
        System.out.printf("로그인 비밀번호 확인 : ");
        member_pwd_confirm = Container.scanner.nextLine().trim();

        if (member_pwd_confirm.length() == 0) {
          System.out.println("로그인 비밀번호를 입력해주세요.");
          continue;
        }

        if (member_pwd.equals(member_pwd_confirm) == false) {
          System.out.println("로그인 비밀번호가 일치하지 않습니다.");
          member_pwd_confirmIsSame = false;
          break;
        }
        break;
      }

      if (member_pwd_confirmIsSame) {
        break;
      }
    }

    // 이름 입력
    while (true) {
      System.out.printf("닉네임 : ");
      member_name = Container.scanner.nextLine().trim();

      if (member_name.length() == 0) {
        System.out.println("닉네임을 입력해주세요.");
        continue;
      }
      break;
    }

    // 생년월일 입력
    while (true) {
      System.out.printf("생년월일 : ");
      member_birth = Container.scanner.nextLine().trim();

      if (member_birth.length() == 0) {
        System.out.println("생년월일을 입력해주세요.");
        continue;
      }
      break;
    }

    // 성별 입력
    while (true) {
      System.out.printf("성별 : ");
      member_gender = Container.scanner.nextLine().trim();

      if (member_gender.length() == 0) {
        System.out.println("성별을 입력해주세요.");
        continue;
      }
      break;
    }

    int id = memberService.join(member_email, member_pwd, member_name, member_birth, member_gender);

    System.out.printf("%d번 회원이 등록되었습니다.\n", id);
  }

  public void login() {
    String member_email;
    String member_pwd;

    System.out.println("== 로그인 ==");

    System.out.printf("로그인 아이디(이메일) : ");
    member_email = Container.scanner.nextLine().trim();

    if (member_email.length() == 0) {
      System.out.println("로그인 아이디(이메일)를 입력해주세요.");
      return;
    }

    Member member = memberService.getMemberByLoginId(member_email);//member_email

    if (member == null ) {
      System.out.println("입력하신 로그인 아이디(이메일)는 존재하지 않습니다.");
      return;
    }

    int loginTryMaxCount = 3; // 로그인아이디를 친 후 비밀번호 3번 이상 틀리면 비밀번호 확인 후 다음에 시도하라는 문구 나옴
    int loginTryCount = 0;

    // 로그인 비밀번호 입력
    while (true) {
      if(loginTryCount >= loginTryMaxCount) {
        System.out.println("비밀번호 확인 후 다음에 다시 시도해주세요.");
        break;
      }

      System.out.printf("로그인 비밀번호 : ");
      member_pwd = Container.scanner.nextLine().trim();

      if (member_pwd.length() == 0) {
        System.out.println("로그인 비밀번호를 입력해주세요.");
        continue;
      }

      if(member.getMember_pwd().equals(member_pwd) == false) {
        loginTryCount++;
        System.out.println("비밀번호가 일치하지 않습니다.");
        continue;
      }

      System.out.printf("\"%s\"님 환영합니다.\n", member.getMember_name());

      System.out.printf("\"%s\"님 ! 로그인 이후 사용 가능 한 기능목록 입니다. \n 1. 내정보 \n 2. 상품등록 \n 3. 상품검색 \n 4. 상품설명 \n 5. 상품수정 \n 6. 상품삭제 \n 7. 리뷰등록 \n 8. 리뷰검색 \n 9. 리뷰수정 \n 10. 리뷰삭제\n", member.getMember_name());
      Container.session.login(member);

      break;
    }
  }

  public void whoami() {
    if(Container.session.isLogined() == false) {
      System.out.println("로그인 상태가 아닙니다.");
    }
    else {
      System.out.println("아이디 : " + Container.session.loginedMember.getMember_email());
      System.out.println("비밀번호 : " + Container.session.loginedMember.getMember_pwd());
      System.out.println("닉네임 : " + Container.session.loginedMember.getMember_name());
      System.out.println("생년월일 : " + Container.session.loginedMember.getMember_birth());
      System.out.println("성별 : " + Container.session.loginedMember.getMember_gender());
    }
  }

  public void logout() {
    Container.session.logout();
    System.out.println("로그아웃 되었습니다.");
  }
}
