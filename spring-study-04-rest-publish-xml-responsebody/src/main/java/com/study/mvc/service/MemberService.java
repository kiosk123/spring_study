package com.study.mvc.service;

import java.util.Collection;

import com.study.mvc.domain.Member;

public interface MemberService {

    Collection<Member> findAll();
    Member find(long id);
}
