package be.abvv.bali.member.business.service;

import be.abvv.bali.member.exception.BusinessException;
import be.abvv.bali.member.exception.SystemException;
import be.abvv.bali.member.persistence.domain.IMemberEntity;
import be.abvv.bali.member.persistence.rpg.Db2MultiTenantResolver;
import be.abvv.bali.member.persistence.rpg.dao.MemberRepository;
import be.abvv.bali.member.persistence.rpg.domain.MemberEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Profile({"local", "!xxlocal"})
public class MemberService implements IMemberService{
    @Autowired
    private Environment env;

    @Autowired
    private MemberRepository memberDao;

    private String schemaPrefix;
    private String nationalSchemaPrefix;
    private boolean isProdEnv = false;


    /**
     * DB2 Service Implementation constructor.
     *
     * @param env environment file
     */
    @Autowired
    public MemberService(Environment env) {
        this.env = env;
        this.schemaPrefix = env.getProperty(SCHEMA_NAME_KEY,
                String.class,
                Db2MultiTenantResolver.DEFAULT_SCHEMA_PREFIX);
        this.nationalSchemaPrefix = env.getProperty("spring.db2.national.datasource.default_schema_name",
                String.class);


        this.isProdEnv = Arrays.asList(this.env.getActiveProfiles()).contains("prod");
    }
    public MemberService(MemberRepository memberDao) {
        this.memberDao=memberDao;
    }

    public IMemberEntity getMember(String firstName, String lastName) {
/*        Db2MultiTenantResolver.setTenant(
                this.schemaPrefix
                        + Db2MultiTenantResolver.DEFAULT_TENANT_IDENTIFIER);*/
/*        Db2MultiTenantResolver.setTenant(
                this.schemaPrefix
                        + "R");*/
        IMemberEntity member = memberDao.getMember(firstName,lastName);
        if(member ==null) {
            throw new BusinessException("Le membre n'existe pas", BusinessException.MEMBER_NOT_FOUND);
        }
        verifyMember(member);
        return member;
    }

    private void verifyMember(IMemberEntity member) {
        if (member.getId().equals("5000003")){
            member.setWarningMessage("Attention le membre pas en ordre de cotisation");
        }
    }

    //getMember with LIDNumber
    public IMemberEntity getMember(String id) {
        IMemberEntity member = memberDao.getMember(id);
        if(member !=null) {
            verifyMember(member);
            return member;
        }

        return null;
    }

    public void updatephonenumber(String id, String phone) throws SQLException {
        verifyPhoneNumber(phone);
/*        Db2MultiTenantResolver.setTenant(
                this.schemaPrefix
                        + "R");*/

        IMemberEntity member = new MemberEntity();
        member.setId(id);
        member.setTelephone(phone);
        memberDao.setMemberPhoneNumber(member);
        //throw new SQLException();
    }
    private void verifyPhoneNumber(String phone){
        Pattern pattern = Pattern.compile("([0-9]|\\/)*");
        Matcher matcher = pattern.matcher(phone);
        if (phone.length()>14){
            throw new BusinessException("Le numéro de de téléphone est trop long (max 14 caratères)", BusinessException.PHONE_TOO_LONG);
        }
        if (!matcher.matches()){
            throw new BusinessException("Le numéro de de téléphone est mal formé (unniquement chiffres ou / acceptés)", BusinessException.PHONE_MAL_FORMATTED);
        }
    }

    public void updateMembre(String id, MemberEntity member) {

/*        Db2MultiTenantResolver.setTenant(
                this.schemaPrefix
                        + "R");*/
        memberDao.setMemberPhoneNumber(member);
    }

}
