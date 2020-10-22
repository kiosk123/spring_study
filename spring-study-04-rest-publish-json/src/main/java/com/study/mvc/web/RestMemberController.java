package com.study.mvc.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @RequestMapping(value="/members", produces=MediaType.APPLICATION_XML_VALUE)
    public String getRestMembersXml(Model model) {
        Members members = new Members();
        members.addMembers(memberService.findAll());
        model.addAttribute("members", members);
        return "xmlmembertemplate";
    }

    @RequestMapping(value="/members", produces= MediaType.APPLICATION_JSON_VALUE)
    public String getRestMembersJson(Model model) {
        Members members = new Members();
        members.addMembers(memberService.findAll());
        model.addAttribute("members", members);
        return "jsonmembertemplate";
    }
    
    @RequestMapping(value = "/rest/members", 
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Members getJson() {
        Members members = new Members();
        members.addMembers(memberService.findAll());
        return members;
    }
}
