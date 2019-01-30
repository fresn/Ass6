package ca.yiming.ass6.dummy;

import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ca.yiming.ass6.MainActivity;
import ca.yiming.ass6.R;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class PlaceContent {

    /**
     * An array of sample (place) items.
     */
    public static final List<PlaceItem> ITEMS = new ArrayList<PlaceItem>();

    /**
     * A map of sample (place) items, by ID.
     */
    public static final Map<String, PlaceItem> ITEM_MAP = new HashMap<String, PlaceItem>();

//    private static final int COUNT = 2;

//    static {
//        // Add some sample items.
//        for (int i = 1; i <= COUNT; i++) {
//            addPlaceItem(createPlaceItem(i));
//        }
//    }

    public static void initData(){
        ITEMS.clear();
        ITEM_MAP.clear();
    }
    public static void addPlaceItem(PlaceItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

//    private static PlaceItem createPlaceItem(int position) {
//        return new PlaceItem(String.valueOf(position), "Item " + position, makeDetails(position),);
//    }

//    private static String makeDetails(int position) {
//        StringBuilder builder = new StringBuilder();
//        builder.append("Details about Item: ").append(position);
//        for (int i = 0; i < position; i++) {
//            builder.append("\nMore details information here.");
//        }
//        return builder.toString();
//    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class PlaceItem {
        public final String id;
        public final String content;
        public final String details;
        public final int imageRes;

        public PlaceItem(String id, String content, String details,int imageRes) {
            this.id = id;
            this.content = content;
            this.details = details;
            this.imageRes=imageRes;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}
