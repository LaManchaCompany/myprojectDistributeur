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
@Table(name = "ingredientcocktail", catalog = "distriwebapp")

public class Ingredientcocktail implements java.io.Serializable,Comparable<Ingredientcocktail> {

	public static final String ID = "id";
	public static final String COCKTAIL = "cocktail";
	public static final String INGREDIENT = "ingredient";
	public static final String NUMORDRE = "numOrdre";
	public static final String QUANTITECL = "quantiteCl";

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id", unique = true, nullable = false)

	private int id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_id_cocktail", nullable = false)

	private Cocktail cocktail;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_id_ingredient", nullable = false)

	private Ingredient ingredient;

	@Column(name = "num_ordre", nullable = false)

	private int numOrdre;

	@Column(name = "quantite_cl", nullable = false, precision = 12, scale = 0)

	private BigDecimal quantiteCl;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Cocktail getCocktail() {
		return cocktail;
	}

	public void setCocktail(Cocktail cocktail) {
		this.cocktail = cocktail;
	}

	public Ingredient getIngredient() {
		return ingredient;
	}

	public void setIngredient(Ingredient ingredient) {
		this.ingredient = ingredient;
	}

	public int getNumOrdre() {
		return numOrdre;
	}

	public void setNumOrdre(int numOrdre) {
		this.numOrdre = numOrdre;
	}

	public BigDecimal getQuantiteCl() {
		return quantiteCl;
	}

	public void setQuantiteCl(BigDecimal quantiteCl) {
		this.quantiteCl = quantiteCl;
	}

	/**
	* toString
	* @return String
	*/
	public String toString() {
		return new ToStringBuilder(this).append("\n").append("id", id).append("\n").toString();
	}

	public boolean equals(Object obj) {
		if (obj instanceof Ingredientcocktail == false) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		Ingredientcocktail rhs = (Ingredientcocktail) obj;
		return new EqualsBuilder().append("id", rhs.id).isEquals();
	}

	public int hashCode() {
		return new HashCodeBuilder(17, 37).append(id).toHashCode();
	}

	@Override
	public int compareTo(Ingredientcocktail o) {
		
		return Integer.compare(numOrdre, o.numOrdre);
	}
}
