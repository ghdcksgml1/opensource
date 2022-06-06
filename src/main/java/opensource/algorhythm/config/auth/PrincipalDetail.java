package opensource.algorhythm.config.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import opensource.algorhythm.entity.Member;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@Getter
@AllArgsConstructor
public class PrincipalDetail implements UserDetails {

    private Member member;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(()->{ return "ROLE_"+member.getRole(); });
        return authorities;
    }

    @Override
    public String getPassword() {
        return member.getPassword();
    }

    @Override
    public String getUsername() {
        return member.getUsername();
    }

    public String getBojUsername() {
        return member.getBaekjoonId();
    }
    public String getGithubUsername() { return member.getGithubId(); }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
