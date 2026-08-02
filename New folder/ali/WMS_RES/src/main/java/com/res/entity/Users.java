package com.res.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

//@Audited
@Entity
@Table(name = "USERS")
public class Users extends Auditable implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
    private String username;
    
    private String password;
    
    private String name;
    
    @JoinColumn(name = "DESIGNATION_ID", referencedColumnName = "ID")
	@OneToOne
	//@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private Designation designation;
    
    @Column(name = "EMAIL_ID")
	private String emailId;
	
	@Column(name = "MOBILE_NO")
	private String mobileNo;
	
	@Column(name = "IS_OIC")
	private Short isOIC;
	
	@JoinColumn(name = "OFFICE_TYPE_ID", referencedColumnName = "ID")
	@ManyToOne
	//@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private OfficeType officeType;
	
	@JoinColumn(name = "OFFICE_ID", referencedColumnName = "ID")
	@ManyToOne
	private Office office;
	
	@JoinColumn(name = "SUB_DIVISIONAL_OFFICE_ID", referencedColumnName = "ID")
	@ManyToOne
	private Office subDivisionalOffice;
    
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "USER_ROLE", joinColumns = @JoinColumn(name = "ID"), inverseJoinColumns = @JoinColumn(name = "ROLE_CODE"))
    private Set<Role> role;
    
    private String status;
    
    @JoinColumn(name = "CONTRACTOR_ID", referencedColumnName = "ID")
	@ManyToOne
	private Contractor contractor;
    
	@Column(name = "VERIFICATION_RANDOM_STR")
    private String verificationRandomString;
	
	@Column(name = "last_password_updated_on")
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastPasswordUpdatedOn;

	
	public Date getLastPasswordUpdatedOn() {
		return lastPasswordUpdatedOn;
	}

	public void setLastPasswordUpdatedOn(Date lastPasswordUpdatedOn) {
		this.lastPasswordUpdatedOn = lastPasswordUpdatedOn;
	}
    
    public Users() {
		super();
	}

	public Users(Long id) {
		this.id=id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
    
    public Set<Role> getRoles() {
        return role;
    }

    public void setRoles(Set<Role> role) {
        this.role = role;
    }

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String state) {
		this.status = state;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Designation getDesignation() {
		return designation;
	}

	public void setDesignation(Designation designation) {
		this.designation = designation;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public Short getIsOIC() {
		return isOIC;
	}

	public void setIsOIC(Short isOic) {
		this.isOIC = isOic;
	}

	public OfficeType getOfficeType() {
		return officeType;
	}

	public void setOfficeType(OfficeType officeType) {
		this.officeType = officeType;
	}

	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}

	public String getVerificationRandomString() {
		return verificationRandomString;
	}

	public void setVerificationRandomString(String verificationRandomString) {
		this.verificationRandomString = verificationRandomString;
	}

	public Contractor getContractor() {
		return contractor;
	}

	public void setContractor(Contractor contractor) {
		this.contractor = contractor;
	}

	public Office getSubDivisionalOffice() {
		return subDivisionalOffice;
	}

	public void setSubDivisionalOffice(Office subDivisionalOffice) {
		this.subDivisionalOffice = subDivisionalOffice;
	}

	
	
	
}
