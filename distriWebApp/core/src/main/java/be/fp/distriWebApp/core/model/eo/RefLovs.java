package be.fp.distriWebApp.core.model.eo;
// Generated by Hibernate Tools 4.3.1.Final

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
@Table(name = "ref_lovs", catalog = "distriwebapp")

public class RefLovs implements java.io.Serializable {

	public static final String DOMCODE = "domCode";
	public static final String REFLOVSDOMAINS = "refLovsDomains";
	public static final String CODE = "code";
	public static final String LABFR = "labFr";
	public static final String LONGLABFR = "longLabFr";
	public static final String SEQUENCENR = "sequenceNr";
	public static final String CREATIONDATE = "creationDate";
	public static final String CREATIONUSER = "creationUser";
	public static final String MODIFICATIONDATE = "modificationDate";
	public static final String MODIFICATIONUSER = "modificationUser";

	@Id

	@Column(name = "dom_code", unique = true, nullable = false, length = 200)

	private String domCode;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "domain", nullable = false)

	private RefLovsDomains refLovsDomains;

	@Column(name = "code", nullable = false, length = 50)

	private String code;

	@Column(name = "lab_fr")

	private String labFr;

	@Column(name = "long_lab_fr", length = 65535)

	private String longLabFr;

	@Column(name = "sequence_nr")

	private Integer sequenceNr;

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

	public String getDomCode() {
		return domCode;
	}

	public void setDomCode(String domCode) {
		this.domCode = domCode;
	}

	public RefLovsDomains getRefLovsDomains() {
		return refLovsDomains;
	}

	public void setRefLovsDomains(RefLovsDomains refLovsDomains) {
		this.refLovsDomains = refLovsDomains;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLabFr() {
		return labFr;
	}

	public void setLabFr(String labFr) {
		this.labFr = labFr;
	}

	public String getLongLabFr() {
		return longLabFr;
	}

	public void setLongLabFr(String longLabFr) {
		this.longLabFr = longLabFr;
	}

	public Integer getSequenceNr() {
		return sequenceNr;
	}

	public void setSequenceNr(Integer sequenceNr) {
		this.sequenceNr = sequenceNr;
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
		return new ToStringBuilder(this).append("\n").append("domCode", domCode).append("\n").toString();
	}

	public boolean equals(Object obj) {
		if (obj instanceof RefLovs == false) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		RefLovs rhs = (RefLovs) obj;
		return new EqualsBuilder().append("domCode", rhs.domCode).isEquals();
	}

	public int hashCode() {
		return new HashCodeBuilder(17, 37).append(domCode).toHashCode();
	}

}
