import os
import shutil


def load_config(config_file):
    """
    读取 resource.txt 配置文件
    支持格式: key=value 或 key: value
    """
    config = {}
    if not os.path.exists(config_file):
        print(f"❌ 错误: 找不到配置文件 {config_file}")
        return None

    with open(config_file, 'r', encoding='utf-8') as f:
        for line in f:
            line = line.strip()
            # 跳过空行或注释
            if not line or line.startswith('#'):
                continue

            # 解析 key=value 或 key: value
            if '=' in line:
                key, value = line.split('=', 1)
            elif ':' in line:
                key, value = line.split(':', 1)
            else:
                continue

            config[key.strip()] = value.strip()

    return config


def sync_directories(src_root, tgt_root):
    """
    遍历 src_root 并同步到 tgt_root
    """
    print(f"🔄 开始同步...")
    print(f"📂 源目录: {src_root}")
    print(f"📂 目标目录: {tgt_root}")

    count = 0

    # os.walk 会递归遍历目录下所有子目录和文件
    for root, dirs, files in os.walk(src_root):
        for file_name in files:
            # 1. 获取源文件的完整路径
            src_file_path = os.path.join(root, file_name)

            # 2. 计算相对路径 (例如: aaa/bbb.java)
            # os.path.relpath 会计算 src_file_path 相对于 src_root 的路径
            relative_path = os.path.relpath(src_file_path, src_root)

            # 3. 构建目标文件的完整路径
            # 将相对路径拼接到 tgt_root 后面
            tgt_file_path = os.path.join(tgt_root, relative_path)

            # 4. 确保目标目录存在 (如果 aaa 文件夹不存在，则创建它)
            tgt_dir = os.path.dirname(tgt_file_path)
            if not os.path.exists(tgt_dir):
                os.makedirs(tgt_dir)
                # print(f"📁 创建目录: {tgt_dir}") # 可选：显示创建的目录

            # 5. 复制并覆盖文件
            # copy2 会覆盖目标文件，并保留元数据（如修改时间）
            shutil.copy2(src_file_path, tgt_file_path)

            print(f"✅ 已更新: {relative_path}")
            count += 1

    print("-" * 30)
    print(f"🎉 完成! 共处理了 {count} 个文件。")


if __name__ == "__main__":
    # 获取当前脚本所在的目录
    current_dir = os.path.dirname(os.path.abspath(__file__))
    config_path = os.path.join(current_dir, 'resource.txt')

    # 1. 加载配置
    config = load_config(config_path)

    if config:
        src = config.get('src_path')
        tgt = config.get('tgt_path')

        if src and tgt:
            # 简单的路径规范化，处理末尾斜杠问题
            src = os.path.normpath(src)
            tgt = os.path.normpath(tgt)

            if os.path.exists(src):
                sync_directories(src, tgt)
            else:
                print(f"❌ 错误: 源路径不存在 -> {src}")
        else:
            print("❌ 错误: resource.txt 中缺少 src_path 或 tgt_path")