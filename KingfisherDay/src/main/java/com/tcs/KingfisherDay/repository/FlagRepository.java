package com.tcs.KingfisherDay.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tcs.KingfisherDay.model.Flag;

@Repository
public interface FlagRepository extends JpaRepository<Flag, String> {
	List<Flag> findByKey(String key);
}
