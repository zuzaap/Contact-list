package contacts;

public class Address {
    private String street;
    private String buildingNum;
    private  String flatNum;
    private String postCode;
    private String generalDelivery;

    public Address(String st, String bn, String locn, String pc, String gn) {
        street = st;
        buildingNum = bn;
        flatNum = locn;
        postCode = pc;
        generalDelivery = gn;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBuildingNum() {
        return buildingNum;
    }

    public void setBuildingNum(String buildingNum) {
        this.buildingNum = buildingNum;
    }

    public String getFlatNum() {
        return flatNum;
    }

    public void setFlatNum(String flatNum) {
        this.flatNum = flatNum;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getGeneralDelivery() {
        return generalDelivery;
    }

    public void setGeneralDelivery(String generalDelivery) {
        this.generalDelivery = generalDelivery;
    }
}
