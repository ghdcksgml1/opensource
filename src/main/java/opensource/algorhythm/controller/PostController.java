package opensource.algorhythm.controller;

import lombok.RequiredArgsConstructor;
import opensource.algorhythm.config.auth.PrincipalDetail;
import lombok.extern.slf4j.Slf4j;
import opensource.algorhythm.dto.PostFormDto;
import opensource.algorhythm.entity.Post;
import opensource.algorhythm.repository.MemberRepository;
import opensource.algorhythm.service.PostService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final MemberRepository memberRepository;

    //게시물 폼 생성
    @GetMapping(value = "/new")
    public String postForm(Model model, @AuthenticationPrincipal PrincipalDetail principal){
        model.addAttribute("postFormDto", new PostFormDto());
        model.addAttribute("principal",principal);

        return "contentForm";
    }

    //게시물 생성
    @ResponseBody
    @PostMapping("/new")
    public String newPost(@RequestBody PostFormDto postFormDto, Model model,@AuthenticationPrincipal PrincipalDetail principal){
        try {

            Long member_id = memberRepository.findByUsername(principal.getUsername()).getId();
            postFormDto.setMemberId(member_id);

            Post post = postService.createPost(postFormDto);
            log.info("title={}, num={}, name={}, code={}", post.getTitle(), post.getProblemNum(), post.getProblemName(), post.getCode());

        }catch (IllegalStateException e){
            model.addAttribute("errorMessage", e.getMessage());
            return "signup";
        }
        return "ok";
    }

    //게시물 조회
    @GetMapping(value = "/{id}")
    public String seePost(){
        return "";
    }

    //게시물 수정
    @PostMapping(value = "/{id}/edit")
    public String editPost(){
        return "";
    }

    //게시물 삭제
    @PostMapping(value = "/{id}/delete")
    public String deletePost(){
        return "";
    }

    // 유저 프로필 페이지
    @GetMapping("/userProfile")
    public String userProfile(Model model, @AuthenticationPrincipal PrincipalDetail principal){
        model.addAttribute("principal",principal);
        model.addAttribute("boj_username",principal.getBojUsername());
        return "contentView";
    }
}
