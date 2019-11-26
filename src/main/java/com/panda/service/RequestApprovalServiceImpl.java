package com.panda.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.panda.dao.RequestApprovalDao;
import com.panda.pojo.Approval;
import com.panda.pojo.Purchase;
import com.panda.pojo.Request;
import com.panda.pojo.RequestApproval;

@Service
@Transactional
public class RequestApprovalServiceImpl implements RequestApprovalService{

	@Resource
	RequestApprovalDao requestApprovalDao;
	
	public List<Approval> qureyByRequestCode(String requestPlanCode) {
		return requestApprovalDao.qureyByRequestCode(requestPlanCode);
	}

	public List<Request> getUnapproval() {
		return requestApprovalDao.getUnapproval();
	}
	
	public Request queryByRequestPlanCode(String requestPlanCode) {
		return requestApprovalDao.queryByRequestPlanCode(requestPlanCode);
	}

	public Boolean approvalPass(String requestPlanCode, int status) {
		return requestApprovalDao.approvalPass(requestPlanCode, status);
	}
	
	public Boolean approvalBack(String requestPlanCode) {
		return requestApprovalDao.approvalBack(requestPlanCode);
	}

	public void approvalSave(RequestApproval approval) {
		requestApprovalDao.approvalSave(approval);
		
	}

	public String getApprovalOpinion(String requestPlanCode) {
		return requestApprovalDao.getApprovalOpinion(requestPlanCode);
	}

	

}
