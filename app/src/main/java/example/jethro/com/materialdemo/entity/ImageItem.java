package example.jethro.com.materialdemo.entity;

/**
 * Created by lin on 2017/3/1.
 */

public class ImageItem {

    private String text;
    private String image;

    public ImageItem(String text, String image) {
        this. text = text;
        this.image = image;
    }

    public String getText() {
        return text;
    }

    public String getImage() {
        return image;
    }
}
