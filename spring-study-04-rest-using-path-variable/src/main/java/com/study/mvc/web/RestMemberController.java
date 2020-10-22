package com.study.mvc.web;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
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
    
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // yyyy-MM-dd  포맷을 Date 타입으로 바인딩하기 위한 처리
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

    @RequestMapping("/members")
    @ResponseBody
    public Members getRestMembers() {
        Members members = new Members();
        members.addMembers(memberService.findAll());
        return members;
    }

    // 와일드 카드 *를 사용하여 REST 엔드포인트를 표현할 수도 있다. "/member/*/{memberid}"
    @RequestMapping("/member/{memberid}")
    @ResponseBody
    public Member getMember(@PathVariable("memberid") long memberID) {
        return memberService.find(memberID);
    }
    
    @RequestMapping("/datebind/{date}")
    @ResponseBody
    public String getDatebind(@PathVariable("date")Date date) {
        return date.toString();
    }
}