package emu.grasscutter.server.packet.recv;

import emu.grasscutter.net.packet.Opcodes;
import emu.grasscutter.net.packet.PacketHandler;
import emu.grasscutter.net.packet.PacketOpcodes;
import emu.grasscutter.net.proto.SetUpLunchBoxWidgetReqOuterClass.SetUpLunchBoxWidgetReq;
import emu.grasscutter.server.game.GameSession;
import emu.grasscutter.server.packet.send.PacketSetUpLunchBoxWidgetRsp;

@Opcodes(PacketOpcodes.SetUpLunchBoxWidgetReq)
public class HandlerSetUpLunchBoxWidgetReq extends PacketHandler {
	@Override
	public void handle(GameSession session, byte[] header, byte[] payload) throws Exception {
		SetUpLunchBoxWidgetReq req = SetUpLunchBoxWidgetReq.parseFrom(payload);

		session.getPlayer().sendPacket(new PacketSetUpLunchBoxWidgetRsp(req.getLunchBoxData()));
	}
}
