package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

public class listaDrzew {

	private Vector<wezeldrzewa> klauzule = new Vector<wezeldrzewa>();
	
	private String predykaty;// bedzie zawieral predykaty
	
	public listaDrzew()
	{
		
	}
	
	
	public void wypelnijListe()
	{
		String przerywnik = "[\\s]+";
		ArrayList<String> slowa = new ArrayList<String>(Arrays.asList(predykaty.split(przerywnik))); 
               
                    
                int i = 0;
		
	}
	
	//public 

	public String getPredykaty() {
		return predykaty;
	}

	public void setPredykaty(String predykaty) {
		this.predykaty = predykaty;
	}
	
}
