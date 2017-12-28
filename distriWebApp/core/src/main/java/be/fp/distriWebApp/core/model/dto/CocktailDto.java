package be.fp.distriWebApp.core.model.dto;
// Generated by Hibernate Tools 4.3.1.Final

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class CocktailDto implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1405515382862168729L;
	private Integer id;
	private String intitule;
	private Float prix;
	private Integer color;

	private Set<IngredientcocktailDto> ingredientcocktails = new HashSet<IngredientcocktailDto>(0);

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	public String getIntitule() {
		return intitule;
	}

	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}

	public Float getPrix() {
		return prix;
	}

	public void setPrix(Float prix) {
		this.prix = prix;
	}

	public Integer getColor() {
		return color;
	}

	public void setColor(Integer color) {
		this.color = color;
	}

	public Set<IngredientcocktailDto> getIngredientcocktails() {
		return ingredientcocktails;
	}

	public void setIngredientcocktails(Set<IngredientcocktailDto> ingredientcocktails) {
		this.ingredientcocktails = ingredientcocktails;
	}
	
	public Set<IngredientDto> getIngredientsDto(){
		Set<IngredientDto> ingredientDtoList = new HashSet<IngredientDto>();
		for(IngredientcocktailDto currIngrCock : ingredientcocktails){
			ingredientDtoList.add(currIngrCock.getIngredient());
		}
		return ingredientDtoList;		
	}
	
	public List<IngredientcocktailDto> getIngredientcocktailsList(){
		List<IngredientcocktailDto> ingCockList = new ArrayList<IngredientcocktailDto>(0);
		ingCockList.addAll(ingredientcocktails);
		return ingCockList;		
	}


}
