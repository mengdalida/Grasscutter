package emu.grasscutter.server.packet.send;

import emu.grasscutter.net.packet.BasePacket;
import emu.grasscutter.net.packet.PacketOpcodes;
import emu.grasscutter.net.proto.TakeMaterialDeleteReturnRspOuterClass;

public class PacketTakeMaterialDeleteReturnRsp  extends BasePacket {

    public PacketTakeMaterialDeleteReturnRsp() {
        super(PacketOpcodes.TakeMaterialDeleteReturnRsp);

        this.setData(TakeMaterialDeleteReturnRspOuterClass.TakeMaterialDeleteReturnRsp.newBuilder().build());
    }

}