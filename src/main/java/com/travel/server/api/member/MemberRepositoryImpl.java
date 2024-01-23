package com.travel.server.api.member;

import com.travel.server.api.member.dto.MemberDto;
import com.travel.server.api.member.model.QMember;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import static com.travel.server.api.member.model.QMember.member;


@Slf4j
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public List<MemberDto> searchAllMember(String userId, String nickName) {
        QMember member = QMember.member;
        log.info("[Test] userId: {}, nickName: {}", eqUserId(userId), eqNickName(nickName));

        return queryFactory
                .select(Projections.bean(MemberDto.class,
                        member.id,
                        member.userId,
                        member.nickName,
                        member.phoneNumber,
                        member.email,
                        member.role,
                        member.createdAt,
                        member.updatedAt,
                        member.deletedAt))
                .from(member)
                .where(
                    eqUserId(userId),
                    eqNickName(nickName)
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
