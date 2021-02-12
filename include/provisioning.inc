do_deploy_provisioning() {
	sh ${RZG2_SECURE_BASE}/include/provisioning.sh ${SIGNING_KEY} ${ENCRYPTION_KEY} ${2} ${1}
}

DEPENDS_append = " openssl-native"
