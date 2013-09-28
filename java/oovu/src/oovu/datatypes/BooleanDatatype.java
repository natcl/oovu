package oovu.datatypes;

import java.util.Map;

import oovu.messaging.DatatypeMessageHandler;
import oovu.servers.Server;
import oovu.servers.members.AttributeServer;

import com.cycling74.max.Atom;

public class BooleanDatatype extends GenericDatatype {

    private class ToggleMessageHandler extends DatatypeMessageHandler {

        public ToggleMessageHandler(AttributeServer attribute_server) {
            super(attribute_server);
        }

        @Override
        public void call_after() {
            this.attribute_server.reoutput_value();
        }

        @Override
        public String get_name() {
            return "toggle";
        }

        @Override
        public Atom[][] run(Atom[] arguments) {
            BooleanDatatype.this.toggle();
            return null;
        }
    }

    public BooleanDatatype(Atom[] arguments) {
        this(null, Server.process_atom_arguments(arguments));
    }

    public BooleanDatatype(AttributeServer client,
        Map<String, Atom[]> argument_map) {
        super(client, argument_map);
        if (this.client != null) {
            this.client.add_message_handler(new ToggleMessageHandler(
                this.client));
        }
    }

    @Override
    public Atom[] get_default() {
        return Atom.newAtom(new boolean[] {
            false
        });
    }

    @Override
    public Atom[] process_input(Atom[] input) {
        Atom[] result = new Atom[1];
        if (0 < input.length) {
            result[0] = Atom.newAtom(input[0].toBoolean());
        }
        return result;
    }

    public void toggle() {
        boolean value = this.get_value()[0].toBoolean();
        this.set_value(Atom.newAtom(new boolean[] {
            !value
        }));
    }
}
