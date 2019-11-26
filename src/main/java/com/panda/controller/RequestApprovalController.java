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

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.panda.pojo.Approval;
import com.panda.pojo.Purchase;
import com.panda.pojo.Request;
import com.panda.pojo.RequestApproval;
import com.panda.service.RequestApprovalService;

@Controller
public class RequestApprovalController{
	
	/**
     * @category 依赖注入 DI IOC
     */
	@Resource
	RequestApprovalService requestApprovalService;
	
	/**
	 * @category 根据权限获取采购计划表中所有未审批的计划
	 * @param parameter
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/request/approval/getUnapproval")
	public @ResponseBody String getApprovalStatus(
			@RequestParam Map<String, String> parameter, HttpServletRequest request, HttpServletResponse response) {
		System.out.println("已经接收到前端请求所有没审批的");
		String role = parameter.get("role");
		int r = Integer.parseInt(role);
		
		Map<String, Object> result = new HashMap<String, Object>();
		List<Request> requestTables = requestApprovalService.getUnapproval();
		
		if (requestTables.isEmpty()) {
			result.put("message", "查询失败");
			result.put("status", "0");
			return JSON.toJSONString(result);
		}
		
		List<Request> list = new ArrayList<Request>();
		for (Request requestTable : requestTables) {
			int approvalStatus = Integer.parseInt(requestTable.getApprovalStatus());
			switch (r) {
			case 2:
				if (approvalStatus == 2) {
					list.add(requestTable);
				}
				break;
			case 4:
				if (approvalStatus == 3) {
					list.add(requestTable);
				}
				break;
			case 5:
				//权限不足，无法查看
				break;
			case 6:
				if (approvalStatus == 2) {
					list.add(requestTable);
				}
				break;
			case 100:
				list.add(requestTable);
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
	 * @category 根据权限获取采购计划表中所有未审批的计划
	 * @param parameter
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/request/approval/getApprovaled")
	public @ResponseBody String getApprovaledStatus(
			@RequestParam Map<String, String> parameter, HttpServletRequest request, HttpServletResponse response) {
		System.out.println("已经接收到前端请求所有没审批的");
		String role = parameter.get("role");
		int r = Integer.parseInt(role);
		
		Map<String, Object> result = new HashMap<String, Object>();
		List<Request> requestTables = requestApprovalService.getUnapproval();
		
		if (requestTables.isEmpty()) {
			result.put("message", "查询失败");
			result.put("status", "0");
			return JSON.toJSONString(result);
		}
		
		List<Request> list = new ArrayList<Request>();
		for (Request requestTable : requestTables) {
			int approvalStatus = Integer.parseInt(requestTable.getApprovalStatus());
			switch (r) {
			case 2:
				if (approvalStatus == 3) {
					list.add(requestTable);
				}
				break;
			case 4:
				if (approvalStatus == 4) {
					list.add(requestTable);
				}
				break;
			case 5:
				//权限不足，无法查看
				break;
			case 6:
				if (approvalStatus == 3) {
					list.add(requestTable);
				}
				break;
			case 100:
				list.add(requestTable);
				break;
			}
		}
		if (list.isEmpty()) {
			result.put("message", "权限不足，无法查看、编辑");
			result.put("status", "2");
			return JSON.toJSONString(result);
		}
		List<String> approvalOpinion = new ArrayList<String>();
		for (Request request2 : list) {
			String opinion = requestApprovalService.getApprovalOpinion(request2.getRequestPlanCode());
			approvalOpinion.add(opinion);
		}
		result.put("message", "查询成功");
		result.put("status", "1");
		result.put("data", list);
		result.put("opinion", approvalOpinion);
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
	@RequestMapping("/request/approval/approvalPass")
	public @ResponseBody String approvalPass(
			@RequestParam Map<String, String> parameter, HttpServletRequest request, HttpServletResponse response) {
		String cName = parameter.get("cName");
		String requestPlanCode = parameter.get("requestPlanCode");
		String approvalResult = parameter.get("approvalResult");
		String approvalExplain = parameter.get("approvalOpinion");
		System.out.println(cName + "  " + requestPlanCode +"  " + approvalResult + "  " + approvalExplain);
		
		Date date = new Date();
    	SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    	String approvalTime = dateFormat.format(date);
    	Request requestTable = requestApprovalService.queryByRequestPlanCode(requestPlanCode);
    	
    	int status = Integer.parseInt(requestTable.getApprovalStatus());
    	
    	RequestApproval approval = new RequestApproval();
		
    	approval.setRequestPlanCode(requestTable.getRequestPlanCode());
		approval.setApprovalPerson(cName);
		approval.setApprovalTime(approvalTime);
		approval.setApprovalResult(approvalResult);
		approval.setApprovalExplain(approvalExplain);
		requestApprovalService.approvalSave(approval);
		Boolean res = requestApprovalService.approvalPass(requestPlanCode, status);
		
		Map<String, String> result = new HashMap<String,String>();
		result.put("message","数据更新成功");
		result.put("status", "1");
		return JSON.toJSONString(result);
	}
	
	
}