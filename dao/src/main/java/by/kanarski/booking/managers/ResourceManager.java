package by.kanarski.booking.managers;

import by.kanarski.booking.constants.ResourcePath;

import java.util.Locale;
import java.util.ResourceBundle;

public enum ResourceManager {

    OPERATION_MESSAGES(ResourcePath.OPERATION_MESSAGES_SOURCE),
    JSP_TEXT(ResourcePath.TEXT_SOURCE),
    EXCEPTION(ResourcePath.EXCEPTION_MESSAGES_SOURCE),
    DATABASE(ResourcePath.DATABASE_SOURCE),

    //Mail resources

    AUTHENTIFICATION(ResourcePath.AUTHENTIFICATION_SOURCE),
    MAIL_MESSAGES(ResourcePath.MAIL_MESSAGES_SOURCE);
    
    private String resoucePath;
    private ResourceBundle bundle;

    ResourceManager(String resoucePath) {
        this.resoucePath = resoucePath;
    }

//    public ResourceBuilder setLocale(Locale locale) {
//        this.locale = locale;
//        return this;
//    }
//
//    public ResourceBundle create() {
//        if (locale == null) {
//            bundle = ResourceBundle.getBundle(resoucePath);
//        } else {
//            bundle = ResourceBundle.getBundle(resoucePath, locale);
//        }
//        return bundle;
//    }

    private static final ResourceCache RESOURCE_CACHE = new ResourceCache();

    public ResourceBundle get() {
        String resourceName = this.name() + "Default";
        bundle = RESOURCE_CACHE.get(resourceName);
        if (bundle == null) {
            bundle = ResourceBundle.getBundle(resoucePath);
            RESOURCE_CACHE.put(resourceName, bundle);
        }
        return bundle;
    }

    public ResourceBundle get(Locale locale) {
        String resourceName = this.name() + locale.getDisplayName();
        bundle = RESOURCE_CACHE.get(resourceName);
        if (bundle == null) {
            bundle = ResourceBundle.getBundle(resoucePath);
            RESOURCE_CACHE.put(resourceName, bundle);
        }
        return bundle;
    }
    
}
