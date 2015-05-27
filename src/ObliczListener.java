import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

import model.OperacjeNaDrzewach;
import model.ListaDrzew;

public class ObliczListener implements ActionListener{

	private OknoProgramu widok;
	private OperacjeNaDrzewach operacje;// = new OperacjeNaDrzewach();
//	private WidokPoczatkowy widok;
//	private WidokKoncowy koniec;
	
    ObliczListener(OknoProgramu widok)
    {
		this.widok = widok;
    }
    
    @Override
    
    public void actionPerformed(ActionEvent e) {
    	
    	operacje = new OperacjeNaDrzewach();
    	
    	
    	String predykaty = widok.getPredykaty();
        
    	
        operacje.getPredykaty().setText(predykaty);
        operacje.getTeza().setText(widok.getTeza());
            try
            {
                operacje.Oblicz();
            } catch (IllegalAccessException ex)
            {
                Logger.getLogger(ObliczListener.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    	
    	
        // TODO dorobic by brana byla pod uwage teza
    	
    	widok.ustawDrzewa(operacje.getPredykaty().getPredykaty());
    	
    	widok.ustawKoniec(operacje);
        if(operacje.getCzyUdowodniono())
        {
            JOptionPane.showMessageDialog(null,"Udalo sie udowodnic teze", "Wynik", JOptionPane.PLAIN_MESSAGE);
        }
        else 
        {
            JOptionPane.showMessageDialog(null,"Nie udalo sie udowosnic tezy", "Wynik", JOptionPane.PLAIN_MESSAGE);
        }
    }
    
}