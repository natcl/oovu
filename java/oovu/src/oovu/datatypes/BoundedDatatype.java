package oovu.datatypes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import oovu.environment.InterfaceHandler;
import oovu.nodes.AttributeNode;
import oovu.nodes.Node;
import com.cycling74.max.Atom;

abstract public class BoundedDatatype extends GenericDatatype {

    private class GetMaximumInterfaceHandler extends InterfaceHandler {

        @Override
        public String get_name() {
            return "getmaximum";
        }

        @Override
        public Atom[][] run(Node node, Atom[] arguments) {
            Atom[][] result = new Atom[1][];
            Float maximum = BoundedDatatype.this.get_maximum();
            String label = "maximum";
            if (maximum != null) {
                result[0] = Atom.newAtom(label,
                    Atom.newAtom(new float[] { maximum }));
            } else {
                result[0] = Atom.newAtom(new String[] { label });
            }
            return result;
        }
    }

    private class GetMinimumInterfaceHandler extends InterfaceHandler {

        @Override
        public String get_name() {
            return "getminimum";
        }

        @Override
        public Atom[][] run(Node node, Atom[] arguments) {
            Atom[][] result = new Atom[1][];
            Float minimum = BoundedDatatype.this.get_minimum();
            String label = "minimum";
            if (minimum != null) {
                result[0] = Atom.newAtom(label,
                    Atom.newAtom(new float[] { minimum }));
            } else {
                result[0] = Atom.newAtom(new String[] { label });
            }
            return result;
        }
    }

    private class SetMaximumInterfaceHandler extends InterfaceHandler {

        @Override
        public String get_name() {
            return "maximum";
        }

        @Override
        public Atom[][] run(Node node, Atom[] arguments) {
            Float maximum = null;
            if (0 < arguments.length) {
                maximum = arguments[0].getFloat();
            }
            BoundedDatatype.this.set_maximum(maximum);
            return null;
        }

    }

    private class SetMinimumInterfaceHandler extends InterfaceHandler {

        @Override
        public String get_name() {
            return "minimum";
        }

        @Override
        public Atom[][] run(Node node, Atom[] arguments) {
            Float minimum = null;
            if (0 < arguments.length) {
                minimum = arguments[0].getFloat();
            }
            BoundedDatatype.this.set_minimum(minimum);
            return null;
        }

    }

    protected Float minimum = null;
    protected Float maximum = null;

    public BoundedDatatype(AttributeNode client,
        Map<String, Atom[]> argument_map) {
        super(client, argument_map);
        if (this.client != null) {
            this.client.add_interface_handler(new GetMaximumInterfaceHandler());
            this.client.add_interface_handler(new GetMinimumInterfaceHandler());
            this.client.add_interface_handler(new SetMaximumInterfaceHandler());
            this.client.add_interface_handler(new SetMinimumInterfaceHandler());
        }
        this.initialize_extrema(argument_map);
    }

    protected Float get_maximum() {
        return this.maximum;
    }

    protected Float get_minimum() {
        return this.minimum;
    }

    protected void initialize_extrema(Map<String, Atom[]> argument_map) {
        if (argument_map.containsKey("minimum")) {
            this.set_minimum(
                this.extract_floats_from_atoms(argument_map.get("minimum"))[0]);
        }
        if (argument_map.containsKey("maximum")) {
            this.set_maximum(argument_map.get("maximum")[0].toFloat());
        }
    }

    protected void set_maximum(Float maximum) {
        this.maximum = maximum;
        this.sort_extrema();
    }

    protected void set_minimum(Float minimum) {
        this.minimum = minimum;
        this.sort_extrema();
    }

    protected void sort_extrema() {
        if ((this.minimum != null) && (this.maximum != null)) {
            Float[] extrema = new Float[] { this.minimum, this.maximum };
            Arrays.sort(extrema);
            this.minimum = extrema[0];
            this.maximum = extrema[1];
        }
    }
    
    protected Float[] extract_floats_from_atoms(Atom[] atoms) {
        ArrayList<Float> floats = new ArrayList<Float>();
        for (Atom atom : atoms) {
            floats.add(atom.toFloat());
        }
        return floats.toArray(new Float[0]);
    }
    
    protected Integer[] extract_ints_from_atoms(Atom[] atoms) {
        ArrayList<Integer> ints = new ArrayList<Integer>();
        for (Atom atom : atoms) {
            ints.add(atom.toInt());
        }
        return ints.toArray(new Integer[0]);
    }
}