/**
 * MyonVPN
 *
 * @author FantaBlueMystery
 * @copyright 2020 by FantaBlueMystery & KeRn
 * @license http://opensource.org/licenses/lgpl-license.php LGPL - GNU Lesser General Public License
 */
package com.myonvpn.tuntap;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * TunTapWindows
 * @author FantaBlueMystery
 */
public class TunTapWindows extends TunTap {

	/**
	 * Static
	 */
	static {
		try {
			TunTap.loadLib("clib\\libTunTapWindows.dll", "clib\\libTunTapWindows64.dll");
		}
		catch( Throwable e ) {
			Logger.getLogger("").log(Level.SEVERE, "Could not load libTunTapWindows.dll", e);
		}
    }

	/**
	 * cPtr
	 */
	private long _cPtr;

	/**
	 * dev
	 */
    private String _dev;

	/**
	 * TunTapWindows
	 * @throws Exception
	 */
	public TunTapWindows() throws Exception {
		if( 0 != this.openTun() ) {
			throw new Exception("Could not open Virtual Eternat Adapter!\n" +
				"Make sure the TAP-Win32 driver ist installed.");
		}
	}

	/**
	 * getDev
	 * @return
	 */
	@Override
	public String getDev() {
        return this._dev;
    }

	/**
	 * openTun
	 * @return
	 */
	private native int openTun();

	/**
	 * close
	 */
	@Override
    public native void close();

	/**
	 * write
	 * @param b
	 * @param len
	 */
	@Override
    public native void write(byte[] b, int len);

	/**
	 * read
	 * @param b
	 * @return
	 */
	@Override
    public native int read(byte[] b);

	public void setIP(String ip, String subnetmask) {
		super.setIP(ip, subnetmask);

    	try {
            String[] cmd = {
                "netsh", "interface", "ip", "set", "address", this._dev, "static", ip, subnetmask
				};

            String[] metric = {
                "netsh", "interface", "ip", "set", "interface", "interface=" + this._dev,
                "metric=10"
				};

            String[] rename = {
                "netsh", "interface", "set", "interface", "name=" + this._dev,
                "newname=P2P VPN"
				};

            Process p = Runtime.getRuntime().exec(cmd);

			p = Runtime.getRuntime().exec(metric);
            p = Runtime.getRuntime().exec(rename);

    	}
		catch( Exception e ) {
			Logger.getLogger("").log(Level.WARNING, "Could not set IP!", e);
    	}
    }

	/**
	 * _errorLog
	 * @param message
	 */
	@Override
	protected void _errorLog(String message) {
		Logger.getLogger("").log(Level.SEVERE, "TunTapWindows: {0}", message);
	}
}