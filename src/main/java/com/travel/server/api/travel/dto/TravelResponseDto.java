package com.travel.server.api.travel.dto;

import com.travel.server.api.travel.model.Nation;
import com.travel.server.api.travel.model.Travel;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TravelResponseDto {

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
    private LocalDateTime startAt;

    /**
     * 여행지 마지막일
     */
    private LocalDateTime endAt;

    public TravelResponseDto(Travel travel){
        this.id = travel.getId();
        this.nation = travel.getNation();
        this.title=travel.getTitle();
        this.thumbnail=travel.getThumbnail();
        this.startAt=travel.getStartAt();
        this.endAt=travel.getEndAt();
    }
}
