package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

public class ListaDrzew {

	private Vector<Vector<Literal>> klauzule = new Vector<Vector<Literal>>();
        private Vector<WezelDrzewa> predykaty = new Vector<WezelDrzewa>();
	private ArrayList<ArrayList<String>> tablicaSlow = new ArrayList<ArrayList<String>>();
	private String text;// bedzie zawieral predykaty
	
	
	
	public ListaDrzew()
	{
          
		
	}
        private void usunNawiasy()
        {
            for(int i = 0;i<predykaty.size();i++)
            {
                
                
                
                nawiasy(predykaty.get(i));
                
            }
            
            
        }
        private void nawiasy(WezelDrzewa wz)
        {
            
        }
        
        public void neguj()  //tylko dla tezy
        {
            if(predykaty.size() == 0)
            {
                return;
            }
            
            Vector<WezelDrzewa> tempVector = new Vector<>();
            WezelDrzewa temp = new WezelDrzewa(-1);
            temp.setZnak(true);
            tempVector.add(temp);
            temp.setPrawy(predykaty.get(0));
            if(predykaty.size() > 1)
            { 
                WezelDrzewa temp2;
                for(int i = 1;i<predykaty.size();i++)
                {
                    temp2 = new WezelDrzewa(1);
                    temp2.setLewy(temp.getPrawy());
                    temp.setPrawy(temp2);
                    temp2.setPrawy(predykaty.get(i));
                    

                }
                
                
            }
            predykaty = tempVector;
                             
           
            
        }
	
        
        
	public void dzielSlowa()
	{
            
		
		//ArrayList<ArrayList<String>> tablicaSlow = new ArrayList<ArrayList<String>>();
	
	
		String przerywnik = "[\\n]+";
		ArrayList<String> linie = new ArrayList<String>(Arrays.asList(text.split(przerywnik)));
		
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
	            		pomocniczy = "";
	            	}
	            	else if (znak == '<' && x+2 < poczatkowy.length() && poczatkowy.charAt(x+1) == '=' && poczatkowy.charAt(x+2) == '>')
	            	{
	            		if (!pomocniczy.isEmpty())
	            		{
	            			dzielenieSlow.add(pomocniczy);
	            		}
	            		dzielenieSlow.add("<=>");
	            		x+=2;
	            		pomocniczy = "";
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
            
            WezelDrzewa temp,kozen,ostatni;
            boolean czyKozen,czyStart;
            boolean czySymbol;
            boolean czyMinus = false;
            int symbol = 0;
            ostatni = new WezelDrzewa("test");
            kozen = new WezelDrzewa("test");
            temp = new WezelDrzewa("test");
            
            
            
            
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
                        temp = new WezelDrzewa(5);
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
                        temp = new WezelDrzewa(linia.get(j));
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
                        
                        temp = new WezelDrzewa(symbol);
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
                predykaty.add(kozen);
            
            
            
            
            
            }
            return false;
        }
	
	public void wypelnijListe()
	{
		dzielSlowa();
                wczytajSlowa();

	}
	
	//public 

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

        public Vector<WezelDrzewa> getPredykaty()
        {
            return predykaty;
        }

        public void setPredykaty(Vector<WezelDrzewa> predykaty)
        {
            this.predykaty = predykaty;
        }

        public Vector<Vector<Literal>> getKlauzule()
        {
            return klauzule;
        }

        public void setKlauzule(Vector<Vector<Literal>> klauzule)
        {
            this.klauzule = klauzule;
        }

}
