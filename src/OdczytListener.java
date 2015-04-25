
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mateusz
 */
public class OdczytListener implements ActionListener{

	private WidokPoczatkowy widok;
	
    OdczytListener(WidokPoczatkowy widok)
    {
    	this.widok = widok;
    }
	
    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("txt", "txt");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(null);
        String linia = new String();
        
        String SymboleZdan = new String();
        String Predykaty = new String();
        String Teza = new String();
        
        int stan = 0;
        if(returnVal == JFileChooser.APPROVE_OPTION)
        {
            try {
                Path path  = Paths.get(chooser.getSelectedFile().getPath());
                String text = new String(Files.readAllBytes(path));
                BufferedReader reader = new BufferedReader(new StringReader(text));
                while((linia = reader.readLine()) != null)
                {
                	if (linia.charAt(0) == '%' && linia.charAt(1) == '$' && linia.charAt(2) == '%')
                	{
                		stan++;
                	}
                	else
                	{
                		if (stan == 0)
                		{
                			SymboleZdan += linia;
                		}
                		else if (stan == 1)
                		{
                			Predykaty += linia;
                		}
                		else
                		{
                			Teza += linia;
                		}
                	}
                }
                widok.setZnaczenia(SymboleZdan);
                widok.setPredykaty(Predykaty);
                widok.setTeza(Teza);
                
            } catch (IOException ex) {
                Logger.getLogger(OdczytListener.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
