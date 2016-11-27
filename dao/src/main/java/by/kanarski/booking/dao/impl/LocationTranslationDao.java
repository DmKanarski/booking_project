package by.kanarski.booking.dao.impl;

import by.kanarski.booking.dao.interfaces.ILocationTranslationDao;
import by.kanarski.booking.entities.location.LocationTranslation;

public class LocationTranslationDao extends ExtendedBaseDao<LocationTranslation> implements ILocationTranslationDao {

    private static LocationTranslationDao instance = null;

    private LocationTranslationDao() {
    }

    public static synchronized LocationTranslationDao getInstance() {
        if (instance == null) {
            instance = new LocationTranslationDao();
        }
        return instance;
    }

}