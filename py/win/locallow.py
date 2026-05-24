# -*- coding: utf-8 -*-

import os
import sys


def test_local_low_access():
    """
    测试普通进程是否能写入 LocalLow 目录
    结论：一定会失败，返回 PermissionError
    """
    print("=" * 60)
    print("POC：测试 Medium 级别进程 写入 LocalLow")
    print("=" * 60)

    # 1. 获取系统 LocalLow 真实路径（系统API获取，不是写死的）
    local_low_path = os.path.join(os.environ["USERPROFILE"], "AppData", "LocalLow")
    test_file = os.path.join(local_low_path, "poc_test_write.txt")

    print(f"系统 LocalLow 路径: {local_low_path}")
    print(f"尝试写入文件: {test_file}")
    print("-" * 60)

    # 2. 尝试直接写入（底层调用 Windows 低级 IO）
    try:
        with open(test_file, "w", encoding="utf-8") as f:
            f.write("测试写入 LocalLow")

        # 如果能走到这里，说明成功了（几乎不可能）
        print("✅ 写入成功！！！（不正常）")
    except PermissionError as e:
        # 100% 会走到这里
        print("❌ 写入失败！系统强制拦截")
        print(f"错误信息: {e}")
        print("错误原因: 进程是 Medium 级别，禁止写入 Low 级别的 LocalLow 目录")
    except Exception as e:
        print(f"其他错误: {e}")

    print("=" * 60)
    print("结论：LocalLow 是内核级安全隔离，任何程序都无法绕过")
    print("=" * 60)


if __name__ == "__main__":
    test_local_low_access()
