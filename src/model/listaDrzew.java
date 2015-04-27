package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

public class listaDrzew {

	private Vector<wezeldrzewa> klauzule = new Vector<wezeldrzewa>();
	
	private String predykaty;// bedzie zawieral predykaty
	
	private ArrayList<ArrayList<String>> tablicaSlow = new ArrayList<ArrayList<String>>();
	
	public listaDrzew()
	{
		
	}
	
	public void dzielSlowa()
	{
		
		//ArrayList<ArrayList<String>> tablicaSlow = new ArrayList<ArrayList<String>>();
	
	
		String przerywnik = "[\\n]+";
		ArrayList<String> linie = new ArrayList<String>(Arrays.asList(predykaty.split(przerywnik)));
		
		przerywnik = "[\\s]+";
		
		for(int x = 0; x < linie.size(); x++)
		{
			tablicaSlow.add(new ArrayList<String>(Arrays.asList(linie.get(x).split(przerywnik))));
		}
	           
	    System.out.println(tablicaSlow);
	    
	    
	    String pomocniczy = ""; // bedzie przechowywal znaki jednego slowa podzielone przez | oraz ^
	    String poczatkowy = ""; // bedzie przechowywal caly String pobierany z tablicySlow
	    ArrayList<String> dzielenieSlow = new ArrayList<String>();
	    
	    char znak;
	    
	    int pom;
	    
	    for (int columna = 0; columna < tablicaSlow.size(); columna++)
	    {
	    	for(int szereg = 0;szereg < tablicaSlow.get(columna).size(); szereg++)
	    	{
	            poczatkowy = tablicaSlow.get(columna).get(szereg);
	            
	            for(int x = 0; x < poczatkowy.length(); x++)
	            {
	            	znak = poczatkowy.charAt(x);
	            	if (znak == '|' || znak == '&' || znak == '(' || znak == ')' || znak == '-')
	            	{
	            		if (!pomocniczy.isEmpty())
	            		{
	            			dzielenieSlow.add(pomocniczy);
	            		}
	            		dzielenieSlow.add(String.valueOf(znak));
	            		
	            		pomocniczy = "";
	            	}
	            	else if (znak == '=' && x+1 < poczatkowy.length() && poczatkowy.charAt(x+1) == '>' && x-1 > 0 && poczatkowy.charAt(x-1) != '<')
	            	{
	            		if (!pomocniczy.isEmpty())
	            		{
	            			dzielenieSlow.add(pomocniczy);
	            		}
	            		dzielenieSlow.add("=>");
	            		x++;
	            	}
	            	else if (znak == '<' && x+2 < poczatkowy.length() && poczatkowy.charAt(x+1) == '=' && poczatkowy.charAt(x+2) == '>')
	            	{
	            		if (!pomocniczy.isEmpty())
	            		{
	            			dzielenieSlow.add(pomocniczy);
	            		}
	            		dzielenieSlow.add("<=>");
	            		x+=2;
	            	}
	            		
	            	else pomocniczy += znak;
	            	if (x+1 == poczatkowy.length() && znak != '|' && znak != '&' &&
	            		znak != '(' && znak != ')' && znak != '-' && !pomocniczy.isEmpty())
	            		dzielenieSlow.add(pomocniczy);
	            }
	            pomocniczy = "";
	    	}
    	
	        // usuwanie zawartosci tablicy
	        pom = tablicaSlow.get(columna).size();
	        for (int x = 0; x < pom; x++)
	        {
	        	tablicaSlow.get(columna).remove(0);
	        }
	        
	        // wstawianie nowych wartosci do tablicySlow i usuwanie ich z dzielenieSlow
	        
	        pom = dzielenieSlow.size();
	        for (int x = 0; x < pom; x++)
	        {
	        	tablicaSlow.get(columna).add(dzielenieSlow.get(x));
	        }
	    	dzielenieSlow.clear();
        
	    }
	    
	    // TODO tutaj zapierdalamy !!!!
	
	    
	    System.out.println("___");
	    
	    
	    System.out.println(tablicaSlow);
	}
	
	private void wczytajSlowa()
        {
            
            wezeldrzewa temp;
            boolean a;
            for(int i = 0;i<tablicaSlow.size();i++)
            {
                
                
                ArrayList<String> linia = tablicaSlow.get(i);
                for(int j = 0;j<tablicaSlow.get(i).size();j++)
                {
                    if(linia.get(j).charAt(0) == '&')
                    {
                    }
                    else
                    {
                        temp = new wezeldrzewa(linia.get(j));
                        
                    }
                    
                    
                }
            
            
            
            
            
            }
        }
	
	public void wypelnijListe()
	{
		dzielSlowa();

	}
	
	//public 

	public String getPredykaty() {
		return predykaty;
	}

	public void setPredykaty(String predykaty) {
		this.predykaty = predykaty;
	}
	
}
