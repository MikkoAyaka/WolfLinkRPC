package org.wolflink.windows.wolflinkrpc.command

import org.wolflink.common.wolflinkrpc.api.annotations.LocalCallHandler
import org.wolflink.common.wolflinkrpc.api.enums.PermissionLevel
import org.wolflink.common.wolflinkrpc.api.interfaces.CallbackFunction
import org.wolflink.common.wolflinkrpc.entity.RPCDataPack
import org.wolflink.common.wolflinkrpc.entity.impl.handler.local.RemoteAPICallImpl
import org.wolflink.windows.wolflinkrpc.RPCLogger

@LocalCallHandler
class RemoteAPICall : RemoteAPICallImpl(object : CallbackFunction{
    override fun success(datapack: RPCDataPack) {
        RPCLogger.info(datapack.jsonObject.get("info").asString)
    }
    override fun failed(datapack: RPCDataPack) {
        RPCLogger.info("响应超时，未能在指定时间内接收到来自客户端的回应。")
    }
}){
    override fun getPermission(): PermissionLevel = PermissionLevel.DEFAULT
}