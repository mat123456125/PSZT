package model;


import java.util.Vector;
import java.util.ArrayList;

public class OperacjeNaDrzewach
{
	private ListaDrzew predykaty = new ListaDrzew();
	private ListaDrzew teza = new ListaDrzew();
	private Vector<Vector<Literal>> klauzule = new Vector<Vector<Literal>>();
        private ArrayList<ArrayList<Integer>> identyfikatory_przodkow = new ArrayList<ArrayList<Integer>> ();
	
	public OperacjeNaDrzewach()
	{
		
	}
	
	private boolean sprawdzCzyDaSieSworzyc(Vector<Literal> pierwsze, Vector<Literal> drugie)
	{
		
		Literal literal1,literal2;
		
		for (int x = 0; x < pierwsze.size(); x++)
		{
			for(int y = 0; y < drugie.size(); y++)
			{
				literal1 = pierwsze.get(x);
				literal2 = drugie.get(y);
				if (literal1.isZnak() != literal2.isZnak() && literal1.getZdanie().equals(literal2.getZdanie()))
				{
					return true;
				}
			}
		}
		return false;
	}
	
	private boolean czyKoniec() // metoda sluzaca do sprawdzenia czy dodana klauzula nie jest zaprzeczeniem innej klauzuli
								// jesli jest to koniec wnioskowania
	{
		Vector<Literal> dodanaKlauzula = klauzule.get(klauzule.size()-1); // pobieramy ostatni element ( dodany element )
		Vector<Literal> aktualnaKlauzula;
		// bedziemy sprawdzac pozostale wiersze od 0 do przedostatniego czy jest zaprzeczenie jesli jest to koniec
		// jesli przejdzie przez wszystko to oznacza ze nie jest zaprzeczeniem innej klauzuli wiec nie konczy dowodu
		// przez rezolucje i zaprzeczenie
				
		if (dodanaKlauzula.size() != 1)
		{
			return false;		// jesli wiecej niz 1 literal to nie koniec sprawdzania
		}
		
		for (int x = 0; x < klauzule.size() - 1; x++)				// czyli ze mozna sprawdzac czy nie zaprzecza czemus innemu
		{
			if (klauzule.get(x).size() == 1)
			{
				aktualnaKlauzula = klauzule.get(x);
				if ((aktualnaKlauzula.get(0).getZdanie().equals(dodanaKlauzula.get(0).getZdanie())) &&
						(aktualnaKlauzula.get(0).isZnak() != dodanaKlauzula.get(0).isZnak()))
				{
					// oznacza ze znalezlismy 2 klauzule sprzeczne (ostatnia i x)
					return true;
				}
			}
		}
		
		return false;
	}
	
	private void dodajKlauzule (Vector<Literal> nowa)  // Metoda sluzaca do dodania nowej klauzuli do naszego zbioru klazul
	{													// wiadomo ze dodawana klauzula nie wystepuje w bazie
		
		klauzule.addElement(nowa);
		
		return;
	}
	
	// TODO zdaje sie jest OK !!!!!!!!!!!!!!!!!!!!!!!!
	private boolean czyToSamo(Vector<Literal> pierwsza, Vector<Literal> druga) // przechodzenie literal po literale
	{
		Literal literalZPierwszego, literalZDrugiego;
		
		if (pierwsza.size() != druga.size())
		{
			return false;
		}
		
		for (int x = 0; x < pierwsza.size(); x++) // dla kazdego titeralu przechodze w drugiej klauzuli czy nie ma tego samego
		{
			literalZPierwszego = pierwsza.get(x);

			for (int y = 0; y < druga.size(); y++)
			{
				literalZDrugiego = druga.get(y);
			
				if ((literalZPierwszego.isZnak() == literalZDrugiego.isZnak()) && (literalZPierwszego.getZdanie().equals(literalZDrugiego.getZdanie())))
				{
					break;
				}
				else if (y == druga.size() - 1)
				{
					return true;
				}
			}
			
		}
		
		return false;
	}
	
	private boolean sprawdzCzyDodacKlauzule (Vector<Literal> klauzulaDoDodania)
	{
		 // TODO metoda do przechodzenia przez baze naszych klauzul i sprawdza czy taka juz jest i jesli taka
		// jest to wtedy zwracamy false 
		// jesli nie ma to zwracamy true
		
		System.out.println("\nSprawdzam czy dodac klauzule !!!");
		
		for (int licznik = 0; licznik < klauzule.size(); licznik++)
		{
			System.out.println("Przechodze po klauzulach");
			if (czyToSamo(klauzulaDoDodania, klauzule.get(licznik))) // sprawdzenie czy klauzule takie same ( wywolanie od 2 klauzul)
			{
				System.out.println("Znaleziono 2 takie same klauzule");
				return false;
			}
		}
			
		return true;
	}
	
	private void wypisywanie(Vector<Literal> nowy)
	{
		System.out.print ("\nZawartosc nowej klauzuli to: ");
		
		for (int z = 0; z < nowy.size(); z++)
		{
			if (nowy.elementAt(z).isZnak())
			{
				System.out.print(" -");
			}
			else System.out.print (" ");
			
			System.out.print(nowy.elementAt(z).getZdanie());
		}
		
		System.out.println("___________________________");
	}
	
	private void przypiszZawartosciDoKlauzul()
	{
		
                ArrayList<Integer> temp;
                for (int x = 0; x < teza.getKlauzule().size(); x++)
		{
			klauzule.add(teza.getKlauzule().elementAt(x));
                        temp = new ArrayList<Integer>();
                        temp.add(-1);
                        temp.add(-1);
                        identyfikatory_przodkow.add(temp);
                        
		}
		for (int x = 0; x < predykaty.getKlauzule().size(); x++)
		{
			klauzule.add(predykaty.getKlauzule().elementAt(x));
                        temp = new ArrayList<Integer>();
                        temp.add(-1);
                        temp.add(-1);
                        identyfikatory_przodkow.add(temp);
		}
	}
        
        private void usunPowtarzanieAll()
        {
            Vector<Vector<Literal>> temp = new Vector<Vector<Literal>>();
            
            for (int x = 0; x < klauzule.size(); x++)
            {
                
                temp.add(usunPowtarzanieIOdwrotnosci(klauzule.get(x)));
                

            }
            klauzule = temp;

        }
	
	private Vector<Literal> usunPowtarzanieIOdwrotnosci (Vector<Literal> oryginal)
	{
		Vector<Literal> zastepczy = new Vector<Literal>(oryginal);
		
		for (int x = 0; x < zastepczy.size() - 1; x++)
		{
			for (int y = x + 1; y < zastepczy.size(); y++)
			{
				
				Literal lit1 = zastepczy.get(x);
				Literal lit2 = zastepczy.get(y);
				
				if (lit1.getZdanie().equals(lit2.getZdanie()) && lit1.isZnak() == lit2.isZnak())
				{
					zastepczy.remove(y);
				}
			}
		}
		
		for (int x = 0; x < zastepczy.size() - 1; x++)
		{
			for (int y = x + 1 ; y < zastepczy.size() ; y++)
			{
				
				Literal lit1 = zastepczy.get(x);
				Literal lit2 = zastepczy.get(y);
				
				System.out.println(lit1.getZdanie());
				System.out.println(lit2.getZdanie());
				
				if (lit1.getZdanie().equals(lit2.getZdanie()) && lit1.isZnak() != lit2.isZnak())
				{
					zastepczy.remove(x);
					zastepczy.remove(y-1);
				}
			}
		}
		
		return zastepczy;
	}
	
	private Vector<Literal> tworzNowaKlauzule (Vector<Literal> pierwszy, Vector<Literal> drugi)
	{
		
		Vector<Literal> nowy = new Vector<Literal>();
		
		for (int x = 0; x < pierwszy.size(); x++)
		{
			nowy.add(pierwszy.elementAt(x));
		}
		for (int x = 0; x < drugi.size(); x++)
		{
			nowy.add(drugi.elementAt(x));
		}
		
		nowy = usunPowtarzanieIOdwrotnosci(nowy);
				
		return nowy;
	}
	
	private void  wytwarzajNoweKlauzuleNaPodstawieObecnych()
	{
		for (int x = 0; x + 1 < klauzule.size(); x++)
		{
			for (int y = x + 1 ; y < klauzule.size(); y++)
			{
				
				// sprawdzenie czy z x i y da sie stworzyc klauzule
				if (sprawdzCzyDaSieSworzyc(klauzule.get(x), klauzule.get(y)))
				{
					Vector<Literal> nowy = tworzNowaKlauzule(klauzule.get(x), klauzule.get(y));
						
					wypisywanie(nowy);
					
					if (sprawdzCzyDodacKlauzule (nowy) ) // sprawdz czy juz takiej klauzuli nie ma w bazie
					{
						dodajKlauzule(nowy);  //		dodajemy nowa klauzule do bazy
						
                        ArrayList<Integer> temp = new ArrayList<Integer>();
                        temp.add(x);
                        temp.add(y);
                        identyfikatory_przodkow.add(temp);
						
						if (czyKoniec())
                        {
										// sprawdz czy dodana klauzula nie jest zaprzeczeniem innej jesli tak to koniec
							return;
						}
					}
				}
			}
		}
	}
	
	public void Oblicz() throws IllegalAccessException
	{
                teza.setTeza(true);
                predykaty.wypelnijListe();
                teza.wypelnijListe();
                
                przypiszZawartosciDoKlauzul();
                usunPowtarzanieAll();
                for (int x = 0; x  < klauzule.size(); x++)
                {
                    wypisywanie(klauzule.get(x));
                }
                wytwarzajNoweKlauzuleNaPodstawieObecnych();
	}

    public ListaDrzew getPredykaty()
    {
        return predykaty;
    }

    public void setPredykaty(ListaDrzew predykaty)
    {
        this.predykaty = predykaty;
    }

    public ListaDrzew getTeza()
    {
        return teza;
    }

    public void setTeza(ListaDrzew teza)
    {
        this.teza = teza;
    }	
}
