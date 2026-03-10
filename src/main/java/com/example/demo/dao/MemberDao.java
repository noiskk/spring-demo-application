package com.example.demo.dao;

import com.example.demo.model.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberDao {

    @PersistenceContext
    private EntityManager em;

    public Member findByMemberNo(String memberNo) {
        return em.find(Member.class, memberNo);
    }

    public Member findByLoginId(String loginId) {
        return em.createQuery("SELECT m FROM Member m WHERE m.loginId = :loginId", Member.class)
                .setParameter("loginId", loginId)
                .getResultStream()
                .findFirst()
                .orElse(null);
    }

    public List<Member> findAll() {
        return em.createQuery("SELECT m FROM Member m", Member.class)
                .getResultList();
    }
}
