package oovu.servers;

import java.util.HashMap;
import java.util.Map;

import oovu.addresses.OscAddress;
import oovu.events.Event;
import oovu.events.EventTypes;

import com.cycling74.max.Atom;

public class AudioReceiveServer extends AudioServer {

    public final static Map<OscAddress, AudioReceiveServer> audio_receive_servers =
        new HashMap<OscAddress, AudioReceiveServer>();

    public static AudioReceiveServer allocate(
        Integer module_id,
        String desired_name,
        Atom[] argument_list) {
        return (AudioReceiveServer) ModuleMemberServer.allocate_from_label(
            "AudioReceive", module_id, desired_name, argument_list);
    }

    public AudioReceiveServer(ModuleServer module_node,
        Map<String, Atom[]> argument_map) {
        super(module_node, argument_map);
        OscAddress osc_address = this.get_osc_address();
        if (osc_address != null) {
            AudioReceiveServer.audio_receive_servers.put(osc_address, this);
            Event.notify_observers(EventTypes.AUDIO_RECEIVERS_CHANGED);
        }
    }

    @Override
    public void deallocate() {
        AudioReceiveServer.audio_receive_servers.remove(this.get_osc_address());
        super.deallocate();
        Event.notify_observers(EventTypes.AUDIO_RECEIVERS_CHANGED);
    }

    @Override
    public AudioReceiveServer new_instance(
        Integer module_id,
        Map<String, Atom[]> argument_map) {
        ModuleServer module_node = ModuleServer.allocate(module_id);
        return new AudioReceiveServer(module_node, argument_map);
    }

    @Override
    public void on_parent_notification() {
        AudioReceiveServer.audio_receive_servers.put(this.get_osc_address(),
            this);
        Event.notify_observers(EventTypes.AUDIO_RECEIVERS_CHANGED);
    }
}
