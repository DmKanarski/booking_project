package pre.jsonHandler;

import by.kanarski.booking.dao.impl.*;
import by.kanarski.booking.entities.User;
import by.kanarski.booking.entities.hotel.Hotel;
import by.kanarski.booking.entities.location.Location;
import by.kanarski.booking.exceptions.DaoException;
import by.kanarski.booking.utils.EntityBuilder;

import java.util.*;

import static by.kanarski.booking.constants.FieldValue.ROLE_CLIENT;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

public class TestEntityGenerator {

    private static TestEntityGenerator instance = null;

    private final UserDao USER_DAO = UserDao.getInstance();
    private final LocationDao LOCATION_DAO = LocationDao.getInstance();
    private final HotelDao HOTEL_DAO = HotelDao.getInstance();
    private final RoomTypeDao ROOM_TYPE_DAO = RoomTypeDao.getInstance();
    private final RoomDao ROOM_DAO = RoomDao.getInstance();
    private final BillDao BILL_DAO = BillDao.getInstance();

    private static final int ENTITIES_AMOUNT = 30;
    private static final int UNIQUE_VALUES = 10;

    private TestEntityGenerator() {
    }

    public static TestEntityGenerator getInstance() {
        if (instance == null) {
            instance = new TestEntityGenerator();
        }
        return instance;
    }


    public List<User> generateUsers() throws DaoException {
        Random random = getRandom();
        List<User> userList = new ArrayList<>();
        for (int i = 1; i <= ENTITIES_AMOUNT; i++) {
            long userId = i;
            String firstName = "testFirstNameExpected" + random.nextInt(UNIQUE_VALUES);
            String lastName = "testLastNameExpected" + random.nextInt(UNIQUE_VALUES);
            String email = "testExpected" + i + "@gmail.com";
            String login = "testLoginExpected" + i;
            String password = "testPasswordExpected" + random.nextInt(UNIQUE_VALUES);
            String role = ROLE_CLIENT;
            User user = EntityBuilder.buildUser(userId, firstName, lastName, email, login, password, role);
            userList.add(user);
        }
        return userList;
    }

    public List<Location> generateLocations() throws DaoException {
        Random random = getRandom();
        List<Location> locationList = new ArrayList<>();
        for (int i = 1; i <= ENTITIES_AMOUNT; i++) {
            long locationId = i;
            String country = "testCountryExpected" + random.nextInt(UNIQUE_VALUES);
            String city = "testCityExpected" + i;
            Location location = EntityBuilder.buildLocation(locationId, country, city);
            locationList.add(location);
        }
        return locationList;
    }

    public List<Hotel> generateHotels(List<Location> generatedLocations) throws DaoException {
        Random random = getRandom();
        List<Hotel> hotelList = new ArrayList<>();
        for (int i = 1; i <= ENTITIES_AMOUNT; i++) {
            long hotelId = i;
            Location hotelLocation = generatedLocations.get(random.nextInt(ENTITIES_AMOUNT));
            String hotelName = "testHotelNameExpected" + i;
            Hotel hotel = EntityBuilder.buildHotel(hotelId, hotelLocation, hotelName);
            hotelList.add(hotel);
        }
        return hotelList;
    }

//    public List<RoomType> generateRoomTypes() throws DaoException {
//        Random random = getRandom();
//        List<RoomType> roomTypeList = new ArrayList<>();
//        for (int i = 1; i <= ENTITIES_AMOUNT; i++) {
//            long roomTypeId = i;
//            String roomTypeName = "testRoomTypeExpected" + random.nextInt(UNIQUE_VALUES);
//            int maxPersons = 1 + random.nextInt(UNIQUE_VALUES);
//            double pricePerNight = 1 + random.nextInt(UNIQUE_VALUES) * 100;
//            int facilitiesAmount = random.nextInt(UNIQUE_VALUES);
//            Set<String> facilities = new HashSet<>();
//            for (int j = 1; j <= facilitiesAmount; j++) {
//                String facility = "testFacilityExpected" + i;
//                facilities.add(facility);
//            }
//            RoomType roomType = EntityBuilder.buildRoomType(roomTypeId, roomTypeName, maxPersons, pricePerNight, facilities);
//            roomTypeList.add(roomType);
//        }
//        return roomTypeList;
//    }
    private Random getRandom() {
        long seed = new Random().nextLong();
        Random random = new Random(seed);
        return random;
    }



}