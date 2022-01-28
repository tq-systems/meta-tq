require tq-image-generic-rt.bb
require tq-image-debug.inc

SUMMARY =  "This is a generic image for TQ SOM running PREEMPT_RT kernel with test and debug features."

DESCRIPTION = "Demo image based on core-image-generic and essential packages \
for the machine. This creates a larger image and includes real-time test suite \
and tools appropriate for real-time use. \
This creates a medium sized image, that must not be used for production - \
especially from the aspect of security."
