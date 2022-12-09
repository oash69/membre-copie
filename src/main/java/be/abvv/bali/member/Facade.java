package be.abvv.bali.member;

import be.abvv.bali.member.business.service.IMemberService;
import be.abvv.bali.member.business.service.MemberService;
import be.abvv.bali.member.exception.BaliException;
import be.abvv.bali.member.exception.BusinessException;
import be.abvv.bali.member.persistence.domain.IMemberEntity;
import be.abvv.bali.member.persistence.rpg.Db2MultiTenantResolver;
import be.abvv.bali.member.persistence.rpg.domain.MemberEntity;
import be.abvvfgtb.bali.domain.BaliResultDto;
import be.abvvfgtb.bali.domain.MessageResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

@RequestMapping("/v1")
@RestController
@Slf4j
public class Facade implements IFacade{

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Autowired
    IMemberService memberService;

/*    @GetMapping("/memberFacade")
    public String getMember(@RequestParam(value = "firstName") String firstName,
                            @RequestParam(value = "lastName") String lastName,
                            HttpServletRequest request) {
        String html = "Hi " + firstName+ " " +lastName + " ! Your phone number is: " + memberService.getMember(firstName,lastName).getTelephone();
        html += "<form method='GET' action='/bali/services/v1/memberPhoneChange'>";
        html += "<label path='name'>Modify phone number </form:label> <input type='text' name='tel' maxlength='14' pattern= '([0-9]|\\/)*'/> </br>";
        html += "<input type='hidden' name ='id' value='" + memberService.getMember(firstName,lastName).getId()+"'>";
        html += "<input type='submit'>";
        html += "</br> </form>";

        return html;

    }*/
    @GetMapping("/memberFacade")
    public ResponseEntity<IMemberEntity> getMember(@RequestParam(value = "firstName") String firstName,
                                                   @RequestParam(value = "lastName") String lastName,
                                                   HttpServletRequest request) {

        log.debug("getMember ");
        System.out.println("getMember");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Warning", "attention au loup");

/*        if (true) {
            throw new BusinessException("Member not found", BusinessException.MEMBER_NOT_FOUND);
        }*/

        return new ResponseEntity<>(memberService.getMember(firstName,lastName),headers, HttpStatus.OK);
        //return new ResponseEntity<>(memberService.getMember(firstName,lastName),headers, HttpStatus.BAD_REQUEST);

/*        return ResponseEntity.ok(new BaliResultDto()
                .messageResponse(new MessageResponseDto()
                        .message(String.format(memberService.getMember(firstName,lastName)))));*/

    }
    /*
    @GetMapping("/memberFacade")
    public MemberEntity getMember(@RequestParam(value = "firstName") String firstName,
                                  @RequestParam(value = "lastName") String lastName,
                                  HttpServletRequest request) {

        log.debug("getMember ");
        System.out.println("getMember");
        return memberService.getMember(firstName,lastName);

    }*/
    @GetMapping("/memberPhoneChange")
    public ResponseEntity<IMemberEntity> setMember(@RequestParam(value = "tel") String tel, @RequestParam(value = "id") String id) throws SQLException {
        log.debug(" memberPhoneChange ");
        memberService.updatephonenumber(id, tel);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        return new ResponseEntity<>(memberService.getMember(id),headers, HttpStatus.OK);
    }

    @PutMapping("/putChange")
    public ResponseEntity<IMemberEntity> updateMember(@RequestParam(value = "tel") String tel, @RequestParam(value = "id") String id) throws SQLException {
        log.debug(" memberPhoneChange ");
        memberService.updatephonenumber(id, tel);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        return new ResponseEntity<>(memberService.getMember(id),headers, HttpStatus.OK);
    }

}
