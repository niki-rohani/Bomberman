package particuleObjet;

import com.bombershinobi.Manager.RessourceManager;

public class chidoParticle extends particule {

	
	
	public chidoParticle (int pos, int po, RessourceManager ressource) {
		super (pos, po, ressource.getFile  ("EXPLOSE1"), ressource.getImage ("EXPLOSION1"));
	}
}
