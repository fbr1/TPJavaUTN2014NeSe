package UI.Desktop;

import javax.swing.JFrame;

public class defaultJFrame extends JFrame{
	
	public enum resultado { Completado, Cancelado, Error}
	private resultado result;
	
	public resultado getResultado() {
		return result;
	}

	public void setResultado(resultado resultado) {
		this.result = resultado;
	}
}
