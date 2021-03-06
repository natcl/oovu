package oovu.addresses;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import oovu.Proxy;
import oovu.servers.Server;

public class OscAddressNode implements Comparable<OscAddressNode> {

    public static
        String
        find_unique_name(String desired_name, Set<String> names) {
        if (!names.contains(desired_name)) {
            return desired_name;
        }
        Integer counter = 1;
        String acquired_name = desired_name + '.' + counter.toString();
        while (names.contains(acquired_name)) {
            counter += 1;
            acquired_name = desired_name + '.' + counter.toString();
        }
        return acquired_name;
    }

    private String name;
    private Integer number;
    private final Set<Proxy> proxies = new HashSet<Proxy>();
    private final Map<String, OscAddressNode> named_children =
        new HashMap<String, OscAddressNode>();
    private final Map<Integer, OscAddressNode> numbered_children =
        new HashMap<Integer, OscAddressNode>();
    private OscAddressNode parent = null;
    private Server server = null;

    public OscAddressNode(Integer number) {
        this(null, number);
    }

    public OscAddressNode(String name) {
        this(name, null);
    }

    private OscAddressNode(String name, Integer number) {
        this.name = name;
        this.number = number;
    }

    public String acquire_name(String desired_name) {
        if (desired_name == this.name) {
            return desired_name;
        } else if ((desired_name == null)
            || ((this.name != null) && (this.server != null))) {
            throw new RuntimeException();
        }
        if (this.parent == null) {
            this.name = desired_name;
            return desired_name;
        } else if (!this.parent.named_children.containsKey(desired_name)) {
            this.name = desired_name;
            this.parent.named_children.put(desired_name, this);
            return desired_name;
        } else if (this.parent.named_children.get(desired_name).get_server() == null) {
            this.parent.named_children.get(desired_name).merge_with(this);
            return desired_name;
        } else {
            Set<String> names = new HashSet<String>();
            for (String name : this.parent.named_children.keySet()) {
                if (this.parent.named_children.get(name).get_server() != null) {
                    names.add(name);
                }
            }
            String acquired_name =
                OscAddressNode.find_unique_name(desired_name, names);
            if (this.parent.named_children.containsKey(acquired_name)) {
                this.parent.named_children.get(acquired_name).merge_with(this);
            } else {
                this.name = acquired_name;
                this.parent.add_child(this);
            }
            return acquired_name;
        }
    }

    public void add_child(OscAddressNode child) {
        OscAddressNode[] parentage = this.get_parentage();
        if (Arrays.asList(parentage).contains(child)) {
            return;
        }
        if (child.get_parent() != null) {
            child.get_parent().remove_child(child);
        }
        if (child.name != null) {
            if (this.named_children.get(child.name) != null) {
                throw new RuntimeException("Named child already exists!: "
                    + child.name);
            }
            this.named_children.put(child.name, child);
        }
        if (child.number != null) {
            if (this.numbered_children.get(child.number) != null) {
                throw new RuntimeException("Numbered child already exists!: "
                    + child.number);
            }
            this.numbered_children.put(child.number, child);
        }
        child.parent = this;
    }

    public void add_proxy(Proxy proxy) {
        this.proxies.add(proxy);
    }

    public void clear() {
        for (OscAddressNode child : this.get_all_children()) {
            this.remove_child(child);
        }
        if (this.parent != null) {
            this.parent.remove_child(this);
        }
    }

    @Override
    public int compareTo(OscAddressNode other) {
        if (this.name == null) {
            if (other.name == null) {
                return this.number - other.number;
            } else {
                return -1;
            }
        } else {
            if (other.name != null) {
                return this.name.compareTo(other.name);
            } else {
                return 1;
            }
        }
    }

    public OscAddressNode create_address(
        OscAddress osc_address,
        boolean uniquely) {
        if (osc_address.has_wildcard_tokens
            || osc_address.has_parent_path_tokens) {
            throw new RuntimeException("OSC address is ambiguous: "
                + osc_address);
        } else if (osc_address.is_relative
            && (osc_address.address_items.length == 0)) {
            return this;
        }
        OscAddressNode parent = this;
        if (!osc_address.is_relative) {
            parent = this.get_root();
        }
        OscAddressNode child = null;
        for (int i = 0; i < osc_address.address_items.length; i++) {
            String name = osc_address.address_items[i];
            if ((i == (osc_address.address_items.length - 1)) && uniquely) {
                Set<String> names = parent.named_children.keySet();
                name = OscAddressNode.find_unique_name(name, names);
            }
            child = parent.get_named_child(name);
            if (child == null) {
                child = new OscAddressNode(name, null);
                parent.add_child(child);
            }
            parent = child;
        }
        return child;
    }

    public Set<OscAddressNode> get_all_children() {
        Set<OscAddressNode> all_children = new HashSet<OscAddressNode>();
        all_children.addAll(this.named_children.values());
        all_children.addAll(this.numbered_children.values());
        return all_children;
    }

    public String get_debug_piece() {
        StringBuilder string_builder = new StringBuilder();
        string_builder.append("<Node ");
        if (this.name != null) {
            string_builder.append("'" + this.name + "'");
        } else {
            string_builder.append("null");
        }
        string_builder.append(":");
        if (this.number != null) {
            string_builder.append(this.number);
        } else {
            string_builder.append("null");
        }
        if ((0 < this.proxies.size()) || (this.server != null)) {
            string_builder.append(" (");
            if (0 < this.proxies.size()) {
                string_builder.append("proxies: ");
                string_builder.append(this.proxies.size());
                if (this.server != null) {
                    string_builder.append(", ");
                }
            }
            if (this.server != null) {
                string_builder.append("server: "
                    + this.server.getClass().getSimpleName());
            }
            string_builder.append(")");
        }
        string_builder.append(">");
        return string_builder.toString();
    }

    public String[] get_debug_pieces() {
        ArrayList<String> pieces = new ArrayList<String>();
        pieces.add(this.get_debug_piece());
        ArrayList<OscAddressNode> all_children =
            new ArrayList<OscAddressNode>(this.get_all_children());
        Collections.sort(all_children);
        for (OscAddressNode child : all_children) {
            for (String child_piece : child.get_debug_pieces()) {
                pieces.add("...." + child_piece);
            }
        }
        return pieces.toArray(new String[0]);
    }

    public String get_name() {
        return this.name;
    }

    public OscAddressNode get_named_child(String name) {
        return this.named_children.get(name);
    }

    public int get_number() {
        return this.number;
    }

    public OscAddressNode get_numbered_child(Integer number) {
        return this.numbered_children.get(number);
    }

    public OscAddress get_osc_address() {
        String osc_address_string = this.get_osc_address_string();
        if (osc_address_string == null) {
            return null;
        }
        return OscAddress.from_cache(this.get_osc_address_string());
    }

    public String get_osc_address_string() {
        ArrayList<String> names = new ArrayList<String>();
        for (OscAddressNode osc_address_node : this.get_parentage()) {
            if (osc_address_node.name == null) {
                return null;
            }
            names.add(osc_address_node.name);
        }
        Collections.reverse(names);
        StringBuilder string_builder = new StringBuilder();
        for (String name : names) {
            if (name.equals("")) {
                continue;
            }
            string_builder.append("/");
            string_builder.append(name);
        }
        return string_builder.toString();
    }

    public OscAddressNode get_parent() {
        return this.parent;
    }

    public OscAddressNode[] get_parentage() {
        ArrayList<OscAddressNode> parentage = new ArrayList<OscAddressNode>();
        OscAddressNode child = this;
        OscAddressNode parent = this.parent;
        parentage.add(child);
        while (parent != null) {
            parentage.add(parent);
            child = parent;
            parent = child.parent;
        }
        return parentage.toArray(new OscAddressNode[0]);
    }

    public Set<Proxy> get_proxies() {
        return Collections.unmodifiableSet(this.proxies);
    }

    public int get_reference_count() {
        int count = this.get_all_children().size();
        count += this.proxies.size();
        if (this.server != null) {
            count += 1;
        }
        return count;
    }

    public String get_relative_osc_address_string(
        OscAddressNode relative_osc_address_node) {
        List<OscAddressNode> source_parentage =
            Arrays.asList(this.get_parentage());
        List<OscAddressNode> relative_parentage =
            Arrays.asList(relative_osc_address_node.get_parentage());
        Collections.reverse(source_parentage);
        Collections.reverse(relative_parentage);
        int counter = 0;
        while ((counter < source_parentage.size())
            && (counter < relative_parentage.size())
            && (source_parentage.get(counter) == relative_parentage
                .get(counter))) {
            counter += 1;
        }
        StringBuilder string_builder = new StringBuilder();
        while (counter < source_parentage.size()) {
            string_builder.append("/");
            string_builder.append(source_parentage.get(counter).get_name());
            counter += 1;
        }
        String result = string_builder.toString();
        if (result.equals("")) {
            return null;
        }
        return result;
    }

    public OscAddressNode get_root() {
        OscAddressNode[] parentage = this.get_parentage();
        return parentage[parentage.length - 1];
    }

    public Server get_server() {
        return this.server;
    }

    public String[] get_summary_pieces() {
        ArrayList<String> pieces = new ArrayList<String>();
        if (this.name != "") {
            pieces.add("/" + this.name);
        }
        for (OscAddressNode child : this.named_children.values()) {
            for (String piece : child.get_summary_pieces()) {
                if (this.name != "") {
                    pieces.add("/" + this.name + piece);
                } else {
                    pieces.add(piece);
                }
            }
        }
        String[] sorted_pieces = pieces.toArray(new String[0]);
        Arrays.sort(sorted_pieces);
        return sorted_pieces;
    }

    public boolean is_empty() {
        if ((this.server == null) && (this.named_children.size() == 0)) {
            return true;
        }
        return false;
    }

    public boolean is_in_environment() {
        return this.get_root() == Environment.root_osc_address_node;
    }

    public boolean is_named() {
        return this.name != null;
    }

    public boolean is_numbered() {
        return this.number != null;
    }

    public boolean matches(String token) {
        if (token.equals("*") || this.name.equals(token)) {
            return true;
        } else if (this.name.contains(".") && token.contains(".")) {
            String[] token_parts = token.split("\\.");
            String[] name_parts = this.name.split("\\.");
            boolean[] matches = new boolean[] {
                false, false
            };
            if (token_parts[0].equals("*")
                || token_parts[0].equals(name_parts[0])) {
                matches[0] = true;
            }
            if (token_parts[1].equals("*")
                || token_parts[1].equals(name_parts[1])) {
                matches[1] = true;
            }
            if (matches[0] && matches[1]) {
                return true;
            }
        }
        return false;
    }

    public void merge_with(OscAddressNode other) {
        other.parent.remove_child(other);
        this.number = other.number;
        if (other.server != null) {
            other.server.attach_to_osc_address_node(this);
        }
        for (Proxy proxy : other.proxies) {
            proxy.attach(this);
        }
        Set<String> other_named_children = other.named_children.keySet();
        for (String child_name : other_named_children) {
            OscAddressNode child = other.get_named_child(child_name);
            if (this.named_children.containsKey(child_name)) {
                this.named_children.get(child_name).merge_with(child);
            } else {
                this.add_child(child);
            }
        }
        this.numbered_children.putAll(other.numbered_children);
        this.parent.add_child(this);
    }

    private void prune() {
        OscAddressNode[] parentage = this.get_parentage();
        parentage = Arrays.copyOf(parentage, parentage.length - 1);
        for (OscAddressNode osc_address_node : parentage) {
            if (!osc_address_node.is_empty()) {
                break;
            }
            osc_address_node.clear();
        }
    }

    public void prune_if_necessary() {
        if (0 == this.get_reference_count()) {
            this.prune();
        }
    }

    public void relinquish_name() {
        if ((this.name != null) && (this.parent != null)) {
            this.parent.named_children.remove(this.name);
        }
        this.name = null;
    }

    public void relinquish_number() {
        if ((this.number != null) && (this.parent != null)) {
            this.parent.numbered_children.remove(this.number);
        }
        this.number = null;
    }

    public void remove_child(OscAddressNode child) {
        if (child == null) {
            return;
        }
        if (child.name != null) {
            if (this.named_children.get(child.name) == child) {
                this.named_children.remove(child.name);
            }
        }
        if (child.number != null) {
            if (this.numbered_children.get(child.number) == child) {
                this.numbered_children.remove(child.number);
            }
        }
        child.parent = null;
        this.prune_if_necessary();
    }

    public void remove_proxy(Proxy proxy) {
        this.proxies.remove(proxy);
    }

    public Set<OscAddressNode> search(OscAddress osc_address) {
        Set<OscAddressNode> old_cursors = new HashSet<OscAddressNode>();
        Set<OscAddressNode> new_cursors = new HashSet<OscAddressNode>();
        if (osc_address.is_relative) {
            old_cursors.add(this);
        } else {
            old_cursors.add(this.get_root());
        }
        if (osc_address.address_items.length < 1) {
            return old_cursors;
        }
        for (String current_address_item : osc_address.address_items) {
            new_cursors.clear();
            for (OscAddressNode current_cursor : old_cursors) {
                if (current_address_item.equals("..")
                    && (current_cursor.get_parent() != null)) {
                    new_cursors.add(current_cursor.get_parent());
                } else if (current_address_item.contains("*")) {
                    for (OscAddressNode child : current_cursor.named_children
                        .values()) {
                        if (child.matches(current_address_item)) {
                            new_cursors.add(child);
                        }
                    }
                } else {
                    OscAddressNode child =
                        current_cursor.get_named_child(current_address_item);
                    if (child != null) {
                        new_cursors.add(child);
                    }
                }
            }
            old_cursors.clear();
            old_cursors.addAll(new_cursors);
        }
        return new_cursors;
    }

    public OscAddressNode search_for_one(OscAddress osc_address) {
        if (osc_address.has_wildcard_tokens) {
            return null;
        }
        Set<OscAddressNode> search_results = this.search(osc_address);
        if (0 == search_results.size()) {
            return null;
        } else {
            return search_results.toArray(new OscAddressNode[0])[0];
        }
    }

    public void set_server(Server server) {
        this.server = server;
    }

    @Override
    public String toString() {
        return this.get_osc_address_string();
    }
}
