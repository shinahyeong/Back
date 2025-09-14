package com.sbs.sample.repository;

import com.sbs.sample.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
//DB와 연결되는 부분, @Repository는 @Component를 상속받아 만들어짐 -> 싱글톤 개념이 적용되어 단하나의 객체만 생성 (DB를 연결시키는 통로)
public interface MemberRepository extends JpaRepository<Member, Long> {
    // 기본적으로 제공되는 findByAll() => SELECT * from member;로 바뀌어 적용됨

    // SELECT * from member WHERE email = ''; => ' '내부와 같은 이메일이 있는지 찾으라는 의미
    Optional<Member> findByEmail(String email);
    boolean existsByEmail(String email);

    // SELECT * from member WHERE email = '' and pwd = ''; -> 작명 규칙에만 맞춰서 만들어 주면 쿼리문이 형성됨
    // but 모든게 작명 규칙으로 만들어지지는 않음  -> 실제 쿼리와 메서드를 연결시키는 방법을 이용
    Optional<Member> findByEmailAndPwd(String email, String pwd);
}
