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
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.panda.pojo.Purchase;
import com.panda.pojo.Request;
import com.panda.service.PurchaseService;

@Controller
public class PurchaseController {
	
	 /**
     * @category 依赖注入 DI IOC
     */
    @Resource
    PurchaseService purchaseService;// @Resource注解 等价于 userService = new UserServiceImpl();
    
    /**
     * @category 将来自于需求表中的数据做补充后保存到采购计划表中
     * @param parameter
     * @param request
     * @param response
     * @return
     */
    @SuppressWarnings("unused")
	@RequestMapping("/purchase/savePurchase")
    public @ResponseBody String savePurchase(
    		@RequestParam Map<String, String> parameter, HttpServletRequest request, HttpServletResponse response) {
    	Map<String, String> result = new HashMap<String, String>();

		System.out.println("正在接收到前端传来的数据："+JSON.toJSONString(parameter));
    	String classificationCode = parameter.get("classificationCode");
		String classificationName = parameter.get("classificationName");
		String materialCode = parameter.get("materialCode");
		String materialName = parameter.get("materialName");
		String specification = parameter.get("specification");
		String type = parameter.get("type");
		String unit = parameter.get("unit");
		String quantity = parameter.get("quantity");
		String requestMonth = parameter.get("requestMonth");
		String requestDate = parameter.get("requestDate");
		String sureSource = parameter.get("sureSource");
		String wishSupplier = parameter.get("wishSupplier");
		String fixedSupplier = parameter.get("fixedSupplier");
		String requestDeportment = parameter.get("requestDeportment");
    	String requestPlanCode = parameter.get("requestPlanCode");
    	String supplyQuantity = parameter.get("supplyQuantity");
    	String supplyWay = parameter.get("supplyWay");    	
    	String availableStock = parameter.get("availableStock");
    	String quantityRoutes = parameter.get("quantityRoutes");
    	String usedStock = parameter.get("usedStock");
    	String totalStock = parameter.get("totalStock");
    	String purchaseLeadTime = parameter.get("purchaseLeadTime");
    	String purchaseDate = parameter.get("purchaseDate");
    	String materialTrackingCode = parameter.get("materialTrackingCode");
    	String originator = parameter.get("originator");
    	
    	Purchase purchase = new Purchase();
    	
    	purchase.setClassificationCode(classificationCode);
    	purchase.setClassificationName(classificationName);
    	purchase.setMaterialCode(materialCode);
    	purchase.setMaterialName(materialName);
    	purchase.setSpecification(specification);
    	purchase.setType(type);
    	purchase.setUnit(unit);
    	purchase.setQuantity(Double.parseDouble(quantity));
    	purchase.setRequestMonth(requestMonth);
    	purchase.setRequestDate(requestDate);
    	purchase.setSureSource(sureSource);
    	purchase.setWishSupplier(wishSupplier);
    	purchase.setFixedSupplier(fixedSupplier);
    	purchase.setRequestDeportment(requestDeportment);
    	purchase.setRequestPlanCode(requestPlanCode);
    	purchase.setSupplyWay(supplyWay);
    	purchase.setSupplyQuantity(Double.parseDouble(supplyQuantity));
    	purchase.setAvailableStock(Double.parseDouble(availableStock));
    	purchase.setQuantityRoutes(Double.parseDouble(quantityRoutes));
    	purchase.setUsedStock(Double.parseDouble(usedStock));
    	purchase.setTotalStock(Double.parseDouble(usedStock));
    	purchase.setPurchaseLeadTime(purchaseLeadTime);
    	purchase.setMaterialTrackingCode(materialTrackingCode);
    	purchase.setOriginator(originator);
    	purchase.setApprovalStatus("6");
    	
    	Date date = new Date();
    	SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
    	
    	purchase.setMakingTime(dateFormat.format(date));
    	Request requestTable = purchaseService.queryByRequestPlanCode(requestPlanCode);
    	purchase.setPurchasePlanType(requestTable.getRequestPlanType());
    	
    	purchaseService.savePurchase(purchase);
    	purchaseService.updateRequestApprovalStatus(requestPlanCode);
    	result.put("message","存储成功");
    	result.put("status", "1");
    	return JSON.toJSONString(result);
    }
    
    /**
     * @category 生成采购计划并将审批状态修改为（采购计划）已提交
     * @param parameter
     * @param request
     * @param response
     * @return
     */
   	@RequestMapping("/purchase/createPurchasePlan")
    public @ResponseBody String createPurchasePlan(
       		@RequestParam Map<String, String> parameter, HttpServletRequest request, HttpServletResponse response) {
       	Map<String, String> result = new HashMap<String, String>();
       	String requestPlanCode = parameter.get("requestPlanCode");
       	boolean res = purchaseService.createPurchasePlan(requestPlanCode);
       	if (res == false) {
			result.put("message", "生成失败");
			result.put("status", "0");
			return JSON.toJSONString(result);
		}
       	result.put("message", "生成成功");
		result.put("status", "1");
		return JSON.toJSONString(result);
    }
    
    
    @SuppressWarnings("unused")
   	@RequestMapping("/purchase/getUnwritePurchase")
    public @ResponseBody String getUnwritePurchase(
       		@RequestParam Map<String, String> parameter, HttpServletRequest request, HttpServletResponse response) {
    	Map<String, Object> result = new HashMap<String, Object>();
       	String requestPlanCode = parameter.get("requestPlanCode");
       	List<Purchase> purchases = purchaseService.getUnwritePurchase();
       	if (purchases == null) {
       		result.put("message", "查询失败");
        	result.put("status", "0");
        	return JSON.toJSONString(result);
		}
       	result.put("message", "查询成功");
    	result.put("status", "1");
    	result.put("data", purchases);
    	return JSON.toJSONString(result);
    }
    
	@RequestMapping("/purchase/purchaseBack")
    public @ResponseBody String purchaseBack(
    		@RequestParam Map<String, String> parameter, HttpServletRequest request, HttpServletResponse response) {
    	System.out.println("正在接收前端的请求...");
		String requestPlanCode = parameter.get("requestPlanCode");
    	boolean condition = purchaseService.purchaseBack(requestPlanCode);
    	Map<String, String> result = new HashMap<String, String>();
    	if (condition == false) {
    		result.put("message", "退回失败");
        	result.put("status", "0");
        	return JSON.toJSONString(result);
		}
    	result.put("message", "退回成功");
    	result.put("status", "1");
    	return JSON.toJSONString(result);
    	
    }
    
    /**
     *  @category 查询所有数据
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/purchase/queryAll")
    public@ResponseBody String queryAll(HttpServletRequest request, HttpServletResponse response) {
    	response.setHeader("Access-Control-Allow-Origin", "*");// 解决跨域问题
    	System.out.print("qyeryAll方法已收到前端传来的请求，正在查询......");
    	List<Purchase> list = purchaseService.queryAll();
    	Map<String, Object> result = new HashMap<String, Object>();
		if (list == null || list.size() == 0) {
			result.put("message", "目前还没有任何数据，请先添加数据");
			result.put("status", "0");
			return JSON.toJSONString(result);
		}

		result.put("message", "查询成功");
		result.put("status", "1");
		result.put("data", list);
		return JSON.toJSONString(result);
    }
    	
    /**
     * @category 通过需求计划编码修改采购计划名称
     * @param parameter
     * @param request
     * @param response
     * @param session
     * @return
     */
    @RequestMapping("/purchase/updatePlanName")
    public@ResponseBody String updatePlanName(
    		@RequestParam Map<String, String> parameter, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
    	String planName = parameter.get("purchasePlanName");
    	String planCode = parameter.get("requestPlanCode");
    	
    	//查询目标对象
    	//Purchase purchase = purchaseService.findPurchaseById(planCode);
    	//重新赋值
    	//purchase.setPurchasePlanType(planName);
    	
    	//更新
    	//purchaseService.updatePlanName(purchase)
    	
    	System.out.print("已接收到前端传来的数据" + planCode + planName);
		purchaseService.updatePlanName(planCode, planName);
		Map<String, String> result = new HashMap<String, String>();
		result.put("message", "更新成功");
		result.put("status", "1");
		return JSON.toJSONString(result);
    }
    
    /**
     * @category 从需求表中读出数据
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/purchase/queryUpdate")
    public@ResponseBody String queryUpdate(HttpServletRequest request, HttpServletResponse response) {
    	response.setHeader("Access-Control-Allow-Origin", "*");// 解决跨域问题
    	System.out.print("queryUpdate已收到前端传来的请求，正在查询......");
    	List<Request> list = purchaseService.queryUpdate();
    	Map<String, Object> result = new HashMap<String, Object>();
    	if (list == null || list.size() == 0) {
    		System.out.println("暂无数据或查询失败");
			result.put("message", "暂无数据或查询失败");
			result.put("status", "2");
			return JSON.toJSONString(result);
		}
    	List<Request> requests = new ArrayList<Request>();
    	for (Request request2 : list) {
			int requestStatus = Integer.parseInt(request2.getApprovalStatus());
			if (requestStatus != 4) {
				continue;
			} else {
				System.out.println(requestStatus);
				requests.add(request2);
			}
		}   	
    	
		if (requests == null || requests.size() == 0) {
			System.out.println("数据仍在审批！");
			result.put("message", "数据仍在审批！");
			result.put("status", "0");
			return JSON.toJSONString(result);
		}
		System.out.println("查询成功！");
		result.put("message", "查询成功");
		result.put("status", "1");
		result.put("data", requests);
		return JSON.toJSONString(result);
    }
    
    /**
     * @category 需求退回函数
     * @param parameter
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/purchase/requestBack")
    public @ResponseBody String requestBack(
    		@RequestParam Map<String, String> parameter,HttpServletRequest request, HttpServletResponse response) {
    	response.setHeader("Access-Control-Allow-Origin", "*");// 解决跨域问题
    	String planCode = parameter.get("planCode");
    	System.out.println("已接收到前端要求将需求计划退回的请求并已获取到需求计划编码：" + planCode);
		boolean res = purchaseService.requestBack(planCode);
    	Map<String, Object> result = new HashMap<String, Object>();
    	if (res) {
    		result.put("message", "更新成功");
    		result.put("status", "1");
    		return JSON.toJSONString(result);
		}
    	result.put("message", "更新失败");
		result.put("status", "0");
		return JSON.toJSONString(result);
    }
    
    /**
     * @category 通过采购计划编码查询采购计划进行打印
     * @param parameter
     * @param request
     * @param response
     * @return
     */
	@RequestMapping("/purchase/getPrintData")
    public@ResponseBody String getPrintData(
    		@RequestParam Map<String, String> parameter, HttpServletRequest request, HttpServletResponse response) {
		String purchasePlanCode = parameter.get("purchasePlanCode");
		Purchase purchase = purchaseService.getPrintData(purchasePlanCode);
		Map<String, Object> result = new HashMap<String, Object>();
		if (purchase == null) {
			result.put("message", "查询失败");
			result.put("status", "0");
			return JSON.toJSONString(result);
		}
		result.put("message", "查询成功");
		result.put("status", "1");
		result.put("data", purchase);
    	return JSON.toJSONString(result);
	}
	
	/**
	 * @category 通过需求计划编码查询对应的采购计划编码
	 * @param parameter
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/purchase/getPurchasePlanCode")
	public@ResponseBody String getPurchasePlanCode(
			@RequestParam Map<String, String> parameter, HttpServletRequest request, HttpServletResponse response) {
		Map<String, String> result = new HashMap<String, String>();
		String requestPlanCode = parameter.get("requestPlanCode");
		String purchasePlanCode = purchaseService.getPurchasePlanCode(requestPlanCode);
		if (purchasePlanCode == null) {
			String message = "查询以" + requestPlanCode +"作为需求计划编码的采购计划失败，请先保存该计划！";
			result.put("message", message);
			result.put("status", "0");
			return JSON.toJSONString(result);
		}
		result.put("message", "查询成功");
		result.put("status", "1");
		result.put("data", purchasePlanCode);
		return JSON.toJSONString(result);
	}
}
