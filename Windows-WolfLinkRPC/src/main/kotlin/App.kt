package org.wolflink.windows.wolflinkrpc

import org.wolflink.common.wolflinkrpc.RPCCore
import org.wolflink.common.wolflinkrpc.entity.impl.ConsoleSender
import org.wolflink.common.wolflinkrpc.service.RPCService
import java.util.Scanner

object App
{
    fun enable()
    {
        PersistenceCfg.loadCfg()
        RPCCore.initSystem(RPCConfiguration)

        initScanner()
        //下方无法触及
    }
    fun disable()
    {
        PersistenceCfg.saveCfg()
        RPCCore.closeSystem()
    }

    private fun initScanner()
    {
        RPCLogger.info("Input scanner has been initialized.")
        val scanner = Scanner(System.`in`)
        var inputString = scanner.nextLine()
        while (inputString != "stop")
        {
            RPCService.analyseCommand(ConsoleSender(RPCConfiguration.getQueueName(),RPCConfiguration.getClientType()),inputString)
            inputString = scanner.nextLine()
        }
        disable()
    }
}