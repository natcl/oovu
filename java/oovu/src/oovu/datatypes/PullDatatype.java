package oovu.datatypes;

import java.util.Arrays;
import java.util.Map;

import oovu.environment.Environment;
import oovu.environment.InterfaceHandler;
import oovu.nodes.AttributeNode;
import oovu.nodes.Node;

import com.cycling74.max.Atom;

public class PullDatatype extends OscAddressDatatype {

    private class GetPullAddressesInterfaceHandler extends InterfaceHandler {

        @Override
        public String get_name() {
            return "getpulladdresses";
        }

        @Override
        public Atom[][] run(Node context, Atom[] arguments) {
            Atom[][] result = new Atom[1][];
            String[] pull_addresses = Environment.pull_addresses.keySet()
                .toArray(new String[0]);
            Arrays.sort(pull_addresses);
            result[0] = Atom.newAtom("addresses", Atom.newAtom(pull_addresses));
            return result;
        }
    }

    public PullDatatype(AttributeNode client, Map<String, Atom[]> argument_map) {
        super(client, argument_map);
        if (client != null) {
            client
                .add_interface_handler(new GetPullAddressesInterfaceHandler());
        }
    }

}
