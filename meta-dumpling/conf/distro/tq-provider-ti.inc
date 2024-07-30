PREFERRED_PROVIDER_virtual/kernel:tqma335x = "linux-ti-tq"
PREFERRED_PROVIDER_virtual/kernel:tqma57xx = "linux-ti-tq"

# Workaround for incorrect SIGGEN_EXCLUDERECIPES_ABISAFE in meta-ti
SIGGEN_EXCLUDERECIPES_ABISAFE:remove = "mesa-pvr"
