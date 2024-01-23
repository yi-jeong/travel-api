package com.travel.server.api.travel;

import com.travel.server.api.travel.dto.TravelResponseDto;
import com.travel.server.api.travel.model.Travel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TravelService {

    private final TravelRepository travelRepository;

    public Page<TravelResponseDto> getTravelList(long member_id, Pageable pageable){
        Page<Travel> travelList = travelRepository.findByMemberId(member_id, pageable);

        // TravelResponseDto 변환
        List<TravelResponseDto> resultList =
                travelList.stream()
                        .map(TravelResponseDto::new)
                        .collect(Collectors.toList());

        return new PageImpl<>(resultList, pageable, travelList.getTotalElements());
    }

}
