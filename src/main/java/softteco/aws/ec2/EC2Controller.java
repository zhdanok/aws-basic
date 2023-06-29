package softteco.aws.ec2;

import com.amazonaws.regions.Region;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/region")
public class EC2Controller {

    private final EC2Service ec2Service;

    public EC2Controller(EC2Service ec2Service) {
        this.ec2Service = ec2Service;
    }

    @GetMapping
    public ResponseEntity<Region> getRegion(){
        return ok(ec2Service.getRegion());
    }

    @GetMapping("/az")
    public ResponseEntity<String> getRegionAndAz(){
        return ok(ec2Service.getRegionAndAz());
    }
}
