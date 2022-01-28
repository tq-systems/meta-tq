require recipes-core/images/core-image-minimal.bb

# Skip processing of this recipe if linux-rt-tq is not explicitly specified as the
# PREFERRED_PROVIDER for virtual/kernel. This avoids errors when trying
# to build multiple virtual/kernel providers.
python () {
    if d.getVar("PREFERRED_PROVIDER_virtual/kernel") != "linux-rt-tq":
        raise bb.parse.SkipRecipe("Set PREFERRED_PROVIDER_virtual/kernel to linux-rt-tq to enable it")
}

SUMMARY =  "This is an RT image for TQ SOM with some RT test / debug tools."
DESCRIPTION = "Demo image based on core-image-minimal and essential packages \
for the machine. This creates a larger image and includes real-time test suite \
and tools appropriate for real-time use."
DEPENDS += "linux-rt-tq"

IMAGE_INSTALL += "\
    rt-tests \
    hwlatdetect \
"

require tq-image.inc
