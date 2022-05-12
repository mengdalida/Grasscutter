package emu.grasscutter.server.packet.send;

import emu.grasscutter.net.packet.BasePacket;
import emu.grasscutter.net.packet.PacketOpcodes;
import emu.grasscutter.net.proto.SetWidgetSlotRspOuterClass.SetWidgetSlotRsp;
import emu.grasscutter.net.proto.SlotOuterClass;
import emu.grasscutter.net.proto.WidgetSlotChangeNotifyOuterClass.WidgetSlotChangeNotify;
import emu.grasscutter.net.proto.WidgetSlotDataOuterClass;
import emu.grasscutter.net.proto.WidgetSlotOpOuterClass.WidgetSlotOp;
import emu.grasscutter.net.proto.WidgetSlotTagOuterClass.WidgetSlotTag;

import java.util.List;

public class PacketWidgetSlotChangeNotify extends BasePacket {

	public PacketWidgetSlotChangeNotify(WidgetSlotOp op, int materialId) {
		super(PacketOpcodes.WidgetSlotChangeNotify);

		WidgetSlotChangeNotify proto = WidgetSlotChangeNotify.newBuilder()
				.setOp(op)
				.setSlot(WidgetSlotDataOuterClass.WidgetSlotData.newBuilder().setMaterialId(materialId).setIsActive(true).build())
				.build();
		this.setData(proto);
	}
}
