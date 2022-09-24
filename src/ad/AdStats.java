package ad;

import java.util.HashMap;

public class AdStats {
    private final String adType;
    private long amount;
    private double maxPrice;
    private double minPrice = Double.MAX_VALUE;

    private final HashMap<Long, Integer> adIdMap;
    private int duplicates;

    public AdStats(Ad ad) {
        this.adIdMap = new HashMap<>();
        this.adType = ad.getAdType();
        this.add(ad);
    }

    public void add(Ad ad) {
        if (!this.adType.equals(ad.getAdType())) {
            throw new IllegalArgumentException("AdTypes does not match");
        }

        this.amount += 1;

        if (maxPrice < ad.getAdPrice()) {
            maxPrice = ad.getAdPrice();
        }

        if (minPrice > ad.getAdPrice()) {
            minPrice = ad.getAdPrice();
        }
        if (adIdMap.containsKey(ad.getAdId())) {
            duplicates++;
        } else {
            adIdMap.put(ad.getAdId(), 1);
        }

    }

    public long getAmount() {
        return amount;
    }

    public double getMinPrice() {
        return minPrice;
    }

    public double getMaxPrice() {
        return maxPrice;
    }

    public String getAdType() {
        return adType;
    }

    public int getDuplicates() {
        return duplicates;
    }

    @Override
    public String toString() {
        return String.format("Ad type:       %s \nAmount of ads: %d \nLowest price:  %.2f \nHighest price: %.2f \nDuplicates:    %d", this.getAdType(), this.getAmount(), this.getMinPrice(), this.getMaxPrice(), this.getDuplicates());
    }

}
