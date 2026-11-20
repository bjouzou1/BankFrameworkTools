package bank.framework.tools;

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;

public final class document

{
	// ---( internal utility methods )---

	final static document _instance = new document();

	static document _newInstance() { return new document(); }

	static document _cast(Object o) { return (document)o; }

	// ---( server methods )---




	public static final void getAttributesFromKeyValues (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(getAttributesFromKeyValues)>> ---
		// @sigtype java 3.5
		// [i] record:1:required documents
		// [i] - field:0:required key
		// [i] - field:0:required value
		// [o] record:0:required attributes
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
		IDataCursor pipelineCursor_1 = pipeline.getCursor();
		
			// documents
			IData[]	documents = IDataUtil.getIDataArray( pipelineCursor, "documents" );
			IData	attributes = IDataFactory.create();
			if ( documents != null)
			{
				for ( int i = 0; i < documents.length; i++ )
				{
					IDataCursor documentsCursor = documents[i].getCursor();
						String	key = IDataUtil.getString( documentsCursor, "key" );
						String	value = IDataUtil.getString( documentsCursor, "value" );
					
						
						if (key != null && !"".equals(key.trim())) {
							IDataCursor attributesCursor = attributes.getCursor();
		                    IDataUtil.put(attributesCursor, key, value);
		            		attributesCursor.destroy();
		                }
					
					documentsCursor.destroy();
				}
			}
		pipelineCursor.destroy();
		
		// pipeline
		
		
		// attributes
		IDataUtil.put( pipelineCursor_1, "attributes", attributes );
		pipelineCursor_1.destroy();
		// --- <<IS-END>> ---

                
	}
}

