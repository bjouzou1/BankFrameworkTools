package bank.framework.tools;

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
import com.wm.util.*;
import java.io.*;

public final class date

{
	// ---( internal utility methods )---

	final static date _instance = new date();

	static date _newInstance() { return new date(); }

	static date _cast(Object o) { return (date)o; }

	// ---( server methods )---

	public static final void currentTimeMillis (IData pipeline)
	throws ServiceException
	{
		// --- <<IS-START(currentTimeMillis)>> ---
		// @sigtype java 3.5
		// Portage de psa.frmkUtilities.priv.date.java:getCurrentTimestamp
		// (new Date().getTime()). Renvoie l'epoch courant en millisecondes sous forme
		// d'objet java.lang.Long, sous le champ de sortie "timestamp" (nom conserve a
		// l'identique de l'on-premise pour que les MAPCOPY existants restent valides).
		// Equivalent fidele et reutilisable : aucun built-in io ne fournit un epoch ms
		// (pub.date:currentNanoTime est un compteur relatif System.nanoTime(), pas un
		// epoch ; pub.date:getCurrentDateString renvoie une chaine formatee).

		long nowMs = System.currentTimeMillis();

		IDataCursor pipelineCursor = pipeline.getCursor();
		IDataUtil.put(pipelineCursor, "timestamp", Long.valueOf(nowMs));
		pipelineCursor.destroy();

		// --- <<IS-END>> ---

                
	}

	public static final void elapsedMillis (IData pipeline)
	throws ServiceException
	{
		// --- <<IS-START(elapsedMillis)>> ---
		// @sigtype java 3.5
		// Portage de psa.frmkUtilities.priv.date:getElapsedMilliTime.
		// Calcule le temps ecoule (en MILLISECONDES) depuis un instant de depart fourni
		// en nanosecondes. Reproduit fidelement la semantique on-premise : entree
		// "startNanoTime" (java.lang.Long, nanosecondes issues de pub.date:currentNanoTime),
		// sortie "elapsedMilliTime" de type STRING (millisecondes), ISO on-prem :
		// le getElapsedMilliTime on-prem renvoyait un string et le flow aval traite
		// processDuration comme du texte (concatene dans la liste de log). Emettre un
		// Long provoquait un conflit de metadonnees dans io ("mappings not displayed
		// correctly", elapsedMilliTime object vs MAPCOPY ;1;0 string). Noms conserves
		// a l'identique pour que les MAPCOPY existants restent valides sans reecriture.
		// Un built-in io (pub.date:elapsedNanoTime) existe mais renvoie des nanosecondes ;
		// on encapsule ici la conversion ns->ms pour rester en millisecondes comme l'on-prem.

		IDataCursor pipelineCursor = pipeline.getCursor();
		Object startObj = IDataUtil.get(pipelineCursor, "startNanoTime");
		pipelineCursor.destroy();

		long elapsedMs = 0L;
		if (startObj != null) {
			long startNano;
			if (startObj instanceof Number) {
				startNano = ((Number) startObj).longValue();
			} else {
				startNano = Long.parseLong(startObj.toString());
			}
			long elapsedNano = System.nanoTime() - startNano;
			if (elapsedNano < 0L) {
				elapsedNano = 0L;
			}
			elapsedMs = elapsedNano / 1000000L;
		}

		IDataCursor pipelineCursor_1 = pipeline.getCursor();
		IDataUtil.put(pipelineCursor_1, "elapsedMilliTime", String.valueOf(elapsedMs));
		pipelineCursor_1.destroy();

		// --- <<IS-END>> ---

                
	}


	// --- <<IS-START-SHARED>> ---

	// --- <<IS-END-SHARED>> ---
}
