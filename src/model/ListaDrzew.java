package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

public class ListaDrzew {

	private Vector<Vector<Literal>> klauzule = new Vector<Vector<Literal>>();
        private Vector<WezelDrzewa> predykaty = new Vector<WezelDrzewa>();
	private ArrayList<ArrayList<String>> tablicaSlow = new ArrayList<ArrayList<String>>();
	private String text;// bedzie zawieral predykaty
        private boolean teza = false;
	
	
	
	public ListaDrzew()
	{
          
		
	}
        private void usunNawiasy()
        {
            for(int i = 0;i<predykaty.size();i++)
            {
                if(predykaty.get(i).getSpojnik() == -1)
                {
                    if( predykaty.get(i).isZnak())
                    {
                    predykaty.set(i, predykaty.get(i).getPrawy());
                    predykaty.get(i).setZnak(true);
                    }
                    else
                    {
                        predykaty.set(i, predykaty.get(i).getPrawy());
                        
                    }
                }
                if(predykaty.get(i).getSpojnik() == 0)
                    continue;
                
                
                nawiasy(predykaty.get(i));
                
            }
            
            
        }
        private void nawiasy(WezelDrzewa wz)
        {
            //prawe poddrzewo
            
            if(wz.getPrawy().getSpojnik() == -1)
            {
                if(wz.getPrawy().isZnak())
                {
                    wz.getPrawy().getPrawy().setZnak(true);
                }
                wz.setPrawy(wz.getPrawy().getPrawy());
                nawiasy(wz.getPrawy());
                
            }
            else if(wz.getPrawy().getSpojnik() != 0)
            {
                nawiasy(wz.getPrawy());
            }
            //lewe poddrzewo
            if(wz.getLewy().getSpojnik() == -1)
            {
                if(wz.getLewy().isZnak())
                {
                    wz.getLewy().getPrawy().setZnak(true);
                }
                wz.setLewy(wz.getLewy().getPrawy());
                nawiasy(wz.getLewy());
                
            }
            else if(wz.getLewy().getSpojnik() != 0)
            {
                nawiasy(wz.getLewy());
            }
            
            
            
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
	            	else if ((znak == '=') && (x+1 < poczatkowy.length()) && (poczatkowy.charAt(x+1) == '>') && (x-1 >= 0) && (poczatkowy.charAt(x-1) != '<'))
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
            int czyNawias = 0;
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
                        czyNawias++;
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
                        czyNawias--;
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
                        
                        
                        if(czyKozen)
                        {
                            ostatni = kozen;
                            if(kozen.getSpojnik() < symbol && (czyNawias  == 0 ))
                            {
                                temp = new WezelDrzewa(symbol);
                                temp.setLewy(kozen);
                                kozen = temp;
                                ostatni = temp;
                                
                            }
                            else
                            {
                                if (czyNawias != 0)
                                {
                                    temp = kozen;

                                    while (ostatni.getPrawy().getSpojnik() != 0)
                                    {
                                        ostatni = ostatni.getPrawy();
                                        if (ostatni.getSpojnik() == 5)
                                            temp = ostatni;

                                    }
                                    ostatni = temp;

                                }
                            
                                                          

                                while(ostatni.getPrawy().getSpojnik() >= symbol)
                                {
                                    ostatni = ostatni.getPrawy();
                                }
                                temp = new WezelDrzewa(symbol);
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
	
        private void zmienNaKlauzule()
        {
        	int k,s;
        	s = predykaty.size();
        	for(k = 0;k<s;k++)
                {

        		conversion.IMPEQ(predykaty.get(k));
        		conversion.sprawdzNegacje(predykaty.get(k));
                }
                for(k = 0;k<predykaty.size();k++)
                {
                        
        		rozdzielKoniunkcje(predykaty.get(k));
        	}
                wyciagnijKoniunkcje();

        }
        
        private void wypelnijLitaraly() throws IllegalAccessException
        {
            
            Vector<Literal> temp;
            WezelDrzewa wz;
            for(int i = 0; i< predykaty.size();i++ )
            {
                temp = new Vector<Literal>();
                wz = predykaty.get(i);
                
                DrzewoNaVectorKlauzula(wz, temp);
                
                
                klauzule.add(temp);
                
            }  
            
        }

       public void DrzewoNaVectorKlauzula(WezelDrzewa m,Vector<Literal> klauzula)
       {
    	   if (m.getLewy() != null)
               DrzewoNaVectorKlauzula(m.getLewy(), klauzula);

           if (m.getPrawy() != null)
               DrzewoNaVectorKlauzula(m.getPrawy(), klauzula);
           if(m.getLewy()== null && m.getPrawy() == null)
           {
               Literal l = new Literal(m.isZnak(), m.getZdanie());
               klauzula.add(l);
           }
    	   
       }
        
        
        
        
        
	public void wypelnijListe() throws IllegalAccessException
	{
		dzielSlowa();
                wczytajSlowa();
                if(teza) neguj();
                usunNawiasy();
                zmienNaKlauzule();
                wypelnijLitaraly();
                

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

        public boolean isTeza()
        {
            return teza;
        }

        public void setTeza(boolean teza)
        {
            this.teza = teza;
        }

    private int rozdzielKoniunkcje(WezelDrzewa wz)
    {
    	if(wz.getSpojnik()==1){
    	predykaty.addElement(wz.getLewy());
    	predykaty.addElement(wz.getPrawy());
    	predykaty.removeElement(wz);
    	return 0;
    	}
    	return 1;
        
    }

    private void wyciagnijKoniunkcje()
    {
        WezelDrzewa temp,temp2,noweDrzewo;
        
        

        
        	for(int k = 0;k < predykaty.size();)
                {
                    temp = null;
                    temp2 = null;
                    temp = znajdujWierzcholekKoniunkcji(predykaty.get(k),null);
                    if(temp == null )
                    {
                        k++;
                       
                    }
                    else
                    {
                        

                        noweDrzewo = conversion.copyTree(predykaty.get(k));
                        predykaty.add(k+1, noweDrzewo);
                        temp2 = znajdujWierzcholekKoniunkcji(noweDrzewo,null);
                        if(temp.getLewy().getSpojnik() == 1)
                        {
                           

                            temp2.setLewy(temp.getLewy().getPrawy());
                            temp.setLewy(temp.getLewy().getLewy());
                            
                        }
                        else
                        {
                            

                            temp2.setPrawy(temp.getPrawy().getPrawy());
                            temp.setPrawy(temp.getPrawy().getLewy());
                        }
                        
                        
                    }
                    

        		
        	
                
                
                }
                
                
                
                
    }
        
    

    private WezelDrzewa znajdujWierzcholekKoniunkcji (WezelDrzewa wezel, WezelDrzewa ojciec)
    {
    	WezelDrzewa nowy = null;
    	
    	if (wezel == null)
    	{
    		return null;
    	}
    	
    	if (wezel.getSpojnik() == 1)
    	{
    		return ojciec;
    	}
    	
    	nowy = znajdujWierzcholekKoniunkcji(wezel.getLewy(), wezel);
    	
    	if (nowy != null)
    	{
            return nowy;
        
        }
        nowy = znajdujWierzcholekKoniunkcji(wezel.getPrawy(), wezel);
    	
    	if (nowy != null)
    	{
            return nowy;
        
        }
        return null;
    	
    	
    	
    }
        
}
