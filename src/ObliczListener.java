import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import model.OperacjeNaDrzewach;
import model.ListaDrzew;

public class ObliczListener implements ActionListener{

	private OknoProgramu widok;
	private OperacjeNaDrzewach operacje = new OperacjeNaDrzewach();
//	private WidokPoczatkowy widok;
//	private WidokKoncowy koniec;
	
    ObliczListener(OknoProgramu widok)
    {
		this.widok = widok;
    }
    
    @Override
    
    public void actionPerformed(ActionEvent e) {
    	
    	
    	String predykaty = widok.getPredykaty();
        
    	
        operacje.getPredykaty().setText(predykaty);
        operacje.getTeza().setText(widok.getTeza());
        operacje.Oblicz();
        
    	
    	
        // TODO dorobic by brana byla pod uwage teza
    	
    	widok.ustawDrzewa(operacje.getPredykaty().getKlauzule());
    	
    	widok.ustawKoniec();
    }
    
}