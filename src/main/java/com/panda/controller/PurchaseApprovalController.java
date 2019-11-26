package com.panda.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.panda.dao.ApprovalDao;
import com.panda.pojo.Approval;
import com.panda.pojo.Purchase;
import com.panda.service.ApprovalService;
import com.panda.service.PurchaseService;

@Controller
public class PurchaseApprovalController {
	
	/**
     * @category 依赖注入 DI IOC
     */
	@Resource
	ApprovalService approvalService;
	
	/**
     * @category 依赖注入 DI IOC
     */
	@Resource
	PurchaseService purchaseService;
	
	/**
	 * @category 根据需求计划编码查询审批表信息
	 * @param parameter
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/purchase/getApproval")
	public @ResponseBody String getModifyPurchase(
			@RequestParam Map<String, String> parameter,HttpServletRequest request, HttpServletResponse response) {
		String requestPlanCode = parameter.get("planCode");
		System.out.println("已经拿到了前台传过来的需求计划编码：" + requestPlanCode + "正在查询...");
		List<Approval> list = approvalService.qureyByRequestCode(requestPlanCode);
		Map<String, Object> result = new HashMap<String, Object>();
		if (list == null || list.size() == 0) {
			result.put("message", "该计划仍处于审批流程中，请过段时间再来查询！");
			result.put("status", "0");
			return JSON.toJSONString(result);
		}
		result.put("message", "查询成功");
		result.put("status", "1");
		result.put("data", list);
		return JSON.toJSONString(result);
	}
	
	/**
	 * @category 根据权限获取采购计划表中所有未审批的计划
	 * @param parameter
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/approval/getUnapproval")
	public @ResponseBody String getApprovalStatus(
			@RequestParam Map<String, String> parameter, HttpServletRequest request, HttpServletResponse response) {
		System.out.println("已经接收到前端请求所有没审批的");
		String role = parameter.get("role");
		int r = Integer.parseInt(role);
		
		Map<String, Object> result = new HashMap<String, Object>();
		List<Purchase> purchases = approvalService.getUnapproval();
		
		if (purchases.isEmpty()) {
			result.put("message", "查询失败");
			result.put("status", "0");
			return JSON.toJSONString(result);
		}
		
		List<Purchase> list = new ArrayList<Purchase>();
		for (Purchase purchase : purchases) {
			int approvalStatus = Integer.parseInt(purchase.getApprovalStatus());
			switch (r) {
			case 0:
				//权限不足，无法查看
				break;
			case 1:
				//权限不足，无法查看
				break;
			case 2:
				//权限不足，无法查看
				break;
			case 3:
				//权限不足，无法查看
				break;
			case 4:
				//权限不足，无法查看
				break;
			case 5:
				//权限不足，无法查看
				break;
			case 6:
				//权限不足，无法查看
				break;
			case 7:
				//权限不足，无法查看
				break;
			case 8:
				if (approvalStatus == 7) {
					list.add(purchase);
				}
				break;
			case 9:
				//权限不足，无法查看、编辑
				break;
			case 10:
				//权限不足，无法查看
				break;
			case 11:
				//暂无该角色
				break;
			case 100:
				list.add(purchase);
				break;
			}
		}
		if (list.isEmpty()) {
			result.put("message", "权限不足，无法查看、编辑");
			result.put("status", "2");
			return JSON.toJSONString(result);
		}
		result.put("message", "查询成功");
		result.put("status", "1");
		result.put("data", list);
		return JSON.toJSONString(result);
	}
	
	
	/**
	 * @category 审批通过按钮点击事件
	 * @param parameter
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unused")
	@RequestMapping("/approval/approvalPass")
	public @ResponseBody String approvalPass(
			@RequestParam Map<String, String> parameter, HttpServletRequest request, HttpServletResponse response) {
		String cName = parameter.get("cName");
		String purchasePlanCode = parameter.get("purchasePlanCode");
		String approvalOpinion = parameter.get("approvalOpinion");
		String approvalExplain = parameter.get("explain");
		System.out.println(cName + "  " + purchasePlanCode +"  " + approvalOpinion + "  " + approvalExplain);
		
		Date date = new Date();
    	SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    	String approvalTime = dateFormat.format(date);
    	Purchase purchase = approvalService.queryByPurchasePlanCode(purchasePlanCode);
    	
    	int status = Integer.parseInt(purchase.getApprovalStatus());
    	
    	Approval approval = new Approval();
		
    	approval.setRequestPlanCode(purchase.getRequestPlanCode());
		approval.setApprovalPerson(cName);
		approval.setApprovalTime(approvalTime);
		approval.setApprovalOpinion(approvalOpinion);
		approval.setApprovalExplain(approvalExplain);
		approvalService.approvalSave(approval);
		Boolean res = approvalService.approvalPass(purchasePlanCode, status);
		
		Map<String, String> result = new HashMap<String,String>();
		result.put("message","数据更新成功");
		result.put("status", "1");
		return JSON.toJSONString(result);
	}
	
	
	/**
	 * @category 审批退回按钮点击事件
	 * @param parameter
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unused")
	@RequestMapping("/approval/approvalBack")
	public @ResponseBody String approvalBack(
			@RequestParam Map<String, String> parameter, HttpServletRequest request, HttpServletResponse response) {
		String cName = parameter.get("cName");
		String purchasePlanCode = parameter.get("purchasePlanCode");
		String approvalOpinion = parameter.get("approvalOpinion");
		String approvalExplain = parameter.get("explain");
		
		Date date = new Date();
    	SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    	String approvalTime = dateFormat.format(date);
    	Purchase purchase = approvalService.queryByPurchasePlanCode(purchasePlanCode);
    	
    	Approval approval = new Approval();
		approval.setRequestPlanCode(purchase.getRequestPlanCode());
		approval.setApprovalPerson(cName);
		approval.setApprovalTime(approvalTime);
		approval.setApprovalOpinion(approvalOpinion);
		approval.setApprovalExplain(approvalExplain);
		approvalService.approvalSave(approval);
		Boolean res = approvalService.approvalBack(purchasePlanCode);
		
		Map<String, String> result = new HashMap<String,String>();
		result.put("message","数据更新成功");
		result.put("status", "1");
		return JSON.toJSONString(result);
	}
	
	
	/**
	 * @category 根据权限获取采购计划表中所有未审批的计划
	 * @param parameter
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/approval/getApprovaled")
	public @ResponseBody String getApprovaled(
			@RequestParam Map<String, String> parameter, HttpServletRequest request, HttpServletResponse response) {
		System.out.println("已经接收到前端请求所有没审批的");
		String role = parameter.get("role");
		int r = Integer.parseInt(role);
		
		Map<String, Object> result = new HashMap<String, Object>();
		List<Purchase> purchases = approvalService.getUnapproval();
		
		if (purchases.isEmpty()) {
			result.put("message", "查询失败");
			result.put("status", "0");
			return JSON.toJSONString(result);
		}
		
		List<Purchase> list = new ArrayList<Purchase>();
		for (Purchase purchase : purchases) {
			int approvalStatus = Integer.parseInt(purchase.getApprovalStatus());
			switch (r) {
			case 0:
				//权限不足，无法查看
				break;
			case 1:
				//权限不足，无法查看
				break;
			case 2:
				//权限不足，无法查看
				break;
			case 3:
				//权限不足，无法查看
				break;
			case 4:
				//权限不足，无法查看
				break;
			case 5:
				//权限不足，无法查看
				break;
			case 6:
				//权限不足，无法查看
				break;
			case 7:
				//权限不足，无法查看
				break;
			case 8:
				if (approvalStatus == 8) {
					list.add(purchase);
				}
				break;
			case 9:
				if (approvalStatus == 9) {
					list.add(purchase);
				}
				break;
			case 10:
				//权限不足，无法查看
				break;
			case 11:
				//暂无该角色
				break;
			case 100:
				list.add(purchase);
				break;
			}
		}
		
		if (list.isEmpty()) {
			result.put("message", "权限不足，无法查看、编辑");
			result.put("status", "2");
			return JSON.toJSONString(result);
		}
		List<String> approvalOpinion = new ArrayList<String>();
		for (Purchase purchase : list) {
			String opinion = approvalService.getApprovalOpinion(purchase.getRequestPlanCode());
			approvalOpinion.add(opinion);
		}
		result.put("message", "查询成功");
		result.put("status", "1");
		result.put("data", list);
		result.put("opinion", approvalOpinion);
		return JSON.toJSONString(result);
	}
}