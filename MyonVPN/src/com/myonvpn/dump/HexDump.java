/**
 * MyonVPN
 *
 * @author FantaBlueMystery
 * @copyright 2020 by FantaBlueMystery & KeRn
 * @license http://opensource.org/licenses/lgpl-license.php LGPL - GNU Lesser General Public License
 */
package com.myonvpn.dump;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

/**
 * HexDump
 * @author FantaBlueMystery
 */
public class HexDump {

	/**
	 * consts
	 */
	static private final String NL		= System.getProperty( "line.separator" );
    static private final int NL_LENGTH	= NL.length();

	static private final char[] SPACE_CHARS = {
        ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ',
        ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ',
        ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '
		};

	static public final char[] HEX_DIGITS = {
        '0', '1', '2', '3', '4', '5',
        '6', '7', '8', '9', 'A', 'B',
        'C', 'D', 'E', 'F'
		};

	/**
	 * dump
	 * @param ps
	 * @param src
	 * @param srcIndex
	 * @param length
	 */
	static public void dump(PrintStream ps, byte[] src, int srcIndex, int length) {
		if( length == 0 ) {
            return;
        }

		if( length == -1 ) {
			length = src.length-srcIndex;

			if( length < 1 ) {
				return;
			}
		}

		int s = length % 16;
        int r = ( s == 0 ) ? length / 16 : length / 16 + 1;

		char[] c = new char[r * (74 + HexDump.NL_LENGTH)];
        char[] d = new char[16];

        int i;
        int si = 0;
        int ci = 0;

		do {
			HexDump.toHexChars(si, c, ci, 5);

			ci += 5;
            c[ci++] = ':';

            do {
				if( si == length ) {
                    int n = 16 - s;

                    System.arraycopy(HexDump.SPACE_CHARS, 0, c, ci, n * 3);

                    ci += n * 3;

                    System.arraycopy(HexDump.SPACE_CHARS, 0, d, s, n);

                    break;
                }

				c[ci++] = ' ';
                i = src[srcIndex + si] & 0xFF;

                HexDump.toHexChars( i, c, ci, 2 );

                ci += 2;

                if( i < 0 || Character.isISOControl((char)i)) {
                    d[si % 16] = '.';
                }
				else {
                    d[si % 16] = (char)i;
                }
			}
			while(( ++si % 16 ) != 0 );

			c[ci++] = ' ';
            c[ci++] = ' ';
            c[ci++] = '|';

            System.arraycopy( d, 0, c, ci, 16 );

            ci += 16;
            c[ci++] = '|';

            HexDump.NL.getChars(0, HexDump.NL_LENGTH, c, ci);

            ci += HexDump.NL_LENGTH;
		}
		while( si < length );

		ps.println(c);
	}

	/**
	 * dump
	 * @param ps
	 * @param src
	 */
	static public void dump(PrintStream ps, byte[] src) {
		HexDump.dump(ps, src, 0, -1);
	}

	/**
	 * dump
	 * @param src
	 */
	static public void dump(byte[] src) {
		HexDump.dump(System.out, src);
	}

	/**
	 * dumpStr
	 * @param src
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	static public String dumpStr(byte[] src) throws UnsupportedEncodingException {
		final ByteArrayOutputStream baos = new ByteArrayOutputStream();

		try (PrintStream ps = new PrintStream(baos, true, "UTF-8")) {
			HexDump.dump(ps, src);
		}

		return new String(baos.toByteArray(), StandardCharsets.UTF_8);
	}

	/**
	 * toHexChars
	 * @param val
	 * @param dst
	 * @param dstIndex
	 * @param size
	 */
	static public void toHexChars( int val, char dst[], int dstIndex, int size ) {
        while( size > 0 ) {
            int i = dstIndex + size - 1;

            if( i < dst.length ) {
                dst[i] = HexDump.HEX_DIGITS[val & 0x000F];
            }

            if( val != 0 ) {
                val >>>= 4;
            }

            size--;
        }
    }

	/**
	 * toHexChars
	 * @param val
	 * @param dst
	 * @param dstIndex
	 * @param size
	 */
    static public void toHexChars( long val, char dst[], int dstIndex, int size ) {
        while( size > 0 ) {
            dst[dstIndex + size - 1] = HexDump.HEX_DIGITS[(int)( val & 0x000FL )];

            if( val != 0 ) {
                val >>>= 4;
            }

            size--;
        }
    }
}