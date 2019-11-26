package com.panda.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="t_panda_inventory")
public class Inventory {
	
private static final long serialVersionUID = 1L;
	
	@Id
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	@GeneratedValue(generator = "system-uuid")
    @Column
	private String id;//主键
	@Column
	private String materialCode;//物料编码
	@Column
	private String materialName;//物料名称
	@Column
	private String inventory;//库存量
	public String getId() {return id;}
	public void setId(String id) {this.id = id;}
	public String getMaterialCode() {return materialCode;}
	public void setMaterialCode(String materialCode) {this.materialCode = materialCode;}
	public String getMaterialName() {return materialName;}
	public void setMaterialName(String materialName) {this.materialName = materialName;}
	public String getInventory() {return inventory;}
	public void setInventory(String inventory) {this.inventory = inventory;}
	
	
}
