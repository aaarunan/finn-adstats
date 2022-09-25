package ad;

import java.util.HashMap;

/**
 * Class represents statistics for a single ad type.
 */
public class AdStats {
    private final String adType;
    private final HashMap<Ad, Integer> duplicatesMap;
    private long amount;
    private double maxPrice;
    private double minPrice = Double.MAX_VALUE;
    private int duplicates;

    /**
     * Initialize the AdStats with an ad.
     * The AdTypes will then be locked to the ads specific type.
     *
     * @param ad Ad to collect stats from
     */
    public AdStats(Ad ad) {
        this.duplicatesMap = new HashMap<>();
        this.adType = ad.getAdType();
        this.add(ad);
    }

    /**
     * Adds an ad to the stats. The ad has to have the same ad type.
     *
     * @param ad Ad that is being added
     */
    public void add(Ad ad) {
        if (!this.getAdType().equals(ad.getAdType())) {
            throw new IllegalArgumentException("Can not ad different ad type.");
        }

        this.amount += 1;

        if (maxPrice < ad.getAdPrice()) {
            maxPrice = ad.getAdPrice();
        }
        if (minPrice > ad.getAdPrice()) {
            minPrice = ad.getAdPrice();
        }
        if (duplicatesMap.containsKey(ad)) {
            duplicates++;
        } else {
            ad.setAdType(null);
            //ad.setAdPrice(null);
            duplicatesMap.put(ad, 1);
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
