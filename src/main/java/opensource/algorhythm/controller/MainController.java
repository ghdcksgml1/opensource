package opensource.algorhythm.controller;

import opensource.algorhythm.config.auth.PrincipalDetail;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {

    @GetMapping(value = "/")
    public String main(Model model, @AuthenticationPrincipal PrincipalDetail principal){
        model.addAttribute("principal",principal);
        model.addAttribute("boj_username",principal.getBojUsername());
        model.addAttribute("github_username",principal.getGithubUsername());
        return "index";
    }

    @GetMapping(value = "/login")
    public String login(){
        return "login";
    }

}
