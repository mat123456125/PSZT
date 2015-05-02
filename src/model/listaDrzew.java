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
	
	private boolean wczytajSlowa()
        {
            
            wezeldrzewa temp,kozen,ostatni;
            boolean czyKozen,czyStart;
            boolean czySymbol;
            boolean czyMinus = false;
            int symbol = 0;
            ostatni = new wezeldrzewa("test");
            kozen = new wezeldrzewa("test");
            temp = new wezeldrzewa("test");
            
            
            
            
            for(int i = 0;i<tablicaSlow.size();i++)
            {
                
                
                czyKozen = true;
                czyStart = true;
             
                ArrayList<String> linia = tablicaSlow.get(i);
                for(int j = 0;j<tablicaSlow.get(i).size();j++)
                {
                    czySymbol = false;
                    
                    if(linia.get(j).charAt(0) == '&')
                    {
                        symbol = 1;
                        czySymbol = true;
                   
                        
                        
                    }
                    
                    else if(linia.get(j).charAt(0) == '|')
                    {
                          symbol = 2;
                          czySymbol = true;
                   
                        
                        
                    }
                    
                    else if(linia.get(j).charAt(0) == '=')
                    {
                        if(linia.get(j).charAt(1) == '>')
                        {
                            symbol = 3;
                            czySymbol = true;
                        }
                        else
                        {
                            return true;
                        }
                        
                    }
                    
                    else if(linia.get(j).charAt(0) == '<')
                    {
                          if(linia.get(j).charAt(1) == '=' && linia.get(j).charAt(2) == '>')
                        {
                            symbol = 4;
                            czySymbol = true;
                        }
                          
                        else
                        {
                            return true;
                        }
                        
                    }
                    else if(linia.get(j).charAt(0) == '(')
                    {
                        temp = new wezeldrzewa(5);
                        if(czyKozen)
                        {   
                            
                            if(czyStart)
                            {
                                kozen = temp;
                                ostatni = kozen;
                                czyStart = false;
                                czyKozen = false;
                            }
                            else
                            {
                                return true;
                            }
                        }
                        else
                        {
                            ostatni.setPrawy(temp);
                            ostatni = temp;
                            
                        }
                        
                        
                        
                        
                    }
                    else if(linia.get(j).charAt(0) == ')')
                    {
                        ostatni = kozen;
                        temp = kozen;

                                while(ostatni.getPrawy().getSpojnik()!= 0)
                                {
                                    ostatni = ostatni.getPrawy();
                                    if(ostatni.getSpojnik() == 5)
                                        temp = ostatni;
                                    
                                }
                                temp.setSpojnik(-1);
                        
                        
                        
                    }
                     else if(linia.get(j).charAt(0) == '-')
                    {
                        czyMinus = true;  
                        continue;
                        
                        
                    }
                    
                    
                    else
                    {
                        temp = new wezeldrzewa(linia.get(j));
                        if(czyKozen)
                        {   
                            
                            if(czyStart)
                            {
                                kozen = temp;
                                ostatni = kozen;
                                czyStart = false;
                                czyKozen = true;
                            }
                        }
                        else
                        {
                            ostatni.setPrawy(temp);
                            ostatni = kozen;
                            czyKozen = true;
                        }
                        
                        
                    }
                    
                    if(czySymbol)
                    {
                             if(czyStart)
                        {
                            return true;
                        }
                        
                        temp = new wezeldrzewa(symbol);
                        if(czyKozen)
                        {
                            if(kozen.getSpojnik() < symbol)
                            {
                                temp.setLewy(kozen);
                                kozen = temp;
                                ostatni = temp;
                                
                            }
                            else
                            {
                                ostatni = kozen;

                                while(ostatni.getPrawy().getSpojnik() >= symbol)
                                {
                                    ostatni = ostatni.getPrawy();
                                }
                                temp.setLewy(ostatni.getPrawy());
                                ostatni.setPrawy(temp);
                                ostatni = temp;
                                
                            }
                            czyKozen = false;
                            
                            
                        }
                        else
                        {
                            return true;
                        }
                        
                        czySymbol = false;
                    }
                    if(czyMinus)
                    {
                        temp.setZnak(true);
                        czyMinus = false;
                    }
                    
                    
                }
                klauzule.add(kozen);
            
            
            
            
            
            }
            return false;
        }
	
	public void wypelnijListe()
	{
		dzielSlowa();
                wczytajSlowa();

	}
	
	//public 

	public String getPredykaty() {
		return predykaty;
	}

	public void setPredykaty(String predykaty) {
		this.predykaty = predykaty;
	}
	
}
