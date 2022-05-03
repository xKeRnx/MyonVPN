/**
 * MyonVPN
 *
 * @author FantaBlueMystery
 * @copyright 2020 by FantaBlueMystery
 * @license http://opensource.org/licenses/lgpl-license.php LGPL - GNU Lesser General Public License
 */
package com.myonvpn;

import com.myonvpn.internal.InternalServer;
import com.myonvpn.network.VPNConnector;
import com.myonvpn.tuntap.TunTap;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * MyonVPN
 * @author FantaBlueMystery
 */
public class MyonVPN {

	/**
	 * main
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		new MyonVPNTray();

		try {
			new InternalServer();
		}
		catch( IOException ex ) {
			Logger.getLogger(MyonVPN.class.getName()).log(Level.SEVERE, null, ex);
		}

		try {
			VPNConnector con = new VPNConnector();
			TunTap tt = con.getTunTap();

			tt.setIP("10.0.5.1", "255.255.255.0");
		}
		catch( Exception ex ) {
			Logger.getLogger(MyonVPN.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}