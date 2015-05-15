import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class PowrotListener implements ActionListener
{
	private OknoProgramu widok;
	
	 public void actionPerformed(ActionEvent e)
	 {
		 widok.ustawWidokPoczatkowy();
	 }
}
