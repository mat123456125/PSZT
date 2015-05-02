
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
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
public class ZapisListener implements ActionListener{
    
	private WidokPoczatkowy widok;
	
    ZapisListener(WidokPoczatkowy widok)
    {
    	this.widok = widok;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("txt", "txt");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showSaveDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) 
        {
            try {
                String text = widok.getZnaczenia() + "\n%$%\n" + widok.getPredykaty() + "\n%$%\n" + widok.getTeza();

                Path path  = Paths.get(chooser.getSelectedFile().getPath());
                Files.write(path, text.getBytes(), StandardOpenOption.CREATE,StandardOpenOption.TRUNCATE_EXISTING);
               
            } catch (IOException ex) {
                Logger.getLogger(OdczytListener.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
