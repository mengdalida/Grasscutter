package emu.grasscutter.server.packet.recv;

import emu.grasscutter.net.packet.Opcodes;
import emu.grasscutter.net.packet.PacketHandler;
import emu.grasscutter.net.packet.PacketOpcodes;
import emu.grasscutter.net.proto.SetUpLunchBoxWidgetReqOuterClass.SetUpLunchBoxWidgetReq;
import emu.grasscutter.net.proto.SetWidgetSlotReqOuterClass.SetWidgetSlotReq;
import emu.grasscutter.server.game.GameSession;
import emu.grasscutter.server.packet.send.PacketSetUpLunchBoxWidgetRsp;
import emu.grasscutter.server.packet.send.PacketSetWidgetSlotRsp;
import emu.grasscutter.server.packet.send.PacketWidgetSlotChangeNotify;

@Opcodes(PacketOpcodes.SetWidgetSlotReq)
public class HandlerSetWidgetSlotReq extends PacketHandler {
	@Override
	public void handle(GameSession session, byte[] header, byte[] payload) throws Exception {
		SetWidgetSlotReq req = SetWidgetSlotReq.parseFrom(payload);

		session.send(new PacketSetWidgetSlotRsp(req.getOp(),req.getTagListList(),req.getMaterialId()));
		session.send(new PacketWidgetSlotChangeNotify(req.getOp(),req.getMaterialId()));

	}
}
