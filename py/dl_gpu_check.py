import tensorflow as tf
import torch

if torch.cuda.is_available():
    print(torch.cuda.get_device_name())
    print("\n\ntorch: True")
else:
    print("\n\ntorch: False")

print("\n\n")
if tf.test.is_gpu_available():
    print(tf.config.list_physical_devices("GPU"))
    print("\n\ntensorflow: True")
else:
    print("\n\ntensorflow: False")
if not tf.test.is_built_with_cuda():
    print("tensorflow-gpu not install")
