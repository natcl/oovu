package oovu.servers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import oovu.addressing.OscAddressNode;
import oovu.clients.ServerClient;
import oovu.messaging.InterfaceRequest;
import oovu.messaging.InterfaceResponse;
import oovu.messaging.MessageHandler;
import oovu.messaging.MessagePasser;
import oovu.messaging.Request;
import oovu.messaging.Response;
import oovu.messaging.ValueRequest;

import org.apache.commons.lang3.ArrayUtils;

import com.cycling74.max.Atom;
import com.cycling74.max.MaxObject;

abstract public class Server implements MessagePasser {

    private class DumpMetaMessageHandler extends MessageHandler {

        @Override
        public String get_name() {
            return "dumpmeta";
        }

        @Override
        public Atom[][] run(Atom[] arguments) {
            ArrayList<Atom[]> result = new ArrayList<Atom[]>();
            MessageHandler getMetaMessageHandler = Server.this.message_handlers
                .get("getmeta");
            Atom[] meta = Atom
                .removeFirst(getMetaMessageHandler.run(arguments)[0]);
            for (Atom name : meta) {
                MessageHandler message_handler = Server.this.message_handlers
                    .get(name.toString());
                if (message_handler == null) {
                    continue;
                }
                for (Atom[] subresult : message_handler.run(null)) {
                    result.add(subresult);
                }
            }
            return result.toArray(new Atom[0][]);
        }
    }

    private class GetInterfaceMessageHandler extends MessageHandler {

        @Override
        public String get_name() {
            return "getinterface";
        }

        @Override
        public Atom[][] run(Atom[] arguments) {
            Atom[][] result = new Atom[1][];
            String[] message_handler_names = Server.this.message_handlers
                .keySet().toArray(new String[0]);
            Arrays.sort(message_handler_names);
            result[0] = Atom.newAtom("interface",
                Atom.newAtom(message_handler_names));
            return result;
        }
    }

    private class GetMetaMessageHandler extends MessageHandler {

        @Override
        public String get_name() {
            return "getmeta";
        }

        @Override
        public Atom[][] run(Atom[] arguments) {
            Atom[][] result = new Atom[1][];
            ArrayList<Atom> getters = new ArrayList<Atom>();
            String[] message_handler_names = Server.this.message_handlers
                .keySet().toArray(new String[0]);
            Arrays.sort(message_handler_names);
            for (String message_handler_name : message_handler_names) {
                if (message_handler_name.startsWith("get")) {
                    getters.add(Atom.newAtom(message_handler_name));
                }
            }
            result[0] = Atom.newAtom("meta", getters.toArray(new Atom[0]));
            return result;
        }
    }

    private class GetNameMessageHandler extends MessageHandler {

        @Override
        public String get_name() {
            return "getname";
        }

        @Override
        public Atom[][] run(Atom[] arguments) {
            Atom[][] result = new Atom[1][];
            result[0] = Atom.newAtom(new String[] {
                "name", Server.this.get_name()
            });
            return result;
        }
    }

    private class GetOscAddressMessageHandler extends MessageHandler {

        @Override
        public String get_name() {
            return "getoscaddress";
        }

        @Override
        public Atom[][] run(Atom[] arguments) {
            Atom[][] result = new Atom[1][];
            result[0] = Atom.newAtom(new String[] {
                "oscaddress", Server.this.get_osc_address()
            });
            return result;
        }
    }

    private class ReportMessageHandler extends MessageHandler {

        @Override
        public String get_name() {
            return "report";
        }

        @Override
        public Atom[][] run(Atom[] arguments) {
            String[] report_pieces = Server.this.get_report_pieces();
            for (String report_piece : report_pieces) {
                MaxObject.post(report_piece + "\n");
            }
            return null;
        }
    }

    private class ShowMessageHandler extends MessageHandler {

        @Override
        public String get_name() {
            return "show";
        }

        @Override
        public Atom[][] run(Atom[] arguments) {
            for (ServerClient server_client : Server.this.server_clients) {
                server_client.getMaxBox().getPatcher()
                    .send("front", new Atom[0]);
            }
            return null;
        }
    }

    public static Map<String, Atom[]> process_atom_arguments(Atom[] arguments) {
        HashMap<String, Atom[]> argument_map = new HashMap<String, Atom[]>();
        String current_key = null;
        ArrayList<Atom> current_list = new ArrayList<Atom>();
        for (Atom argument : arguments) {
            if ((current_key == null) && (argument.toString().charAt(0) != ':')) {
                continue;
            }
            if (argument.toString().charAt(0) == ':') {
                if ((current_key != null) && (0 < current_list.size())) {
                    argument_map.put(current_key,
                        current_list.toArray(new Atom[current_list.size()]));
                    current_list.clear();
                }
                current_key = argument.toString().substring(1);
            } else {
                current_list.add(argument);
            }
        }
        if (0 < current_list.size()) {
            argument_map.put(current_key,
                current_list.toArray(new Atom[current_list.size()]));
        }
        return Collections.unmodifiableMap(argument_map);
    }

    public final Map<String, Atom[]> argument_map;
    protected final Set<Server> child_servers = new HashSet<Server>();
    protected final Map<String, MessageHandler> message_handlers = new HashMap<String, MessageHandler>();
    protected String name = null;
    protected Server parent_server = null;
    protected OscAddressNode osc_address_node = null;
    public final Set<ServerClient> server_clients = new HashSet<ServerClient>();

    public Server(Map<String, Atom[]> argument_map) {
        if (argument_map != null) {
            this.argument_map = Collections.unmodifiableMap(argument_map);
        } else {
            this.argument_map = null;
        }
        this.add_message_handler(new DumpMetaMessageHandler());
        this.add_message_handler(new GetMetaMessageHandler());
        this.add_message_handler(new GetInterfaceMessageHandler());
        this.add_message_handler(new GetNameMessageHandler());
        this.add_message_handler(new GetOscAddressMessageHandler());
        this.add_message_handler(new ReportMessageHandler());
        this.add_message_handler(new ShowMessageHandler());
    }

    public void add_message_handler(MessageHandler message_handler) {
        this.message_handlers.put(message_handler.get_name(), message_handler);
    }

    public void attach_to_osc_address_node(OscAddressNode osc_address_node) {
        this.detach_from_osc_address_node();
        this.osc_address_node = osc_address_node;
        if (osc_address_node != null) {
            this.osc_address_node.set_server(this);
        }
    }

    public void attach_to_parent_server(Server parent_server) {
        this.detach_from_parent_server();
        this.parent_server = parent_server;
        if (parent_server != null) {
            parent_server.child_servers.add(this);
        }
    }

    public void clear() {
        for (Server child_server : this.child_servers.toArray(new Server[0])) {
            child_server.detach_from_parent_server();
        }
        this.detach_from_parent_server();
        for (ServerClient server_client : this.server_clients) {
            server_client.detach_from_server();
        }
        this.detach_from_osc_address_node();
    }

    protected void deallocate() {
        Server parent_server = this.get_parent_server();
        this.clear();
        if (parent_server != null) {
            parent_server.deallocate_if_necessary();
        }
    }

    public void deallocate_if_necessary() {
        if (this.get_reference_count() == 0) {
            this.deallocate();
        }
    }

    public void detach_from_osc_address_node() {
        if (this.osc_address_node != null) {
            this.osc_address_node.relinquish_number();
            this.osc_address_node.set_server(null);
            this.osc_address_node.prune_if_necessary();
        }
        this.osc_address_node = null;
    }

    public void detach_from_parent_server() {
        if (this.parent_server != null) {
            this.parent_server.child_servers.remove(this);
        }
        this.parent_server = null;
    }

    public String get_name() {
        return this.name;
    }

    @Override
    public String get_osc_address() {
        if (this.get_osc_address_node() == null) {
            return null;
        }
        OscAddressNode[] parentage = this.get_osc_address_node()
            .get_parentage();
        ArrayUtils.reverse(parentage);
        StringBuilder string_builder = new StringBuilder();
        for (OscAddressNode osc_address_node : parentage) {
            if (osc_address_node.get_name() == null) {
                return null;
            }
            if (!osc_address_node.get_name().equals("")) {
                string_builder.append("/");
                string_builder.append(osc_address_node.get_name());
            }
        }
        return string_builder.toString();
    }

    @Override
    public OscAddressNode get_osc_address_node() {
        return this.osc_address_node;
    }

    public Server get_parent_server() {
        return this.parent_server;
    }

    public int get_reference_count() {
        return this.server_clients.size() + this.child_servers.size();
    }

    public String[] get_report_pieces() {
        return new String[0];
    }

    public void handle_interface_request(InterfaceRequest request) {
        MessageHandler message_handler = this.message_handlers
            .get(request.message_handler_name);
        if (message_handler == null) {
            return;
        }
        Atom[][] payload = message_handler.run(request.payload);
        if (payload == null) {
            return;
        }
        Response response = new InterfaceResponse(this, payload, request);
        request.source.handle_response(response);
    }

    @Override
    public void handle_request(Request request) {
        if (request == null) {
            return;
        }
        if (ValueRequest.class.isInstance(request)) {
            this.handle_value_request((ValueRequest) request);
        } else if (InterfaceRequest.class.isInstance(request)) {
            this.handle_interface_request((InterfaceRequest) request);
        }
    }

    @Override
    public void handle_response(Response response) {
        if (response == null) {
            return;
        }
        for (ServerClient server_client : this.server_clients) {
            server_client.handle_response(response);
        }
        if (this.parent_server != null) {
            this.parent_server.handle_response(response);
        }
    }

    public void handle_value_request(ValueRequest request) {
    }

    @Override
    public String toString() {
        return "<" + this.getClass().getSimpleName() + ": "
            + this.get_osc_address() + ">";
    }
}
