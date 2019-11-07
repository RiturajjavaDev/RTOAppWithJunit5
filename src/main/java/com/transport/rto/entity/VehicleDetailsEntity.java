package com.transport.rto.entity;


import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;
/**
 * this entity is used to set data from  VehicleDetails UI store in that class with help of BeanUtils
 * and send data to VehicleDetailsRepository 
 * 
 * @author Rituraj
 *
 */
@Data
@Entity
@Table(name="vehicle_dtls")
public class VehicleDetailsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator ="vehicle_dtls")
	@SequenceGenerator(name="vehicle_dtls",sequenceName = "VCHL_DTLS_ID_SEQ",allocationSize=1)
	private Integer vhicleDtlsId;
	@Column(name="vehicle_type",length = 15)
	private String vehicleType;
	private Integer mfdYear;
	@Column(name="company_name",length = 30)
	private String companyName;
	@Column(name="color",length=30)
	private String  color;
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date  create_Dt;
	@Temporal(TemporalType.TIMESTAMP)
	@UpdateTimestamp
	private Date update_Dt;
	@OneToOne(cascade = CascadeType.ALL,orphanRemoval=true)
	@JoinColumn(name="vehicle_Owner_Id")
	private VehicleOwnerDtlsEntity dtlsEntity;
	
	
	
}
