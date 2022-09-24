package ad;

import java.util.HashMap;

public class AdMap {
    private final HashMap<String, AdStats> adHashMap;

    public AdMap() {
        this.adHashMap = new HashMap<>();
    }

    public void map(Ad ad) {
        if (adHashMap.containsKey(ad.getAdType())) {
            adHashMap.get(ad.getAdType()).add(ad);
        } else {
            adHashMap.put(ad.getAdType(), new AdStats(ad));
        }
    }

    public int size() {
       return adHashMap.size();
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
