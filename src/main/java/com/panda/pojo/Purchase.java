package com.panda.pojo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * 采购表的实体类
 * @author DELL
 *
 */
@Entity
@Table(name="t_panda_purchase")
public class Purchase implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	@GeneratedValue(generator = "system-uuid")
    @Column
	private String id; //主键
	@Column
	private String classificationCode;//物料分类编码
	@Column
	private String classificationName;//物料分类名称
	@Column
	private String materialCode;//物料编码
	@Column
	private String materialName;//物料名称
	@Column
	private String specification;//型号
	@Column
	private String type;//规格
	@Column
	private String unit;//单位
	@Column
	private double quantity;//数量
	@Column
	private String requestMonth;//需求月份
	@Column
	private String requestDate;//需求日期
	@Column
	private String sureSource;//货源是否确定
	@Column
	private String wishSupplier;//期望供应商
	@Column
	private String fixedSupplier;//固定供应商
	@Column
	private String requestDeportment;//需求部门
	@Column
	private String requestPlanCode;//需求计划编码
	@Column
	private String supplyWay;//供应方式
	@Column
	private double supplyQuantity;//供应数量
	@Column
	private double availableStock;//可用库存
	@Column
	private double quantityRoutes;//在途数量
	@Column
	private double usedStock;//已用库存
	@Column
	private double totalStock;//库存总量
	@Column
	private String purchaseLeadTime;//采购提前期
	@Column
	private String purchaseDate;//采购日期
	@Column
	private String materialTrackingCode;//物料追踪码
	
	@Id
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	@GeneratedValue(generator = "system-uuid")
	@Column
	private String purchasePlanCode;//采购计划编码
	@Column
	private String purchasePlanName;//采购计划名称
	@Column 
	private String approvalStatus;//审批状态
	@Column
	private String purchasePlanType;//需求计划类型
	@Column
	private String originator;//采购计划制单人
	@Column
	private String makingTime;//采购计划制单时间
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getClassificationCode() {
		return classificationCode;
	}
	public void setClassificationCode(String classificationCode) {
		this.classificationCode = classificationCode;
	}
	public String getClassificationName() {
		return classificationName;
	}
	public void setClassificationName(String classificationName) {
		this.classificationName = classificationName;
	}
	public String getMaterialCode() {
		return materialCode;
	}
	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}
	public String getMaterialName() {
		return materialName;
	}
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}
	public String getSpecification() {
		return specification;
	}
	public void setSpecification(String specification) {
		this.specification = specification;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public double getQuantity() {
		return quantity;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	public String getRequestMonth() {
		return requestMonth;
	}
	public void setRequestMonth(String requestMonth) {
		this.requestMonth = requestMonth;
	}
	public String getRequestDate() {
		return requestDate;
	}
	public void setRequestDate(String requestDate) {
		this.requestDate = requestDate;
	}
	public String getSureSource() {
		return sureSource;
	}
	public void setSureSource(String sureSource) {
		this.sureSource = sureSource;
	}
	public String getWishSupplier() {
		return wishSupplier;
	}
	public void setWishSupplier(String wishSupplier) {
		this.wishSupplier = wishSupplier;
	}
	public String getFixedSupplier() {
		return fixedSupplier;
	}
	public void setFixedSupplier(String fixedSupplier) {
		this.fixedSupplier = fixedSupplier;
	}
	public String getRequestDeportment() {
		return requestDeportment;
	}
	public void setRequestDeportment(String requestDeportment) {
		this.requestDeportment = requestDeportment;
	}
	public String getRequestPlanCode() {
		return requestPlanCode;
	}
	public void setRequestPlanCode(String requestPlanCode) {
		this.requestPlanCode = requestPlanCode;
	}
	public String getSupplyWay() {
		return supplyWay;
	}
	public void setSupplyWay(String supplyWay) {
		this.supplyWay = supplyWay;
	}
	public double getSupplyQuantity() {
		return supplyQuantity;
	}
	public void setSupplyQuantity(double supplyQuantity) {
		this.supplyQuantity = supplyQuantity;
	}
	public double getAvailableStock() {
		return availableStock;
	}
	public void setAvailableStock(double availableStock) {
		this.availableStock = availableStock;
	}
	public double getQuantityRoutes() {
		return quantityRoutes;
	}
	public void setQuantityRoutes(double quantityRoutes) {
		this.quantityRoutes = quantityRoutes;
	}
	public double getUsedStock() {
		return usedStock;
	}
	public void setUsedStock(double usedStock) {
		this.usedStock = usedStock;
	}
	public double getTotalStock() {
		return totalStock;
	}
	public void setTotalStock(double totalStock) {
		this.totalStock = totalStock;
	}
	public String getPurchaseLeadTime() {
		return purchaseLeadTime;
	}
	public void setPurchaseLeadTime(String purchaseLeadTime) {
		this.purchaseLeadTime = purchaseLeadTime;
	}
	public String getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(String purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	public String getMaterialTrackingCode() {
		return materialTrackingCode;
	}
	public void setMaterialTrackingCode(String materialTrackingCode) {
		this.materialTrackingCode = materialTrackingCode;
	}
	public String getPurchasePlanCode() {
		return purchasePlanCode;
	}
	public void setPurchasePlanCode(String purchasePlanCode) {
		this.purchasePlanCode = purchasePlanCode;
	}
	public String getPurchasePlanName() {
		return purchasePlanName;
	}
	public void setPurchasePlanName(String purchasePlanName) {
		this.purchasePlanName = purchasePlanName;
	}
	public String getApprovalStatus() {
		return approvalStatus;
	}
	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}
	public String getPurchasePlanType() {
		return purchasePlanType;
	}
	public void setPurchasePlanType(String purchasePlanType) {
		this.purchasePlanType = purchasePlanType;
	}
	public String getOriginator() {
		return originator;
	}
	public void setOriginator(String originator) {
		this.originator = originator;
	}
	public String getMakingTime() {
		return makingTime;
	}
	public void setMakingTime(String makingTime) {
		this.makingTime = makingTime;
	}
}
