
# Add runtime dependencies for i.MX8M Plus tensorflow delegate
RDEPENDS:${PN}:append:mx8mp-nxp-bsp  = " tensorflow-lite-vx-delegate"
