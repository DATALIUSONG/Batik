package com.panda.service;
import java.util.List;

import org.apache.spark.streaming.scheduler.StreamInputInfo;

import com.panda.pojo.Inventory;
import com.panda.pojo.Purchase;
import com.panda.pojo.Request;

public interface PurchaseService {

	/**
	  *   查询所有信息
	 * @return
	 */
	List<Purchase> queryAll();
	
	/**
	 * 更新采购计划中的需求计划名称
	 * @param planCode
	 * @param planName
	 */
	void updatePlanName(String planCode, String planName);
	
	/**
	  *   查询所有需求表中的数据
	 * @return
	 */
	List<Request> queryUpdate();
	
	/**
	 * @category 通过需求计划编码查询需求计划
	 * @param requestPlanCode
	 * @return
	 */
	Request queryByRequestPlanCode(String requestPlanCode);
	
	/**
	 * @category 需求计划退回
	 * @return
	 */
	Boolean requestBack(String planCode);
	
	/**
	 * @category 存储来自于需求表中的数据
	 * @param purchase
	 */
	void savePurchase(Purchase purchase);
	
	/**
	 * @category 在存储需求表中信息的同时，修改原需求表中的审批状态为已交由采购计划处理
	 * @param requestPlanCode
	 */
	void updateRequestApprovalStatus(String requestPlanCode);
	
	/**
	 * @category 通过需求计划编码生成采购计划
	 * @param requestPlanCode
	 * @return
	 */
	Boolean createPurchasePlan(String requestPlanCode);
	
	/**
	 * @category 通过需求计划编码查询指定采购计划
	 * @param requestPlanCode
	 * @return
	 */
	List<Purchase> getUnwritePurchase();
	
	/**
	 * @category 通过需求计划编码删除采购计划
	 * @param requestPlanCode
	 */
	Boolean purchaseBack(String requestPlanCode);
	
	/**
	 * @category 通过物料编码查询物料库存情况
	 * @param materialCode
	 * @return
	 */
	Inventory queryByMaterialCode(String materialCode);
	
	/**
	 * @category 根据采购计划编码查询采购计划
	 * @param purchasePlanCode
	 * @return
	 */
	Purchase getPrintData(String purchasePlanCode);
	
	/**
	 * @category 通过需求计划代码获得采购计划代码
	 * @param requestPlanCode
	 * @return
	 */
	String getPurchasePlanCode(String requestPlanCode);
	
	/**
     * @category 根据materialTrackingCode来查询
     * @param materialTrackingCode
     * @return
     */
    Request queryById(String materialTrackingCode);
	
    /**
     * @category 删除需求计划记录（更新需求计划中的需求计划状态）
     * @param goods
     */
    void Delete(Request goods);
}
