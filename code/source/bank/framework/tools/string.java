package bank.framework.tools;

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
import com.wm.util.*;
import java.io.*;

public final class string

{
	// ---( internal utility methods )---

	final static string _instance = new string();

	static string _newInstance() { return new string(); }

	static string _cast(Object o) { return (string)o; }

	// ---( server methods )---

	public static final void contains (IData pipeline)
	throws ServiceException
	{
		// --- <<IS-START(contains)>> ---
		// @sigtype java 3.5
		// Portage de psa.frmkUtilities.priv.string.java:contains (105 usages en transformer
		// sur le seul perimetre Vindriven). Semantique conservee a l'identique :
		// recherche insensible a la casse, sortie String "true"/"false", et "false" des
		// que l'une des deux entrees est absente ou vide.

		IDataCursor pipelineCursor = pipeline.getCursor();
		String string = IDataUtil.getString(pipelineCursor, "string");
		String search = IDataUtil.getString(pipelineCursor, "search");
		pipelineCursor.destroy();

		boolean bContain = false;
		if (string != null && !string.isEmpty() && search != null && !search.isEmpty()) {
			bContain = string.toLowerCase().contains(search.toLowerCase());
		}

		IDataCursor pipelineCursor_1 = pipeline.getCursor();
		IDataUtil.put(pipelineCursor_1, "value", String.valueOf(bContain));
		pipelineCursor_1.destroy();

		// --- <<IS-END>> ---

                
	}


	// --- <<IS-START-SHARED>> ---

	// --- <<IS-END-SHARED>> ---
}
