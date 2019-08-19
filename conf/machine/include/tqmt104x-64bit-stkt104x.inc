# Common definitions for TQ-Group TQMT104x modules on STKT104x

MACHINEOVERRIDES =. "tqmt104x-64bit:"
MACHINEOVERRIDES =. "e5500-64b:"

ARCH="powerpc64"
TARGET_FPU = "hard"
require conf/multilib.conf
DEFAULTTUNE ?= "ppc64e5500"
DEFAULTTUNE_virtclass-multilib-lib32 ?= "ppce5500"
MULTILIBS ?= "multilib:lib32"
require conf/machine/include/tune-ppce5500.inc
