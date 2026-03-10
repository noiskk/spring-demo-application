package com.example.demo.dao;

import com.example.demo.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberDao extends JpaRepository<Member, Long> {
    // 담당자 A가 MemberDao를 재작성할 때, 여기에 추가적인 쿼리 메서드를 정의할 것입니다.
}
