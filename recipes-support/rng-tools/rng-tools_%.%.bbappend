# make sure we do not miss it for kernel / hardware without functional /dev/hwrng
PACKAGECONFIG_append = " libjitterentropy"
