package com.panda.dao;

import java.util.List;

import com.panda.pojo.PurchaseModify;

public interface PurchaseModifyDao {

	List<PurchaseModify> queryByPlanCode(String purchasePlanCode);
}
