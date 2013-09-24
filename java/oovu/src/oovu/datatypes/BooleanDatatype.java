package oovu.datatypes;

import java.util.Map;

import oovu.messaging.MessageHandler;
import oovu.servers.AttributeServer;
import oovu.servers.Server;

import com.cycling74.max.Atom;

public class BooleanDatatype extends GenericDatatype {

    private class ToggleMessageHandler extends MessageHandler {

        @Override
        public String get_name() {
            return "toggle";
        }

        @Override
        public Atom[][] run(Server node, Atom[] arguments) {
            AttributeServer attribute_node = (AttributeServer) node;
            BooleanDatatype.this.toggle();
            attribute_node.reoutput_value();
            return null;
        }
    }

    public BooleanDatatype(AttributeServer client,
        Map<String, Atom[]> argument_map) {
        super(client, argument_map);
        if (this.client != null) {
            this.client.add_message_handler(new ToggleMessageHandler());
        }
    }

    @Override
    public Atom[] get_default() {
        return Atom.newAtom(new boolean[] { false });
    }

    @Override
    public Atom[] process_input(Atom[] input) {
        Atom[] result = new Atom[1];
        if (0 < input.length) {
            if (input[0].isString()) {
                result[0] = Atom.newAtom(true);
            } else {
                result[0] = Atom.newAtom(input[0].toBoolean());
            }
        }
        return result;
    }
    
    public void toggle()  {
    	boolean value = this.get_value()[0].toBoolean();
    	this.set_value(Atom.newAtom(new boolean[]{! value}));
    }

}
