package prg01.LabConf;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.EventQueue;
import javax.swing.JFrame;

public class mainLcConf extends JFrame {
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainLcConf frame = new mainLcConf();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public mainLcConf() {
		LcTabla miTabla;
		miTabla = new LcTabla();
		miTabla.setVisible(true);
	}
}
