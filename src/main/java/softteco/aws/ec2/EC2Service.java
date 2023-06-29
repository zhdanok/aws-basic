package softteco.aws.ec2;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2ClientBuilder;
import com.amazonaws.services.ec2.model.AvailabilityZone;
import com.amazonaws.services.ec2.model.DescribeAvailabilityZonesResult;
import com.amazonaws.services.ec2.model.DescribeRegionsResult;
import com.amazonaws.services.ec2.model.Region;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EC2Service {

    public com.amazonaws.regions.Region getRegion() {
        return Regions.getCurrentRegion();

    }


    public String getRegionAndAz() {
        final AmazonEC2 ec2 = AmazonEC2ClientBuilder.standard().withCredentials(new ProfileCredentialsProvider("FullAccessUserEC2_l.zhdanok")).build();
        DescribeRegionsResult regions_response = ec2.describeRegions();
        Region currentRegion = regions_response.getRegions().stream().filter(region -> region.getRegionName().equals(Regions.EU_NORTH_1.getName())).collect(Collectors.toList()).get(0);
        DescribeAvailabilityZonesResult zones_response = ec2.describeAvailabilityZones();
        List<AvailabilityZone> zones = zones_response.getAvailabilityZones().stream().filter(availabilityZone -> availabilityZone.getRegionName().equals(Regions.EU_NORTH_1.getName())).collect(Collectors.toList());
        StringBuffer strBuffer = new StringBuffer();
        zones.forEach(availabilityZone -> strBuffer.append(String.format("%s, ",availabilityZone.getZoneName())));
        return String.format("Region - %s, zones - %s", currentRegion.getRegionName(), strBuffer);

    }
}
