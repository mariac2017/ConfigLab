package prg01.LabConf;

public class LcPregunta {

		public Integer preTema;
		public Integer preCod;
		public String  prePre;
		
		// Retorna tema
		public Integer getpalaTema() {
			return preTema;
		}

		// Muestra tema
		public void setpreTema(Integer preTema) {
			this.preTema = preTema;
		}
		
		// Retorna Codigo Tema
		public Integer getpreCod() {
			return preCod;
		}

		// Muestra codigo 
		public void setpreCod(Integer preCod) {
			this.preCod = preCod;
		}
		
		// Retorna pregunta
		public String getprePre() {
			return prePre;
		}
		
		// Muestra pregunta	
		public void setprePre(String prePre){
			this.prePre = prePre;
		}
}
