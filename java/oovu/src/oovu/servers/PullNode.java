package oovu.servers;

import java.util.Map;

import com.cycling74.max.Atom;

public class PullNode extends AudioNode {

    public static PullNode allocate(Integer module_id, String desired_name,
        Atom[] argument_list) {
        return (PullNode) ModuleMemberNode.allocate_from_label("PullNode",
            module_id, desired_name, argument_list);
    }

    public PullNode(ModuleNode module_node, Map<String, Atom[]> argument_map) {
        super(module_node, argument_map);
    }

    @Override
    public PullNode new_instance(Integer module_id,
        Map<String, Atom[]> argument_map) {
        ModuleNode module_node = ModuleNode.allocate(module_id);
        return new PullNode(module_node, argument_map);
    }

}