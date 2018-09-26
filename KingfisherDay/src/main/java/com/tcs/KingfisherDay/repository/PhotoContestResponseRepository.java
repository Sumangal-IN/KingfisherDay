package com.tcs.KingfisherDay.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tcs.KingfisherDay.model.PhotoContestResponse;

@Repository
public interface PhotoContestResponseRepository extends JpaRepository<PhotoContestResponse, String> {

	List<PhotoContestResponse> findByImageId(String imageId);
}
