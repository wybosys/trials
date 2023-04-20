import pkg_resources
import platform

try:
    import torch

    if torch.cuda.is_available():
        print(torch.cuda.get_device_name())
        print("\n\ntorch: True")
    else:
        print("\n\ntorch: False")
except:
    print("检查torch的安装情况")

print("\n\n")
try:
    import tensorflow as tf

    if tf.test.is_gpu_available():
        print(tf.config.list_physical_devices("GPU"))
        print("\n\ntensorflow: True")
    else:
        print("\n\ntensorflow: False")
        if platform.system() == "Windows":
            ver = pkg_resources.parse_version(tf.version.VERSION)
            if ver.minor > 10:
                print("windows上的tensorflow如果需要gpu支持必须安装<=2.10.0")

    if not tf.test.is_built_with_cuda():
        print("tensorflow-gpu not install")
except:
    print("检查tensorflow的安装情况")
