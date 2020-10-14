package com.study.mvc.service;

import java.util.List;

import com.study.mvc.vo.Member;

public interface MemberService {

    void add(Member member);
    void remove(String memberName);
    List<Member> list();
}
