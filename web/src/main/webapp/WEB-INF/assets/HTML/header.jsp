<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<header>
    <div class="wrap clearfix">
        <!--logo-->
        <h1 class="logo">
            <a href="controller?command=goToMain" title="Book Your Travel - home">
                <img src="images/txt/logo.png" alt="Book Your Travel" />
            </a>
        </h1>
        <!--//logo-->

        <div id="uesrForm">
            <ul class="userCenterNav">
                <li class="userCenterLanguage"></li>
                <c:choose>
                    <c:when test="${empty user}">
                        <li class="userCenterLoginField">
                            <form id="login" name="loginForm" method="POST" action="controller">
                                <input type="hidden" name="command" value="login"/>
                                <fieldset id="inputs">
                                    <input id="username" type="text" name="login" placeholder="${header_login}" autofocus required>
                                    <input id="password" type="password" name="password" placeholder="${header_password}" required>
                                </fieldset>
                                <fieldset id="actions">
                                    <input type="submit" id="submit" value="${header_signIn}">
                                    <a href="controller?command=goToRemindPassword">${header_forgotPassword}</a>
                                    <a href="controller?command=goToRegistration">${header_register}</a>
                                </fieldset>
                                    ${errorLoginOrPassword}<br/>
                            </form>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li>${header_welcome} ${user.firstName}</li>
                        <li>
                            <c:if test="${user.role eq 'admin'}">
                                <p>${header_administrator}</p>
                                <a href="controller?command=goToAdminPage">${header_goToAdminPage}</a>
                            </c:if>
                        </li>
                        <li class="userCenterAccount">
                            <a href="controller?command=goToAccount">${header_goToAccount}</a>
                        </li>
                        <li class="userCenterLogoutField">
                            <a href="controller?command=logout">${header_signOut}</a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>

        <!--ribbon-->
        <div class="ribbon">
            <nav>
                <ul class="profile-nav">
                    <li class="active">
                        <a href="#" title="My Account">My Account</a>
                    </li>
                    <c:choose>
                        <c:when test="${empty user}">
                            <li>
                                <a href="controller?command=goToLogin" title="Login">Login</a>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <c:if test="${user.role eq 'admin'}">
                                <li>
                                    <p>${header_welcome} ${user.firstName}${header_administrator}</p>
                                    <a href="controller?command=goToAdminPage">${header_goToAdminPage}</a>
                                </li>
                            </c:if>
                            <li>
                                <a href="controller?command=goToAccount" title="Settings">Settings</a>
                            </li>
                            <li>
                                <a href="controller?command=logout">${header_signOut}</a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </ul>

                <ul class="lang-nav">
                    <c:set var="localeSet" value="${localeMap.keySet()}"/>
                    <c:forEach var="locale" items="${localeSet}">
                        <c:set var="displayLanguage" value="${localeMap.get(locale)}"/>
                        <c:choose>
                            <c:when test="${currentLocale eq locale}">
                                <li class="active">
                                    <a href="#" title=${displayLanguage}>${displayLanguage}</a>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li>
                                    <a href="controller?command=setLocale&${locale}" title=${displayLanguage}>${displayLanguage}</a>
                                </li>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </ul>

                <ul class="currency-nav">
                    <c:forEach var="currency" items="${currencySet}">
                        <c:set var="displayCurrency" value="${currencyMap.get(currency)}"/>
                        <c:choose>
                            <c:when test="${currentCurrency eq currency}">
                                <li class="active">
                                    <a href="#" title="${displayCurrency}">${displayCurrency}</a>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li>
                                    <a href="controller?command=setCurrency&${currency}" title="${displayCurrency}">${displayCurrency}</a>
                                </li>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </ul>
            </nav>
        </div>
        <!--//ribbon-->

        <!--search-->
        <div class="search">
            <form id="search-form" method="get" action="search-form">
                <input type="search" placeholder="Search entire site here" name="site_search" id="site_search" />
                <input type="submit" id="submit-site-search" value="submit-site-search" name="submit-site-search"/>
            </form>
        </div>
        <!--//search-->

        <!--contact-->
        <div class="contact">
            <span>24/7 Support number</span>
            <span class="number">1- 555 - 555 - 555</span>
        </div>
        <!--//contact-->
    </div>

    <!--main navigation-->
    <nav class="main-nav" role="navigation" id="nav">
        <ul class="wrap">
            <li class="active"><a href="hotels.html" title="Hotels">Hotels</a>
                <ul>
                    <li><a href="#">Secondary navigation</a></li>
                    <li><a href="#">example links</a></li>
                    <li><a href="error.html">Error page</a></li>
                    <li><a href="loading.html">Loading page</a></li>
                </ul>
            </li>
            <li><a href="flights.html" title="Flights">Flights</a></li>
            <li><a href="flight_and_hotels.html" title="Flight + Hotel">Flight + Hotel</a></li>
            <li><a href="self_catering.html" title="Self catering">Self catering</a></li>
            <li><a href="cruise.html" title="Cruises">Cruises</a></li>
            <li><a href="car_rental.html" title="Car rental">Car rental</a></li>
            <li><a href="hot_deals.html" title="Hot deals">Hot deals</a></li>
            <li><a href="#" title="Vacations">Vacations</a></li>
            <li><a href="#" title="Business travel">Business travel</a></li>
            <li><a href="#" title="Group travel">Group travel</a></li>
            <li><a href="get_inspired.html" title="Get inspired">Get inspired</a></li>
            <li><a href="#" title="Travel guides">Travel guides</a>
                <ul>
                    <li><a href="location.html">Location Details</a></li>
                </ul>
            </li>
        </ul>
    </nav>
    <!--//main navigation-->

    <!--operation message-->
    <div>
        <p class="message">${operationMessage}</p>
    </div>
    <!--//operation message-->

</header>
