package com.transport.rto.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;
/**
 * this entity is used to set data from  VehicleOwnerDtls UI store in that class with help of BeanUtils
 * and send data to VehicleOwnerDtlsRepository 
 * 
 * @author Rituraj
 *
 */

@Data
@Entity
@Table(name="vhcl_Owner_Dtls")
public class VehicleOwnerDtlsEntity {

	@Id
	@Column(name="vehicle_Owner_Id",updatable = false,nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ownerId_generator")
	@SequenceGenerator(name="ownerId_generator", sequenceName = "Vchl_owner_Id_Seq",allocationSize = 1)
	private Integer vhclOwnerid;
	@Column(name="first_name" ,length = 30)
	private String fname;
	@Column(name="last_name",length = 30)
	private String lname;
	@Temporal(TemporalType.DATE)
	@Column(name="DOB")
	private Date dob;
	@Column(name="Gender",length = 10)
	private String gender;
	@Column(name="Email",length = 40)
	private String email;
	private Long phno;
	@Column(name="SSN_ID",length = 11)
	private String zzn;
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	@Column(name="Create_DT")
	private Date  createDate;
	@Temporal(TemporalType.TIMESTAMP)
	@UpdateTimestamp
	@Column(name="Update_DT")
	private Date updateDate;
}
