package com.panda.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.panda.dao.ApprovalDao;
import com.panda.pojo.Approval;
import com.panda.pojo.Purchase;

@Service
@Transactional
public class ApprovalServiceImpl implements ApprovalService{

	@Resource
	ApprovalDao approvalDao;
	
	public List<Approval> qureyByRequestCode(String requestPlanCode) {
		return approvalDao.qureyByRequestCode(requestPlanCode);
	}

	public List<Purchase> getUnapproval() {
		return approvalDao.getUnapproval();
	}
	
	public Purchase queryByPurchasePlanCode(String purchasePlanCode) {
		return approvalDao.queryByPurchasePlanCode(purchasePlanCode);
	}

	public Boolean approvalPass(String purchasePlanCode, int status) {
		return approvalDao.approvalPass(purchasePlanCode, status);
	}
	
	public Boolean approvalBack(String purchasePlanCode) {
		return approvalDao.approvalBack(purchasePlanCode);
	}

	public void approvalSave(Approval approval) {
		approvalDao.approvalSave(approval);
		
	}

	public String getApprovalOpinion(String requestPlanCode) {
		return approvalDao.getApprovalOpinion(requestPlanCode);
	}
}
