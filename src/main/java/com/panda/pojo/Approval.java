package com.panda.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="t_panda_approval_purchase")
public class Approval implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	@GeneratedValue(generator = "system-uuid")
    @Column(name="id")
	private String id;//主键
	@Column(name="requestPlanCode")
	private String requestPlanCode;//需求计划编码
	@Column(name="approvalPerson")
	private String approvalPerson;//审批人
	@Column(name="approvalTime")
	private String approvalTime;//审批时间
	@Column(name="approvalOpinion")
	private String approvalOpinion;//审批意见
	@Column(name="approvalExplain")
	private String approvalExplain;//说明
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRequestPlanCode() {
		return requestPlanCode;
	}
	public void setRequestPlanCode(String requestPlanCode) {
		this.requestPlanCode = requestPlanCode;
	}
	public String getApprovalPerson() {
		return approvalPerson;
	}
	public void setApprovalPerson(String approvalPerson) {
		this.approvalPerson = approvalPerson;
	}
	public String getApprovalTime() {
		return approvalTime;
	}
	public void setApprovalTime(String approvalTime) {
		this.approvalTime = approvalTime;
	}
	public String getApprovalOpinion() {
		return approvalOpinion;
	}
	public void setApprovalOpinion(String approvalOpinion) {
		this.approvalOpinion = approvalOpinion;
	}
	public String getApprovalExplain() {
		return approvalExplain;
	}
	public void setApprovalExplain(String approvalExplain) {
		this.approvalExplain = approvalExplain;
	}
}
