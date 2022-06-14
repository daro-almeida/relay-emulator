package relay.messaging;

import io.netty.buffer.ByteBuf;
import pt.unl.fct.di.novasys.network.ISerializer;
import pt.unl.fct.di.novasys.network.data.Host;

import java.io.IOException;

public class RelayMessageSerializer implements ISerializer<RelayMessage> {

	@Override
	public void serialize(RelayMessage relayMessage, ByteBuf out) throws IOException {
		out.writeInt(relayMessage.getType().opCode);
		out.writeInt(relayMessage.seqN);
		Host.serializer.serialize(relayMessage.from, out);
		Host.serializer.serialize(relayMessage.to, out);
		relayMessage.getType().serializer.serialize(relayMessage, out);
	}

	@Override
	public RelayMessage deserialize(ByteBuf in) throws IOException {
		RelayMessage.Type type = RelayMessage.Type.fromOpcode(in.readInt());
		int seqN = in.readInt();
		Host from = Host.serializer.deserialize(in);
		Host to = Host.serializer.deserialize(in);
		return type.serializer.deserialize(seqN, from, to, in);
	}

}
