# Common definitions for TQ-Group TQMT104x modules on STKT104x

MACHINEOVERRIDES =. "tqmt104x-64bit:"

TARGET_FPU = "hard"
DEFAULTTUNE ?= "ppc64e5500"

require conf/machine/include/tune-ppce5500.inc

MACHINEOVERRIDES =. "e5500-64b:"

require conf/multilib.conf
MULTILIBS ?= "multilib:lib32"
DEFAULTTUNE_virtclass-multilib-lib32 ?= "ppce5500"
