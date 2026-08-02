package com.res.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import com.res.bean.ChangePasswordBean;
import com.res.bean.DesignationBean;
import com.res.bean.DistrictBean;
import com.res.bean.OfficeBean;
import com.res.bean.RoleBean;
import com.res.bean.UserBean;
import com.res.constants.RESConstants;
import com.res.entity.Designation;
import com.res.entity.District;
import com.res.entity.Office;
import com.res.entity.OfficeType;
import com.res.entity.Role;
import com.res.entity.SqmAllocation;
import com.res.entity.SqmAllocationHistory;
import com.res.entity.Users;
import com.res.entity.Work;
import com.res.exception.RESBusinessException;
import com.res.json.UserJson;
import com.res.repository.DesignationRepository;
import com.res.repository.DistrictRepository;
import com.res.repository.OfficeRepository;
import com.res.repository.OfficeTypeRepository;
import com.res.repository.RoleRepository;
import com.res.repository.SqmAllocationHistoryRepository;
import com.res.repository.SqmAllocationRepository;
import com.res.repository.UserRepository;
import com.res.service.NotificationService;
import com.res.service.UserService;
import com.res.util.RESUtil;
import com.res.util.SHAHashingUtil;

@Service
public class UserServiceImpl implements UserService {

	public static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository userRepository;
//Rakesh
	@Autowired
	private SqmAllocationRepository sqmAllocationRepository;

	@Autowired
	private SqmAllocationHistoryRepository sqmAllocationHistoryRepository;

	@Autowired
	private DesignationRepository designationRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private OfficeTypeRepository officeTypeRepository;

	@Autowired
	private OfficeRepository officeRepository;

	@Autowired
	private DistrictRepository districtRepository;

	@Autowired
	private NotificationService notificationService;

	public NotificationService getNotificationService() {
		return notificationService;
	}

	public void setNotificationService(NotificationService notificationService) {
		this.notificationService = notificationService;
	}

	@Override
	public Users findByUserName(String userName) {
		return userRepository.findByUsernameAndStatus(userName, RESConstants.STATUS_ACTIVE);
	}

	@Override
	public Users findByEmailId(String emailId) {
		return userRepository.findByEmailIdAndStatusNot(emailId, RESConstants.STATUS_DELETED);
	}

	@Override
	public void changePassword(ChangePasswordBean changePassword, String userName) throws RESBusinessException {

		try {
			Users userEntity = userRepository.findByUsernameAndStatus(userName, RESConstants.STATUS_ACTIVE);

			if (userEntity != null) {
				userEntity.setPassword(changePassword.getPassword());
				userEntity.setLastPasswordUpdatedOn((new Date()));
				userRepository.save(userEntity);
			}
		} catch (Exception e) {
			logger.error("An exception occurred.", e);
		}

	}

	@Override
	public void resetPassword(String emailId) throws RESBusinessException {

		try {
			Users userEntity = userRepository.findByEmailIdAndStatusNot(emailId, RESConstants.STATUS_DELETED);

			if (userEntity != null) {
				String password = RESUtil.generatePassword();
//				logger.info("New password " + password);
//				System.out.println("New password " + password);
				PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

				String encodedPassword = passwordEncoder.encode(SHAHashingUtil.encryptPassword(password));
				userEntity.setPassword(encodedPassword);
//				logger.info("New password Encoded " + encodedPassword);
//				System.out.println("New password Encoded " + encodedPassword);

				userRepository.save(userEntity);

				getNotificationService().sendPasswordResetNotification(userEntity.getEmailId(),
						userEntity.getMobileNo(), password);

			}
		} catch (Exception e) {
			logger.error("An exception occurred.", e);
		}
	}

	@Override
	public String registerUser(UserBean signUpBean, String verifyServiceUrl) throws RESBusinessException {

		try {
			Users entity = userRepository.findByUsernameAndStatusNot(signUpBean.getEmailId(),
					RESConstants.STATUS_DELETED);
			if (entity != null) {
				return "User with given Email Already exist!";
			} else {
				entity = new Users();
				convertUserBeanToEntity(entity, signUpBean);

				/*
				 * Set<Role> roles = new HashSet<>(); for(String role : signUpBean.getRoles()){
				 * roles.add(roleRepository.findOne(role)); } entity.setRoles(roles);
				 */
				String verificationRandomString = SHAHashingUtil.encryptPassword(SHAHashingUtil.generatePassword())
						.toString();

				entity.setVerificationRandomString(verificationRandomString);

				userRepository.save(entity);

				String emailLink = verifyServiceUrl + "?id=" + entity.getId() + "&verificationStr="
						+ verificationRandomString;

				// sending email for verification
				getNotificationService().sendRegistrationNotification(entity.getEmailId(), entity.getMobileNo(),
						emailLink);
				return null;
			}
		} catch (Exception e) {
			logger.error("An exception occurred.", e);
			return RESConstants.ERROR_SAVING_DATA;
		}
	}

	private Users convertUserBeanToEntity(Users entity, UserBean bean) throws RESBusinessException {

		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

		if (entity != null && bean != null) {
			if (bean.getId() != null) {
				entity.setId(bean.getId());
			}
			entity.setUsername(bean.getEmailId());
			if (!StringUtils.isEmpty(bean.getPassword())) {
				entity.setPassword(passwordEncoder.encode(bean.getPassword()));
			}
			entity.setName(bean.getName());
			entity.setDesignation(designationRepository.findOne(bean.getDesignationId()));
			entity.setEmailId(bean.getEmailId());
			entity.setMobileNo(bean.getMobileNo());
			entity.setIsOIC(bean.getIsOIC());
			entity.setOfficeType(officeTypeRepository.findOne(bean.getOfficeTypeId()));
			entity.setOffice(officeRepository.findOne(bean.getOfficeId()));
			if (bean.getSubDivionOfficeId() != null)
				entity.setSubDivisionalOffice(officeRepository.findOne(bean.getSubDivionOfficeId()));

			if (!StringUtils.isEmpty(bean.getStatus())) {
				entity.setStatus(bean.getStatus());
			} else {
				entity.setStatus(RESConstants.STATUS_PENDING_VERIFICATION);// during
																			// signup
			}
		}
		return entity;
	}

	// Rakesh
	private Users convertUserBeanToEntityNew(Users entity, UserBean bean) throws RESBusinessException {

		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

		if (entity != null && bean != null) {
			if (bean.getId() != null) {
				entity.setId(bean.getId());
			}
			entity.setUsername(bean.getEmailId());
			if (!StringUtils.isEmpty(bean.getPassword())) {
				entity.setPassword(passwordEncoder.encode(bean.getPassword()));
			}
			entity.setName(bean.getName());
			if (bean.getDesignationId() != null)
				entity.setDesignation(designationRepository.findOne(bean.getDesignationId()));
			entity.setEmailId(bean.getEmailId());
			entity.setMobileNo(bean.getMobileNo());
			entity.setIsOIC(bean.getIsOIC());
			if (bean.getOfficeTypeId() != null)
				entity.setOfficeType(officeTypeRepository.findOne(bean.getOfficeTypeId()));
			if (bean.getOfficeId() != null)
				entity.setOffice(officeRepository.findOne(bean.getOfficeId()));

			if (bean.getSubDivionOfficeId() != null)
				entity.setSubDivisionalOffice(officeRepository.findOne(bean.getSubDivionOfficeId()));

			if (!StringUtils.isEmpty(bean.getStatus())) {
				entity.setStatus(bean.getStatus());
			} else {
				entity.setStatus(RESConstants.STATUS_PENDING_VERIFICATION);// during
																			// signup
			}
		}
		return entity;
	}

	@Override
	public UserJson getAllUsers(Pageable pageable, String searchBoxVal, String designation, String status,
			Long officeId) {

		UserJson userJson = null;
		String[] statusArr = null;

		/*
		 * String[] statusNotArr = { RESConstants.STATUS_DELETED,
		 * RESConstants.STATUS_PENDING_VERIFICATION };
		 */
		String[] statusNotArr = { RESConstants.STATUS_DELETED };
		if (StringUtils.isEmpty(status)) {
			/*
			 * statusArr = new String[] { RESConstants.STATUS_ACTIVE,
			 * RESConstants.STATUS_INACTIVE, RESConstants.STATUS_PENDING_ACTIVATION};
			 */
			statusArr = new String[] { RESConstants.STATUS_ACTIVE, RESConstants.STATUS_INACTIVE,
					RESConstants.STATUS_PENDING_ACTIVATION, RESConstants.STATUS_PENDING_VERIFICATION };
		} else {
			statusArr = new String[] { status };
		}
		try {
			Office office = null;
			List<Short> isOIC = new LinkedList<Short>();
			if (officeId != null) {// non-admin
				office = officeRepository.findOne(officeId);
				isOIC.add((short) 0);
			} else {
				isOIC.add((short) 0);
				isOIC.add((short) 1);
			}
			Page<Users> users = null;

			if (!StringUtils.isEmpty(searchBoxVal)
					&& (!StringUtils.isEmpty(designation) || !StringUtils.isEmpty(status)))

				users = userRepository
						.findByNameContainingOrEmailIdContainingAndDesignationAndStatusInAndIsOICNotNullAndOffice(
								pageable, searchBoxVal, searchBoxVal, designation, statusArr, office, isOIC);// IsOICNotNull
																												// is to
																												// filter
																												// out
																												// Admin
																												// user
			else if (!StringUtils.isEmpty(searchBoxVal))
				users = userRepository.findByNameContainingOrEmailIdContainingAndStatusNotInAndIsOICNotNullAndOffice(
						pageable, searchBoxVal, searchBoxVal, statusNotArr, office, isOIC);
			else if (!StringUtils.isEmpty(designation) || !StringUtils.isEmpty(status))
				users = userRepository.findByDesignationAndStatusInAndIsOICNotNullAndOffice(pageable, designation,
						statusArr, office, isOIC);
			else
				users = userRepository.findByStatusNotInAndIsOICNotNullAndOffice(pageable, statusNotArr, office, isOIC);

			if (users != null) {
				List<Users> entityList = users.getContent();
				List<UserBean> beanList = new ArrayList<>();
				if (entityList != null && !entityList.isEmpty()) {

					int index = pageable.getPageNumber() * pageable.getPageSize();
					for (Users user : entityList) {

						UserBean bean = convertUserEntityToBean(user);
						bean.setIndex(++index);
						beanList.add(bean);
					}
				}
				userJson = new UserJson();
				userJson.setiTotalDisplayRecords(users.getTotalElements());
				userJson.setiTotalRecords(
						userRepository.countByStatusNotInAndIsOICNotNullAndOffice(statusNotArr, office, isOIC));
				userJson.setAaData(beanList);
			}
			return userJson;
		} catch (Exception e) {
			logger.error("An exception occurred.", e);
			return userJson;
		}
	}

//Rakesh
	@Override
	public UserJson getAllSqmUsers(Pageable pageable, String searchBoxVal, String designation, String status,
			Long officeId) {

		UserJson userJson = null;
		/*
		 * String[] statusArr = null;
		 * 
		 * String[] statusNotArr = { RESConstants.STATUS_DELETED,
		 * RESConstants.STATUS_PENDING_VERIFICATION }; if (StringUtils.isEmpty(status))
		 * { statusArr = new String[] { RESConstants.STATUS_ACTIVE,
		 * RESConstants.STATUS_INACTIVE, RESConstants.STATUS_PENDING_ACTIVATION }; }
		 * else { statusArr = new String[] { status }; } try { Office office = null;
		 * Short isOIC = null; if (officeId != null) {// non-admin office =
		 * officeRepository.findOne(officeId); isOIC = 0; } else { isOIC = 1; }
		 * Page<Users> users = null;
		 * 
		 * if (!StringUtils.isEmpty(searchBoxVal) && (!StringUtils.isEmpty(designation)
		 * || !StringUtils .isEmpty(status)))
		 * 
		 * users = userRepository
		 * .findByNameContainingOrEmailIdContainingAndDesignationAndStatusInAndIsOICNotNullAndOffice(
		 * pageable, searchBoxVal, searchBoxVal, designation, statusArr, office,
		 * isOIC);// IsOICNotNull // is to // filter // out // Admin // user else if
		 * (!StringUtils.isEmpty(searchBoxVal)) users = userRepository
		 * .findByNameContainingOrEmailIdContainingAndStatusNotInAndIsOICNotNullAndOffice(
		 * pageable, searchBoxVal, searchBoxVal, statusNotArr, office, isOIC); else if
		 * (!StringUtils.isEmpty(designation) || !StringUtils.isEmpty(status)) users =
		 * userRepository .findByDesignationAndStatusInAndIsOICNotNullAndOffice(
		 * pageable, designation, statusArr, office, isOIC); else users = userRepository
		 * .findByStatusNotInAndIsOICNotNullAndOffice(pageable, statusNotArr, office,
		 * isOIC);
		 */
		try {
			int maxLimit = (pageable.getPageSize());
			List<Object[]> entityList = userRepository.findBySqmRole(pageable.getOffset(), maxLimit);
			if (entityList != null) {
				// List<Users> entityList = users.getContent();
				List<UserBean> beanList = new ArrayList<>();
				if (entityList != null && !entityList.isEmpty()) {

					int index = pageable.getPageNumber() * pageable.getPageSize();
					for (Object[] user : entityList) {

						// UserBean bean = convertUserEntityToBean(user);
						UserBean bean = new UserBean();
						bean.setId(Long.parseLong(user[0].toString()));
						bean.setName(user[1].toString());
						if (user[2] != null)
							bean.setUsername(user[2].toString());
						if (user[3] != null)
							bean.setEmailId(user[3].toString());
						bean.setMobileNo(user[4].toString());
						bean.setStatus(user[5].toString());
						bean.setRole(user[6].toString());
						bean.setIndex(++index);
						beanList.add(bean);
					}
				}
				userJson = new UserJson();
				long size = userRepository.findBySqmRoleCount();
				userJson.setiTotalDisplayRecords(size);
				userJson.setiTotalRecords(size);
				userJson.setAaData(beanList);
			}
			return userJson;
		} catch (Exception e) {
			logger.error("An exception occurred.", e);
			return userJson;
		}
	}

	@Override
	public UserJson getAllSqmUsersForSe(Pageable pageable, String searchBoxVal, String designation, String status,
			Long officeId) {

		UserJson userJson = null;

		try {
			int maxLimit = (pageable.getPageSize() * pageable.getPageNumber()) == 0 ? pageable.getPageSize()
					: (pageable.getPageSize() * pageable.getPageNumber());
			List<Office> officeList = officeRepository.findByParentOfficeAndEnabled(new Office(officeId), (short) 1);
			List<Long> eeOficeIdList = new LinkedList<Long>();
			if (null != officeList) {
				for (Office office : officeList) {
					eeOficeIdList.add(office.getId());
				}
			}

			List<Object[]> entityList = userRepository.findBySqmRoleForSe(pageable.getOffset(), maxLimit,
					eeOficeIdList);
			if (entityList != null) {
				List<UserBean> beanList = new ArrayList<>();
				if (entityList != null && !entityList.isEmpty()) {

					int index = pageable.getPageNumber() * pageable.getPageSize();
					for (Object[] user : entityList) {

						UserBean bean = new UserBean();
						bean.setId(Long.parseLong(user[0].toString()));
						bean.setName(user[1].toString());
						if (user[2] != null)
							bean.setUsername(user[2].toString());
						if (user[3] != null)
							bean.setEmailId(user[3].toString());
						bean.setMobileNo(user[4].toString());
						bean.setStatus(user[5].toString());
						bean.setRole(user[6].toString());
						bean.setIndex(++index);
						beanList.add(bean);
					}
				}
				userJson = new UserJson();
				long size = userRepository.findBySqmRoleCountForSe(eeOficeIdList);
				userJson.setiTotalDisplayRecords(size);
				userJson.setiTotalRecords(size);
				userJson.setAaData(beanList);
			}
			return userJson;
		} catch (Exception e) {
			logger.error("An exception occurred.", e);
			return userJson;
		}
	}

	@Override
	public UserBean convertUserEntityToBean(Users user) {

		UserBean bean = new UserBean();

		if (user != null) {
			bean.setId(user.getId());
			bean.setUserId(user.getId());
			bean.setEmailId(user.getEmailId());
			bean.setName(user.getName());
			/*
			 * bean.setNameAndDesig(user.getName()+"-"+user.getDesignation().getDesignation(
			 * ));
			 */
			bean.setMeasureByName(user.getName());
			bean.setUsername(user.getUsername());
			if (null != user.getDesignation()) {
				bean.setDesignationId(user.getDesignation().getId());
			}

			bean.setDesignationBean(convertDesignationEntityToBean(user.getDesignation()));
			bean.setMobileNo(user.getMobileNo());
			bean.setIsOIC(user.getIsOIC());
			if (null != user.getIsOIC()) {
				bean.setIsOICString(user.getIsOIC() == 1 ? "Yes" : "No");
			}

			if (null != user.getOfficeType()) {
				bean.setOfficeTypeId(user.getOfficeType().getId());
			}
			OfficeBean officeBean = new OfficeBean();
			if (null != user.getOffice()) {

				bean.setOfficeId(user.getOffice().getId());
				officeBean.setId(user.getOffice().getId());
				officeBean.setOfficeName(user.getOffice().getOfficeName());
				officeBean.setOfficeAddress(user.getOffice().getOfficeAddress());
				officeBean.setDistrictCode(user.getOffice().getDistrictCode());
				if (user.getOffice().getParentOffice() != null)// EnC user
					officeBean.setParentOffice(new OfficeBean(user.getOffice().getParentOffice().getId()));

				if (officeBean.getParentOffice() != null) {
					Office office = officeRepository.findOne(officeBean.getParentOffice().getId());
					if (office.getParentOffice() != null)
						officeBean.setChiefEngineerOfficeId(office.getParentOffice().getId());
				}

				if (officeBean.getParentOffice() != null) {
					Office office = officeRepository.findOne(officeBean.getParentOffice().getId());
					if (office.getParentOffice() != null)
						officeBean.setSdoOfficeId(null);
				}
			}
			bean.setOfficeBean(officeBean);
			if (null != user.getStatus()) {
				bean.setStatus(user.getStatus());
				bean.setOldStatus(user.getStatus());
			}

			// List<RoleBean> roleBeans = new ArrayList<>();
			List<String> roleList = new ArrayList<>();
			Set<Role> roles = user.getRoles();
			String loggedInUserRole = null;
			int index = 0;
			for (Role role : roles) {
				// roleBeans.add(convertRoleEntityToBean(role));
				roleList.add(role.getRoleCode());
				if (index == 0) {
					loggedInUserRole = role.getRoleCode();
				}
				index++;
			}
			bean.setRole(loggedInUserRole);
			bean.setLoggedInUserRole(loggedInUserRole);
			bean.setRoles(roleList);
			// bean.setRoles(roleBeans);

			// setting password.
			bean.setPassword(user.getPassword());
		}
		return bean;
	}

	private DesignationBean convertDesignationEntityToBean(Designation entity) {

		DesignationBean bean = new DesignationBean();
		if (entity != null) {
			bean.setId(entity.getId());
			bean.setDesignation(entity.getDesignation());
			bean.setMeasuredByDesignation(entity.getDesignation());
			bean.setDesignationH(entity.getDesignationH());
			bean.setEnabled(entity.getEnabled());
		}
		return bean;
	}

	private RoleBean convertRoleEntityToBean(Role role) {

		RoleBean bean = new RoleBean();

		if (role != null) {
			bean.setRoleCode(role.getRoleCode());
			bean.setRoleName(role.getRoleName());
			bean.setOrder(role.getOrder());
		}
		return bean;
	}

	@Override
	public List<RoleBean> fetchRoles() {

		try {
			List<Role> roles = roleRepository.findAll();

			List<RoleBean> beanList = new ArrayList<>();
			for (Role role : roles) {
				beanList.add(convertRoleEntityToBean(role));
			}
			return beanList;
		} catch (Exception e) {
			logger.error("An exception occurred.", e);
			return null;
		}
	}

	@Override
	public UserBean fetchUserDetails(Long id) {

		try {
			Users entity = userRepository.findOne(id);

			return convertUserEntityToBean(entity);
		} catch (Exception e) {
			logger.error("An exception occurred.", e);
			return null;
		}
	}

	@Override
	public UserBean fetchSqmUserDetails(Long id) {
		UserBean userBean = null;
		try {
			Users entity = userRepository.findOne(id);

			userBean = convertUserEntityToBean(entity);

			return userBean;
		} catch (Exception e) {
			logger.error("An exception occurred.", e);
			return null;
		}
	}

	@Override
	public boolean checkSqmUserDetailByEmailId(String emailId) {
		/*
		 * Boolean entity = userRepository.checkUserExist(emailId.trim());
		 * 
		 * if(entity!=null) { return true; }else { return false; }
		 */

		List<Object[]> entities = userRepository.findBySqmEmailId(emailId.toString());

		if (entities != null && !entities.isEmpty() && entities.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public String editUser(UserBean bean, String websiteURL) {

		try {
			Users entity = userRepository.findOne(bean.getId());

			if (bean.getIsOIC() == 1 && RESConstants.STATUS_ACTIVE.equals(bean.getStatus())) {// set OIC in Office table
				Office office = officeRepository.findOne(bean.getOfficeId());
				if (office != null) {
					office.setOic(entity);
					officeRepository.save(office);
				}
				// change isOIC flag of previous OIC to 0
				Users previousOIC = userRepository.findByOfficeIdAndIsOICAndStatus(bean.getOfficeId(), bean.getIsOIC(),
						RESConstants.STATUS_ACTIVE);
				if (previousOIC != null) {
					previousOIC.setIsOIC(new Short("0"));
					userRepository.save(previousOIC);
				}
			}
			Set<Role> roles = new HashSet<>();
			/*
			 * for (String role : bean.getRoles()) {
			 * roles.add(roleRepository.findOne(role)); }
			 */
			roles.add(roleRepository.findOne(bean.getRole()));// support for one
																// role only in
																// present

			convertUserBeanToEntity(entity, bean);
			if (null != entity) {
				entity.setRoles(roles);
			}

			userRepository.save(entity);

			// When status change from Pending Activation to Active - User
			// activation
			// happens
			if (bean.getOldStatus() != null && null != entity) {
				if (bean.getOldStatus().equals(RESConstants.STATUS_PENDING_ACTIVATION)
						&& !bean.getOldStatus().equals(bean.getStatus())) {
					getNotificationService().sendAccountActivationNotification(entity.getEmailId(),
							entity.getMobileNo(), websiteURL);
				}
			}
			return null;

		} catch (Exception e) {
			logger.error("An exception occurred.", e);
			return RESConstants.ERROR_SAVING_DATA;
		}
	}

	// Rakesh
	@Override
	@Transactional(rollbackFor = Exception.class)
	public String addUser(UserBean bean, String websiteURL) {

		try {
			// Users entity = userRepository.findOne(bean.getId());
			Users entity = new Users();
			/*
			 * if (bean.getIsOIC() == 1 &&
			 * RESConstants.STATUS_ACTIVE.equals(bean.getStatus())) {// set OIC in Office
			 * table Office office = officeRepository.findOne(bean.getOfficeId()); if
			 * (office != null) { office.setOic(entity); // officeRepository.save(office); }
			 * // change isOIC flag of previous OIC to 0 Users previousOIC = userRepository
			 * .findByOfficeIdAndIsOICAndStatus(bean.getOfficeId(), bean.getIsOIC(),
			 * RESConstants.STATUS_ACTIVE); if (previousOIC != null) {
			 * previousOIC.setIsOIC(new Short("0")); // userRepository.save(previousOIC); }
			 * }
			 */
			Set<Role> roles = new HashSet<>();
			/*
			 * if( bean.getRoles()!=null) { for (String role : bean.getRoles()) {
			 * roles.add(roleRepository.findOne(role)); }
			 * 
			 * 
			 * }
			 */// support for one
				// role only in
			roles.add(roleRepository.findOne(RESConstants.ROLE_SQM)); // present

			convertUserBeanToEntityNew(entity, bean);
			entity.setRoles(roles);

			// entity.setRole(((List<Role>) roles).get(0));
			// Password
			String password = RESUtil.generatePassword();
			// logger.info("New password " + password);
			// System.out.println("New password " + password);
			PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

			String encodedPassword = passwordEncoder.encode(SHAHashingUtil.encryptPassword(password));
			entity.setPassword(encodedPassword);
			// logger.info("New password Encoded " + encodedPassword);
//to set verification link
			// String verificationRandomString =
			// SHAHashingUtil.encryptPassword(SHAHashingUtil.generatePassword()).toString();

			// entity.setVerificationRandomString(verificationRandomString);

			Users u = userRepository.save(entity);
			// System.out.println("generated Id:"+u.getId());
			if (null != bean) {
				if (bean.getWorksIds() != null) {
					String[] worksAndOffices = bean.getWorksIds().split(",");

					for (String worksAndOffice : worksAndOffices) {

						try {
							/*
							 * List<Long> list =
							 * Arrays.stream(worksAndOffice.split("-")).map(Long::parseLong).collect(
							 * Collectors.toList()); SqmAllocation sqmAllocation= new SqmAllocation();
							 * sqmAllocation.setOfficeId(list.get(0)); sqmAllocation.setWork(new
							 * Work(list.get(1))); sqmAllocation.setUsers(u);
							 * sqmAllocation.setEnabled((short) 1);
							 * sqmAllocationRepository.save(sqmAllocation);
							 */
							if (worksAndOffice.contains("-")) {
								List<Long> list = Arrays.stream(worksAndOffice.split("-")).map(Long::parseLong)
										.collect(Collectors.toList());
								SqmAllocation sqmAllocation = new SqmAllocation();
								sqmAllocation.setOfficeId(list.get(0));
								sqmAllocation.setWork(new Work(list.get(1)));
								sqmAllocation.setUsers(u);
								sqmAllocation.setEnabled((short) 1);
								sqmAllocation.setInspectionDone((short) 0);
								// sqmAllocation.setModifiedBy(u.getUsername());
								sqmAllocationRepository.save(sqmAllocation);
							} else {
								SqmAllocation sqmAllocation = new SqmAllocation();
								sqmAllocation.setOfficeId(Long.parseLong(worksAndOffice));
								// sqmAllocation.setWork(new Work(list.get(1)));
								sqmAllocation.setUsers(u);
								sqmAllocation.setEnabled((short) 1);
								sqmAllocation.setInspectionDone((short) 0);
								sqmAllocationRepository.save(sqmAllocation);
							}
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}

					}
					/*
					 * List<Long> list =
					 * Arrays.stream(bean.getWorksIds().split(",")).map(Long::parseLong).collect(
					 * Collectors.toList()); for(Long workId:list) { SqmAllocation sqmAllocation=
					 * new SqmAllocation(); sqmAllocation.setWork(new Work(workId));
					 * sqmAllocation.setUsers(u); sqmAllocation.setEnabled((short) 1);
					 * sqmAllocationRepository.save(sqmAllocation); }
					 */
				}
			}

			// When status change from Pending Activation to Active - User
			// activation
			// happens
			/*
			 * if(bean.getOldStatus()!= null) { if (bean.getOldStatus().equals(
			 * RESConstants.STATUS_PENDING_ACTIVATION) &&
			 * !bean.getOldStatus().equals(bean.getStatus())) {
			 * getNotificationService().sendAccountActivationNotification(
			 * entity.getEmailId(), entity.getMobileNo(), websiteURL); } }
			 */
			//
			// String contextPath = "";
//			if (!StringUtils.isEmpty(httpServletRequest.getContextPath())) {
//				contextPath = httpServletRequest.getContextPath();
//			}
//
//			String fullPath = httpServletRequest.getScheme() + "://"
//					+ getApplicationDeploymentServerName() + ":"
//					+ httpServletRequest.getServerPort() + contextPath;
//			String verifyServiceUrl = fullPath + "/" + RESConstants.VERIFY_EMAIL_SERVICE_NAME;
//
//			entity.setVerificationRandomString(verificationRandomString);
//			String emailLink = verifyServiceUrl + "?id=" + entity.getId()
//			+ "&verificationStr=" + verificationRandomString;

			// sending email for verification
			try {
				if (null != bean && bean.getStatus() != null && bean.getStatus().equals(RESConstants.STATUS_ACTIVE)) {
					getNotificationService().sendSqmAccountActivationNotification(entity.getEmailId(),
							entity.getMobileNo(), password);
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return null;

		} catch (Exception e) {
			logger.error("An exception occurred.", e);
			return RESConstants.ERROR_SAVING_DATA;
		}
	}

	// Update Sqm user
	@Override
	@Transactional(rollbackFor = Exception.class)
	public String updateSqmUser(UserBean bean, String websiteURL) {

		try {
			// Users entity = userRepository.findOne(bean.getId());
			Users entity = userRepository.findOne(bean.getId());

			Set<Role> roles = new HashSet<>();
			/// support for one
			// role only in
			roles.add(roleRepository.findOne(RESConstants.ROLE_SQM)); // present

			convertUserBeanToEntityNew(entity, bean);
			if (null != entity) {
				entity.setRoles(roles);
			}

			/*
			 * String password = RESUtil.generatePassword(); logger.info("New password " +
			 * password);
			 * 
			 * PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			 * 
			 * String encodedPassword = passwordEncoder.encode(SHAHashingUtil
			 * .encryptPassword(password)); entity.setPassword(encodedPassword);
			 * logger.info("New password Encoded " + encodedPassword);
			 * 
			 * String verificationRandomString = SHAHashingUtil
			 * .encryptPassword(SHAHashingUtil.generatePassword()) .toString();
			 * 
			 * entity.setVerificationRandomString(verificationRandomString);
			 */

			Users u = userRepository.save(entity);
			// Get All Allocation'
			User loggedInUser = RESUtil.getUserDetail();
			List<SqmAllocation> oldSqmAllocations = sqmAllocationRepository.findByUsersAndEnabled(new Users(u.getId()),
					(short) 1);
			if (oldSqmAllocations != null && !oldSqmAllocations.isEmpty()) {
				for (SqmAllocation allocation : oldSqmAllocations) {
					// SqmAllocationHistory sqmAllocationHistory= new SqmAllocationHistory();
					// sqmAllocationHistory.setId(id);
					// sqmAllocationHistory.setCreatedBy(allocation.getCreatedBy());
					// sqmAllocationHistory.setCreatedDate(allocation.getCreatedDate());
					// sqmAllocationHistory.setEnabled(allocation.getEnabled());
					// sqmAllocationHistory.setModifiedBy(allocation.getModifiedBy());
					// sqmAllocationHistory.setOfficeId(allocation.getOfficeId());
					// sqmAllocationHistory.setModifiedDate(allocation.getModifiedDate());
					// sqmAllocationHistory.setUsers(allocation.getUsers());
					// sqmAllocationHistory.setInspectionDone(allocation.getInspectionDone());
					// sqmAllocationHistory.setWork(allocation.getWork());
					// sqmAllocationHistory.setEditDate(new Date());
					// sqmAllocationHistoryRepository.save(sqmAllocationHistory);
					// delete form table
					// sqmAllocationRepository.delete(allocation);
					allocation.setEnabled((short) 0);
					allocation.setModifiedDate(new Date());
					allocation.setModifiedBy(loggedInUser.getUsername());
				}
			}

			if (bean.getWorksIds() != null) {
				String[] worksAndOffices = bean.getWorksIds().split(",");

				for (String worksAndOffice : worksAndOffices) {

					try {

						if (worksAndOffice.contains("-")) {
							List<Long> list = Arrays.stream(worksAndOffice.split("-")).map(Long::parseLong)
									.collect(Collectors.toList());
							SqmAllocation sqmAllocation = new SqmAllocation();
							sqmAllocation.setOfficeId(list.get(0));
							sqmAllocation.setWork(new Work(list.get(1)));
							sqmAllocation.setUsers(u);
							sqmAllocation.setInspectionDone((short) 0);
							// sqmAllocation.setModifiedBy(loggedInUser.getUsername());
							// sqmAllocation.setModifiedDate(new Date());
							sqmAllocation.setEnabled((short) 1);
							sqmAllocationRepository.save(sqmAllocation);
						} else {
							SqmAllocation sqmAllocation = new SqmAllocation();
							sqmAllocation.setOfficeId(Long.parseLong(worksAndOffice));
							// sqmAllocation.setWork(new Work(list.get(1)));
							sqmAllocation.setUsers(u);
							sqmAllocation.setEnabled((short) 1);
							sqmAllocation.setInspectionDone((short) 0);
							sqmAllocation.setModifiedBy(loggedInUser.getUsername());
							sqmAllocationRepository.save(sqmAllocation);
						}
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}

				}

			}

			// When status change from Pending Activation to Active - User
			// activation
			// happens
			/*
			 * if(bean.getOldStatus()!= null) { if (bean.getOldStatus().equals(
			 * RESConstants.STATUS_PENDING_ACTIVATION) &&
			 * !bean.getOldStatus().equals(bean.getStatus())) {
			 * getNotificationService().sendAccountActivationNotification(
			 * entity.getEmailId(), entity.getMobileNo(), websiteURL); } }
			 */
			//
			String contextPath = "";
			/*
			 * try { getNotificationService().sendPasswordResetNotification(
			 * entity.getEmailId(), entity.getMobileNo(), password); }catch (Exception e) {
			 * // TODO: handle exception e.printStackTrace(); }
			 */
			return null;

		} catch (Exception e) {
			logger.error("An exception occurred.", e);
			return RESConstants.ERROR_SAVING_DATA;
		}
	}

	// updateOfficerInsp

	@Override
	@Transactional(rollbackFor = Exception.class)
	public String updateOfficerInsp(UserBean bean, String websiteURL) {

		try {
			// Users entity = userRepository.findOne(bean.getId());
			Users entity = userRepository.findOne(bean.getId());

			/* Set<Role> roles = new HashSet<>(); */
			/// support for one
			// role only in
			/* roles.add(roleRepository.findOne(RESConstants.ROLE_SQM)); */ // present

			/*
			 * convertUserBeanToEntityNew(entity , bean); entity.setRoles(roles);
			 */

			/*
			 * String password = RESUtil.generatePassword(); logger.info("New password " +
			 * password);
			 * 
			 * PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			 * 
			 * String encodedPassword = passwordEncoder.encode(SHAHashingUtil
			 * .encryptPassword(password)); entity.setPassword(encodedPassword);
			 * logger.info("New password Encoded " + encodedPassword);
			 * 
			 * String verificationRandomString = SHAHashingUtil
			 * .encryptPassword(SHAHashingUtil.generatePassword()) .toString();
			 * 
			 * entity.setVerificationRandomString(verificationRandomString);
			 */

			/* Users u= userRepository.save(entity); */
			// Get All Allocation'
			User loggedInUser = RESUtil.getUserDetail();
			List<SqmAllocation> oldSqmAllocations = sqmAllocationRepository
					.findByUsersAndEnabled(new Users(bean.getUserId()), (short) 1);
			if (oldSqmAllocations != null && !oldSqmAllocations.isEmpty()) {
				for (SqmAllocation allocation : oldSqmAllocations) {
					// SqmAllocationHistory sqmAllocationHistory= new SqmAllocationHistory();
					// sqmAllocationHistory.setId(id);
					// sqmAllocationHistory.setCreatedBy(allocation.getCreatedBy());
					// sqmAllocationHistory.setCreatedDate(allocation.getCreatedDate());
					// sqmAllocationHistory.setEnabled(allocation.getEnabled());
					// sqmAllocationHistory.setModifiedBy(allocation.getModifiedBy());
					// sqmAllocationHistory.setOfficeId(allocation.getOfficeId());
					// sqmAllocationHistory.setModifiedDate(allocation.getModifiedDate());
					// sqmAllocationHistory.setUsers(allocation.getUsers());
					// sqmAllocationHistory.setInspectionDone(allocation.getInspectionDone());
					// sqmAllocationHistory.setWork(allocation.getWork());
					// sqmAllocationHistory.setEditDate(new Date());
					// sqmAllocationHistoryRepository.save(sqmAllocationHistory);
					// delete form table
					// sqmAllocationRepository.delete(allocation);
					allocation.setEnabled((short) 0);
					allocation.setModifiedDate(new Date());
					allocation.setModifiedBy(loggedInUser.getUsername());
				}
			}

			if (bean.getWorksIds() != null) {
				String[] worksAndOffices = bean.getWorksIds().split(",");

				for (String worksAndOffice : worksAndOffices) {

					try {

						if (worksAndOffice.contains("-")) {
							List<Long> list = Arrays.stream(worksAndOffice.split("-")).map(Long::parseLong)
									.collect(Collectors.toList());
							SqmAllocation sqmAllocation = new SqmAllocation();
							sqmAllocation.setOfficeId(list.get(0));
							sqmAllocation.setWork(new Work(list.get(1)));
							sqmAllocation.setUsers(new Users(bean.getUserId()));
							sqmAllocation.setInspectionDone((short) 0);
							// sqmAllocation.setModifiedBy(loggedInUser.getUsername());
							// sqmAllocation.setModifiedDate(new Date());
							sqmAllocation.setEnabled((short) 1);
							if (bean.getDesignationId() != null) {
								sqmAllocation.setOfficeType(new OfficeType(bean.getDesignationId()));
							}
							if (bean.getOffice() != null) {
								sqmAllocation.setOfficerOfficeId(new Office(bean.getOffice()));
							}
							sqmAllocationRepository.save(sqmAllocation);
						} else {
							SqmAllocation sqmAllocation = new SqmAllocation();
							sqmAllocation.setOfficeId(Long.parseLong(worksAndOffice));
							// sqmAllocation.setWork(new Work(list.get(1)));
							sqmAllocation.setUsers(new Users(bean.getUserId()));
							sqmAllocation.setEnabled((short) 1);
							sqmAllocation.setInspectionDone((short) 0);
							sqmAllocation.setModifiedBy(loggedInUser.getUsername());
							if (bean.getDesignationId() != null) {
								sqmAllocation.setOfficeType(new OfficeType(bean.getDesignationId()));
							}
							if (bean.getOffice() != null) {
								sqmAllocation.setOfficerOfficeId(new Office(bean.getOffice()));
							}
							sqmAllocationRepository.save(sqmAllocation);
						}
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}

				}

			}

			// When status change from Pending Activation to Active - User
			// activation
			// happens
			/*
			 * if(bean.getOldStatus()!= null) { if (bean.getOldStatus().equals(
			 * RESConstants.STATUS_PENDING_ACTIVATION) &&
			 * !bean.getOldStatus().equals(bean.getStatus())) {
			 * getNotificationService().sendAccountActivationNotification(
			 * entity.getEmailId(), entity.getMobileNo(), websiteURL); } }
			 */
			//
			String contextPath = "";
			/*
			 * try { getNotificationService().sendPasswordResetNotification(
			 * entity.getEmailId(), entity.getMobileNo(), password); }catch (Exception e) {
			 * // TODO: handle exception e.printStackTrace(); }
			 */
			return null;

		} catch (Exception e) {
			logger.error("An exception occurred.", e);
			return RESConstants.ERROR_SAVING_DATA;
		}
	}

	//
	@Override
	public boolean checkIsOICByOfficeId(Long officeId) {

		Users isOIC = userRepository.findByOfficeIdAndIsOICAndStatus(officeId, (short) 1, RESConstants.STATUS_ACTIVE);

		if (isOIC == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public String deleteUser(Long id) {

		try {
			Users entity = userRepository.findOne(id);
			if (entity != null) {
				entity.setStatus(RESConstants.STATUS_DELETED);
				userRepository.save(entity);
			}
			return null;
		} catch (Exception e) {
			logger.error("An exception occurred.", e);
			return RESConstants.ERROR_DELETING_DATA;
		}
	}

	@Override
	public UserBean fetchUserDetailsByUserName(String userName) {
		try {
			Users entity = userRepository.findByUsernameAndStatus(userName, RESConstants.STATUS_ACTIVE);
			UserBean bean = convertUserEntityToBean(entity);
			DistrictBean districtBean = new DistrictBean();
			OfficeBean officeBean = new OfficeBean();
			if (null != bean.getOfficeBean() && bean.getOfficeBean().getDistrictCode() != null) {
				District district = districtRepository
						.findByDistrictCodeAndEnabled(bean.getOfficeBean().getDistrictCode(), (short) 1);
				if (null != district && null != district.getDistrictId()) {
					districtBean.setDistrictId(district.getDistrictId());
					districtBean.setDistrictName(district.getDistrictName());
					districtBean.setDistrictNameH(district.getDistrictNameH());
					districtBean.setLgdDistrictCode(district.getLgdDistrictCode());
				}

			}

			bean.setDistrictBean(districtBean);
			return bean;
		} catch (Exception e) {
			logger.error("An exception occurred.", e);
			return null;
		}
	}

	@Override
	public String verifyEmail(Long id, String verificationStr) {

		try {

			Users user = userRepository.findByIdAndVerificationRandomString(id, verificationStr);
			if (user != null) {
				String status = user.getStatus();
				if (!status.equals(RESConstants.STATUS_PENDING_VERIFICATION)) {// Already
																				// Verified
					return "Already Verified";
				}
				user.setStatus(RESConstants.STATUS_PENDING_ACTIVATION);
				userRepository.save(user);

				// sending email for activation
				getNotificationService().sendEmailVerificationNotification(user.getEmailId(), user.getMobileNo());

				return "Email Verified";
			}
			return null;
		} catch (Exception e) {
			logger.error("An exception occurred.", e);
			return null;
		}
	}

	@Override
	public UserJson getAllInspUsers(Pageable pageable, String searchBoxVal, String designation, String status,
			Long officeId) {

		UserJson userJson = null;

		try {
			int maxLimit = (pageable.getPageSize());
			List<Object[]> entityList = sqmAllocationRepository.findByInspRole(pageable.getOffset(), maxLimit);
			if (entityList != null) {
				// List<Users> entityList = users.getContent();
				List<UserBean> beanList = new ArrayList<>();
				if (entityList != null && !entityList.isEmpty()) {

					int index = pageable.getPageNumber() * pageable.getPageSize();
					for (Object[] user : entityList) {

						// UserBean bean = convertUserEntityToBean(user);
						UserBean bean = new UserBean();
						bean.setId(Long.parseLong(user[0].toString()));
						bean.setName(user[1].toString());
						if (user[2] != null)
							bean.setUsername(user[2].toString());
						if (user[3] != null)
							bean.setEmailId(user[3].toString());
						bean.setMobileNo(user[4].toString());
						bean.setStatus(user[5].toString());
						bean.setRole(user[6].toString());
						bean.setIndex(++index);
						beanList.add(bean);
					}
				}
				userJson = new UserJson();
				long size = sqmAllocationRepository.findByInspRoleCount();
				userJson.setiTotalDisplayRecords(size);
				userJson.setiTotalRecords(size);
				userJson.setAaData(beanList);
			}
			return userJson;
		} catch (Exception e) {
			logger.error("An exception occurred.", e);
			return userJson;
		}
	}

	@Override
	public UserBean fetchInspUserDetails(Long id) {
		UserBean userBean = null;
		try {
			Users entity = userRepository.findOne(id);

			userBean = convertUserEntityToBean(entity);
			List<SqmAllocation> aa = sqmAllocationRepository.findDistinctByUsers(new Users(id));
			userBean.setOfficeTypeId(aa.get(0).getOfficeType().getId());
			userBean.setOfficerOfficeId(aa.get(0).getOfficerOfficeId().getId());

			return userBean;
		} catch (Exception e) {
			logger.error("An exception occurred.", e);
			return null;
		}
	}

}
