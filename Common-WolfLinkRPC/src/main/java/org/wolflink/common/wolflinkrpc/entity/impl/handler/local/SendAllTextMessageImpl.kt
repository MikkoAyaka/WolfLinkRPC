package org.wolflink.common.wolflinkrpc.entity.impl.handler.local

import org.wolflink.common.wolflinkrpc.api.enums.PermissionLevel
import org.wolflink.common.wolflinkrpc.api.interfaces.command.ILocalHandler
import org.wolflink.common.wolflinkrpc.entity.RPCDataPack
import org.wolflink.common.wolflinkrpc.entity.impl.databody.SimpleTextMessageBody
import org.wolflink.common.wolflinkrpc.entity.role.BroadcastReceiver
import org.wolflink.common.wolflinkrpc.entity.role.RPCUser
import org.wolflink.common.wolflinkrpc.service.MQService

open class SendAllTextMessageImpl : ILocalHandler {
    override fun getCommand(): String = "> 广播消息"

    override fun getPermission(): PermissionLevel = PermissionLevel.ADMIN
    override fun invoke(sender : RPCUser, args: List<String>): Pair<Boolean,String> {
        val message = args.joinToString(" ")
        val datapack = RPCDataPack.Builder()
            .setDatapackBody(SimpleTextMessageBody(message))
            .setSender(sender)
            .addReceiver(BroadcastReceiver())
            .build()
        MQService.sendDataPack(datapack)
        return Pair(true,"Command executed successfully .")
    }
}