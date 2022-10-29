package org.wolflink.common.wolflinkrpc.api.interfaces.datapack

import com.google.gson.JsonObject

interface ICommandExecuteBody : IDataPackBody{
    fun getCommand() : String
    override fun toJsonObject(): JsonObject {
        val jsonObject = JsonObject()

        jsonObject.addProperty("command",getCommand())

        return jsonObject
    }
}