# plenty of tools for test / development
IMAGE_INSTALL_append = "\
    packagegroup-testutils \
"

# enable root login w/o password and passwordless ssh login
IMAGE_FEATURES_append = " debug-tweaks"
# poke defined collection of debug/test tools
IMAGE_FEATURES_append = " tools-debug"
IMAGE_FEATURES_append = " tools-testapps"
