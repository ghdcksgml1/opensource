package opensource.algorhythm.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import opensource.algorhythm.constant.Role;
import opensource.algorhythm.dto.MemberFormDto;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Entity
@Table
@Getter @Setter
@ToString
public class Member {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String userName;

    @Column(unique = true)
    private String email;

    private String baekjoonId;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    public static Member createMember(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder){
        Member member = new Member();
        member.setUserName(memberFormDto.getUserName());
        member.setEmail(memberFormDto.getEmail());
        member.setBaekjoonId(memberFormDto.getBaekjoonId());
        String password = passwordEncoder.encode(memberFormDto.getPassword());
        member.setPassword(password);
        member.setRole(Role.USER);
        return member;
    }
}
