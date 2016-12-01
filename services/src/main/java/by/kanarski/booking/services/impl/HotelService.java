package by.kanarski.booking.services.impl;

import by.kanarski.booking.constants.AliasName;
import by.kanarski.booking.constants.AliasValue;
import by.kanarski.booking.constants.SearchParameter;
import by.kanarski.booking.dao.impl.HotelDao;
import by.kanarski.booking.dao.impl.HotelTranslationDao;
import by.kanarski.booking.dao.impl.LocationDao;
import by.kanarski.booking.dao.impl.LocationTranslationDao;
import by.kanarski.booking.dto.DestinationDto;
import by.kanarski.booking.dto.hotel.HotelDto;
import by.kanarski.booking.dto.location.LocationDto;
import by.kanarski.booking.entities.hotel.Hotel;
import by.kanarski.booking.entities.hotel.HotelTranslation;
import by.kanarski.booking.entities.location.Location;
import by.kanarski.booking.entities.location.LocationTranslation;
import by.kanarski.booking.exceptions.DaoException;
import by.kanarski.booking.exceptions.LocalisationException;
import by.kanarski.booking.exceptions.ServiceException;
import by.kanarski.booking.services.interfaces.IHotelService;
import by.kanarski.booking.utils.DtoToEntityConverter;
import by.kanarski.booking.utils.ExceptionHandler;
import by.kanarski.booking.utils.transaction.TransactionManager;
import by.kanarski.booking.utils.transaction.TransactoinWrapper;
import by.kanarski.booking.utils.wrappers.SearchFilter;

import java.util.ArrayList;
import java.util.List;


public class HotelService extends ExtendedBaseService<Hotel, HotelDto> implements IHotelService {

    private static HotelService instance;
    private static HotelDao hotelDao = HotelDao.getInstance();
    private static LocationDao locationDao = LocationDao.getInstance();
    private DtoToEntityConverter<Location, LocationDto> locationConverter = new DtoToEntityConverter<>(
            Location.class, LocationDto.class);


    private HotelService() {
    }

    public static synchronized HotelService getInstance() {
        if (instance == null) {
            instance = new HotelService();
        }
        return instance;
    }

    @Override
    public List<HotelDto> getByHotelName(HotelDto initialHotelDto, int page, int perPage) throws ServiceException {
        TransactoinWrapper transaction = TransactionManager.getTransaction();
        List<HotelDto> hotelDtoList = null;
        try {
            transaction.begin();
            String localizedHotelName = initialHotelDto.getHotelName();
            String language = initialHotelDto.getLanguage();
            String hotelName = getNotLocalizedHotelName(localizedHotelName);
            SearchFilter hotelFilter = SearchFilter.createBasicEqFilter(SearchParameter.HOTELNAME, hotelName);
            List<Hotel> hotelList = hotelDao.getListByFilter(hotelFilter, page, perPage);
            hotelDtoList = converter.toDtoList(hotelList);
            transaction.rollback();
        } catch (DaoException e) {
            ExceptionHandler.handleDaoException(transaction, e);
        } catch (LocalisationException e) {
            ExceptionHandler.handleLocalizationException(e);
        }
        return hotelDtoList;
    }

    @Override
    public List<HotelDto> getByCountry(HotelDto initialHotelDto, int page, int perPage) throws ServiceException {
        TransactoinWrapper transaction = TransactionManager.getTransaction();
        List<HotelDto> hotelDtoList = null;
        try {
            transaction.begin();
            String localizedCountry = initialHotelDto.getLocation().getCountry();
            String country = getNotLocalizedCountry(localizedCountry);
            SearchFilter hotelFilter = SearchFilter.createAliasFilter(AliasName.LOCATION, AliasValue.LANGUAGE);
            hotelFilter.setEqFilter(SearchParameter.LOCATION_COUNTRY, country);
            List<Hotel> hotelList = hotelDao.getListByFilter(hotelFilter, page, perPage);
            hotelDtoList = converter.toDtoList(hotelList);
            transaction.commit();
        } catch (DaoException e) {
            ExceptionHandler.handleDaoException(transaction, e);
        } catch (LocalisationException e) {
            ExceptionHandler.handleLocalizationException(e);
        }
        return hotelDtoList;
    }

    @Override
    public List<HotelDto> getByCity(HotelDto initialHotelDto, int page, int perPage) throws ServiceException {
        TransactoinWrapper transaction = TransactionManager.getTransaction();
        List<HotelDto> hotelDtoList = null;
        try {
            transaction.begin();
            String localizedCity = initialHotelDto.getLocation().getCity();
            String notLocalizedCity = getNotLocalizedCity(localizedCity);
            SearchFilter hotelFilter = SearchFilter.createAliasFilter(AliasName.LOCATION, AliasValue.LANGUAGE);
            hotelFilter.setEqFilter(SearchParameter.LOCATION_CITY, notLocalizedCity);
            List<Hotel> hotelList = hotelDao.getListByFilter(hotelFilter, page, perPage);
            hotelDtoList = converter.toDtoList(hotelList);
            transaction.commit();
        } catch (DaoException e) {
            ExceptionHandler.handleDaoException(transaction, e);
        } catch (LocalisationException e) {
            ExceptionHandler.handleLocalizationException(e);
        }
        return hotelDtoList;
    }

    public List<HotelDto> getByDestination(DestinationDto destinationDto, int page, int perPage) throws ServiceException {
        TransactoinWrapper transaction = TransactionManager.getTransaction();
        List<HotelDto> hotelDtoList = null;
        List<String> parameterList = destinationDto.getParameterList();
        try {
            transaction.begin();
            int resultsLeft = perPage;
            List<Hotel> hotelList = new ArrayList<>();
            List<Location> locationList = new ArrayList<>();
            switch (parameterList.size()) {
//                case 1:
                default: {
                    String parameter = parameterList.get(0);
                    {
                        String defaultHotelName = getNotLocalizedHotelName(parameter);
                        SearchFilter filter = SearchFilter.createBasicIlikeFilter(SearchParameter.HOTELNAME,
                                defaultHotelName);
                        List<Hotel> partHotelList = hotelDao.getListByFilter(filter, page, resultsLeft);
                        hotelList.addAll(partHotelList);
                        resultsLeft -= hotelList.size();
                    }
                    if (resultsLeft > 0) {
                        String defaultCountry = getNotLocalizedCountry(parameter);
                        SearchFilter filter = SearchFilter.createBasicIlikeFilter(SearchParameter.COUNTRY,
                                defaultCountry);
                        List<Location> partLocationList = locationDao.getListByFilter(filter, page, resultsLeft);
                        locationList.addAll(partLocationList);
                        resultsLeft -= locationList.size();
                    }
                    if (resultsLeft > 0) {
                        String defaultCity = getNotLocalizedCity(parameter);
                        SearchFilter filter = SearchFilter.createBasicIlikeFilter(SearchParameter.CITY,
                                defaultCity);
                        List<Location> partLocationList = locationDao.getListByFilter(filter, page, resultsLeft);
                        locationList.addAll(partLocationList);
                        resultsLeft -= locationList.size();
                    }
                    break;
                }
//                case 2: {
//                    String country = parameterList.get(0);
//                    String defaultCountry = getNotLocalizedCountry(country);
//                    SearchFilter filter = SearchFilter.createBasicIlikeFilter(SearchParameter.LOCATION_COUNTRY,
//                            defaultCountry);
//
//                    break;
//                }
            }
            List<HotelDto> partHotelDtoList = converter.toDtoList(hotelList);
            List<LocationDto> partLocationDtoList = locationConverter.toDtoList(locationList);
            List<HotelDto> anyHotelDtoList = converter.toAnyHotelDtoList(partLocationDtoList);
            hotelDtoList = partHotelDtoList;
            hotelDtoList.addAll(anyHotelDtoList);
            transaction.commit();
        } catch (DaoException e) {
            ExceptionHandler.handleDaoException(transaction, e);
        } catch (LocalisationException e) {
            ExceptionHandler.handleLocalizationException(e);
        }
        return hotelDtoList;
    }

    private String getNotLocalizedHotelName(String hotelName) throws DaoException {
        SearchFilter filter = SearchFilter.createLanguageFilter("RU");
        filter.setILikeFilter(SearchParameter.HOTELNAME, hotelName);
        List<HotelTranslation> hotelTranslationList = HotelTranslationDao.getInstance().getListByFilter(filter, 0, 1);
        String notLocalizedHotelName = null;
        if (hotelTranslationList.size() > 0) {
            notLocalizedHotelName = hotelTranslationList.get(0).getHotel().getHotelName();
        }
        return notLocalizedHotelName;
    }

    private String getNotLocalizedCountry(String country) throws DaoException {
        SearchFilter filter = SearchFilter.createLanguageFilter("RU");
        filter.setILikeFilter(SearchParameter.COUNTRY, country);
        List<LocationTranslation> locationTranslationList = LocationTranslationDao.getInstance().getListByFilter(filter, 0, 1);
        String notLocalizedCountry = null;
        if (locationTranslationList.size() > 0) {
            notLocalizedCountry = locationTranslationList.get(0).getLocation().getCountry();
        }
        return notLocalizedCountry;
    }

    private String getNotLocalizedCity(String city) throws DaoException {
        SearchFilter filter = SearchFilter.createLanguageFilter("RU");
        filter.setILikeFilter(SearchParameter.CITY, city);
        List<LocationTranslation> locationTranslationList = LocationTranslationDao.getInstance().getListByFilter(filter, 0, 1);
        String notLocalizedCity = null;
        if (locationTranslationList.size() > 0) {
            notLocalizedCity = locationTranslationList.get(0).getLocation().getCity();
        }
        return notLocalizedCity;
    }
}
