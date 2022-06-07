package opensource.algorhythm.controller;

import opensource.algorhythm.config.auth.PrincipalDetail;
import opensource.algorhythm.entity.Post;
import opensource.algorhythm.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Controller
public class MainController {

    @Autowired
    PostService postService;

    @GetMapping(value = "/")
    public String main(Model model, @AuthenticationPrincipal PrincipalDetail principal){
        model.addAttribute("principal",principal);
        model.addAttribute("boj_username",principal.getBojUsername());
        model.addAttribute("github_username",principal.getGithubUsername());
        List<Post> postList = postService.findAllPost();
        model.addAttribute("postList", postList);
        return "index";
    }

    @GetMapping(value = "/login")
    public String login(){
        return "login";
    }

}
