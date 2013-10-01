package oovu;

import oovu.clients.AudioServerClient;
import oovu.servers.members.PullServer;

import com.cycling74.max.Atom;

public class PullSource extends AudioServerClient {

    public PullSource(Atom[] arguments) {
        this.declareIO(2, 1);
        this.check_arguments(arguments);
        Integer module_id = arguments[0].toInt();
        String desired_name = arguments[1].toString();
        this.server = PullServer.allocate(module_id, desired_name,
            Atom.removeFirst(arguments, 2));
        this.server.server_clients.add(this);
        this.generate_message_passer_callback();
    }
}
