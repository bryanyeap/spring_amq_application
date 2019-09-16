package AssetClassExample;

import AMQ.MessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.jms.JMSException;
import java.net.URI;

@RestController
@RequestMapping(path="/incidentMgmtSystem")
public class AssetController {

    private MessageSender messageSender;

    @Autowired
    private AssetClassDao assetClassDao;

    @GetMapping(path="/getClass", produces = "application/json")
    public AssetClasses getAssetClasses() {
        return assetClassDao.getAllClasses();
    }

    @PostMapping(path="/postClass", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> addAssetClass(@RequestBody AssetClass assetClass) throws JMSException {
        Integer id = assetClassDao.getAllClasses().getAssetClassList().size() + 1;
        assetClass.setId(id);
        assetClassDao.addClasss(assetClass);
        //messageSender.sendMessage(assetClass.toString());

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(assetClass.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
}
