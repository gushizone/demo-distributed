package tk.gushizone.keycloak.spi.authentication.face.properties;

/**
 * @author gushizone@gmail.com
 * @date 2022/2/21 2:21 下午
 */
public class IamFormHuaweiFaceProperties {

    private String regionId;

    private String accessKey;

    private String secretAccessKey;

    private String faceSet;

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretAccessKey() {
        return secretAccessKey;
    }

    public void setSecretAccessKey(String secretAccessKey) {
        this.secretAccessKey = secretAccessKey;
    }

    public String getFaceSet() {
        return faceSet;
    }

    public void setFaceSet(String faceSet) {
        this.faceSet = faceSet;
    }
}
