PREFERRED_PROVIDER_virtual/kernel:tqmls1012al ?= "linux-imx-tq"
PREFERRED_PROVIDER_virtual/kernel:tqmls1028a ?= "linux-imx-tq"
PREFERRED_PROVIDER_virtual/kernel:tqmls1088a ?= "linux-lsdk-tq"
PREFERRED_PROVIDER_virtual/kernel:tqmlx2160a ?= "linux-lsdk-tq"
PREFERRED_PROVIDER_virtual/kernel:tqmt10xx ?= "linux-tqmt"

IMAGE_CLASSES += "\
    image_type_bootonly \
    image_type_pblvariant \
"

BOOTONLY_IMAGE_FSTYPES = "\
    wic.bootonly \
    wic.bootonly.pblvariant \
"

IMAGE_FSTYPES:tqmls104xa += "${BOOTONLY_IMAGE_FSTYPES}"
IMAGE_FSTYPES:tqmlx2160a += "${BOOTONLY_IMAGE_FSTYPES}"
