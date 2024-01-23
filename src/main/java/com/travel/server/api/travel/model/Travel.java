package com.travel.server.api.travel.model;

import com.travel.server.api.common.model.BaseEntity;
import com.travel.server.api.member.model.Member;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "TRAVEL")
public class Travel extends BaseEntity {

    /**
     * 여행지 일련번호
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * 여행지 국가
     */
    private Nation nation;

    /**
     * 여행지 명
     */
    private String title;

    /**
     * 여행지 썸네일 이미지 경로
     */
    private String thumbnail;

    /**
     * 여행지 시작일
     */
    @Column(name = "start_at")
    private LocalDateTime startAt;

    /**
     * 여행지 마지막일
     */
    @Column(name = "end_at")
    private LocalDateTime endAt;

    /**
     * 작성자 정보
     */
    @ManyToOne
    @JoinColumn(name = "member_id", referencedColumnName = "id")
    private Member member;

}
