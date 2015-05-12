import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import model.ListaDrzew;

public class ObliczListener implements ActionListener{

	private OknoProgramu widok;
//	private WidokPoczatkowy widok;
//	private WidokKoncowy koniec;
	
    ObliczListener(OknoProgramu widok)
    {
		this.widok = widok;
    }

        @Override
    public void actionPerformed(ActionEvent e) {
    	String predykaty = widok.getPredykaty();
        
    	
    	ListaDrzew lista = widok.getLista();
        lista.setPredykaty(predykaty);
    	
    	lista.wypelnijListe();
    	
    	widok.ustawKoniec();
    }
    
}