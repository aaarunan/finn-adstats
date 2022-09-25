package ad;

import java.util.Objects;

public class Ad {
    private long adId;
    private String adType;
    private double adPrice;

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

    @Override
    public int hashCode() {
        return Objects.hash(this.getAdType(), this.getAdId(), this.getAdPrice());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ad ad)) return false;
        return Objects.equals(this.getAdType(), ad.getAdType()) && this.getAdId() == ad.getAdId() && this.getAdPrice() == this.getAdPrice();
    }
}
