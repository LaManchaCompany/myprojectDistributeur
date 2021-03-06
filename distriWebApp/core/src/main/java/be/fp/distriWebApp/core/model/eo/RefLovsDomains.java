package be.fp.distriWebApp.core.model.eo;
// Generated by Hibernate Tools 4.3.1.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
@Table(name = "ref_lovs_domains", catalog = "distriwebapp")

public class RefLovsDomains implements java.io.Serializable {

	public static final String DOMAIN = "domain";
	public static final String LABFR = "labFr";
	public static final String CREATIONDATE = "creationDate";
	public static final String CREATIONUSER = "creationUser";
	public static final String MODIFICATIONDATE = "modificationDate";
	public static final String MODIFICATIONUSER = "modificationUser";

	@Id

	@Column(name = "domain", unique = true, nullable = false)

	private String domain;

	@Column(name = "lab_fr", length = 500)

	private String labFr;

	@Temporal(TemporalType.DATE)
	@Column(name = "creation_date", nullable = false, length = 10)

	private Date creationDate;

	@Column(name = "creation_user", nullable = false, length = 300)

	private String creationUser;

	@Temporal(TemporalType.DATE)
	@Column(name = "modification_date", length = 10)

	private Date modificationDate;

	@Column(name = "modification_user", length = 300)

	private String modificationUser;


	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getLabFr() {
		return labFr;
	}

	public void setLabFr(String labFr) {
		this.labFr = labFr;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getCreationUser() {
		return creationUser;
	}

	public void setCreationUser(String creationUser) {
		this.creationUser = creationUser;
	}

	public Date getModificationDate() {
		return modificationDate;
	}

	public void setModificationDate(Date modificationDate) {
		this.modificationDate = modificationDate;
	}

	public String getModificationUser() {
		return modificationUser;
	}

	public void setModificationUser(String modificationUser) {
		this.modificationUser = modificationUser;
	}


	/**
	* toString
	* @return String
	*/
	public String toString() {
		return new ToStringBuilder(this).append("\n").append("domain", domain).append("\n").toString();
	}

	public boolean equals(Object obj) {
		if (obj instanceof RefLovsDomains == false) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		RefLovsDomains rhs = (RefLovsDomains) obj;
		return new EqualsBuilder().append("domain", rhs.domain).isEquals();
	}

	public int hashCode() {
		return new HashCodeBuilder(17, 37).append(domain).toHashCode();
	}

}
