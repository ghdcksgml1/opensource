package opensource.algorhythm.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import opensource.algorhythm.constant.Role;
import opensource.algorhythm.dto.MemberFormDto;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Getter @Setter
public class Member {

    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String username;

    private String githubId;

    private String baekjoonId;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "member")
    private List<Post> post = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "profile_id")
    private MemberProfile memberProfile;

    public static Member createMember(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder){
        Member member = new Member();
        member.setUsername(memberFormDto.getUsername());
        member.setGithubId(memberFormDto.getGithubId());
        member.setBaekjoonId(memberFormDto.getBaekjoonId());
        String password = passwordEncoder.encode(memberFormDto.getPassword());
        member.setPassword(password);
        member.setRole(Role.USER);
        return member;
    }
}
