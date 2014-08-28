package UI.Desktop;

import javax.swing.JDialog;

public class defaultDialog extends JDialog{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public enum resultado { Completado, Cancelado, Error}
	private resultado result;
	
	public resultado getResultado() {
		return result;
	}

	public void setResultado(resultado resultado) {
		this.result = resultado;
	}
}
