/**
 * MyonVPN
 *
 * @author FantaBlueMystery, KeRn
 * @copyright 2020 by FantaBlueMystery & KeRn
 * @license http://opensource.org/licenses/lgpl-license.php LGPL - GNU Lesser General Public License
 */

/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class com_myonvpn_tuntap_TunTapWindows */

#ifndef _Included_com_myonvpn_tuntap_TunTapWindows
#define _Included_com_myonvpn_tuntap_TunTapWindows
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     com_myonvpn_tuntap_TunTapWindows
 * Method:    openTun
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_com_myonvpn_tuntap_TunTapWindows_openTun
  (JNIEnv *, jobject);

/*
 * Class:     com_myonvpn_tuntap_TunTapWindows
 * Method:    close
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_com_myonvpn_tuntap_TunTapWindows_close
  (JNIEnv *, jobject);

/*
 * Class:     com_myonvpn_tuntap_TunTapWindows
 * Method:    write
 * Signature: ([BI)V
 */
JNIEXPORT void JNICALL Java_com_myonvpn_tuntap_TunTapWindows_write
  (JNIEnv *, jobject, jbyteArray, jint);

/*
 * Class:     com_myonvpn_tuntap_TunTapWindows
 * Method:    read
 * Signature: ([B)I
 */
JNIEXPORT jint JNICALL Java_com_myonvpn_tuntap_TunTapWindows_read
  (JNIEnv *, jobject, jbyteArray);

#ifdef __cplusplus
}
#endif
#endif