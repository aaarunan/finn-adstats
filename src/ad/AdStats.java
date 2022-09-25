package ad;

import java.util.HashMap;

/**
 * Class represents statistics for a single ad type.
 */
public class AdStats {
    private final String adType;
    private final HashMap<Ad, Integer> duplicatesMap;
    private long amount;
    private Ad expensiveAd = new Ad(0, null, 0);
    private Ad cheapAd = new Ad(0, null, Double.MAX_VALUE);
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

        this.amount++;

        if (expensiveAd.getAdPrice() < ad.getAdPrice()) {
            expensiveAd = ad.copy();
        }
        if (cheapAd.getAdPrice() > ad.getAdPrice()) {
            cheapAd = ad.copy();
        }

        Ad copy = ad.copy();
        copy.setAdType(null);
        copy.setAdPrice(0);
        //copy.setAdId(0);

        // if added one already exists there are two duplicates
        // else only add one more duplicate
        if (duplicatesMap.containsKey(copy)) {
            if (duplicatesMap.get(copy) == 1) {
                duplicates += 2;
                duplicatesMap.put(copy, 2);
            } else {
                duplicates++;
            }
        } else {
            duplicatesMap.put(copy, 1);
        }

    }

    public long getAmount() {
        return amount;
    }

    public Ad getCheapAd() {
        return cheapAd;
    }

    public Ad getExpensiveAd() {
        return expensiveAd;
    }

    public String getAdType() {
        return adType;
    }

    public int getDuplicates() {
        return duplicates;
    }

    @Override
    public String toString() {
        return String.format("""
                Ad type:          %s\s
                Amount of ads:    %d\s
                Lowest priced ad: %d  (%.2fkr)\s
                Highest price:    %d (%.2fkr)\s
                Duplicates:       %d""",
                this.getAdType(), this.getAmount(), this.getCheapAd().getAdId(), this.getCheapAd().getAdPrice(), this.getExpensiveAd().getAdId(), this.getExpensiveAd().getAdPrice(), this.getDuplicates());
    }

}
