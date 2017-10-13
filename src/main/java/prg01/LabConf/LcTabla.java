package prg01.LabConf;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
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

import java.awt.Color;
import java.awt.Point;
import java.awt.Component;

public class LcTabla extends JFrame {

	private JPanel Tablero;
	private boolean ALLOW_ROW_SELECTION = true;
	private boolean ALLOW_COLUMN_SELECTION = true;
	
    // variables del tema 
	public Object[][] data = new Object[100][3];
	public JPanel panTema;
	public JTable tableTema;
	public JTextField txtTema;
	public JButton btnTema;
	
	public int wfila = 0;
	public String wNombre = "";
	
	//variables de palabras
	public Object[][] dataWord = new Object[21][6];
	public JPanel panWord;
	public JTable tableWord;
	public JLabel lblWord;
	public JTextField txtWord;
	public JButton btnWord;	
	
	public int wfilaWord = 0;
	public int wColuWord = 0;
	public int wCeldaWord = 0;
	
	public String wWord = "";
	
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
		setBounds(100, 100, 920, 630);
		setTitle("Mantenimiento Temas");
		Tablero = new JPanel();
		Tablero.setBorder(new EmptyBorder(5, 5, 5, 5));
		Tablero.setLayout(null);
		setContentPane(Tablero);

    	CrearComandos();		
    	CargarDatosTema();
    	CrearJTableTema();
	}

    public void CrearComandos() {
    	
     	JLabel lblTema = new JLabel();
    	lblTema.setText("Tema ");
    	lblTema.setBounds(10, 11, 36, 34);
   		Tablero.add(lblTema);

    	txtTema = new JTextField();
    	txtTema.setSize(194, 34);
    	txtTema.setLocation(new Point(56, 11));
    	txtTema.setBackground(Color.YELLOW);
		Tablero.add(txtTema);
		
		btnTema = new JButton();
		btnTema.setText("Guardar");
		btnTema.setBounds(290, 12, 90, 32);
		btnTema.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Tablero.add(btnTema, 1);
		
		btnTema.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				   GuardarDatoTema();
			}
		});

		JLabel lblWord = new JLabel();
     	lblWord.setText("Palabra ");
     	lblWord.setBounds(503, 11, 49, 34);
   		Tablero.add(lblWord);
		
    	txtWord = new JTextField();
    	txtWord.setSize(194, 34);
    	txtWord.setLocation(new Point(557, 11));
    	txtWord.setBackground(Color.YELLOW);
		Tablero.add(txtWord);
		
		btnWord = new JButton();
		btnWord.setText("Guardar");
		btnWord.setBounds(780, 12, 90, 32);
		btnWord.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Tablero.add(btnWord, 1);
		
		btnWord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				   GuardarDatoWord();
			}
		});

	}

  	public void CrearJTableTema() {
		
		//Crear Tema
		panTema = new JPanel();
		panTema.setBackground(Color.BLACK);
		panTema.setBounds(10, 50, 374, 535);
	    Tablero.add(panTema);
	
	 	String[] columnNames = {"Codigo", "Tema", "Actualizar"};
        final JTable tableTema = new JTable(data, columnNames);
        tableTema.setPreferredScrollableViewportSize(new Dimension(350, 500));

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
   		TableColumn col_2 = null;
   		col_2 = tableTema.getColumnModel().getColumn(2);
   		col_2.setPreferredWidth(60);
 
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
						  data[fila][2] = new Boolean(false);
						  fila = fila + 1;
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
//					JOptionPane.showMessageDialog(null, "UPDATE Exitoso");
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
		       	 ResultSet rs = estatuto.executeQuery("SELECT * from tbl_pregunta WHERE preTema = " + wfila);
		       	 LcPregunta word;
				 
				 	//Obtener todos los registros
				    fila = 0; colu = -1;
				   	while (rs.next()){
						  word = new LcPregunta();
						  word.setprePre(rs.getString("prePre"));

						  if (colu <= 3 ) {
						      colu = colu  + 1;
						     } else {
							  fila = fila + 1;
							  colu = 0;
						  }
						  
						  dataWord[fila][colu] = rs.getString("prePre");
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

	public void CrearJTableWord() {
	
		//Crear Tema
		panWord = new JPanel();
		panWord.setBackground(Color.BLACK);
		panWord.setBounds(395, 50, 480, 535);
		Tablero.add(panWord);
		setBounds(100, 100, 918, 630);
		Tablero.repaint();
		

		String[] columnNames = {"Col. 1", "Col. 2", "Col. 3", "Col. 4", "Col. 5"};
		final JTable tableWord = new JTable(dataWord, columnNames);
		tableWord.setPreferredScrollableViewportSize(new Dimension(450, 500));

		//Crear Dimensiones de la tabla
		//Alto de Filas
		tableWord.setRowHeight(25);
		//Ancho de Columnas
		tableWord.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);   
    	TableColumn col_0 = null;
		col_0 = tableWord.getColumnModel().getColumn(0);
		col_0.setPreferredWidth(90);
		TableColumn col_1 = null;
		col_1 = tableWord.getColumnModel().getColumn(1);
		col_1.setPreferredWidth(90);
		TableColumn col_2 = null;
		col_2 = tableWord.getColumnModel().getColumn(2);
		col_2.setPreferredWidth(90);
		TableColumn col_3 = null;
		col_2 = tableWord.getColumnModel().getColumn(3);
		col_2.setPreferredWidth(90);
		TableColumn col_4 = null;
		col_2 = tableWord.getColumnModel().getColumn(4);
		col_2.setPreferredWidth(90);
		
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
			 
			 wCeldaWord = (wfilaWord * 5) + wColuWord;
			 
			 System.out.println("wCeldaWord " + wCeldaWord);
			 String wCelda = wfila + " and preCod = " + wCeldaWord;  
		 
			 LcConex conex = new LcConex();
	       	 Statement estatuto = conex.getConnection().createStatement();
	       	 String Sql = "SELECT * FROM tbl_pregunta WHERE preTema = " + wCelda;
			 ResultSet rs = estatuto.executeQuery(Sql);
			 LcPregunta word;
     			
			 	//Obtener todos los registros
   				if (rs.next()){
    				word = new LcPregunta();
    				word.setpreCod(Integer.parseInt(rs.getString("preCod")));
    				word.setprePre(rs.getString("prePre"));
					 
    				wWord = rs.getString("prePre");
     				txtWord.setText(wWord);
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
	
	public void GuardarDatoWord() {
	
		wWord = txtWord.getText();
		String wCelda = wfila + " and preCod = " + wCeldaWord;
		String Sql = "UPDATE tbl_pregunta SET prePre = " + "'" + wWord + "'" + " WHERE preTema = " + wCelda;
		
		 		// Almacenar Registro 
				try {
					LcConex conex = new LcConex();
					Statement estatuto = conex.getConnection().createStatement();
					estatuto.executeUpdate(Sql);
//					JOptionPane.showMessageDialog(null, "UPDATE Exitoso");
					CargarDatosWord();
					Tablero.repaint();
					
					
				} 
		
				catch (SQLException e){
					   System.out.println(e.getMessage());
					   JOptionPane.showMessageDialog(null, "UPDATE Fallido");
				}
	}
		 
	
}