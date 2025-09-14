package com.sbs.sample.service;

import com.sbs.sample.dto.LoginReqDto;
import com.sbs.sample.dto.SignUpReqDto;
import com.sbs.sample.entity.Member;
import com.sbs.sample.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service // 싱글톤 객체 (스프링 빈 컨테이너에 등록된 싱글톤 객체)
@Transactional  // 여러 개의 물리적 작업 단위를 논리적으로 묶음 , 자동으로 rollback 수행
@RequiredArgsConstructor // 생성자를 자동으로 생성해주는 객체
@Slf4j  // log 메세지를 사용하기 위함 (lombok 제공)

public class AuthService {
    private final MemberRepository memberRepository;    //생성자를 통한 의존성 주입, 내가 만든게 아닌 외부에서 만든걸 받음 (RequiredArgsConstructor을 통해 한줄로 간략히 생성 가능

    // 회원가입 여부 확인
    public boolean isMember(String email){
        return memberRepository.existsByEmail(email);   // 입력창에서 이메일을 입력하는 순간 이 repository에 존재하는지 유무를 확인해줌 -> 있으면 이미 가입되었다고 알려줌
    }

    // 회원가입
    public boolean signup(SignUpReqDto signUpReqDto){
        try {
            //장애 발생 가능성이 있는 구문 넣기
            Member member = convertDtoToEntity(signUpReqDto);
            memberRepository.save(member);  // 쿼리문에서 INSET와 UPDATE의 역할 수행
            return true;
        } catch(Exception e){
            log.error("회원 가입시 오류 발생: {}", e.getMessage());
            return false;
        }
    }

    // 로그인
    public boolean login(LoginReqDto loginReqDto){  //String email, String pwd도 가능
        // Optional:  Null인지 아닌지를 한번 판별해줄 수 있음 -> Null이외에도 이 값이 존재하는지 존재 여부를 알려줄 수 있음
        Optional<Member> member = memberRepository.findByEmailAndPwd(loginReqDto.getEmail(), loginReqDto.getPwd());
        return member.isPresent();  // 여기서 해당하는 객체가 넘어오면 true, 즉 정상적으로 로그인이 됨 , if not false가 넘어옴
    }

    // Dto를 Entity로 변환하는 메서드 -> 자주 사용되어 밖으로 빼냄 (내부에서 사용되기 때문에 private사용-> 같은 클래스 내에서 부름)
    private Member convertDtoToEntity(SignUpReqDto signUpReqDto){
        Member member = new Member();   // member 빈 객체 생성
        member.setEmail(signUpReqDto.getEmail());   // member에 Setter가 존재해서 가능
        member.setPwd(signUpReqDto.getPwd());
        member.setName(signUpReqDto.getName());
        return member;
    }
}
