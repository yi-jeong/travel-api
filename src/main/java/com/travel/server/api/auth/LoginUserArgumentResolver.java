package com.travel.server.api.auth;

import com.travel.server.api.member.MemberRepository;
import com.travel.server.api.member.MemberService;
import com.travel.server.api.member.dto.MemberDto;
import com.travel.server.api.member.model.Member;
import com.travel.server.exception.CustomException;
import com.travel.server.exception.model.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {

    private final MemberRepository memberRepository;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(LoginUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) throws Exception {

        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String id = userDetails.getUsername();

        Optional<Member> optionalMember = memberRepository.findByUserId(id);
        Member member = optionalMember.orElse(null);

        if(member == null){
            throw new CustomException(ErrorCode.MEMBER_NOT_TOKEN);
        }

        return MemberDto.from(member);
    }

}
