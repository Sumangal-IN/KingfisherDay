package com.tcs.KingfisherDay.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcs.KingfisherDay.model.Flag;
import com.tcs.KingfisherDay.repository.FlagRepository;

@Service
public class FlagService {

	@Autowired
	FlagRepository flagRepository;

	public List<Flag> getAllFlags() {
		return flagRepository.findAll();
	}

	public Flag updateFlag(String key, boolean value) {
		List<Flag> flags = flagRepository.findByKey(key);
		if (flags.isEmpty())
			return null;
		flags.get(0).setValue(value);
		flagRepository.save(flags.get(0));
		return flags.get(0);
	}

}
