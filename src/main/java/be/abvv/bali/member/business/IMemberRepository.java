package be.abvv.bali.member.business;

import be.abvv.bali.member.persistence.domain.IMemberEntity;
import be.abvv.bali.member.persistence.rpg.domain.MemberEntity;

public interface IMemberRepository {
    IMemberEntity getMember(String nom, String prenom);

    IMemberEntity getMember(String id);

    void setMemberPhoneNumber(IMemberEntity member);
}
