package emu.grasscutter.server.packet.send;

import emu.grasscutter.net.packet.BasePacket;
import emu.grasscutter.net.packet.PacketOpcodes;
import emu.grasscutter.net.proto.LunchBoxDataOuterClass.LunchBoxData;
import emu.grasscutter.net.proto.SetWidgetSlotRspOuterClass.SetWidgetSlotRsp;
import emu.grasscutter.net.proto.WidgetSlotOpOuterClass.WidgetSlotOp;
import emu.grasscutter.net.proto.WidgetSlotTagOuterClass.WidgetSlotTag;

import java.util.List;

public class PacketSetWidgetSlotRsp extends BasePacket {

	public PacketSetWidgetSlotRsp(WidgetSlotOp op, List<WidgetSlotTag> tagList, int materialId) {
		super(PacketOpcodes.SetWidgetSlotRsp);

		SetWidgetSlotRsp proto = SetWidgetSlotRsp.newBuilder()
				.setOp(op)
				.addAllTagList(tagList)
				.setMaterialId(materialId)
				.build();
		this.setData(proto);
	}
}
