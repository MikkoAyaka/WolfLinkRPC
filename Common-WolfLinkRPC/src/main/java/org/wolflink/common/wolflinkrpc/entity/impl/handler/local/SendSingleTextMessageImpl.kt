package org.wolflink.common.wolflinkrpc.entity.impl.handler.local

import org.wolflink.common.wolflinkrpc.api.interfaces.command.ILocalHandler
import org.wolflink.common.wolflinkrpc.entity.RPCDataPack
import org.wolflink.common.wolflinkrpc.entity.impl.databody.SimpleTextMessageBody
import org.wolflink.common.wolflinkrpc.entity.role.ClientReceiver
import org.wolflink.common.wolflinkrpc.entity.role.RPCUser
import org.wolflink.common.wolflinkrpc.service.MQService

open class SendSingleTextMessageImpl : ILocalHandler {
    override fun getCommand(): String = "> 互通消息"
    override fun invoke(sender : RPCUser, args: List<String>):  Pair<Boolean,String> {
        if(args.size < 2)return Pair(false,"Not enough arguments .")
        val routingKey = args[0]
        val message = args.subList(1,args.size).joinToString(" ")
        val datapack = RPCDataPack.Builder()
            .setDatapackBody(SimpleTextMessageBody(message))
            .setSender(sender)
            .addReceiver(ClientReceiver(routingKey))
            .build()
        MQService.sendDataPack(datapack)
        return Pair(true,"Command executed successfully .")
    }
}