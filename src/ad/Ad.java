package ad;

import java.util.Objects;

/**
 * Class that represents a FINN ad.
 */
public class Ad {
    private long adId;
    private String adType;
    private double adPrice;

    /**
     * Construct an ad with all attributes.
     *
     * @param adId    the id of the ad
     * @param adType  the type of ad
     * @param adPrice the listing price of the ad
     * @throws IllegalArgumentException if the ad id or ad price is less than 0
     */
    public Ad(long adId, String adType, double adPrice) throws IllegalArgumentException {
        if (adId < 0) {
            throw new IllegalArgumentException("Id can not be negative");
        }
        if (adPrice < 0) {
            throw new IllegalArgumentException("Price can not be negative");
        }

        this.adId = adId;
        this.adType = adType;
        this.adPrice = adPrice;
    }

    public long getAdId() {
        return adId;
    }

    public void setAdId(int adId) {
        this.adId = adId;
    }

    public String getAdType() {
        return adType;
    }

    public void setAdType(String adType) {
        this.adType = adType;
    }

    public double getAdPrice() {
        return adPrice;
    }

    public void setAdPrice(double adPrice) {
        this.adPrice = adPrice;
    }

    /**
     * Hashes all the attributes of the class.
     *
     * @return object represented as a hash
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.getAdType(), this.getAdId(), this.getAdPrice());
    }

    /**
     * Equals method checks all the attributes of the ad.
     * All attributes can be null.
     *
     * @param o object that is being compared to
     * @return true if the objects are equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ad ad)) return false;
        return Objects.equals(this.getAdType(), ad.getAdType()) && this.getAdId() == ad.getAdId() && this.getAdPrice() == this.getAdPrice();
    }
}
