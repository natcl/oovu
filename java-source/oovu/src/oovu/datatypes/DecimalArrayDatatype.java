package oovu.datatypes;

import java.util.Map;

import oovu.servers.AttributeServer;
import oovu.servers.Server;
import oovu.timing.MultiEnvelope;

import com.cycling74.max.Atom;

public class DecimalArrayDatatype extends BoundedArrayDatatype {

    public DecimalArrayDatatype(Atom[] arguments) {
        this(null, Server.process_atom_arguments(arguments));
    }

    public DecimalArrayDatatype(AttributeServer client,
        Map<String, Atom[]> argument_map) {
        super(client, argument_map);
        this.multi_envelope =
            new MultiEnvelope(this, Atom.toDouble(this.value));
    }

    @Override
    public Atom[] get_default() {
        return Atom.newAtom(new double[] {
            0.
        });
    }

    @Override
    public Atom[] process_input(Atom[] input) {
        if (this.multi_envelope == null) {
            input = this.ensure_length(input);
        }
        double[] doubles = this.extract_doubles_from_atoms(input);
        if (this.multi_envelope != null) {
            doubles = this.multi_envelope.control_all_envelopes(doubles);
        }
        doubles = this.bound_doubles(doubles);
        Atom[] output = new Atom[doubles.length];
        for (int i = 0, j = doubles.length; i < j; i++) {
            output[i] = Atom.newAtom(doubles[i]);
        }
        return output;
    }
}
