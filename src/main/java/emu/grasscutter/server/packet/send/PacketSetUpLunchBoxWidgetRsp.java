package emu.grasscutter.server.packet.send;

import emu.grasscutter.net.packet.BasePacket;
import emu.grasscutter.net.packet.PacketOpcodes;
import emu.grasscutter.net.proto.SetUpLunchBoxWidgetRspOuterClass.SetUpLunchBoxWidgetRsp;
import emu.grasscutter.net.proto.LunchBoxDataOuterClass.LunchBoxData;

public class PacketSetUpLunchBoxWidgetRsp extends BasePacket {

	public PacketSetUpLunchBoxWidgetRsp(LunchBoxData lunchBoxData) {
		super(PacketOpcodes.SetUpLunchBoxWidgetRsp);

		SetUpLunchBoxWidgetRsp proto = SetUpLunchBoxWidgetRsp.newBuilder()
				.setLunchBoxData(lunchBoxData)
				.build();
		
		this.setData(proto);
	}
}
