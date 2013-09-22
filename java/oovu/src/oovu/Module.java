package oovu;

import oovu.nodes.*;
import oovu.proxies.NodeProxy;
import com.cycling74.max.*;

public class Module extends NodeProxy {

    public Module(Atom[] arguments) {
        if (arguments.length < 2) {
            MaxObject.bail("Bad arguments.");
        } else if (!arguments[0].isInt()) {
            MaxObject.bail("Bad arguments.");
        }
        this.declareIO(2, 1);
        Integer module_id = arguments[0].toInt();
        String desired_name = arguments[1].toString();
        this.node = ModuleNode.allocate(module_id);
        this.node.register_name(desired_name);
        this.node.node_proxies.add(this);
    }

    @Override
    public void output_value_response_payload(Atom[] payload) {
        this.outlet(0, payload);
    }
    
}