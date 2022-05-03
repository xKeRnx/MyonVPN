/**
 * MyonVPN
 *
 * @author FantaBlueMystery
 * @copyright 2020 by FantaBlueMystery
 * @license http://opensource.org/licenses/lgpl-license.php LGPL - GNU Lesser General Public License
 */
package com.myonvpn.trayer;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.MouseListener;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 * Trayer
 * @author FantaBlueMystery
 */
public class Trayer {

	/**
     * SystemTray
     */
    protected SystemTray _systemtray = null;

    /**
     * TrayIcon
     */
    protected TrayIcon _trayicon = null;

	/**
     * Trayer
	 * @param icon
     */
    public Trayer(String icon) {
        if( SystemTray.isSupported() ) {
            this._systemtray = SystemTray.getSystemTray();

            try {
                Image imageicon = Trayer.getImage(icon, this._systemtray);

                this._trayicon = new TrayIcon(imageicon);
				this._trayicon.setImageAutoSize(false);
                this._systemtray.add(this._trayicon);

				this._trayicon.setPopupMenu(new PopupMenu());
            }
            catch( Exception e ) {
				Logger.getLogger(Trayer.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }

	/**
     * setIconTooltip
     * @param msg
     */
    public void setIconTooltip(String msg) {
        if( this._systemtray != null ) {
            this._trayicon.setToolTip(msg);
        }
    }

    /**
     * addMouseListener
     * @param listener
     */
    public void addMouseListener(MouseListener listener) {
        if( this._systemtray != null ) {
            this._trayicon.addMouseListener(listener);
        }
    }

	/**
     * displayMsgError
     * @param title
     * @param msg
     */
    public void displayMsgError(String title, String msg) {
        if( this._trayicon != null ) {
            this._trayicon.displayMessage(
                title,
                msg,
                TrayIcon.MessageType.ERROR);
        }
    }

    /**
     * displayMsgError
     * @param title
     * @param msg
     */
    public void displayMsgInfo(String title, String msg) {
        if( this._trayicon != null ) {
            this._trayicon.displayMessage(
                title,
                msg,
                TrayIcon.MessageType.INFO);
        }
    }

	/**
     * getImage
     * @param filename
     * @param systemtray
     * @return
     * @throws Exception
     */
    protected static Image getImage(String filename, SystemTray systemtray) throws Exception {
        InputStream input_stream =
            ClassLoader.getSystemClassLoader().getResourceAsStream(
                filename);

        Image image = ImageIO.read(input_stream);
        Dimension dimension = systemtray.getTrayIconSize();

        Image scaledimage = image.getScaledInstance(
            dimension.width-3,
            dimension.height-3,
            Image.SCALE_SMOOTH
            );

        return scaledimage;
    }
}