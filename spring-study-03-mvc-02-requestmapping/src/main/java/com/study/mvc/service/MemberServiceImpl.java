package com.study.mvc.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.stereotype.Service;

import com.study.mvc.vo.Member;

@Service
class MemberServiceImpl implements MemberService {

    private Map<String, Member> members = new TreeMap<>();

    public void add(Member member) {
        members.put(member.getName(), member);
    }

    public void remove(String memberName) {
        members.remove(memberName);
    }

    public List<Member> list() {
        return new ArrayList<>(members.values());
    }
}
