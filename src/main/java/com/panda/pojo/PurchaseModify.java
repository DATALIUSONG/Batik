package com.panda.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="t_panda_purchase_modify")
public class PurchaseModify implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	@GeneratedValue(generator = "system-uuid")
    @Column
	private String id; //主键
	@Column 
	private String purchasePlanCode;//采购计划编码
	@Column
	private String modifyPerson;//修改人
	@Column
	private String modifyTime;//修改时间
	@Column
	private String modifyReason;//修改原因
	@Column
	private String compilingPerson;//编制人
	@Column
	private String compilingTime;//编制时间
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPurchasePlanCode() {
		return purchasePlanCode;
	}
	public void setPurchasePlanCode(String purchasePlanCode) {
		this.purchasePlanCode = purchasePlanCode;
	}
	public String getModifyPerson() {
		return modifyPerson;
	}
	public void setModifyPerson(String modifyPerson) {
		this.modifyPerson = modifyPerson;
	}
	public String getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}
	public String getModifyReason() {
		return modifyReason;
	}
	public void setModifyReason(String modifyReason) {
		this.modifyReason = modifyReason;
	}
	public String getCompilingPerson() {
		return compilingPerson;
	}
	public void setCompilingPerson(String compilingPerson) {
		this.compilingPerson = compilingPerson;
	}
	public String getCompilingTime() {
		return compilingTime;
	}
	public void setCompilingTime(String compilingTime) {
		this.compilingTime = compilingTime;
	}
}
