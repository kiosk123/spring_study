package com.study.mvc.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.study.mvc.domain.Member;
import com.study.mvc.domain.Members;
import com.study.mvc.service.MemberService;

@Controller
public class RestMemberController {

    private final MemberService memberService;

    @Autowired
    public RestMemberController(MemberService memberService) {
        super();
        this.memberService = memberService;
    }

    @RequestMapping("/members")
    @ResponseBody
    public Members getRestMembers() {
        Members members = new Members();
        members.addMembers(memberService.findAll());
        return members;
    }

    @RequestMapping("/member/{memberid}")
    @ResponseBody
    public ResponseEntity<Member> getMember(@PathVariable("memberid") long memberID) {
        Member member = memberService.find(memberID);
        if (member != null) {
            //응답할 데이터를 찾은 경우 응답코드 200과 데이터 응답 
            return new ResponseEntity<>(member, HttpStatus.OK);
        }
        //응답 데이터를 못 찾은 경우 응답코드 404만 반환
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}