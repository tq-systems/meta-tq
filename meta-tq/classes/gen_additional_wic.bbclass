# SPDX-License-Identifier: MIT
################################################################################
# Copyright (C) 2023-2024 TQ-Systems GmbH <oss@ew.tq-group.com>
# D-82229 Seefeld, Germany.
# Author: Markus Niebel

# This class generates additional wic images for machines defining list variable
# EXTWIC_BOOTLOADER_IMAGES. This is useful for boards differing for instance
# only in the assembled RAM. Technically only the bootimage of the wic image
# will be replaced
#
# ATTENTION: It is expected that the boot image to be replaced is located in
# unpartitioned area at a fixed location, defined by the following two
# machine-specific variables:
# ${RAW_BOOT_START_OFFSET_KB}: defines the start of the boot image in KiByte
# ${RAW_BOOT_END_OFFSET_KB}: defines the end (first free after) the boot image in KiByte

# List of boot images to generate different wic flavours
# EXTWIC_BOOTLOADER_IMAGES

inherit image-artifact-names

# Since this is a special type of 'conversion' that can produce more than one
# artifact we implement this not as image conversion class but as a set of postfuctions
# for the wic image generation class.

# Add an additional postfunction for wic image generation class
# to provide symlinks for the additionally created images. Use 'create_symlinks'
# from image.bbclass as blueprint
python create_additional_wic_images() {
    deploy_dir = d.getVar('IMGDEPLOYDIR')
    img_name = d.getVar('IMAGE_NAME')
    link_name = d.getVar('IMAGE_LINK_NAME')
    imgsuffix = d.getVar("IMAGE_NAME_SUFFIX")
    bl_images = d.getVar('EXTWIC_BOOTLOADER_IMAGES')
    wicfile = os.path.join(deploy_dir, img_name + imgsuffix + ".wic")
    offset = d.getVar('RAW_BOOT_START_OFFSET_KB')
    end = d.getVar('RAW_BOOT_END_OFFSET_KB')
    size = int(end) - int(offset)
    dd_replacement_args = [f'seek={offset}', f'count={size}', 'bs=1k', 'conv=notrunc']

    # nothing to do for empty IMAGE_FSTYPES or 'wic' not in IMAGE_FSTYPES
    fstypes = d.getVar('IMAGE_FSTYPES', True)
    if not fstypes:
        return
    else:
        has_wic = bb.utils.contains('IMAGE_FSTYPES', "wic", True, False, d)
        if not has_wic:
           return
    if not link_name:
        return

    # class will not be triggered if list is empty, no need to validate
    # placement variables in this case.
    if not bl_images:
        bb.warn("class 'gen_additional_wic' requires non empty 'EXTWIC_BOOTLOADER_IMAGES', do nothing")
        return

    if not offset or not end:
        bb.fatal("class 'gen_additional_wic' requires 'RAW_BOOT_START_OFFSET_KB' and  'RAW_BOOT_END_OFFSET_KB'")
    offset = int(d.getVar('RAW_BOOT_START_OFFSET_KB', True))
    end = int(d.getVar('RAW_BOOT_END_OFFSET_KB', True))
    if not offset < end:
        bb.fatal("class 'gen_additional_wic' requires 'RAW_BOOT_END_OFFSET_KB' > 'RAW_BOOT_START_OFFSET_KB'")

    import subprocess
    bl_images = bl_images.split()
    for bl_image in bl_images:
        outfile = os.path.join(deploy_dir, img_name + "-" + os.path.basename(bl_image) + imgsuffix + ".wic")
        bl_image_file = os.path.join(d.getVar('DEPLOY_DIR_IMAGE'), bl_image)
        bb.utils.copyfile(wicfile, outfile)
        subprocess.run(['dd', 'if=/dev/zero', f'of={outfile}'] + dd_replacement_args, capture_output=True)
        subprocess.run(['dd', f'if={bl_image_file}', f'of={outfile}'] + dd_replacement_args,  capture_output=True)

        dst = os.path.join(deploy_dir, link_name + "-" + os.path.basename(bl_image) + ".wic" )
        src = img_name + "-" + os.path.basename(bl_image) + imgsuffix + ".wic"
        if os.path.exists(os.path.join(deploy_dir, src)):
            bb.note("Creating symlink: %s -> %s" % (dst, src))
            if os.path.islink(dst):
                os.remove(dst)
            os.symlink(src, dst)
        else:
            bb.note("Skipping symlink, source does not exist: %s -> %s" % (dst, src))
}

do_image_wic[postfuncs] += "create_additional_wic_images"
