package org.inventivetalent.minetile;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.Unpooled;
import org.redisson.client.codec.BaseCodec;
import org.redisson.client.protocol.Decoder;
import org.redisson.client.protocol.Encoder;

import java.io.InputStreamReader;

public class RedissonGsonCodec extends BaseCodec {

	private final Gson GSON = new Gson();

	private final Encoder encoder = o -> {
		JsonObject json = new JsonObject();
		json.addProperty("type", o.getClass().getName());
		json.add("data", GSON.toJsonTree(o));
		return Unpooled.wrappedBuffer(GSON.toJson(json).getBytes());
	};

	private final Decoder<Object> decoder = (byteBuf, state) -> {
		JsonObject json = GSON.fromJson(new InputStreamReader(new ByteBufInputStream(byteBuf)), JsonObject.class);
		Class clazz;
		try {
			clazz = Class.forName(json.get("type").getAsString());
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException(e);
		}
		return GSON.fromJson(json.get("data"), clazz);
	};

	@Override
	public Decoder<Object> getValueDecoder() {
		return decoder;
	}

	@Override
	public Encoder getValueEncoder() {
		return encoder;
	}
}
