package com.panda.controller;

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
import com.panda.pojo.PurchaseModify;
import com.panda.service.PurchaseModifyService;

@Controller
public class PurchaseModifyController {
	
	 /**
     * @category 依赖注入 DI IOC
     */
	@Resource
	PurchaseModifyService purchaseModifyService;
	
	/**
	 * @category 通过采购计划编码获取采购计划修改信息
	 * @param parameter
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/purchase/getModifyPurchase")
	public @ResponseBody String getModifyPurchase(
			@RequestParam Map<String, String> parameter,HttpServletRequest request, HttpServletResponse response) {
		System.out.println("已经进入获取修改表数据的后台控制层！！");
		String purchasePlanCode = parameter.get("purchasePlanCode");
		System.out.println("已经拿到了前台传过来的采购计划编码：" + purchasePlanCode + "正在查询...");
		List<PurchaseModify> list = purchaseModifyService.queryByPlanCode(purchasePlanCode);
		Map<String, Object> result = new HashMap<String, Object>();
		if (list == null || list.size() == 0) {
			result.put("message", "目前还没有对该采购计划做任何修改！！");
			result.put("status", "0");
			return JSON.toJSONString(result);
		}
		result.put("message", "查询成功");
		result.put("status", "1");
		result.put("data", list);
		return JSON.toJSONString(result);
	}
}
