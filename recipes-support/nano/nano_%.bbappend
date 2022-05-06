PACKAGECONFIG[utf8] = "--enable-utf8,--disable-utf8"

PACKAGECONFIG ??= "utf8"

PACKAGECONFIG:remove:libc-musl = "utf8"
