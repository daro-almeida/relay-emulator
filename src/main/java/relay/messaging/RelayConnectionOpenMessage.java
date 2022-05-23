package relay.messaging;

import io.netty.buffer.ByteBuf;
import pt.unl.fct.di.novasys.network.data.Host;

import java.io.IOException;

public class RelayConnectionOpenMessage extends RelayMessage {

    public RelayConnectionOpenMessage(Host from, Host to) {
        super(from, to, Type.CONN_OPEN);
    }

    public RelayConnectionOpenMessage(int seqN, Host from, Host to) {
        super(seqN, from, to, Type.CONN_OPEN);
    }

    public static final IRelaySerializer serializer = new IRelaySerializer<RelayConnectionOpenMessage>() {
        @Override
        public void serialize(RelayConnectionOpenMessage msg, ByteBuf out) throws IOException {
            //nothing to do here
        }

        @Override
        public RelayConnectionOpenMessage deserialize(int seqN, Host from, Host to, ByteBuf in) throws IOException {
            return new RelayConnectionOpenMessage(seqN, from, to);
        }
    };
}
