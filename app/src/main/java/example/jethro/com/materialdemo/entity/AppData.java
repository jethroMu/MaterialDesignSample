package example.jethro.com.materialdemo.entity;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class AppData {

    @SerializedName("appList")
    private List<App> mApps;

    public List<SimpleItem> toSimpleItems() {
        List<SimpleItem> simpleItems = new ArrayList<>();
        for (App app : mApps) {
            simpleItems.add(app.toSimpleItem());
        }
        return simpleItems;
    }

    public static class App {

        @SerializedName("title")
        private String mTitle;

        @SerializedName("icon_72")
        private String mIconUrl;

        public SimpleItem toSimpleItem() {
            return new SimpleItem(mTitle, mIconUrl);
        }
    }
}
