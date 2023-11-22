package com.mycom.enjoy.index.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycom.enjoy.index.dao.IndexDao;
import com.mycom.enjoy.index.dto.InfoDto;

@Service
public class IndexServiceImpl implements IndexService {
	@Autowired
	IndexDao dao;

	@Override
	public InfoDto infoTotalCount() {
		return dao.infoTotalCount();
	}

	@Override
	public int visitorTotalCount() {
		return dao.visitorTotalCount();
	}
	


}
