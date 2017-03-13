package example.jethro.com.materialdemo.entity;

public class SimpleItem {

    private String mName;
    private String mIconUrl;
    private Class<?> mClazz;
    private boolean mLollipopPlus;

    public SimpleItem(String name, String iconUrl) {
        mName = name;
        mIconUrl = iconUrl;
    }

    public SimpleItem(String name, Class<?> clazz, boolean lollipopPlus) {
        mName = name;
        mClazz = clazz;
        mLollipopPlus = lollipopPlus;
    }

    public String getName() {
        return mName;
    }

    public String getIconUrl() {
        return mIconUrl;
    }

    public Class<?> getClazz() {
        return mClazz;
    }

    public boolean isLollipopPlus() {
        return mLollipopPlus;
    }
}
