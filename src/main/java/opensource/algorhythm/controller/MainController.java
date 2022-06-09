package opensource.algorhythm.controller;

import lombok.extern.slf4j.Slf4j;
import opensource.algorhythm.config.auth.PrincipalDetail;
import opensource.algorhythm.entity.Post;
import opensource.algorhythm.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Slf4j
@Controller
public class MainController {

    @Autowired
    PostService postService;

    @GetMapping(value = "/")
    public String main(Model model, @AuthenticationPrincipal PrincipalDetail principal){
        model.addAttribute("principal",principal);
        model.addAttribute("boj_username",principal.getBojUsername());
        model.addAttribute("github_username",principal.getGithubUsername());
        model.addAttribute("id",principal.getId());

        List<Post> postList = postService.findAllPost();
        log.info("allPostSize={}", postList.size());
        model.addAttribute("postList", postList);
        return "index";
    }



}
