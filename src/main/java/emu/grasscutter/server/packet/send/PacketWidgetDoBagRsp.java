package emu.grasscutter.server.packet.send;

import emu.grasscutter.net.packet.BasePacket;
import emu.grasscutter.net.packet.PacketOpcodes;
import emu.grasscutter.net.proto.WearEquipRspOuterClass;
import emu.grasscutter.net.proto.WidgetDoBagRspOuterClass;

public class PacketWidgetDoBagRsp  extends BasePacket {

    public PacketWidgetDoBagRsp(int materialId) {
        super(PacketOpcodes.WidgetDoBagRsp);

        this.setData(WidgetDoBagRspOuterClass.WidgetDoBagRsp.newBuilder().setMaterialId(materialId).build());
    }
}
