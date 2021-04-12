arm模拟
arm-linux-gnueabi-gcc test.c
export QEMU_LD_PREFIX=/usr/arm-linux-gnueabi
qemu-arm -L /usr/arm-linux-gnueabi -cpu cortex-a15 ./a.out

