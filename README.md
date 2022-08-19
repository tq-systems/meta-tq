# OpenEmbedded / Yocto Project evaluation BSP layer for TQ-Systems ARM SOM

This README file contains information on the content of the meta-dumpling layer.

Please see the corresponding sections below for details.

[[_TOC_]]

## Overview

### Abstract

This layer provides recipes to generate images to help evaluation of
TQ-Systems CPU modules and Starterkits. This layer additionally contains
optional fixes and extensions for other layers as far as is needed / useful
for TQ-Systems SOM and some initial example distro support.

### Dependencies

This layer in the checked out branch depends on:

URI: https://git.yoctoproject.org/poky  
branch: kirkstone  
layers: meta, meta-poky  

URI: https://github.com/openembedded/meta-openembedded.git
branch: kirkstone  
layers: meta-oe  

URI: https://github.com/tq-systems/meta-tq.git  
branch: kirkstone  
layers: meta-tq  

This layer additionally contains optional fixes and extensions for other layers
as far as needed for TQ-Systems SOM. Most of these bbappends are implemented
under dynamic-layers and are only visible, if the original layer is in your
bblayer.conf. For details look for `BBFILES_DYNAMIC` in Yocto Project
documentation.

### Coding style

It is recommended to use the [Format_Guidelines](https://www.openembedded.org/wiki/Styleguide#Format_Guidelines)
from openembedded.

### Patches

Please submit patches against the meta-dumpling layer to the
maintainer:

Maintainer: Markus Niebel <Markus.Niebel@tq-group.com>

Additionally you can use github's collaboration features.

## Usage

### Adding the meta-dumpling layer to your build

Run 'bitbake-layers add-layer <path-to-meta-dumpling>/meta-dumpling'

Assuming the tq layer exists in subdir sources at the top-level of your
yocto build tree, you can add it to the build system by adding the
location of the tq layer to bblayers.conf, along with any
other layers needed. e.g.:

```
BSPDIR := "${@os.path.abspath(os.path.dirname(d.getVar('FILE', True)) + '/../..')}"

BBLAYERS ?= "\
...
    ${BSPDIR}/sources/poky/meta \
    ${BSPDIR}/sources/poky/meta-poky \
    ${BSPDIR}/sources/poky/meta-openembedded/meta-oe \
    ${BSPDIR}/sources/meta-tq \
    ...
"
```

### Image recipes

The meta-dumpling layer defines images recipes for test / evaluation of
TQ-Systems SOM:

| image                     | intended use                                                     |
| ------------------------- | ---------------------------------------------------------------- |
| tq-image-small            | based on poky core-image-minimal, usable for UBI rootfs          |
| tq-image-small-debug      | based on poky tq-image-small, added debug features               |
| tq-image-generic          | based on poky core-image-minimal                                 |
| tq-image-generic-debug    | based on tq-image-generic, added debug features and packages     |
| tq-image-generic-rt       | based on poky core-image-minimal, PREEMPT_RT kernel              |
| tq-image-generic-rt-debug | based on tq-image-generic-rt, added debug features and packages  |
| tq-image-weston           | based on poky core-image-weston                                  |
| tq-image-weston-debug     | based on tq-image-weston, added debug features and packages      |
| tq-image-qt5              | based on tq-image-weston, added qt5 support                      |
| tq-image-qt5-debug        | based on tq-image-qt5, added debug features and packages         |

**Attention:** These image recipes can be used as a starting point for own customization.
Every recipe comes in two flavours. The images suffixed with `debug` add `debug-tweaks` to
the `IMAGE_FEATURES` to have an open root login for console and ssh for ease of development
and testing. The non suffixed images simply define a base feature set for the use case
and contain a lot of test utils, to evaluate and test TQ-Systems starterkits.
Because of the intended use case, these image recipes are intended as a starting point
for development but should not be used directly for production images.
Depending on the actual project, security must be taken in account.

**Note:** Out of the box (without `debug-tweaks` in `IMAGE_FEATURES`) the images
without `-debug` suffix do not allow user login. This should be default for the vast
majority of embedded systems. If one need an user that can login, see poky documentation
for `extrausers.bbclass` and following example:

```
# add user <username> with password <passwd> and force password change at first login
IMAGE_CLASSES += "extrausers"
USERADDEXTENSION = "useradd-staticids"
USERADD_UID_TABLES = "files/passwd"
USERADD_GID_TABLES = "files/group"

EXTRA_USERS_PARAMS = "\
    useradd <username> ; \
    usermod -P <passwd> <username>; \
    passwd-expire <username>; \
"
```

### Example distros

The meta-dumpling layer defines distros as starting point for own customization.
These distros are also available for usage together with vendor based kernel /
vendor hardware support layer.

| distro name          | purpose                                                              |
| -------------------- | -------------------------------------------------------------------- |
| spaetzle             | small size for usage with \[Q\]SPI-NOR flash                         |
| spaetzle-nxp         | small size for usage with \[Q\]SPI-NOR flash with NXP / FSLC kernel  |
| spaetzle-ti          | small size for usage with \[Q\]SPI-NOR flash with TI kernel          |
| dumpling             | systemd, features depends on machine settings                        |
| dumpling-nxp         | systemd, features depends on machine settings with NXP / FSLC kernel |
| dumpling-ti          | systemd, features depends on machine settings with TI kernel         |
| dumpling-wayland     | like dumpling, additional wayland / weston                           |
| dumpling-wayland-nxp | like dumpling-nxp, additional wayland / weston                       |
| dumpling-wayland-ti  | like dumpling-ti, additional wayland / weston                       |

**Attention:** These distro configs can be used as a starting point for own
customization. Because of the intended usage, they are focused on demonstration.
These configs should not go into a production usage without thorough review.
Depending on the actual project, security must be taken in account.

### Known issues

* tq-image-small\[-debug\]:
  * DHCP is not working
  * restricted support for hardware
  * some warnings from init scripts
