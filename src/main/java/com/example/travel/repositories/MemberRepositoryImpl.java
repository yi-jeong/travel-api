package com.example.travel.repositories;

import com.example.travel.dto.MemberDto;
import com.example.travel.models.Member;
import com.example.travel.models.QMember;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import static com.example.travel.models.QMember.member;

@Slf4j
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public List<MemberDto> searchAllMember(MemberDto memberDto) {
        QMember member = QMember.member;
        log.info("[Test] userId: {}, nickName: {}", eqUserId(memberDto.getUserId()), eqNickName(memberDto.getNickName()));

        return queryFactory
                .select(Projections.bean(MemberDto.class,
                        member.serial,
                        member.userId,
                        member.nickName,
                        member.phoneNumber,
                        member.email,
                        member.role,
                        member.createdAt,
                        member.updatedAt,
                        member.del))
                .from(member)
                .where(
                    eqUserId(memberDto.getUserId()),
                    eqNickName(memberDto.getNickName())
                )
                .fetch();
    }


    private BooleanExpression eqUserId(String userId){
        return userId == null ? null : member.userId.contains(userId);
    }

    private BooleanExpression eqNickName(String nickName){
        return nickName == null ? null : member.nickName.eq(nickName);
    }
}
