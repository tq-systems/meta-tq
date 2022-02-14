PACKAGECONFIG[utf8] = "--enable-utf8,--disable-utf8"

PACKAGECONFIG ??= "utf8"

PACKAGECONFIG_remove_libc-musl = "utf8"
