package jms;
import com.google.gson.*;
import model.Sensor;
import org.apache.activemq.ActiveMQConnectionFactory;
import javax.jms.*;
import javax.jms.JMSException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Producer {
    static Gson json = new Gson();
    static Random random;

    public void sendMessage(String topicName) throws JMSException {

        ActiveMQConnectionFactory factory=new ActiveMQConnectionFactory("tcp://0.0.0.0:61616");
        Connection connection=factory.createConnection("admin","admin");
        connection.start();
        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        Topic topic=session.createTopic(topicName);
        MessageProducer producer=session.createProducer(topic);

        while(true){
            try {
                TimeUnit.SECONDS.sleep(1);
                random=new Random();
                // endpoints
                int id=random.nextInt(2)+1;
                String strReading=newMessage(id);
                TextMessage reading=session.createTextMessage(strReading);
                System.out.println("Sent: "+strReading);
                producer.send(reading);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static String newMessage(int id) {
        String fecha = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
        double temp = new Random().nextInt(40) + 1;
        double humidity = new Random().nextInt(99) + 1;
        Sensor sensor = new Sensor(fecha, id, temp, humidity);

        return json.toJson(sensor);
    }
}




