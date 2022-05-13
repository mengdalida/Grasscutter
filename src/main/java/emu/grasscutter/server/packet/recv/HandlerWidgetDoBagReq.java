package emu.grasscutter.server.packet.recv;

import emu.grasscutter.net.packet.Opcodes;
import emu.grasscutter.net.packet.PacketHandler;
import emu.grasscutter.net.packet.PacketOpcodes;
import emu.grasscutter.net.proto.WearEquipReqOuterClass;
import emu.grasscutter.net.proto.WidgetDoBagReqOuterClass;
import emu.grasscutter.server.game.GameSession;
import emu.grasscutter.server.packet.send.PacketWearEquipRsp;
import emu.grasscutter.server.packet.send.PacketWidgetDoBagRsp;

@Opcodes(PacketOpcodes.WidgetDoBagReq)
public class HandlerWidgetDoBagReq extends PacketHandler {

    @Override
    public void handle(GameSession session, byte[] header, byte[] payload) throws Exception {
        WidgetDoBagReqOuterClass.WidgetDoBagReq req = WidgetDoBagReqOuterClass.WidgetDoBagReq.parseFrom(payload);

        session.send(new PacketWidgetDoBagRsp(req.getMaterialId()));
    }

}
