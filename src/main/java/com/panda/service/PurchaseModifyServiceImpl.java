package com.panda.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.panda.dao.PurchaseModifyDao;
import com.panda.pojo.PurchaseModify;

@Service
@Transactional
public class PurchaseModifyServiceImpl implements PurchaseModifyService{

	@Resource
	PurchaseModifyDao purchaseModifyDao;
	
	public List<PurchaseModify> queryByPlanCode(String purchasePlanCode) {
		return purchaseModifyDao.queryByPlanCode(purchasePlanCode);
	}

}
