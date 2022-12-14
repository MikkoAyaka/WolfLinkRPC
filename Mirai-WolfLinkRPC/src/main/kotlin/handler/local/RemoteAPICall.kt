package org.wolflink.mirai.wolflinkrpc.handler.local

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.wolflink.common.wolflinkrpc.api.annotations.LocalCallHandler
import org.wolflink.common.wolflinkrpc.api.interfaces.CallbackFunction
import org.wolflink.common.wolflinkrpc.entity.RPCDataPack
import org.wolflink.common.wolflinkrpc.entity.impl.handler.local.RemoteAPICallImpl
import org.wolflink.mirai.wolflinkrpc.App
import org.wolflink.mirai.wolflinkrpc.PersistenceConfig

@LocalCallHandler
class RemoteAPICall : RemoteAPICallImpl(object : CallbackFunction{
    override fun success(datapack: RPCDataPack) {
        for (groupID in PersistenceConfig.enabledGroups)
        {
            GlobalScope.launch{
                App.bot.getGroup(groupID)?.sendMessage(datapack.jsonObject.get("info").asString)
            }
        }
    }
    override fun failed(datapack: RPCDataPack) {
        for (groupID in PersistenceConfig.enabledGroups)
        {
            GlobalScope.launch{
                App.bot.getGroup(groupID)?.sendMessage("响应超时，未能在指定时间内接收到来自客户端的回应。")
            }
        }
    }
})