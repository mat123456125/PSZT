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
		
		ArrayList<ArrayList<String>> tablicaSlow = new ArrayList<ArrayList<String>>();
		
		
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
                	if (znak == '|' || znak == '&')
                	{
                		if (!pomocniczy.isEmpty())
                		{
                			dzielenieSlow.add(pomocniczy);
                		}
                		dzielenieSlow.add(String.valueOf(znak));
                		
                		pomocniczy = "";
                	}
                	else pomocniczy += znak;
                	if (x+1 == poczatkowy.length() && znak != '|' && znak != '&') dzielenieSlow.add(pomocniczy);
                	//if (szereg+1 == tablicaSlow.size()) dzielenieSlow.add(pomocniczy);
                	//else if(x == poczatkowy.length())
                	//{
                	//	
                	//	dzielenieSlow.add(pomocniczy);
                	//}
                	//else pomocniczy += znak;
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
        //omg co to jest

        
        System.out.println("___");
        
        
        System.out.println(tablicaSlow);
        
		
	}
	
	//public 

	public String getPredykaty() {
		return predykaty;
	}

	public void setPredykaty(String predykaty) {
		this.predykaty = predykaty;
	}
	
}
