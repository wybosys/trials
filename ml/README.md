# 简单测试

## horovod-tf-test.py

``` sh
: 安装
sudo HOROVOD_GPU_OPERATIONS=NCCL pip3 install horovod

: 本地测试
horovodrun -np 1 -H localhost:1 python horovod-tf-test.py
```
