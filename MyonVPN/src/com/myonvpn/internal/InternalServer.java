/**
 * MyonVPN
 *
 * @author FantaBlueMystery
 * @copyright 2020 by FantaBlueMystery & KeRn
 * @license http://opensource.org/licenses/lgpl-license.php LGPL - GNU Lesser General Public License
 */
package com.myonvpn.internal;

import fi.iki.elonen.NanoHTTPD;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * InternalServer
 * https://android.googlesource.com/platform/external/nanohttpd/+/72a344b/webserver/src/main/java/fi/iki/elonen/SimpleWebServer.java
 * @author FantaBlueMystery
 */
public class InternalServer extends NanoHTTPD {

	/**
	 * InternalServer
	 * @throws IOException
	 */
	public InternalServer() throws IOException {
		super(8080);

		this.start(NanoHTTPD.SOCKET_READ_TIMEOUT, false);
	}

	/**
	 * serve
	 * @param session
	 * @return
	 */
	@Override
    public Response serve(IHTTPSession session) {
		Map<String, String> header = session.getHeaders();
        Map<String, List<String>> parms = session.getParameters();

        String uri = session.getUri();



		return newFixedLengthResponse("Hello world");
	}

	/*public Response _response() {

	}*/
}