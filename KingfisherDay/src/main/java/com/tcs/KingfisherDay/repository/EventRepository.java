package com.tcs.KingfisherDay.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tcs.KingfisherDay.model.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, String> {

	List<Event> findByEventId(String eventId);

}