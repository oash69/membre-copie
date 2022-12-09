package be.abvv.bali.member.persistence.rpg.dao;

import be.abvv.bali.member.business.IMemberRepository;
import be.abvv.bali.member.business.service.IMemberService;
import be.abvv.bali.member.persistence.domain.IMemberEntity;
import be.abvv.bali.member.persistence.mapper.MemberMapper;
import be.abvv.bali.member.persistence.rpg.Db2MultiTenantResolver;
import be.abvv.bali.member.persistence.rpg.domain.BaliFZZEntity;
import be.abvv.bali.member.persistence.rpg.domain.MemberEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class MemberRepository implements IMemberRepository {



    @Autowired
    BaliFZZDAO baliFZZDAO;

    @Autowired
    BaliFLIDAO baliFLIDAO;
    @Autowired
    MemberMapper memberMapper;

    private String schemaPrefix;

    @Autowired
    private Environment env;

    private String nationalSchemaPrefix;

    private boolean isProdEnv = false;

    @Autowired
    public MemberRepository(Environment env) {
        this.env = env;
        this.schemaPrefix = env.getProperty(IMemberService.SCHEMA_NAME_KEY,
                String.class,
                Db2MultiTenantResolver.DEFAULT_SCHEMA_PREFIX);
        this.nationalSchemaPrefix = env.getProperty("spring.db2.national.datasource.default_schema_name",
                String.class);


        this.isProdEnv = Arrays.asList(this.env.getActiveProfiles()).contains("prod");
    }

    @Override
    public IMemberEntity getMember(String firstname, String lastname) {
        IMemberEntity member= new MemberEntity();
        List<BaliFZZEntity> baliFZZList = baliFZZDAO.findGsmByFirstNameAndLastName(firstname,lastname);
        if(!baliFZZList.isEmpty()) {
            BaliFZZEntity baliFzz = baliFZZList.get(0);
            member= memberMapper.toMember(baliFzz);
            return member;
        }
        return null;
    }
    @Override
    public IMemberEntity getMember(String id) {
        IMemberEntity member= new MemberEntity();
        Optional<BaliFZZEntity> baliFZZList = baliFZZDAO.findById(id);
        if(!baliFZZList.isEmpty()) {
            BaliFZZEntity baliFzz = baliFZZList.get();
            member= memberMapper.toMember(baliFzz);
        }
        return member;
    }

    @Override
    public void setMemberPhoneNumber(IMemberEntity member) {
        Db2MultiTenantResolver.setTenant("ABVVTFXR");
        baliFZZDAO.updatePhone(member.getId(), member.getTelephone());
    }
}
