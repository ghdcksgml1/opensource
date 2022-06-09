package opensource.algorhythm.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import opensource.algorhythm.config.auth.PrincipalDetail;
import opensource.algorhythm.dto.MemberFormDto;
import opensource.algorhythm.entity.Member;
import opensource.algorhythm.repository.MemberRepository;
import opensource.algorhythm.service.MemberService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping(value = "/auth/login")
    public String login(){
        return "login";
    }

    @GetMapping(value = "/auth/new")
    public String memberForm(Model model){
        model.addAttribute("memberFormDto", new MemberFormDto());
        return "signup";
    }

    @ResponseBody
    @PostMapping(value = "/auth/new")
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
    @GetMapping(value = "/members/{userId}")
    public String seeProfile(@PathVariable Long userId, Model model, @AuthenticationPrincipal PrincipalDetail principal){
        model.addAttribute("principal",principal);
        model.addAttribute("boj_username",principal.getBojUsername());
        model.addAttribute("github_username",principal.getGithubUsername());
        model.addAttribute("id",principal.getId());
        Member member = memberRepository.findById(userId).get();
        model.addAttribute("member", member);
        return "userProfile";
    }


}
