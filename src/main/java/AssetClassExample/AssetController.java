package AssetClassExample;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.jms.*;
import java.net.URI;

@RestController
@RequestMapping(path="/incidentMgmtSystem")
public class AssetController {

    private static String url = "http://broker-amq-tcp-amq-demo.apps.mta-eam-eval.rhmi.io/";
    private static String subject = "demo_queue";

    @Autowired
    private AssetClassDao assetClassDao;

    @PostMapping(path="/postClass", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> addAssetClass(@RequestBody AssetClass assetClass) throws JMSException {
        Integer id = assetClassDao.getAllClasses().getAssetClassList().size() + 1;
        assetClass.setId(id);
        assetClassDao.addClasss(assetClass);

        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        Connection connection = connectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Destination destination = session.createQueue(subject);

        MessageProducer producer = session.createProducer(destination);

        TextMessage message = session.createTextMessage(assetClass.toString());
        producer.send(message);

        System.out.println("JCG printing@@ '" + message.getText() + "'");
        connection.close();

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(assetClass.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
}
