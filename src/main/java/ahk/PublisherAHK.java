package ahk;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;

public class PublisherAHK {

    public static void main(String[] args) throws IOException {
//        String topic        = "test";
        int qos             = 2;
        String broker       = "tcp://localhost:1883";
        String clientId     = "JavaSample";
//        String content = "Hola este es un mensaje2";
        MemoryPersistence persistence = new MemoryPersistence();
        try {
            MqttClient sampleClient = new MqttClient(broker, clientId, persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);
            System.out.println("Connecting to broker: " + broker);
            sampleClient.connect(connOpts);
            System.out.println("Connected");
            System.out.println("Publishing message");

            BufferedReader cnsl = new BufferedReader(new InputStreamReader(System.in));

            while (true){
                System.out.println("nombre canal : ");
                String topic
                        = cnsl.readLine( );
                if (topic.equals("exit")){
                    break;
                }
                System.out.println("mensaje : ");
                String content
                        = cnsl.readLine( );
                if (content.equals("exit")){
                    break;
                }
                MqttMessage message = new MqttMessage(content.getBytes());
                message.setQos(qos);
                sampleClient.publish(topic, message);
                System.out.println("Message published");
            }



            sampleClient.disconnect();
            System.out.println("Disconnected");
            System.exit(0);
        } catch(MqttException me) {
            System.out.println("reason "+me.getReasonCode());
            System.out.println("msg "+me.getMessage());
            System.out.println("loc "+me.getLocalizedMessage());
            System.out.println("cause "+me.getCause());
            System.out.println("excep "+me);
            me.printStackTrace();
        }
    }

}
