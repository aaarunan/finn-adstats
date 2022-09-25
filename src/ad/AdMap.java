package ad;

import java.util.HashMap;
import java.util.Map;

/**
 * A map for ads.
 */
public class AdMap {
    private final HashMap<String, AdStats> adHashMap;

    public AdMap() {
        this.adHashMap = new HashMap<>();
    }

    /**
     * Maps the ad to the AdMap. If the ad does not exist in the map,
     * a new entry will be added. if it does exist its values wil be
     * added to the entry.
     *
     * @param ad Ad that is being mapped
     */
    public void map(Ad ad) {
        if (adHashMap.containsKey(ad.getAdType())) {
            adHashMap.get(ad.getAdType()).add(ad);
        } else {
            adHashMap.put(ad.getAdType(), new AdStats(ad));
        }
    }

    public Map<String, AdStats> getMap() {
        return new HashMap<>(adHashMap);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (AdStats adStats : adHashMap.values()) {
            sb.append(adStats).append("\n\n");
        }
        return sb.toString();
    }
}
