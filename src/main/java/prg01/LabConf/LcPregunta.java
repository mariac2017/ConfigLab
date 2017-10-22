package prg01.LabConf;

public class LcPregunta {

		public Integer preTema;
		public Integer preCod;
		public String  prePre;
		public String  preRsp;
		
		// Retorna Tema
		public Integer getpreTema() {
			return preTema;
		}

		// Muestra Tema
		public void setpreTema(Integer preTema) {
			this.preTema = preTema;
		}
		
		// Retorna Codigo de Pregunta
		public Integer getpreCod() {
			return preCod;
		}

		// Muestra Codigo de Pregunta 
		public void setpreCod(Integer preCod) {
			this.preCod = preCod;
		}
		
		// Retorna Pregunta
		public String getprePre() {
			return prePre;
		}
		
		// Muestra Pregunta	
		public void setprePre(String prePre){
			this.prePre = prePre;
		}
		
		// Retorna Respuesta
		public String getpreRsp() {
			return preRsp;
		}
		
		// Muestra Respuesta	
		public void setpreRsp(String preRsp){
			this.preRsp = preRsp;
		}
}
