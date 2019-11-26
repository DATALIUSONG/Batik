package com.panda.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.panda.dao.PurchaseDao;
import com.panda.pojo.Inventory;
import com.panda.pojo.Purchase;
import com.panda.pojo.Request;

@Service
@Transactional
public class PurchaseServiceImpl implements PurchaseService{

	@Resource
	PurchaseDao purchaseDao;

	public List<Purchase> queryAll() {
		return purchaseDao.queryAll();
	}

	public void updatePlanName(String planCode, String planName) {
		purchaseDao.updatePlanName(planCode, planName);
	}
	
	public Request queryByRequestPlanCode(String requestPlanCode) {
		return purchaseDao.queryByRequestPlanCode(requestPlanCode);
	}

	public List<Request> queryUpdate() {
		return purchaseDao.queryUpdate();
	}

	public Boolean requestBack(String planCode) {
		return purchaseDao.requestBack(planCode);
	}

	public void savePurchase(Purchase purchase) {
		purchaseDao.savePurchase(purchase);
	}
	
	public void updateRequestApprovalStatus(String requestPlanCode) {
		purchaseDao.updateRequestApprovalStatus(requestPlanCode);
		
	}
	
	public Boolean createPurchasePlan(String requestPlanCode) {
		return purchaseDao.createPurchasePlan(requestPlanCode);
	}
	
	public List<Purchase> getUnwritePurchase() {
		return purchaseDao.getUnwritePurchase();
	}

	public Boolean purchaseBack(String requestPlanCode) {
	    return purchaseDao.purchaseBack(requestPlanCode);
	}

	public Inventory queryByMaterialCode(String materialCode) {
		return purchaseDao.queryByMaterialCode(materialCode);
	}

	public Purchase getPrintData(String purchasePlanCode) {
		return purchaseDao.getPrintData(purchasePlanCode);
	}

	public String getPurchasePlanCode(String requestPlanCode) {
		
		return null;
	}
	

    /**
     * @category 删除需求计划记录（更新需求计划中的需求计划状态）
     * @param goods
     */
    public void Delete(Request goods) {
    	purchaseDao.Delete(goods);
    }

    public Request queryById(String materialTrackingCode) {
        return purchaseDao.queryById(materialTrackingCode);
    }
}
