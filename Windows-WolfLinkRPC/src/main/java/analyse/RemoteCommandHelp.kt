package org.wolflink.windows.wolflinkrpc.analyse

import org.wolflink.common.wolflinkrpc.api.annotations.RemoteCallHandler
import org.wolflink.common.wolflinkrpc.entity.impl.handler.remote.RemoteHelpImpl

@RemoteCallHandler
class RemoteCommandHelp : RemoteHelpImpl("""
                                设置权限 {用户uniqueID} {权限名} - 权限等级必须低于发起者
                                添加服务器数据 {服务器名称}||{服务器标题}||{服务端文件夹}||{启动脚本名(带后缀)}
                                管理MC服务器 查询所有
                                管理MC服务器 启动 {服务器名称}
                                管理MC服务器 关闭 {服务器名称}
                                读取文件 {文件路径} {文件类型} - 文件路径用 |符号 作为文件夹分隔符，例如 C:|Program Files|Logs|latest log
                                运行文件 {文件路径} - 文件路径用 |符号 作为文件夹分隔符，例如 C:|Program Files|Java|java.exe
                                """.trimIndent())