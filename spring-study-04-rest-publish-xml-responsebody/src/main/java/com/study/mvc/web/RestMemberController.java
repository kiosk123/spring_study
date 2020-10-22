package com.study.mvc.web;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @RequestMapping("/members") //이럴땐 requst url에 .xml를 붙여주거나 Accept에 xml 설정 필요
//    @RequestMapping(value = "/members", produces = MediaType.APPLICATION_XML_VALUE)
    @ResponseBody
    public Members getRestMembers() {
        Members members = new Members();
        members.addMembers(memberService.findAll());
        return members;
    }
}
