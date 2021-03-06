package be.fp.distriWebApp.core.model.eo;
// Generated by Hibernate Tools 4.3.1.Final

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity 
@Table(name = "ingredient", catalog = "distriwebapp")

public class Ingredient implements java.io.Serializable {

	public static final String ID = "id";
	public static final String POMPE = "pompe";
	public static final String INTITULE = "intitule";
	public static final String ALCOOLISE = "alcoolise";
	public static final String DEGREALCOOL = "degreAlcool";
	public static final String MARQUE = "marque";
	public static final String QUANTITEPLEINE = "quantitePleine";
	public static final String QUANTITERESTANTE = "quantiteRestante";
	public static final String IMPORTID = "importId";

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "Id", unique = true, nullable = false)

	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_pompe_id")

	private Pompe pompe;

	@Column(name = "intitule", nullable = false, length = 500)

	private String intitule;

	@Column(name = "alcoolise", nullable = false)

	private boolean alcoolise;

	@Column(name = "degre_alcool", nullable = false)

	private Integer degreAlcool;

	@Column(name = "marque", nullable = false, length = 500)

	private String marque;

	@Column(name = "quantite_pleine")

	private BigDecimal quantitePleine;

	@Column(name = "quantite_restante")

	private BigDecimal quantiteRestante;

	@Column(name = "import_id")
	private Integer importId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Pompe getPompe() {
		return pompe;
	}

	public void setPompe(Pompe pompe) {
		this.pompe = pompe;
	}

	public String getIntitule() {
		return intitule;
	}

	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}

	public boolean isAlcoolise() {
		return alcoolise;
	}

	public void setAlcoolise(boolean alcoolise) {
		this.alcoolise = alcoolise;
	}

	public Integer getDegreAlcool() {
		return degreAlcool;
	}

	public void setDegreAlcool(Integer degreAlcool) {
		this.degreAlcool = degreAlcool;
	}

	public String getMarque() {
		return marque;
	}

	public void setMarque(String marque) {
		this.marque = marque;
	}

	public BigDecimal getQuantitePleine() {
		return quantitePleine;
	}

	public void setQuantitePleine(BigDecimal quantitePleine) {
		this.quantitePleine = quantitePleine;
	}

	public BigDecimal getQuantiteRestante() {
		return quantiteRestante;
	}

	public void setQuantiteRestante(BigDecimal quantiteRestante) {
		this.quantiteRestante = quantiteRestante;
	}

	public Integer getImportId() {
		return importId;
	}

	public void setImportId(Integer importId) {
		this.importId = importId;
	}
	/**
	* toString
	* @return String
	*/
	public String toString() {
		return new ToStringBuilder(this).append("\n").append("id", id).append("\n").toString();
	}

	public boolean equals(Object obj) {
		if (obj instanceof Ingredient == false) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		Ingredient rhs = (Ingredient) obj;
		return new EqualsBuilder().append("id", rhs.id).isEquals();
	}

	public int hashCode() {
		return new HashCodeBuilder(17, 37).append(id).toHashCode();
	}

}
