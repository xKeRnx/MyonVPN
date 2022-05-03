/**
 * MyonVPN
 *
 * @author FantaBlueMystery, KeRn
 * @copyright 2020 by FantaBlueMystery & KeRn
 * @license http://opensource.org/licenses/lgpl-license.php LGPL - GNU Lesser General Public License
 */

#include <stdio.h>
#include <stdlib.h>
#include <fcntl.h>
#include <string.h>
#include <sys/socket.h>
#include <linux/ioctl.h>
#include <linux/if.h>
#include <linux/if_tun.h>

#include <jni.h>
#include "com_myonvpn_tuntap_TunTapLinux.h"

/**
 * setFdDev
 * @param env
 * @param this
 * @param fd
 * @param dev
 */
void setFdDev(JNIEnv *env, jobject this, int fd, char* dev) {
    jfieldID jfd, jdev;
    jclass jclass;
    jstring jstr;

    jclass = (*env)->GetObjectClass(env, this);

    jfd = (*env)->GetFieldID(env, jclass, "_fd", "I");
    (*env)->SetIntField(env, this, jfd , fd);

    jstr = (*env)->NewStringUTF(env, dev);
    jdev = (*env)->GetFieldID(env, jclass, "_dev", "Ljava/lang/String;");
    (*env)->SetObjectField(env, this, jdev , jstr);
}

/**
 * getFd
 * @param env
 * @param this
 * @return
 */
int getFd(JNIEnv *env, jobject this) {
    jfieldID jfd;
    jclass jclass;

    jclass = (*env)->GetObjectClass(env, this);

    jfd = (*env)->GetFieldID(env, jclass, "_fd", "I");
    return (*env)->GetIntField(env, this, jfd);
}

/**
 * tuntapErrorLog
 * @param env
 * @param object
 * @param message
 */
void tuntapErrorLog(JNIEnv *env, jobject object, const char *message) {
	jclass class = (*env)->GetObjectClass(env, object);
	jmethodID methodId = (*env)->GetMethodID(env, class, "_errorLog", "(Ljava/lang/String;)V");

	jstring jstr;

	if( methodId == NULL ) {
		printf("method not found on tuntap object: _errorLog\n");
		printf("tuntapError: ");
		printf(message);
		printf("\n");
        return;
    }

	jstr = (*env)->NewStringUTF(env, message);

	(*env)->CallVoidMethod(env, object, methodId, jstr);
}

/*
 * Class:     jtuntap_TunTap
 * Method:    openTun
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_com_myonvpn_tuntap_TunTapLinux_openTun(JNIEnv *env, jobject this) {
    struct ifreq ifr;
    int fd;

    if( (fd = open("/dev/net/tun", O_RDWR)) < 0 ) {
		tuntapErrorLog(env, this, "openTun: fp = 0, error: open");
        return 1;
    }

    memset(&ifr, 0, sizeof(ifr));

    ifr.ifr_flags = IFF_TAP | IFF_NO_PI;

    if( ioctl(fd, TUNSETIFF, (void*)&ifr) < 0 ) {
        close(fd);
		tuntapErrorLog(env, this, "openTun: error: ioctl");
        return 1;
    }

    setFdDev(env, this, fd, ifr.ifr_name);

    return 0;
}

/*
 * Class:     jtuntap_TunTap
 * Method:    close
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_com_myonvpn_tuntap_TunTapLinux_close(JNIEnv *env, jobject this) {
    close(getFd(env, this));
}

/*
 * Class:     jtuntap_TunTap
 * Method:    write
 * Signature: ([BI)V
 */
JNIEXPORT void JNICALL Java_com_myonvpn_tuntap_TunTapLinux_write(JNIEnv *env, jobject this, jbyteArray jb, jint len) {
    int fd;
    jbyte *b;

    fd = getFd(env, this);
    b = (*env)->GetByteArrayElements(env, jb, NULL);

    write(fd, b, len);

    (*env)->ReleaseByteArrayElements(env, jb, b, JNI_ABORT);
}

/*
 * Class:     jtuntap_TunTap
 * Method:    read
 * Signature: ([B)I
 */
JNIEXPORT jint JNICALL Java_com_myonvpn_tuntap_TunTapLinux_read(JNIEnv *env, jobject this, jbyteArray jb) {
    int fd;
    jbyte *b;
    int len;

    fd = getFd(env, this);
    b = (*env)->GetByteArrayElements(env, jb, NULL);

    len = read(fd, b, (*env)->GetArrayLength(env, jb));

    (*env)->ReleaseByteArrayElements(env, jb, b, 0);
    return len;
}
