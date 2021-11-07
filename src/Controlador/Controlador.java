package Controlador;

import Frame.Inicio;
import Frame.Principal;
import Modelo.Modelo;

public class Controlador {
	private Modelo modelito;
	private Inicio miInicio = new Inicio();
	private Principal miPrincipal = new Principal();

	public Controlador() {
		modelito = new Modelo();
	}

	//Cambia ventana
	public void changeWindow() {
		this.miInicio.setVisible(false);
		this.miPrincipal.setVisible(true);
	}

	//Borra info
	public void deleteInfo() {
		modelito.delete(miPrincipal.getTable(), miPrincipal.getModelito());
	}

	//Añade info
	public void addInfo(String txtNombrePiloto, String txtNombreEscuderia) {
		modelito.add(txtNombrePiloto, txtNombreEscuderia);
	}

	//Edita info
	public void editInfo(String txtNombrePiloto, String txtNombreEscuderia) {
		modelito.edit(txtNombrePiloto, txtNombreEscuderia);
	}

	public void setInicio(Inicio miInicio) {
		this.miInicio = miInicio;
	}

	public void setPrincipal(Principal miPrincipal) {
		this.miPrincipal = miPrincipal;
	}

	public void setModelo(Modelo miModelo) {
		this.modelito = miModelo;
	}

}
