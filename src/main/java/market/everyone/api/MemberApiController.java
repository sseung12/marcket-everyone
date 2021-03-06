package market.everyone.api;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import market.everyone.domain.Member;
import market.everyone.dto.MemberRequestDto;
import market.everyone.dto.MemberResponseDto;
import market.everyone.response.Message;
import market.everyone.response.StatusEnum;
import market.everyone.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MemberApiController {

    private final MemberService memberService;


    @PostMapping(value = "/api/member/signup", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity signup(@RequestBody @Valid MemberRequestDto request, Errors error) {

        log.info("signup {}", request);
        Member member = memberService.signup(request);

        MemberResponseDto response = MemberResponseDto.createDto(member);

        Message msg = Message.createMessage(StatusEnum.OK, "정상적으로 등록됐습니다.", response);

        return new ResponseEntity(msg, HttpStatus.OK);
    }

    @GetMapping(value = "/api/member/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity findMemberById(@PathVariable("id") Long id) {

        Member member = memberService.findById(id);
        MemberResponseDto dto = MemberResponseDto.createDto(member);
        Message msg = Message.createMessage(StatusEnum.OK, "아이디 반환", dto);
        return new ResponseEntity(msg, HttpStatus.OK);
    }


    @DeleteMapping(value = "/api/member/{id}")
    public ResponseEntity deleteMember(@PathVariable("id") Long id) {
        memberService.deleteMember(id);
        return new ResponseEntity("삭제완료",HttpStatus.OK);
    }
}
