package com.panda.dao;

import java.util.List;

import com.panda.pojo.Approval;
import com.panda.pojo.Purchase;
import com.panda.pojo.Request;
import com.panda.pojo.RequestApproval;

public interface RequestApprovalDao {

	/**
	 * @category 通过需求计划编码查询所有审批信息
	 * @param requestPlanCode
	 * @return
	 */
	List<Approval> qureyByRequestCode(String requestPlanCode);
	
	/**
	 * @category 查询所有没审批的需求计划
	 * @return
	 */
	List<Request> getUnapproval();
	
	/**
	 * @category 根据采购计划编码来查询采购计划
	 * @param purchasePlanCode
	 * @return
	 */
	Request queryByRequestPlanCode(String requestPlanCode);
	
	/**
	 * @category 根据采购计划编码更新采购计划审批状态
	 * @param purchasePlanCode
	 * @return
	 */
	Boolean approvalPass(String purchasePlanCode,int status);
	
	/**
	 * @category 插入一个新的审批对象
	 * @param approval
	 */
	void approvalSave(RequestApproval approval);
	
	/**
	 * @category 根据采购计划编码更新采购计划审批状态
	 * @param purchasePlanCode
	 * @return
	 */
	Boolean approvalBack(String purchasePlanCode);
	
	/**
	 * @category 根据需求计划编码获取审批说明
	 * @param requestPlanCode
	 * @return
	 */
	String getApprovalOpinion(String requestPlanCode);
}
