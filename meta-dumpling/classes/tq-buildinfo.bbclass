# SPDX-License-Identifier: MIT
#
# Copyright (c) 2024 TQ-Systems GmbH <oss@ew.tq-group.com>, D-82229 Seefeld, Germany
#
# Reuse and extend image-buildinfo.bbclass to generate '/etc/buildinfo' with SCM
# information for the built BSP and configuration information for machine / distro / image
# Additionally the image specific buildinfo is generated in DEPLOY_DIR_IMAGE using
# IMAGE_NAME and IMAGE_LINK_NAME
#
# Usage: add INHERIT += "tq-buildinfo" to your conf file
#

# support creating /etc/buildinfo in image
inherit image-buildinfo

# define what to put in /etc/buildinfo
IMAGE_BUILDINFO_VARS += "DISTRO"
IMAGE_BUILDINFO_VARS += "MACHINE"
IMAGE_BUILDINFO_VARS += "DEFAULTTUNE"
IMAGE_BUILDINFO_VARS += "MACHINE_FEATURES"
IMAGE_BUILDINFO_VARS += "DISTRO_FEATURES"
IMAGE_BUILDINFO_VARS += "COMBINED_FEATURES"
IMAGE_BUILDINFO_VARS += "IMAGE_FEATURES"
IMAGE_BUILDINFO_VARS += "MACHINEOVERRIDES"
IMAGE_BUILDINFO_VARS += "DISTROOVERRIDES"

# use functions from oe.buildcfg to get information about the 
# build space project. This assumes, that the build directory is
# located inside the checked out build space repository. 'TOPDIR' resolves to the
# top level build directory
def topproject_revision(d):
    topdir = d.getVar('TOPDIR')
    bspdir = d.getVar('BSPDIR')

    if bspdir is None :
        bb.warn("BSPDIR is not set. Unknown build configuration.")

    git_description = oe.buildcfg.get_metadata_git_revision(topdir)
    if git_description != '<unknown>':
        git_description = oe.buildcfg.get_metadata_git_describe(topdir)

    return git_description

# set variable to the SCM revision information of our build space repository
TOPPROJECT_REVISION = "${@topproject_revision(d)}"

# we want this information in our /etc/buildinfo
IMAGE_BUILDINFO_VARS += "TOPPROJECT_REVISION"

# Extend the image-buildinfo class to store the information in ${DEPLOY_DIR_IMAGE},
# too
TQ_IMAGE_BUILDINFO_FILE = "${IMAGE_NAME}.buildinfo"
TQ_IMAGE_BUILDINFO_LINK = "${IMAGE_LINK_NAME}.buildinfo"

python tq_buildinfo_build () {
    deploy_dir = d.getVar("DEPLOY_DIR_IMAGE")

    d.setVar("BUILDINFODEST", deploy_dir)
    d.setVar("IMAGE_BUILDINFO_FILE", "/" + d.getVar("TQ_IMAGE_BUILDINFO_FILE"))

    bb.build.exec_func("buildinfo", d)

    dst = os.path.join(deploy_dir, d.getVar("TQ_IMAGE_BUILDINFO_LINK"))
    src = d.getVar("TQ_IMAGE_BUILDINFO_FILE")
    if os.path.exists(os.path.join(deploy_dir, d.getVar("TQ_IMAGE_BUILDINFO_FILE"))):
        bb.note("Creating symlink: %s -> %s" % (dst, src))
        if os.path.islink(dst):
            os.remove(dst)
        os.symlink(src, dst)
    else:
        bb.note("Skipping symlink, source does not exist: %s -> %s" % (dst, src))
}

# add trigger to generate ${DEPLOY_DIR_IMAGE}/${MACHINE}-buildinfo-${SOURCE_DATE_EPOCH}
IMAGE_POSTPROCESS_COMMAND += "tq_buildinfo_build"
