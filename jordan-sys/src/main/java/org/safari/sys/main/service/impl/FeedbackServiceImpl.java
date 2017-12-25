package org.safari.sys.main.service.impl;

import org.safari.sys.main.entity.Feedback;
import org.safari.sys.main.mapper.FeedbackMapper;
import org.safari.sys.main.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FeedbackServiceImpl implements FeedbackService {

	@Autowired
	private FeedbackMapper feedbackMapper;

	@Override
	@Transactional
	public void insert(Feedback feedback) {
		feedbackMapper.insert(feedback);
	}
}
