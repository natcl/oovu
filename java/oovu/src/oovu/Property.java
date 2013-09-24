package oovu;

import oovu.nodes.PropertyNode;
import oovu.proxies.NodeProxy;

import com.cycling74.max.Atom;
import com.cycling74.max.MaxObject;

public class Property extends NodeProxy {

    public Property(Atom[] arguments) {
        if (arguments.length < 2) {
            MaxObject.bail("Bad arguments.");
        } else if (!arguments[0].isInt()) {
            MaxObject.bail("Bad arguments.");
        }
        this.declareIO(2, 2);
        Integer module_id = arguments[0].toInt();
        String desired_name = arguments[1].toString();
        this.node = PropertyNode.allocate(module_id, desired_name,
            Atom.removeFirst(arguments, 2));
        this.node.node_proxies.add(this);
    }

}
