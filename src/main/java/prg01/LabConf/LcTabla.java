package prg01.LabConf;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumn;
import javax.swing.ImageIcon;

import java.awt.Color;
import java.awt.Point;

public class LcTabla extends JFrame {

	private JPanel Tablero;
	private boolean ALLOW_ROW_SELECTION = true;
	private boolean ALLOW_COLUMN_SELECTION = true;
	
    // variables del tema 
	public Object[][] data = new Object[100][2];
	public JPanel panTema;
	public JTable tableTema;
	public JTextField txtTema;
	public JButton btnTema;
	
	public int wfila = 0;
	public String wNombre = "";
	
	//variables de preguntas
	public Object[][] dataWord = new Object[500][4];
	public JPanel panWord;
	public JTable tableWord;
	public JTextField txtWord;
	public JTextField txtResp;
	public JButton btnWord;	
	
	public int wfilaWord = 0;
	public int wColuWord = 0;
	public int wCeldaWord = 0;
	
	public String wWord = "";
	public String wPreg = "";
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LcTabla frame = new LcTabla();
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
	public LcTabla() {
		
		super("SC_Registro");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 630);
		setTitle("Mantenimiento Temas");
		Tablero = new JPanel();
		Tablero.setBorder(new EmptyBorder(5, 5, 5, 5));
		Tablero.setLayout(null);
		setContentPane(Tablero);

    	CrearTextos();
        CrearBotones();    	
    	CargarDatosTema();
    	CrearJTableTema();
	}
	
    public void CrearTextos() {

    	JLabel lblTema = new JLabel();
    	lblTema.setText("Tema ");
    	lblTema.setBounds(15, 11, 36, 34);
   		Tablero.add(lblTema);

    	txtTema = new JTextField();
    	txtTema.setSize(200, 34);
    	txtTema.setLocation(new Point(65, 11));
    	txtTema.setBackground(Color.YELLOW);
		Tablero.add(txtTema);

    	txtWord = new JTextField();
    	txtWord.setSize(201, 34);
    	txtWord.setLocation(new Point(326, 11));
    	txtWord.setBackground(Color.YELLOW);
		Tablero.add(txtWord);
		
    	txtResp = new JTextField();
    	txtResp.setSize(200, 34);
    	txtResp.setLocation(new Point(531, 11));
    	txtResp.setBackground(Color.YELLOW);
		Tablero.add(txtResp);
	}

	public void CrearBotones() {
		
		//Obtener imagenes
		int fila = 11;
		int wCodigo = 0;
    	byte[] imagen = null;
    	
		try {
			  LcConex conex = new LcConex();
			  Statement estatuto = conex.getConnection().createStatement();
			  ResultSet rs = estatuto.executeQuery("SELECT * from tbl_imgs WHERE imgCod = " + fila);
			  LcCmpImg wCampo;
			 
			  if(rs.next()); {
				  wCampo = new LcCmpImg();
				  wCodigo = rs.getInt("imgCod");
				  System.out.println("wCodigo " + wCodigo);
			  
				  wCampo.setImgImg(rs.getBlob("imgImg"));
				  imagen = rs.getBytes("imgImg");
    		  
				  // Mostrar Imagen					  
				  btnTema = new JButton();
				  btnTema.setBackground(Color.WHITE);
				  btnTema.setBounds(270, 6, 40, 40);
				  ImageIcon icoImag = new ImageIcon(imagen);
				  // ajusta imagen al tamaño 
				  ImageIcon icono = new ImageIcon(icoImag.getImage().getScaledInstance(btnTema.getWidth(), btnTema.getHeight(), Image.SCALE_DEFAULT));
				  btnTema.setIcon(icono);
				  btnTema.setLayout(null);
				  Tablero.add(btnTema);
				  Tablero.repaint();
			  				  
				  // Acción de Ir a Mostrar Ficha
				  btnTema.addActionListener(new ActionListener() {
					  public void actionPerformed(ActionEvent arg0) {
						   GuardarDatoTema();
					  }
				  });
				  
				  //Boton de Guardar Pregunta y Respuesta
				  btnWord = new JButton();
				  btnWord.setBackground(Color.WHITE);
				  btnWord.setBounds(735, 6, 40, 40);
				  btnWord.setIcon(icono);
				  btnWord.setLayout(null);
				  Tablero.add(btnWord);
				  Tablero.repaint();
					
				  btnWord.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							   GuardarDatoWord();
						}
				  });
			  }	  
			  rs.close();
			  estatuto.close();
			  conex.desconectar();
		}
		
		catch (SQLException e){
			   System.out.println(e.getMessage());
			   JOptionPane.showMessageDialog(null, "Error linea 143");			
		}
	
	}

  	public void CrearJTableTema() {
		
		//Crear Tema
		panTema = new JPanel();
		panTema.setBackground(Color.BLACK);
		panTema.setBounds(10, 50, 300, 535);
	    Tablero.add(panTema);
	
	 	String[] columnNames = {"Codigo", "Tema"};
        final JTable tableTema = new JTable(data, columnNames);
        tableTema.setPreferredScrollableViewportSize(new Dimension(270, 500));

	    //Crear Dimensiones de la tabla
        //Alto de Filas
	    tableTema.setRowHeight(25);
	    //Ancho de Columnas
        tableTema.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);   
   		TableColumn col_0 = null;
   		col_0 = tableTema.getColumnModel().getColumn(0);
   		col_0.setPreferredWidth(50);
   		TableColumn col_1 = null;
   		col_1 = tableTema.getColumnModel().getColumn(1);
   		col_1.setPreferredWidth(200);
 
        //Crear scroll de la tabla 
        JScrollPane scrollPane = new JScrollPane(tableTema);
        panTema.add(scrollPane);

        //Add the scroll pane to this window. SE TOMA TODA LA PANTALLA
//            setContentPane(scrollPane);
//            pack();
//            setVisible(true);
           
        // Selecciona Fila
        tableTema.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        if (ALLOW_ROW_SELECTION) { // true by default
            ListSelectionModel rowSM = tableTema.getSelectionModel();
            rowSM.addListSelectionListener(new ListSelectionListener() {
                public void valueChanged(ListSelectionEvent e) {
                    ListSelectionModel lsm = (ListSelectionModel)e.getSource();
                    //Si selecciona fila
                    if (lsm.isSelectionEmpty()) {
                        System.out.println("No rows are selected.");
                    } else {
                    	wfila = lsm.getMinSelectionIndex();
                      	SelectTema();
                    	CargarDatosWord();
                    	CrearJTableWord();
                    }
                }
            });
        } else {
            tableTema.setRowSelectionAllowed(false);
        }

	}

	public void CargarDatosTema() {

			int fila = 0; 
			try {
				 LcConex conex = new LcConex();
		       	 Statement estatuto = conex.getConnection().createStatement();
				 ResultSet rs = estatuto.executeQuery("SELECT * from tbl_tema ");
				 LcTema tema;
				 
				 	//Obtener todos los registros
				    fila = 0;
					while (rs.next()){
						  tema = new LcTema();
						  tema.settemaCod(Integer.parseInt(rs.getString("temaCod")));
						  tema.settemaNom(rs.getString("temaNom"));
						  
						  data[fila][0] = rs.getInt("temaCod");
						  data[fila][1] = rs.getString("temaNom");
//						  data[fila][2] = new Boolean(false);
						  fila = fila + 1;
					}
					rs.close();
					estatuto.close();
					conex.desconectar();
			}	
			catch (SQLException e){
				   System.out.println(e.getMessage());
				   JOptionPane.showMessageDialog(null, "Error Linea 278");			
			}
	}
	
	
	public void SelectTema() {

		try {
			 wfila = wfila + 1;  // se suma ya que la tabla comienza en 0 y los datos en 1
			
			 LcConex conex = new LcConex();
	       	 Statement estatuto = conex.getConnection().createStatement();
			 ResultSet rs = estatuto.executeQuery("SELECT * FROM tbl_tema WHERE temaCod = " + wfila);
			 LcTema tema;
			  
			 	//Obtener todos los registros
     			if(rs.next()); {
     				tema = new LcTema();
     				tema.settemaCod(Integer.parseInt(rs.getString("temaCod")));
     				tema.settemaNom(rs.getString("temaNom"));
  					  
     				wNombre = rs.getString("temaNom");
     				txtTema.setText(wNombre);
     			}	  
		 }
		
		catch (SQLException e){
			   System.out.println(e.getMessage());
			   JOptionPane.showMessageDialog(null, "Consulta Fallido");			
		}
		
	}
	
	public void GuardarDatoTema() {
	
		 wNombre = txtTema.getText();
		 String sql = "UPDATE tbl_tema SET temaNom = " + "'" + wNombre + "'" + " WHERE temaCod = " + wfila;

		 		// Almacenar Registro 
				try {
					LcConex conex = new LcConex();
					Statement estatuto = conex.getConnection().createStatement();
					estatuto.executeUpdate(sql);
					CargarDatosTema();
					Tablero.repaint();
				} 
		
				catch (SQLException e){
					   System.out.println(e.getMessage());
					   JOptionPane.showMessageDialog(null, "UPDATE Fallido");
				}
	}
		 


// *************************************  PALABRAS ********************************


	public void CargarDatosWord() {

			int fila = 0; 
			int colu = 0;
			try {
				
				 LcConex conex = new LcConex();
		       	 Statement estatuto = conex.getConnection().createStatement();
		       	 ResultSet rs = estatuto.executeQuery("SELECT * from tbl_preg WHERE preTema = " + wfila);
		       	 LcPregunta preg;
		       	 LcPregunta resp;
		       	 
				 	//Obtener todos los registros
				    fila = 0; colu = -1;
				   	while (rs.next()){
						  preg = new LcPregunta();
						  preg.setprePre(rs.getString("prePre"));
						  fila = rs.getInt("preCod") - 1;
						  
						  dataWord[fila][0] = rs.getString("prePre");
						  dataWord[fila][1] = rs.getString("preRsp");
				   	}  					
			
					rs.close();
					estatuto.close();
					conex.desconectar();
			}	
			catch (SQLException e){
				   System.out.println(e.getMessage());
				   JOptionPane.showMessageDialog(null, " Falla line 323");			
			}
	}

	public void CrearJTableWord() {
	
		//Crear Preguntas y Respuestas
		setBounds(100, 100, 798, 630);
		panWord = new JPanel();
		panWord.setBackground(Color.BLACK);
		panWord.setBounds(325, 50, 450, 535);
		Tablero.add(panWord);
		Tablero.repaint();
		

		String[] columnNames = {"Pregunta", "Respuesta"};
		final JTable tableWord = new JTable(dataWord, columnNames);
		tableWord.setPreferredScrollableViewportSize(new Dimension(420, 500));

		//Crear Dimensiones de la tabla
		//Alto de Filas
		tableWord.setRowHeight(25);
		//Ancho de Columnas
		tableWord.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);   
    	TableColumn col_0 = null;
		col_0 = tableWord.getColumnModel().getColumn(0);
		col_0.setPreferredWidth(200);
		TableColumn col_1 = null;
		col_1 = tableWord.getColumnModel().getColumn(1);
		col_1.setPreferredWidth(200);
		
		//Crear scroll de la tabla 
		JScrollPane scrollPaneWord = new JScrollPane(tableWord);
		panWord.add(scrollPaneWord);
		
		Tablero.repaint();
		panWord.repaint();
		tableWord.repaint();
		
		// Selecciona Fila
		tableWord.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		if (ALLOW_ROW_SELECTION) { // true by default
			ListSelectionModel rowWord = tableWord.getSelectionModel();
			rowWord.addListSelectionListener(new ListSelectionListener() {
				public void valueChanged(ListSelectionEvent e) {
					ListSelectionModel lsmWord = (ListSelectionModel)e.getSource();
					//Si selecciona fila
					if (lsmWord.isSelectionEmpty()) {
						System.out.println("No rows are selected.");
					} else {
						wfilaWord = lsmWord.getMinSelectionIndex();
					}
				}
			});
		} else {
			tableWord.setRowSelectionAllowed(false);
		}
		
	    if (ALLOW_COLUMN_SELECTION) { // false by default
            if (ALLOW_ROW_SELECTION) {
            	tableWord.setCellSelectionEnabled(true);
            } 
            tableWord.setColumnSelectionAllowed(true);
            ListSelectionModel colSM =
       		tableWord.getColumnModel().getSelectionModel();
            colSM.addListSelectionListener(new ListSelectionListener() {
                public void valueChanged(ListSelectionEvent e) {
                    ListSelectionModel lsm = (ListSelectionModel)e.getSource();
                    if (lsm.isSelectionEmpty()) {
                        System.out.println("No columns are selected.");
                    } else {
                    	wColuWord = lsm.getMinSelectionIndex();
                        wColuWord = wColuWord + 1;
						SelecWord();
                    }
                }
            });
        }
 	}

	
	public void SelecWord() {

		try {
			 
			 wCeldaWord = wfilaWord + 1; 
			 System.out.println("wCeldaWord " + wCeldaWord);
			 String wCelda = wfila + " and preCod = " + wCeldaWord;  
		 
			 LcConex conex = new LcConex();
	       	 Statement estatuto = conex.getConnection().createStatement();
	       	 String Sql = "SELECT * FROM tbl_preg WHERE preTema = " + wCelda;
			 ResultSet rs = estatuto.executeQuery(Sql);
			 LcPregunta word;
     			
			 	//Obtener todos los registros
   				if (rs.next()){
    				word = new LcPregunta();
    				word.setpreCod(Integer.parseInt(rs.getString("preCod")));
    				word.setprePre(rs.getString("prePre"));
    				word.setpreRsp(rs.getString("preRsp"));
    				
    				wWord = rs.getString("prePre");
    				wPreg = rs.getString("preRsp");
     				txtWord.setText(wWord);
     				txtResp.setText(wPreg);
   				}
				rs.close();
				estatuto.close();
				conex.desconectar();
		 }
		
		catch (SQLException e){
			   System.out.println(e.getMessage());
			   JOptionPane.showMessageDialog(null, " Falla line 480");			
		}
		
	}
	
	public void GuardarDatoWord() {
		
		wWord = txtWord.getText();
		wPreg = txtResp.getText();
		String wCelda = wfila + " and preCod = " + wCeldaWord;
		String Sql = "UPDATE tbl_preg SET prePre = " + "'" + wWord + "'" + "," + "preRsp =" + "'" + wPreg + "'" + " WHERE preTema = " + wCelda;
		
		 		// Almacenar Registro 
				try {
					LcConex conex = new LcConex();
					Statement estatuto = conex.getConnection().createStatement();
					estatuto.executeUpdate(Sql);
					CargarDatosWord();
					Tablero.repaint();
				} 
		
				catch (SQLException e){
					   System.out.println(e.getMessage());
					   JOptionPane.showMessageDialog(null, "Error linea 503");
				}
	}
}