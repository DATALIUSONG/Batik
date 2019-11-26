package com.panda.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="t_panda_requestconf")
public class RequestSyetemConf implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    @Column
    private String id; //主键
    @Column
    private String requestDepartment;//需求部门
    @Column
    private String unit;//物料单位
    @Column
    private String wishSupplier;//期望供应商
    @Column
    private String fixedSupplier;//固定供应商
    @Column
    private String requestStock;//库存




}
