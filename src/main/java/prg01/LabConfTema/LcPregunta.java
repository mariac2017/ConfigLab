package prg01.LabConfTema;

public class LcPregunta {

		public Integer preTema;
		public Integer preCod;
		public String  prePre;
		public String  preRsp;
		
		// ********************************** Tema
		
		// Retorna Tema
		public Integer getpreTema() {
			return preTema;
		}

		// Muestra Tema
		public void setPreTema(Integer preTema) {
			this.preTema = preTema;
		}
		
		// ********************************** Nro de Pregunta
		
		// Retorna Codigo de Pregunta
		public Integer getPreCod() {
			return preCod;
		}

		// Muestra Codigo de Pregunta 
		public void setPreCod(Integer preCod) {
			this.preCod = preCod;
		}
		
		// ********************************** Pregunta
		
		// Retorna Pregunta
		public String getPrePre() {
			return prePre;
		}
		
		// Muestra Pregunta	
		public void setPrePre(String prePre){
			this.prePre = prePre;
		}
		
		// ********************************** Respuesta
		
		// Retorna Respuesta
		public String getPreRsp() {
			return preRsp;
		}
		
		// Muestra Respuesta	
		public void setPreRsp(String preRsp){
			this.preRsp = preRsp;
		}
}
