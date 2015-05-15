import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class PowrotListener implements ActionListener
{
	private OknoProgramu widok;
	
	PowrotListener(OknoProgramu widok)
	{
		this.widok = widok;
	}
	
	 public void actionPerformed(ActionEvent e)
	 {
		 System.out.println("\n");
		 System.out.println("a");
		 widok.ustawWidokPoczatkowy();
	 }
}
