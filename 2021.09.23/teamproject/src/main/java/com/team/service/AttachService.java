package com.team.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team.mapper.AttachMapper;

@Service
public class AttachService {
	@Autowired
	private AttachMapper attachMapper;
}
