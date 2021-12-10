package controller;
import jms.Producer;
import org.apache.activemq.broker.BrokerService;
import javax.jms.JMSException;
public class Queue {
    public static void main(String[] args) throws JMSException{
        String queue = "sensores";
        BrokerService brokerService=new BrokerService();

        try{

            brokerService.addConnector("tcp://localhost:61616");

            brokerService.start();

        }catch(Exception e){

            e.printStackTrace();

        }
        new Producer().sendMessage(queue);
    }
}
