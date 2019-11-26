package com.panda.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="t_panda_request")
public class Request implements Serializable{
	
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
	private String type;//规格
	@Column
	private String specification;//型号
	@Column
	private String unit;//单位
	@Column
	private String quantity;//数量
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
	private String remarks;//备注
	@Column
	private String requestPlanType;//需求计划类型
	@Id
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	@GeneratedValue(generator = "system-uuid")
	@Column
	private String requestPlanCode;//需求计划编码
	@Id
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	@GeneratedValue(generator = "system-uuid")
	@Column
	private String materialTrackingCode ;//物料追踪码
	@Column
	private String requestPlanName;//需求计划名称
	@Column
	private String requestRemarks; //需求备注
	@Column
	private String requestDeportment;//需求部门
	@Column
	private String requestPerson;//需求人员
	@Column
	private String requestStatus;//需求申请状态
	@Column
	private String approvalStatus;//审批状态
	@Column
	private String updateTime; //修改日期
	@Column
	private String requestplanmonth;  // 需求计划月份
	@Column
	private String modifyReason; //需求计划修改原因
	@Column
	private String createTime;   //创建时间
	@Column
	private String createPerson;   // 创建人
	@Column
	private String modifyPerson;   // 修改人


	public String getModifyReason() {
		return modifyReason;
	}

	public void setModifyReason(String modifyReason) {
		this.modifyReason = modifyReason;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getCreatePerson() {
		return createPerson;
	}

	public void setCreatePerson(String createPerson) {
		this.createPerson = createPerson;
	}

	public String getModifyPerson() {
		return modifyPerson;
	}

	public void setModifyPerson(String modifyPerson) {
		this.modifyPerson = modifyPerson;
	}

	public String getRequestplanmonth() {
		return requestplanmonth;
	}

	public void setRequestplanmonth(String requestplanmonth) {
		this.requestplanmonth = requestplanmonth;
	}


	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
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
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
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
//	public double getRequestStock() {
//		return requestStock;
//	}
//	public void setRequestStock(double requestStock) {
//		this.requestStock = requestStock;
//	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getMaterialTrackingCode() {
		return materialTrackingCode;
	}
	public void setMaterialTrackingCode(String materialTrackingCode) {
		this.materialTrackingCode = materialTrackingCode;
	}
	public String getRequestPlanType() {
		return requestPlanType;
	}
	public String getRequestRemarks() {
		return requestRemarks;
	}

	public void setRequestRemarks(String requestRemarks) {
		this.requestRemarks = requestRemarks;
	}
	public void setRequestPlanType(String requestPlanType) {
		this.requestPlanType = requestPlanType;
	}
	public String getRequestPlanCode() {
		return requestPlanCode;
	}
	public void setRequestPlanCode(String requestPlanCode) {
		this.requestPlanCode = requestPlanCode;
	}
	public String getRequestPlanName() {
		return requestPlanName;
	}
	public void setRequestPlanName(String requestPlanName) {
		this.requestPlanName = requestPlanName;
	}
	public String getRequestDeportment() {
		return requestDeportment;
	}
	public void setRequestDeportment(String requestDeportment) {
		this.requestDeportment = requestDeportment;
	}
	public String getRequestPerson() {
		return requestPerson;
	}
	public void setRequestPerson(String requestPerson) {
		this.requestPerson = requestPerson;
	}
	public String getRequestStatus() {
		return requestStatus;
	}
	public void setRequestStatus(String requestStatus) {
		this.requestStatus = requestStatus;
	}
	public String getApprovalStatus() {
		return approvalStatus;
	}
	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}
}
