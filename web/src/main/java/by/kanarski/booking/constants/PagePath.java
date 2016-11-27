package by.kanarski.booking.constants;

public class PagePath {

    //User pages

    public static final String INDEX_PAGE_PATH = "/WEB-INF/assets/jsp/user/index.jsp";
    public static final String REGISTRATION_PAGE_PATH = "/WEB-INF/assets/jsp/user/registration.jsp";
    public static final String SELECT_HOTEL = "/WEB-INF/assets/jsp/user/selectHotel.jsp";
    public static final String SELECT_ROOM_PATH = "/WEB-INF/assets/jsp/user/selectRooms.jsp";

    //Client pages

    public static final String ACCOUNT_PAGE_PATH = "/WEB-INF/assets/jsp/client/account.jsp";
    public static final String REMIND_PASSWORD_PATH = "/WEB-INF/assets/jsp/client/remindPassword.jsp";

    //Admin pages

    public static final String ADMIN_MAIN_PAGE_PATH = "/WEB-INF/assets/jsp/admin/adminMain.jsp";

    public static final String ROOMS_REDACTOR_PATH = "/WEB-INF/assets/jsp/admin/roomsRedactor.jsp";
    public static final String ROOM_TYPES_REDACTOR_PATH = "/WEB-INF/assets/jsp/admin/roomTypesRedactor.jsp";
    public static final String LOCATIONS_REDACTOR_PATH = "/WEB-INF/assets/jsp/admin/locationsRedactor.jsp";
    public static final String USERS_REDACTOR_PATH = "/WEB-INF/assets/jsp/admin/usersRedactor.jsp";
    public static final String HOTELS_REDACTOR_PATH = "/WEB-INF/assets/jsp/admin/hotelsRedactor.jsp";
    public static final String BILLS_REDACTOR_PATH = "/WEB-INF/assets/jsp/admin/billsRedactor.jsp";

    public static final String ALTER_TABLE_ROW_PATH = "/WEB-INF/assets/jsp/admin/tableRedactor/alterTableRow.jsp";
    public static final String NEW_TABLE_ROW_PATH = "/WEB-INF/assets/jsp/admin/tableRedactor/newTableRow.jsp";

    //Error page

    public static final String ERROR = "/WEB-INF/assets/jsp/error/error.jsp";

    private PagePath() {

    }
}