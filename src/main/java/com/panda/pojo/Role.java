package com.panda.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="t_panda_role")
public class Role implements Serializable{
	
	private static final long serivalVersionUID = 1L;
	
	@Id
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	@GeneratedValue(generator = "system-uuid")
    @Column
	private String id; //主键	
	@Column
	private String role;//角色
	@Column
	private String authority;//权限
	@Column
	private String desc;//描述
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public static long getSerivalversionuid() {
		return serivalVersionUID;
	}
}
