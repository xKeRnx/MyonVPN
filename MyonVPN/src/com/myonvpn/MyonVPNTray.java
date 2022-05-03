/**
 * MyonVPN
 *
 * @author FantaBlueMystery
 * @copyright 2020 by FantaBlueMystery & KeRn
 * @license http://opensource.org/licenses/lgpl-license.php LGPL - GNU Lesser General Public License
 */
package com.myonvpn;

import com.myonvpn.trayer.Trayer;
import edu.stanford.ejalbert.BrowserLauncher;
import edu.stanford.ejalbert.exception.BrowserLaunchingInitializingException;
import edu.stanford.ejalbert.exception.UnsupportedOperatingSystemException;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * MyonVPNTray
 * @author FantaBlueMystery
 */
public class MyonVPNTray implements MouseListener {

	/**
	 * trayer object
	 */
	private Trayer _trayer;

	/**
	 * MyonVPNTray
	 */
	public MyonVPNTray() {
		this._trayer = new Trayer("com/myonvpn/resources/images/icons8-cloud-100.png");
		this._trayer.addMouseListener(this);
	}

	/**
	 * mouseClicked
	 * @param e
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		try {
			BrowserLauncher launcher = new BrowserLauncher();

			launcher.openURLinBrowser("http://localhost:8080/");
		}
		catch( BrowserLaunchingInitializingException | UnsupportedOperatingSystemException ex ) {
			Logger.getLogger(MyonVPNTray.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public void mouseExited(MouseEvent e) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
}