package be.abvv.bali.member.business.service;

import be.abvv.bali.member.persistence.rpg.dao.MemberRepository;
import be.abvv.bali.member.persistence.rpg.domain.MemberEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @Mock
    MemberRepository memberDao;

    MemberService memberService;

    @Mock
    Environment env;

    @BeforeEach
    public void init(){
        memberService = new MemberService(memberDao);
    }

    @Test
    void testGetMember() {
        when(memberDao.getMember(any(String.class),any(String.class) )).thenReturn(buildMember());
        assertEquals(memberService.getMember("Nico","Recchia").getId(),buildMember().getId());
    }


    @Test
    void testUpdatephonenumber(){
        memberService.updatephonenumber("1","064285296");
    }

    private MemberEntity buildMember(){
        MemberEntity member = new MemberEntity();
        member.setId("1");
        member.setPrenom("Nico");
        member.setNom("Recchia");
        member.setTelephone("064285296");
        return member;
    }
}