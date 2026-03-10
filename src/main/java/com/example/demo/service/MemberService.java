package com.example.demo.service;

import com.example.demo.dao.MemberDao;
import com.example.demo.dto.MemberDto;
import com.example.demo.model.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberDao memberDao;

    public MemberDto login(String loginId, String password) {
        Member member = memberDao.findByLoginId(loginId);
        if (member != null && member.getPassword().equals(password)) {
            return MemberDto.from(member);
        }
        return null;
    }

    public MemberDto getMember(String memberNo) {
        Member member = memberDao.findByMemberNo(memberNo);
        if (member == null) {
            return null;
        }
        return MemberDto.from(member);
    }

    public List<MemberDto> getAllMembers() {
        return memberDao.findAll()
                .stream()
                .map(MemberDto::from)
                .collect(Collectors.toList());
    }
}
