package prg01.LabConfTema;

import java.sql.Blob;

public class LcCmpJug {
	public Integer jugCod;
	public String  jugNom;
	public Blob    jugImg; 

	// ********************* CODIGO ************
	
	// Retorna Codigo
	public Integer getImgCod() {
		return jugCod;
	}

	// Muestra Codigo
	public void setImgCod(Integer jugCod) {
		this.jugCod = jugCod;
	}
	
	// ********************* NOMBRE  **********
	
	// Retorna nombre
	public String getImgNom() {
		return jugNom;
	}
	
	// Muestra nombre	
	public void setImgNom(String jugNom){
		this.jugNom = jugNom;
	}
	
	
	// ********************* IMAGEN  **********
	
	// Retorna imagen
	public Blob getImgImg() {
		return jugImg;
    }
	
	// Muestra imagen	
	public void setImgImg(Blob jugImg){
		this.jugImg = jugImg;
	}
}
