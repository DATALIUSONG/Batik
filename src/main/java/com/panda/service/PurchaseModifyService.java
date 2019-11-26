package com.panda.service;

import java.util.List;

import com.panda.pojo.PurchaseModify;

public interface PurchaseModifyService {

	List<PurchaseModify> queryByPlanCode(String purchasePlanCode);
}
