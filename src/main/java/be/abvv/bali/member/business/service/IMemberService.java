package be.abvv.bali.member.business.service;

import be.abvv.bali.member.persistence.domain.IMemberEntity;
import be.abvv.bali.member.persistence.rpg.domain.BaliFZZEntity;
import be.abvv.bali.member.persistence.rpg.domain.MemberEntity;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public interface IMemberService {
    String SCHEMA_NAME_KEY = "spring.db2.datasource.default_schema_name";
    public IMemberEntity getMember(String firstName, String lastName);
    public IMemberEntity getMember(String id);
    public void updatephonenumber(String id, String phone) throws SQLException;

    public void updateMembre(String id, MemberEntity member);
}
