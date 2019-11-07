package com.transport.rto.constants;

/**
 * this class is used to store all String final variables
 * 
 * @author Rituraj
 *
 */
public interface AppConstant {
	/**
	 * this used to return logical view name for vehicleOwnerDetails
	 */
	public static final String LOGICAL_OWNER_DTLS_VIEW = "vehicleOwnerDetails";

	/**
	 * this is used to return logical view name for vehicleDetails
	 */
	public static final String LOGICAL_VEHICLE_DTLS_VIEW = "vehicleDetails";

	/**
	 * this is used to return logical view name for vehicleOwnerAdress
	 */
	public static final String LOGICAL_OWNER_ADDR_VIEW = "vehicleOwnerAdress";

	/**
	 * this is used to return logical view name for vehicleSummary
	 */
	public static final String LOGICAL_VHCL_SUMMARY_VIEW = "vehicleSummary";

	/**
	 * this is used to return logical view name for vehicleSummary
	 */
	public static final String LOGICAL_VHCL_REGIS_VIEW = "RegistrationDetails";

	/**
	 * this is used to store owner data for send from controller to UI
	 */
	public static final String MODEL_ATTR_KEY_OWNER_DTLS = "vehicleOwnerDtls";

	/**
	 * this is used to store vehicle data for send from controller to UI
	 */
	public static final String MODEL_ATTR_KEY_VEHICLE_DTLS = "vehicleDtls";

	/**
	 * this is used to store owneraddr data for send from controller to UI
	 */
	public static final String MODEL_ATTR_KEY_OWNER_ADDR_DTLS = "ownerAddr";

	/**
	 * this is used to store vehicle registration summary data for send from
	 * controller to UI
	 */
	public static final String MODEL_ATTR_KEY_VCHL_REG_SUMMARY = "vchlReg";

	/**
	 * sending primary key of vehicleOwner from controller to UI for making request
	 * as state full
	 */
	public static final String MODEL_ATTR_KEY_FOR_OWNER_PK = "vehicleOwnerKey";

	/**
	 * sending primary key of vehicle owner address from controller to UI for making
	 * request as state full
	 */
	public static final String MODEL_ATTR_KEY_FOR_ADDR_PK = "vehicleAddrKey";

	/**
	 * sending primary key of vehicleDtls from controller to UI for making request
	 * as state full
	 */
	public static final String MODEL_ATTR_KEY_FOR_VHCL_PK = "vehicleAddrKey";

	/**
	 * this is used to store msg for model to send Controller to UI
	 */
	public static final String MODEL_ATTR_SUCC_MSG_KEY = "msg";

	/**
	 * this is used to get msg from cache which we write in yaml file and store in
	 * map object
	 */
	public static final String MODEL_ATTR_SUCC_MSG = "succMsg";

	/**
	 * After completion all form and preview when user click on register button then
	 * we return registration dtls with that key
	 * 
	 */
	public static final String MODEL_ATTR_REGS_KEY = "vchlReg";

	/**
	 * this is used to redirection when all screen data saved then after we need to
	 * show the summary for user
	 */
	public static final String REDIRECT_VEHICLE_SUMMARY = "redirect:summary?vhclId=";

	/**
	 * After owner dtls redirect to address page we use that variables
	 */
	public static final String REDIRECT_VEHICLE_ADDR = "redirect:addrindex";
	/**
	 * After save owner address dtls we redirect with that key to vehicle dtls page
	 */
	public static final String REDIRECT_VEHICLE_DTLS = "redirect:vchlIndex";
	/**
	 * Request parameter value for OwnerId primary key
	 */
	public static final String REQ_ATTR_FOR_OWNER_KEY="ownerId";
	/**
	 * Request Parameter Value for AddressId primary key
	 */
	public static final String  REQ_ATTR_FOR_ADDR_KEY="addrId";
	/**
	 * Request Parameter value for VehicleId primary key
	 */
	public static final String REQ_ATTR_FOR_VHCL_KEY="vehicleId";

}
