PREFERRED_PROVIDER_virtual/kernel:tqmls1012al ?= "linux-imx-tq"
PREFERRED_PROVIDER_virtual/kernel:tqmls1028a ?= "linux-imx-tq"
PREFERRED_PROVIDER_virtual/kernel:tqmls1088a ?= "linux-lsdk-tq"
PREFERRED_PROVIDER_virtual/kernel:tqmlx2160a ?= "linux-lsdk-tq"
PREFERRED_PROVIDER_virtual/kernel:tqmt10xx ?= "linux-tqmt"

IMAGE_CLASSES += "image_type_bootonly"
IMAGE_FSTYPES:tqmlx2160a += "wic.bootonly"
