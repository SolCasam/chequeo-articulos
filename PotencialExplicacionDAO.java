package estructuraTP.DAO;

import java.util.ArrayList;
import java.util.HashMap;

import estructuraTP.modelo.Chequeo;

public class PotencialExplicacionDAO {

	String timezone = "&serverTimezone=UTC";

	public HashMap<String, ArrayList<Chequeo>> potencialesExplicaciones(String categoria) {
		ChequeoDAO cDAO = new ChequeoDAO();
		ArrayList<Chequeo> chequeos = cDAO.todosLosChequeosxCategoria(categoria);
		HashMap<String, ArrayList<Chequeo>> potencialesExplicaciones = new HashMap<>();
		for (Chequeo c : chequeos) {
			String link = c.getEnlace();
			if (!potencialesExplicaciones.containsKey(link)) {
				potencialesExplicaciones.put(link, new ArrayList<Chequeo>());
				potencialesExplicaciones.get(link).add(c);
			}else {
				potencialesExplicaciones.get(link).add(c);
			}
		}
		return potencialesExplicaciones;
	}

}
