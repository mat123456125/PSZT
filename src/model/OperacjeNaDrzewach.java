package model;

public class OperacjeNaDrzewach
{
	private ListaDrzew predykaty;
	private ListaDrzew teza;
	
	public OperacjeNaDrzewach()
	{
		
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
