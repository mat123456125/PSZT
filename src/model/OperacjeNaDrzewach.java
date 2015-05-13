package model;

import java.util.Vector;

public class OperacjeNaDrzewach
{
	private ListaDrzew predykaty;
	private ListaDrzew teza;
	private Vector<WezelDrzewa> klauzule = new Vector<WezelDrzewa>();
	
	public OperacjeNaDrzewach()
	{
		
	}
	
	public void przypiszZawartosciDoKlauzul()
	{
		for (int x = 0; x < teza.getKlauzule().size(); x++)
		{
			klauzule.add(teza.getKlauzule().elementAt(x));
		}
		for (int x = 0; x < predykaty.getKlauzule().size(); x++)
		{
			klauzule.add(teza.getKlauzule().elementAt(x));
		}
	}
	
	public void Oblicz()
	{
		predykaty.wypelnijListe();
		teza.wypelnijListe();
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
