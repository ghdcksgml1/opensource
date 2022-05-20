package opensource.algorhythm.service;

import lombok.RequiredArgsConstructor;
import opensource.algorhythm.entity.Member;
import opensource.algorhythm.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    private void validateDuplicateMember(Member member){
        Member findMember = memberRepository.findByEmail(member.getEmail());
        if (findMember != null){
            throw  new IllegalStateException("이미 가입된 회원입니다");
        }
    }

    public Member saveMember(Member member){
        validateDuplicateMember(member);
        return memberRepository.save(member);
    }
}
