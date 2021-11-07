package Controlador;

import Frame.Inicio;
import Frame.Principal;
import Modelo.Modelo;

public class Lanzador {

	public static void main(String[] args) {

		Controlador miControlador = new Controlador();
		Modelo miModelo = new Modelo();
		Inicio miInicio = new Inicio();
		Principal miPrincipal = new Principal();

		// El controlador conoce al modelo y a la vista
		miControlador.setModelo(miModelo);
		miControlador.setInicio(miInicio);
		miControlador.setPrincipal(miPrincipal);

		// El modelo conoce a las vistas
		miModelo.setInicio(miInicio);
		miModelo.setPrincipal(miPrincipal);

		// Las vistas conocen a controlador y modelo
		miInicio.setModelo(miModelo);
		miInicio.setControlador(miControlador);

		miPrincipal.setModelo(miModelo);
		miPrincipal.setControlador(miControlador);

		//Hacemos visible la primera de las pantallas
		miInicio.setVisible(true);

	}

}
