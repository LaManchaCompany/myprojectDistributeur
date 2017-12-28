package be.fp.distriWebApp.core.bo.impl;

import java.awt.Color;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import be.fp.distriWebApp.core.bo.CocktailBo;
import be.fp.distriWebApp.core.bo.abstractDozerMapperBo;
import be.fp.distriWebApp.core.model.dao.CocktailDao;
import be.fp.distriWebApp.core.model.dao.IngredientCocktailDao;
import be.fp.distriWebApp.core.model.dao.IngredientDao;
import be.fp.distriWebApp.core.model.dao.PompeDao;
import be.fp.distriWebApp.core.model.dao.RefLovsDao;
import be.fp.distriWebApp.core.model.dto.CocktailDto;
import be.fp.distriWebApp.core.model.dto.IngredientDto;
import be.fp.distriWebApp.core.model.dto.IngredientcocktailDto;
import be.fp.distriWebApp.core.model.eo.Cocktail;
import be.fp.distriWebApp.core.model.eo.Ingredient;
import be.fp.distriWebApp.core.model.eo.Ingredientcocktail;
import be.fp.distriWebApp.core.model.eo.Pompe;

@Service
@WebService
public class CocktailBoImpl extends abstractDozerMapperBo implements CocktailBo {
	@Autowired
	private CocktailDao cocktailDao;
	@Autowired
	private PompeDao pompeDao;
	@Autowired
	private IngredientDao ingredientDao;
	@Autowired
	private IngredientCocktailDao ingredientCocktailDao;
	@Autowired
	private RefLovsDao refLovsDao;

	@Transactional(readOnly=true)
	@Override
	public List<CocktailDto> findAllCocktail(){
		List<CocktailDto> cocktailListDto = new ArrayList<CocktailDto>(0);
		List<Cocktail> cocktailList = cocktailDao.getAll();
		if(cocktailList != null){
			for(Cocktail curCock : cocktailList){
				cocktailListDto.add(mapper.map(curCock,CocktailDto.class));
			}
		}
		return cocktailListDto;
	}


	@Transactional(readOnly=false)
	@Override
	public void saveCocktail(CocktailDto cocktailDto) {
		if(cocktailDto != null){			
			saveIngredientCocktail(cocktailDto);			
			Cocktail cockSaved = cocktailDao.save( mapper.map(cocktailDto, Cocktail.class));
			cocktailDto.setId(cockSaved.getId());
		}
	}

	private void saveIngredientCocktail(CocktailDto cocktailDto) {
		boolean bfound = false;
		
		if(cocktailDto.getIngredientcocktails() != null && cocktailDto.getIngredientcocktails().size() > 0){
			// suppression
			Cocktail cocktailinit  = cocktailDao.get(cocktailDto.getId());
			
			for(Iterator<Ingredientcocktail> itIngCockAdd  = cocktailinit.getIngredientcocktails().iterator(); itIngCockAdd.hasNext();){		
				Ingredientcocktail currIngCockInit = (Ingredientcocktail) itIngCockAdd.next();
				
			    for(IngredientcocktailDto curIngCockDto : cocktailDto.getIngredientcocktails()){
				   if(curIngCockDto.getId() == currIngCockInit.getId()){
					   bfound = true;
				   }			   
			    }	
			    if(bfound == false){
			    	itIngCockAdd.remove();
			    	ingredientCocktailDao.remove(currIngCockInit);			    	
			    }
			    bfound = false;
			}		
			// ajout
			for(IngredientcocktailDto currIngCockDto : cocktailDto.getIngredientcocktails()){
				
				for(Iterator<Ingredientcocktail> itIngCockAdd  = cocktailinit.getIngredientcocktails().iterator(); itIngCockAdd.hasNext();){
					Ingredientcocktail currIngCockToAdd = (Ingredientcocktail) itIngCockAdd.next();
					if(currIngCockDto.getId() == currIngCockToAdd.getId()){
						bfound = true;
					}
				}
				if(bfound == false){
					Ingredientcocktail newIngredientCock = mapper.map(currIngCockDto, Ingredientcocktail.class);
					newIngredientCock.setCocktail(cocktailinit);
					newIngredientCock.setQuantiteCl(new BigDecimal(0));
					newIngredientCock.setNumOrdre(99);
					ingredientCocktailDao.save(newIngredientCock);						
				}
				bfound = false;
			}
		}
	}


	@Transactional(readOnly=false)
	@Override
	public void deleteCocktail(CocktailDto coctailDto) {
		if(coctailDto != null){
			Cocktail cocktailToDelete =  cocktailDao.get(coctailDto.getId()); 
			
			for(Iterator<Ingredientcocktail> itIngCockAdd  = cocktailToDelete.getIngredientcocktails().iterator(); itIngCockAdd.hasNext();){
				ingredientCocktailDao.remove((Ingredientcocktail) itIngCockAdd.next());
				
			}
			cocktailDao.remove(cocktailToDelete);
		}
	}

	@Transactional(readOnly=true)
	@Override
	public String getCocktailToString(CocktailDto cocktailDto)
	{
		String coToMake = "C";	
		BigDecimal dureePompe;
		Pompe pompeEnCours = new Pompe();
		Cocktail cocktail = mapper.map(cocktailDto, Cocktail.class);
		if(cocktail != null){
			for(Ingredientcocktail curIngreCocktail : cocktail.getListIngredientCocktailByOrder())
			{
				pompeEnCours = curIngreCocktail.getIngredient().getPompe();
				if(pompeEnCours != null){
					dureePompe = curIngreCocktail.getQuantiteCl().divide(pompeEnCours.getDebitmlperseconde()).multiply(new BigDecimal(100));
					coToMake += "P" + pompeEnCours.getId() + "D" + dureePompe ;
				}
			}
			
			return coToMake;
		}
		return null;

	}
	@Transactional(readOnly=true)
	@Override
	public byte[] getCocktailToArrayByte(CocktailDto cocktailDto)
	{
		Color color = new Color(0, 183,198);
		int intTemp ;
		
		Cocktail cocktail = mapper.map(cocktailDto, Cocktail.class);
		if(cocktail != null){
			ByteBuffer coToMake = ByteBuffer.allocate(cocktail.getNbrByte());		
			String init = "c";
			
			coToMake.put(init.getBytes()[0]);			
			intTemp = color.getRed();
			if( intTemp == 0)
			{
				intTemp ++;
			}
			coToMake.put((byte) (0xFF & intTemp));
			
			
			intTemp = color.getGreen();
			if( intTemp == 0)
			{
				intTemp ++;
			}
			coToMake.put((byte) (0xFF & intTemp));
			intTemp = color.getBlue();
			if( intTemp == 0)
			{
				intTemp ++;
			}
			coToMake.put((byte) (0xFF & intTemp));
			
		
			int numPompe;
			BigDecimal dureePompeDisSec;
			Pompe pompeEnCours = new Pompe();
		
			for(Ingredientcocktail curIngreCocktail : cocktail.getListIngredientCocktailByOrder())
			{
				numPompe = curIngreCocktail.getIngredient().getPompe().getId();
				coToMake.put(BigInteger.valueOf(numPompe).toByteArray()[0]); 
			
				 // TODO Calcul débit quantite
				dureePompeDisSec = curIngreCocktail.getQuantiteCl().divide(pompeEnCours.getDebitmlperseconde()).multiply(new BigDecimal(100));
				
				coToMake.putShort(dureePompeDisSec.shortValueExact());
		
			}
			coToMake.put(BigInteger.valueOf(0).toByteArray()[0]);
			coToMake.put(BigInteger.valueOf(0).toByteArray()[0]);

			return coToMake.array();
		}
		return null;		
	}
	
	@Transactional(readOnly=false)
	@Override
	public boolean remplirBouteille(int idPompe)
	{
		boolean returnValue = false;
		BigDecimal quantiteBouteillePlein;
		
		
		Ingredient ingredient = ingredientDao.get(idPompe);
		if(ingredient != null){
			ingredient.setQuantiteRestante(ingredient.getQuantitePleine());
			returnValue = true;
		}
		return returnValue ; 
	}
	@Override
	public int getNbreCocktailPosIngredient(CocktailDto cocktailDto)
	{
		// renvoie le nombre de cockatail qu'il est possible de faire en fonction des ingrédients restants
		int nbrCockPossible = 9999;
		BigDecimal temp;
		int nbrtemp;
		
		for(IngredientcocktailDto currIngCock : cocktailDto.getIngredientcocktails())
		{
			temp = currIngCock.getIngredient().getQuantiteRestante().divide(currIngCock.getQuantiteCl());
			nbrtemp = (int) Math.floor(temp.intValue());
			if(nbrtemp < nbrCockPossible)
			{
				nbrCockPossible = nbrtemp;
			}			
		}
		if( nbrCockPossible == 9999)
		{
			nbrCockPossible = 0;
		}
		
		return  nbrCockPossible;
	}
	@Override
	public List<IngredientcocktailDto> verifieCocktailSotck(CocktailDto cocktailDto)
	{
		// renvoie la lise des ingredients pour lequel il manque des quantité
		List<IngredientcocktailDto> listeIngredientQuantInsu = new ArrayList<IngredientcocktailDto>();
		
		boolean returnValue = false;
		for(IngredientcocktailDto currIngredientFromCocktail : cocktailDto.getIngredientcocktails())
		{
			if(currIngredientFromCocktail.getIngredient().getQuantiteRestante().compareTo(currIngredientFromCocktail.getQuantiteCl()) < 0){
				listeIngredientQuantInsu.add(currIngredientFromCocktail);
			}			
		}
		
		return listeIngredientQuantInsu;		
	}
	
	@Transactional(readOnly=false)
	@Override
	public void retireQuantiteIngredientcocktail(IngredientcocktailDto ingredCockDto)
	{
		BigDecimal quantiteTemp;
		
		Ingredient ingredient = mapper.map(ingredCockDto.getIngredient(), Ingredient.class);
		if(ingredient != null){
			ingredient.setQuantiteRestante(ingredient.getQuantiteRestante().subtract(ingredCockDto.getQuantiteCl()));
		}
	}
	
}
