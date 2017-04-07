package be.fp.distriWebApp.webapp.managedBean;

import be.fp.distriWebApp.core.bo.CocktailBo;
import be.fp.distriWebApp.core.bo.DistributeurBo;
import be.fp.distriWebApp.core.bo.IngredientBo;
import be.fp.distriWebApp.core.model.dto.CocktailDto;
import be.fp.distriWebApp.core.model.dto.DistributeurDto;
import be.fp.distriWebApp.core.model.dto.IngredientDto;
import be.fp.distriWebApp.core.model.eo.Ingredient;
import be.fp.distriWebApp.webapp.action.BasePage;
import org.apache.avro.generic.GenericData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.ManagedBean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Controller
@ManagedBean
@Scope(value = WebApplicationContext.SCOPE_SESSION)

public class CocktailMb extends BasePage implements Serializable{
	private static final Logger logger = LoggerFactory.getLogger(CocktailMb.class);
	
	@Autowired private CocktailBo cocktailBo;
	@Autowired private IngredientBo ingredientBo;
	AdminiDistriMb adminDistriMb;

	private IngredientDto currentIngredient;
	private CocktailDto currentCocktail;

	/*COCKTAIL*/

	public List<CocktailDto> getAllCocktail(){
		List<CocktailDto> cocktailsListDto = cocktailBo.findAllCocktail();
		return cocktailsListDto;
	}

	public String editCocktail(CocktailDto cocktailToEdit){
		setCurrentCocktail(cocktailToEdit);
		return "cocktailForm";
	}

	public String addCocktail(){
		currentCocktail = new CocktailDto();
		return "cocktailForm";
	}

	public String saveCocktail(){
		if(currentCocktail.getId() != null){
			cocktailBo.saveCocktail(currentCocktail);
		}
		return "cocktailForm";
	}
	public String deleteCocktail(){
		if(currentCocktail  != null){
			cocktailBo.deleteCocktail(currentCocktail);
		}
		currentCocktail = null;
		return "cocktails";
	}

	public String cancelCocktail(){
		currentCocktail = null;
		return "cocktails";
	}

	/*INGREDIENT*/


	public List<IngredientDto> getAllIngredient(){
		List<IngredientDto> ingredientsDto = ingredientBo.findAllIngredients();
		return ingredientsDto;
	}

	public List<IngredientDto> getAllIngredientSoft(){
		List<IngredientDto> ingredientsDto = ingredientBo.findAllIngredientsSoft();
		return ingredientsDto;
	}

	public List<IngredientDto> getAllIngredientAlcolise(){
		List<IngredientDto> ingredientsDto = ingredientBo.findAllIngredientsAlcolise();
		return ingredientsDto;
	}

	public String editIngredient(IngredientDto ingredientToEdit){
		setCurrentIngredient(ingredientToEdit);
		return "ingredientForm";
	}
	public String saveIngredient(){
		if(currentIngredient.getId() != null){
			ingredientBo.saveDistributeur(currentIngredient);
		}else{
			ingredientBo.addIngredient(currentIngredient);
		}
		currentIngredient = null;
		return "ingredients";
	}
	public String deleteIngredient(){
		if(currentIngredient  != null){
			ingredientBo.deleteIngredient(currentIngredient);
		}
		currentIngredient = null;
		return "ingredients";
	}
	public String cancelIngredient(){
		currentIngredient = null;
		return "ingredients";
	}

	public String addIngredient(boolean alcolise){
		currentIngredient = new IngredientDto();
		currentIngredient.setAlcoolise(alcolise);
		return "ingredientForm";
	}
	/*GETTERS AND SETTETS*/

	public IngredientDto getCurrentIngredient() {
		return currentIngredient;
	}

	public void setCurrentIngredient(IngredientDto currentIngredient) {
		this.currentIngredient = currentIngredient;
	}

	public CocktailDto getCurrentCocktail() {
		return currentCocktail;
	}

	public void setCurrentCocktail(CocktailDto currentCocktail) {
		this.currentCocktail = currentCocktail;
	}
}
