package opensource.algorhythm.config.auth;

import lombok.RequiredArgsConstructor;
import opensource.algorhythm.entity.Member;
import opensource.algorhythm.repository.MemberRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrincipalDetailService implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member principal = memberRepository.findByUsername(username);
        if(principal == null){
            throw new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다.");
        }
        return new PrincipalDetail(principal);
    }
}
