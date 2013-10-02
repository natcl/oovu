package oovu;

import oovu.clients.ServerClient;
import oovu.servers.ModuleServer;

import com.cycling74.max.Atom;

public class Module extends ServerClient {

    public Module(Atom[] arguments) {
        this.declareIO(2, 2);
        this.check_arguments(arguments);
        Integer module_id = arguments[0].toInt();
        String desired_name = arguments[1].toString();
        System.out.print("OK!");
        ModuleServer module_server = ModuleServer.allocate(module_id);
        module_server.acquire_name(desired_name);
        this.attach_to_server(module_server);
        this.generate_message_passer_callback();
    }
}
