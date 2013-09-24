package oovu.environment;

import java.util.HashMap;
import java.util.HashSet;

import oovu.Binding;
import oovu.servers.AudioServer;
import oovu.servers.ModuleServer;
import oovu.servers.RootServer;

public class Environment {

    public static final HashMap<Integer, ModuleServer> modules_by_module_id = new HashMap<Integer, ModuleServer>();
    //public static final HashMap<String, Node> osc_addresses = new HashMap<String, Node>();
    public static final HashMap<String, AudioServer> pull_addresses = new HashMap<String, AudioServer>();
    public static final HashMap<String, AudioServer> push_addresses = new HashMap<String, AudioServer>();
    public static final RootServer root_node;
    public static final OscAddressNode root_osc_address_node;
    public static final HashMap<OscAddress, HashSet<Binding>> waiting_bindings = 
    	new HashMap<OscAddress, HashSet<Binding>>();
    
    static {
    	root_node = new RootServer();
    	root_osc_address_node = new OscAddressNode("");
    	root_osc_address_node.set_node(root_node);
    }
    
    public static void reset() {
        Environment.modules_by_module_id.clear();
        Environment.root_node.clear();
        Environment.root_osc_address_node.clear();
        //Environment.osc_addresses.clear();
        Environment.pull_addresses.clear();
        Environment.push_addresses.clear();
        Environment.root_osc_address_node.set_node(Environment.root_node);
    }

}
