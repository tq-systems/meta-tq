# TQMa6\[QP,DP,Q,D,DL,SL\] up to Rev.040x on MBa6x REV.020x carrier board

## Overview

### Supported Hardware:

* TQMa6x: module revisions REV.010x ... REV.040x
* MBa6x:  board revisions REV.020x

### Known issues

- none

## HowTo:

### MBa6x DIP Switch settings for Boot

_Note:_

* S1/2/4 are for BOOT_CFG.
* S5 is for Boot Mode.
* X means position of DIP, - means don't care

_SD Card_

```
	S1			S2			S4			S5
DIP 	1 2 3 4 5 6 7 8		1 2 3 4 5 6 7 8		1 2 3 4 5 6 7 8		1 2
ON 	  X   X        		    X   X      		               		X  
OFF 	X   X   X X X X		X X   X   X X X		- - - - - - - -		  X
```

_e-MMC_

```
	S1			S2			S4			S5
DIP 	1 2 3 4 5 6 7 8		1 2 3 4 5 6 7 8		1 2 3 4 5 6 7 8		1 2
ON 	  X X          		  X   X        		               		X  
OFF 	X     X X X X X		X   X   X X X X		- - - - - - - -		  X
```

_SPI_

```
	S1			S2			S4			S5
DIP 	1 2 3 4 5 6 7 8		1 2 3 4 5 6 7 8		1 2 3 4 5 6 7 8		1 2
ON 	    X X        		               		      X X      		X  
OFF 	X X     X X X X		- - - - - - - -		X X X     X X X		  X
```

## Support Wiki

See [TQ Embedded Wiki for TQMa6x](https://support.tq-group.com/en/arm/tqma6x)
