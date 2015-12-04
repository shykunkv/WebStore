package utils;

import java.util.ResourceBundle;

public class ResourceBundleUtil {

    /**
     * Get resource bundle for specific language
     * @param language language ofresource bundle
     * @return resource bundle for some language
     */
    public static ResourceBundle getResourceBundle(String language) {
        String path = "i18n.webstore";

        if (language != null && !language.equals("en")) {
            path += "_" + language;
        }

        return ResourceBundle.getBundle(path);
    }

}
