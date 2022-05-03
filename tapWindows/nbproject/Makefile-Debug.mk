#
# Gererated Makefile - do not edit!
#
# Edit the Makefile in the project folder instead (../Makefile). Each target
# has a -pre and a -post target defined where you can add customized code.
#
# This makefile implements configuration specific macros and targets.


# Environment
MKDIR=mkdir
CP=cp
CCADMIN=CCadmin
RANLIB=ranlib
CC=gcc.exe
CCC=g++.exe
CXX=g++.exe
FC=

# Include project Makefile
include Makefile

# Object Directory
OBJECTDIR=build/Debug/Cygwin-Windows

# Object Files
OBJECTFILES= \
	${OBJECTDIR}/com_myonvpn_tuntap_TunTapWindows.o

# C Compiler Flags
CFLAGS=-mno-clwb -Wl,--add-stdcall-alias -shared -m32

# CC Compiler Flags
CCFLAGS=
CXXFLAGS=

# Fortran Compiler Flags
FFLAGS=

# Link Libraries and Options
LDLIBSOPTIONS=

# Build Targets
.build-conf: ${BUILD_SUBPROJECTS} dist/Debug/Cygwin-Windows/libTunTapWindows.dll

dist/Debug/Cygwin-Windows/libTunTapWindows.dll: ${OBJECTFILES}
	${MKDIR} -p dist/Debug/Cygwin-Windows
	${LINK.c} -mno-clwb -shared -o dist/Debug/Cygwin-Windows/libTunTapWindows.dll -fPIC ${OBJECTFILES} ${LDLIBSOPTIONS} 

${OBJECTDIR}/com_myonvpn_tuntap_TunTapWindows.o: com_myonvpn_tuntap_TunTapWindows.c 
	${MKDIR} -p ${OBJECTDIR}
	$(COMPILE.c) -g -I C:/Programme/Java/jdk1.8.0_161/include -I C:/Programme/Java/jdk1.8.0_161/include/win32 -fPIC  -o ${OBJECTDIR}/com_myonvpn_tuntap_TunTapWindows.o com_myonvpn_tuntap_TunTapWindows.c

# Subprojects
.build-subprojects:

# Clean Targets
.clean-conf:
	${RM} -r build/Debug
	${RM} dist/Debug/Cygwin-Windows/libTunTapWindows.dll

# Subprojects
.clean-subprojects:
