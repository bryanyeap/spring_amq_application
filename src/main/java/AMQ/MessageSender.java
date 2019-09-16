package AMQ;

import AssetClassExample.AssetClass;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import javax.jms.MessageProducer;

public class MessageSender {

    private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;
    private static String subject = "JCG_QUEUE";

    public void sendMessage(String sentMessage) throws JMSException {

        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        Connection connection = connectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue(subject);
        MessageProducer producer = session.createProducer(destination);
        TextMessage message = session.createTextMessage(sentMessage);
        producer.send(message);
        System.out.println("JCG printing@@ '" + message.getText() + "'");
        connection.close();
    }

}
