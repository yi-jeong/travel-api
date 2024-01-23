package com.travel.server.api.travel;

import com.travel.server.api.travel.model.Travel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TravelRepository extends JpaRepository<Travel, Long> {

    Page<Travel> findByMemberId(long member_id, Pageable pageable);

}
