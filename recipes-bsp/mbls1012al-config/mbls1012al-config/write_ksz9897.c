// SPDX-License-Identifier: GPL-2.0
/*
 * Write KSZ9897 switch register via I2C
 *
 * Copyright (C) 2018 Max Merchel <Max Merchel@tq-group.com>
 *
 * inspired by i2c tools
 */
#include <errno.h>
#include <stdio.h>
#include <stdlib.h>
#include <stdint.h>
#include <unistd.h>
#include <fcntl.h>
#include <string.h>
#include <time.h>
#include <sys/ioctl.h>
#include <linux/i2c.h>
#include <linux/i2c-dev.h>

#define DEFAULT_I2C_BUS		"/dev/i2c-0"
#define DEFAULT_KSZ9897_ADDR	0x5f
#define DEFAULT_KSZ9897_REG	0x7301
#define DEFAULT_KSZ9897_VAL	0x18
#define DEFAULT_KSZ9897_LEN	1

/* return 0 on success, -1 on failure */
int main(int argc, char **argv)
{
	struct i2c_rdwr_ioctl_data msg_rdwr;
	struct i2c_msg i2cmsg;
	int i;
	/* filedescriptor and name of device */
	int d;
	char *dn = DEFAULT_I2C_BUS;
	__u8 wbuf[2 + DEFAULT_KSZ9897_LEN];

	wbuf[0] = (__u8)((DEFAULT_KSZ9897_REG & 0xff00) >> 8);
	wbuf[1] = (__u8)(DEFAULT_KSZ9897_REG);

	d = open(dn, O_RDWR);
	if (d < 0) {
		fprintf(stderr, "Could not open i2c at %s\n", dn);
		perror(dn);
		exit(1);
	}

	for (i = (DEFAULT_KSZ9897_LEN - 1); i >= 0; --i) /* copy buf[0..n] -> _buf[1..n+1] */
		wbuf[1 + (i + 1)] = DEFAULT_KSZ9897_VAL >> (((DEFAULT_KSZ9897_LEN - 1) * 8) - (i * 8));

	msg_rdwr.msgs  = &i2cmsg;
	msg_rdwr.nmsgs = 1;

	i2cmsg.addr  = DEFAULT_KSZ9897_ADDR;
	i2cmsg.flags = 0;
	i2cmsg.len   = 2 + DEFAULT_KSZ9897_LEN;
	i2cmsg.buf   = wbuf;

	i = ioctl(d, I2C_RDWR, &msg_rdwr);
	if (i < 0) {
		perror("ioctl()");
		fprintf(stderr, "ioctl returned %d\n", i);
		close(d);
		exit(1);
	}

	close(d);
	exit(0);
}
