package emu.grasscutter.http;

import ch.qos.logback.classic.Logger;
import emu.grasscutter.Grasscutter;
import emu.grasscutter.command.CommandMap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/**
 * @ClassName NettyHttpServerHandler
 * @Description
 * @Author xingzi
 * @Date 2019/8/27 11:30
 * @Version 1.0
 */
public class NettyHttpServerHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    private static final Logger log =Grasscutter.getLogger();

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, FullHttpRequest fullHttpRequest){
        ByteBuf buf=null;
        FullHttpResponse response;
        try {
            if (fullHttpRequest.method() == HttpMethod.GET) {
                String uri = fullHttpRequest.uri();
                String htmlName = fullHttpRequest.uri().substring(uri.indexOf("/") + 1);
                String data = readHtml(htmlName);
                buf = copiedBuffer(data, CharsetUtil.UTF_8);
                response = responseMsg(HttpResponseStatus.OK, buf);

            } else if (fullHttpRequest.method() == HttpMethod.POST) {
                String command=getPostParamsFromChannel(fullHttpRequest);
                log.info("HTTP Command: "+command);
                CommandMap.getInstance().invoke(null, null, command);
                buf = copiedBuffer("OK", CharsetUtil.UTF_8);
                response = responseMsg(HttpResponseStatus.OK, buf);
            } else {
                response = responseMsg(HttpResponseStatus.INTERNAL_SERVER_ERROR, null);
            }
        }catch (Exception e){
            log.error(e.getMessage(),e);
            response =responseMsg(HttpResponseStatus.BAD_GATEWAY,null);
        }
        // 发送响应
        channelHandlerContext.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);

        try{

            if(buf!=null){
                buf.release();
            }
            if( fullHttpRequest!=null){
                fullHttpRequest.release();
            }
            if(response!=null){
                response.release();
            }
        }catch (Exception e){}

    }

    private ByteBuf copiedBuffer(String data, Charset utf8) {
        if(data==null){
            data="";
        }
        return Unpooled.wrappedBuffer(data.getBytes(utf8));
    }

    private String getPostParamsFromChannel(FullHttpRequest fullHttpRequest) {
        String command = "";
        if (fullHttpRequest.method() == HttpMethod.POST) {
            // 处理post 请求
            ByteBuf buf = fullHttpRequest.content();
            byte[] bytes = new byte[buf.readableBytes()];//
            buf.getBytes(buf.readerIndex(), bytes);
            command = new String(bytes);
        }
        return command;
    }

    private FullHttpResponse responseMsg(HttpResponseStatus status, ByteBuf buf) {
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
                status, buf);
        if (buf != null) {
            response.headers().set("Content-Type", "text/html;charset=utf-8");
            response.headers().set("Content-Length", response.content().readableBytes());
        }
        return response;
    }

    private String readHtml(String name) {
        StringBuilder stringBuilder = new StringBuilder();
        File file = new File("src/main/resources/static/" + name);

        if(!file.exists()){
            throw new RuntimeException("File not found");
        }
        try (FileInputStream fis = new FileInputStream(file);
             InputStreamReader isr = new InputStreamReader(fis, CharsetUtil.UTF_8);
             BufferedReader br = new BufferedReader(isr);
        ) {
            String data;
            while ((data = br.readLine()) != null) {
                stringBuilder.append(data + "\n");
            }
            return stringBuilder.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
