package oovu.environment;

import oovu.nodes.Node;
import com.cycling74.max.Atom;

abstract public class InterfaceHandler {

    abstract public String get_name();

    abstract public Atom[][] run(Node context, Atom[] arguments);

}