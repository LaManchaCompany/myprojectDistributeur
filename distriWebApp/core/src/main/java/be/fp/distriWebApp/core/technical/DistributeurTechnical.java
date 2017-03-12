package be.fp.distriWebApp.core.technical;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import be.fp.distriWebApp.core.model.dto.DistributeurDto;
import be.fp.distriWebApp.core.model.eo.Cocktail;
import be.fp.distriWebApp.core.model.eo.Ingredient;
import be.fp.distriWebApp.core.model.eo.Pompe;
import be.fp.distriWebApp.core.technical.thread.DistributeurServerAtmega;



public class DistributeurTechnical {
	
	private static final Logger logger = LoggerFactory.getLogger(DistributeurTechnical.class);
	private Set<Pompe> pompes = new HashSet<Pompe>();
	private boolean etatMarche;

	private DistributeurDto distributeur;
	

	private Set<Cocktail> coktailsDispo = new HashSet<Cocktail>();
	private LinkedList<Cocktail> coktailsTODO = new LinkedList<Cocktail>();
	private Set<Ingredient> ingredientDispo = new HashSet<Ingredient>();

	private Set<Cocktail> cocktailDipso = new HashSet<Cocktail>();
	
	private ServerSocket sServerAtmega ;
	
	private DistributeurServerAtmega distriServerAtmega;
	private boolean cocktailEnCours = false;
	 		

	public DistributeurTechnical(DistributeurDto distriDto)
	{
		etatMarche = true;
		distributeur = distriDto;
		distriServerAtmega = new DistributeurServerAtmega(distriDto);	
	}
	public Set<Pompe> getPompes() {
		return pompes;
	}
	public void setPompes(Set<Pompe> pompes) {
		this.pompes = pompes;
	}
	public boolean isEtatMarche() {
		return etatMarche;
	}
	public void setEtatMarche(boolean etatMarche) {
		this.etatMarche = etatMarche;
	}
	public Set<Cocktail> getManagerCocktail() {
		return coktailsDispo;
	}
	public void setManagerCocktail(Set<Cocktail> managerCocktail) {
		this.coktailsDispo = managerCocktail;
	}
	public Set<Ingredient> getIngredientDispo() {
		return ingredientDispo;
	}
	public void setIngredientDispo(Set<Ingredient> ingredientDispo) {
		this.ingredientDispo = ingredientDispo;
	}

	public DistributeurServerAtmega getDistiClientAtmega() {
		return distriServerAtmega;
	}
	public void setDistiClientAtmega(DistributeurServerAtmega distiClientAtmega) {
		this.distriServerAtmega = distiClientAtmega;
	}
	
	public LinkedList<Cocktail> getCoktailsTODO() {
		return coktailsTODO;
	}
	public void setCoktailsTODO(LinkedList<Cocktail> coktailsTODO) {
		this.coktailsTODO = coktailsTODO;
	}
	
	public void startServerAtmega()
	{
		try {
			sServerAtmega = new ServerSocket(distributeur.getPortAtmega());
			distriServerAtmega.setServerSocket(sServerAtmega);
			distriServerAtmega.startServer();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	public void startReadSerial()
	{
		try {
			(new SerialCommunicationRxtx()).connect("COM3");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	public void stopServerAtmega()
	{
		distriServerAtmega.stopServer();		
	}
	
	public void startAllServerTablette()
	{

		startServerAtmega();
		
	}
	public void stopAllServerTablette()
	{
		stopServerAtmega();
	}	

	public void setCocktailDipso(Set<Cocktail> cocktailDipso) {
		this.cocktailDipso = cocktailDipso;
	}
	public Set<Cocktail> getCocktailDipso() {
		return cocktailDipso;
	}
	public boolean isCocktailEnCours() {
		return cocktailEnCours;
	}
	public void setCocktailEnCours(boolean cocktailEnCours) {
		this.cocktailEnCours = cocktailEnCours;
	}

	
}
