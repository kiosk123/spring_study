package com.study.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.study.mvc.service.MemberService;
import com.study.mvc.vo.Member;

@Controller
@RequestMapping("/member/*")
public class MemberController {
    
    @Autowired
    private MemberService memberService;
    
    @RequestMapping("add")
    public String addMember(Model model) {
        model.addAttribute("member", new Member());
        model.addAttribute("guest", memberService.list());
        return "memberList";
    }
    
    @RequestMapping(value = {"remove", "delete"}, method = RequestMethod.GET)
    public String removeMember(@RequestParam("memberName")String memberName) {
        memberService.remove(memberName);
        return "redirect:";
    }
    
    @RequestMapping("display/{member}")
    public String displayMember(@PathVariable("member")String member, Model model) {
        Member foundMember = memberService.list().stream()
                            .filter(m -> m.getName().equals(member))
                            .findFirst()
                            .get();
        
        model.addAttribute("member", foundMember);
        return "member";
    }
    
    @RequestMapping
    public void memberList() { }
}
