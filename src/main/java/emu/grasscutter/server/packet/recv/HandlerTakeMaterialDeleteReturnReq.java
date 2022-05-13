package emu.grasscutter.server.packet.recv;

import emu.grasscutter.net.packet.Opcodes;
import emu.grasscutter.net.packet.PacketHandler;
import emu.grasscutter.net.packet.PacketOpcodes;
import emu.grasscutter.net.proto.TakeMaterialDeleteReturnReqOuterClass;
import emu.grasscutter.server.game.GameSession;
import emu.grasscutter.server.packet.send.PacketTakeMaterialDeleteReturnRsp;

@Opcodes(PacketOpcodes.TakeMaterialDeleteReturnReq)
public class HandlerTakeMaterialDeleteReturnReq extends PacketHandler {
    @Override
    public void handle(GameSession session, byte[] header, byte[] payload) throws Exception {
        TakeMaterialDeleteReturnReqOuterClass.TakeMaterialDeleteReturnReq req = TakeMaterialDeleteReturnReqOuterClass.TakeMaterialDeleteReturnReq.parseFrom(payload);

        session.send(new PacketTakeMaterialDeleteReturnRsp());

    }
}