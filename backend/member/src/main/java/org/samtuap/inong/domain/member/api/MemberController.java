package org.samtuap.inong.domain.member.api;

import lombok.RequiredArgsConstructor;
import org.samtuap.inong.domain.member.dto.SignInRequest;
import org.samtuap.inong.domain.member.dto.SignInResponse;
import org.samtuap.inong.domain.member.dto.SignUpRequest;
import org.samtuap.inong.domain.member.dto.SignUpResponse;
import org.samtuap.inong.domain.member.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.samtuap.inong.domain.member.dto.MemberDetailResponse;
import org.samtuap.inong.domain.member.service.MemberService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RequestMapping("/member")
@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    // 소셜 로그인
    @PostMapping("/sign-in")
    public ResponseEntity<?> signIn(@RequestHeader("Authorization") final String authorizationHeader, @RequestBody final SignInRequest signInRequest) {
        String bearerToken = authorizationHeader.replace("Bearer ", "");
        SignInResponse signInResponse = memberService.signIn(bearerToken, signInRequest.socialType());

        return new ResponseEntity<>(signInResponse, HttpStatus.OK);
    }

    // 회원가입
    @PostMapping("/sign-up")
    public ResponseEntity<SignUpResponse> signUp(@RequestHeader("Authorization") final String authorizationHeader, @RequestBody final SignUpRequest signUpRequest) {
        String socialAccessToken = authorizationHeader.replace("Bearer ", "");
        SignUpResponse signUpResponse = memberService.signUp(socialAccessToken, signUpRequest);

        return new ResponseEntity<>(signUpResponse, HttpStatus.CREATED);
    }

    @GetMapping
    public String testApi() {
        return "hello world!!!";
    }

    /**
     * id로 회원 찾아오기 => product 모듈에서 feignclient로 찾아올 수 있도록 추가
     */
    @GetMapping("/{id}")
    public MemberDetailResponse findMember(@PathVariable("id") Long id) {

        return memberService.findMember(id);
    }
}
