package opensource.algorhythm.service;

import lombok.RequiredArgsConstructor;
import opensource.algorhythm.entity.Member;
import opensource.algorhythm.repository.MemberRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;

    private void validateDuplicateMember(Member member){
        Member findMember = memberRepository.findByUserName(member.getUsername());
        if (findMember != null){
            throw  new IllegalStateException("이미 가입된 회원입니다");
        }
    }

    //멤버 생성
    public Member saveMember(Member member){
        validateDuplicateMember(member);
        return memberRepository.save(member);
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Member member = memberRepository.findByUserName(userName);

        if (member == null){
            throw new UsernameNotFoundException(userName);
        }

        return User.builder()
                .username(member.getUsername())
                .password(member.getPassword())
                .roles(member.getRole().toString())
                .build();
    }

    //멤버 조회
    public Optional<Member> findMember(Long memberId){
        Optional<Member> member = memberRepository.findById(memberId);
        return member;
    }
}
