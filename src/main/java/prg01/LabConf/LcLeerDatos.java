package prg01.LabConf;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import prg01.LabConf.LcCmpImg;
import prg01.LabConf.LcConex;

import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class LcLeerDatos extends JFrame {

	private static final long serialVersionUID = 1L;
	
	public JPanel contentPane;
	public JLabel lblCodigo;
	public JLabel lblNombre;
	public JLabel lblImagen;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LcLeerDatos frame = new LcLeerDatos();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LcLeerDatos() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
	
		
		//Obtener imagenes
		int fila = 0;
		try {
			LcConex conex = new LcConex();
			Statement estatuto = conex.getConnection().createStatement();
			ResultSet rs = estatuto.executeQuery("SELECT * from tbl_imgs ");
			LcCmpImg campo;
			
			
			//Obtener todos los registros
			 fila = 0;
			 int wCodigo = 0;
			 String wNombre = "";
			 byte[] imagen = null; 
			 
			 while (rs.next()){
					  campo = new LcCmpImg();
					  campo.setImgCod(rs.getInt("imgCod"));
					  campo.setImgNom(rs.getString("imgNom"));
					  campo.setImgImg(rs.getBlob("imgImg"));
					  
					  wCodigo = rs.getInt("imgCod");
					  wNombre = rs.getString("imgNom");
					  imagen = rs.getBytes("imgImg");

					  // Mostrar Codigo 
					  Integer A = new Integer(wCodigo);
					  String  s = A.toString();
					  lblCodigo = new JLabel();
					  lblCodigo.setBackground(Color.WHITE);
					  lblCodigo.setBounds(20, 10, 93, 93);
					  lblCodigo.setText(s);
					  getContentPane().add(lblCodigo);
					  lblCodigo.setLayout(null);
					  
					  // Mostrar Nombre 
					  lblNombre = new JLabel();
					  lblNombre.setBackground(Color.WHITE);
					  lblNombre.setBounds(20, 50, 93, 93);
					  lblNombre.setText(wNombre);
					  getContentPane().add(lblNombre);
					  lblNombre.setLayout(null);
					  
					  // Mostrar Imagen					  
					  lblImagen = new JLabel();
					  lblImagen.setBackground(Color.WHITE);
					  lblImagen.setBounds(20, 100, 93, 93);
					  ImageIcon icoDado1 = new ImageIcon(imagen);
					  lblImagen.setIcon(icoDado1);
					  getContentPane().add(lblImagen);
					  lblImagen.setLayout(null);
					  
					  fila = fila + 1;
					  
					  System.out.println("wCodigo " + wCodigo);
					  System.out.println("wNombre " + wNombre);
					  
			 }
			 rs.close();
			 estatuto.close();
			 conex.desconectar();
		}
		
		catch (SQLException e){
			   System.out.println(e.getMessage());
			   JOptionPane.showMessageDialog(null, "Consulta Fallido");			
		}
	
	}

}

	
