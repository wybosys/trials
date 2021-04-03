#!/usr/bin/env python3

import tensorflow as tf
version = tf.__version__
gpu_ok = tf.test.is_gpu_available()
print("tf version:", version, "\nuse GPU", gpu_ok)
