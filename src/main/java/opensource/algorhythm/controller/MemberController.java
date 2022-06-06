package opensource.algorhythm.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import opensource.algorhythm.dto.MemberFormDto;
import opensource.algorhythm.entity.Member;
import opensource.algorhythm.service.MemberService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RequestMapping("/members")
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping(value = "/new")
    public String memberForm(Model model){
        model.addAttribute("memberFormDto", new MemberFormDto());
        return "signup";
    }

    @ResponseBody
    @PostMapping(value = "/new")
    public String newMember(@RequestBody MemberFormDto memberFormDto, BindingResult bindingResult, Model model){

        if (bindingResult.hasErrors()){
            return "signup";
        }
        try {
            Member member = Member.createMember(memberFormDto, passwordEncoder);
            memberService.saveMember(member);
            log.info("username={}, baekjoonId={}, githubId={}"
                    ,member.getUsername(), member.getBaekjoonId(), member.getGithubId());
        }catch (IllegalStateException e){
            model.addAttribute("errorMessage", e.getMessage());
            return "signup";
        }

        return "ok";
    }

    //회원 프로필 조회
    @GetMapping(value = "/{id}")
    public String seeProfile(){
        return "ok";
    }


}
