package ahk;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class ConsumerAHK implements MqttCallback {

    /** Url de la mensajeria */
    private static final String brokerUrl ="tcp://localhost:1883";



    public void subscribe(String clientId,String topic) {

        MemoryPersistence persistence = new MemoryPersistence();
        try
        {
            MqttClient sampleClient = new MqttClient(brokerUrl, clientId, persistence);
            System.out.println("Starting: " + clientId);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);
            System.out.println("checking");
            System.out.println("Mqtt Connecting to broker: " + brokerUrl);
            sampleClient.connect(connOpts);
            System.out.println("Mqtt Connected");
            sampleClient.setCallback(this);
            sampleClient.subscribe(topic);
            System.out.println("Subscribed");
            System.out.println("Listening");

        } catch (MqttException me) {
            System.out.println(me);
        }
    }
    //Called when the client lost the connection to the broker
    public void connectionLost(Throwable arg0) {
        System.out.println(arg0);
    }
    //Called when a outgoing publish is complete
    public void deliveryComplete(IMqttDeliveryToken arg0) {
    }
    public void messageArrived(String topic, MqttMessage message) {
        System.out.println("Topic/Cola:" + topic);
        System.out.println("Mensaje: " +message.toString());
    }

    public static void main(String[] args) {
        String topic = "test";
        if (args.length == 2){
            topic = args[1];
        }
        new ConsumerAHK().subscribe(args[0],topic);
    }

}
