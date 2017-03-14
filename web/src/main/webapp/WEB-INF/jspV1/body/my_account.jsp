﻿<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="loc" uri="http://booking.by/localizator" %>
<c:set var="context" value="${pageContext.request.contextPath}"/>

<!--main-->
<div class="main" role="main">
	<div class="wrap clearfix">
		<!--main content-->
		<div class="content clearfix">
			<!--breadcrumbs-->
			<nav role="navigation" class="breadcrumbs clearfix">
				<!--crumbs-->
				<ul class="crumbs">
					<li><a href="#" title="Home">Home</a></li>
					<li><a href="#" title="My Account">My Account</a></li>
				</ul>
				<!--//crumbs-->

			</nav>
			<!--//breadcrumbs-->

			<!--three-fourth content-->
			<section class="three-fourth">

				<h1><spring:message code="account.account"/></h1>

				<!--inner navigation-->
				<nav class="inner-nav">
					<ul>
						<li><a href="#MyBookings" title="My Bookings">My Bookings</a></li>
						<li><a href="#MyReviews" title="My Reviews">My Reviews</a></li>
						<li><a href="#MySettings" title="Settings">Settings</a></li>
					</ul>
				</nav>
				<!--//inner navigation-->

				<!--My Bookings-->
				<section id="MyBookings" class="tab-content">

					<c:forEach var="bill" items="${billList}">
						<c:set var="roomTypeMap" value="${bill.roomTypeMap}"/>
						<c:set var="roomTypeSet" value="${roomTypeMap.keySet()}"/>
						<c:set var="hotel" value="${bill.hotel}"/>
						<!--booking-->
						<article class="bookings">
							<h1><a href="${context}/hotel?hotelId=${hotel.hotelId}">${hotel.hotelName}</a></h1>
							<div class="b-info">
								<table>
									<tr>
										<th><spring:message code="hotelInfo.bookingNumber"/></th>
										<td>${bill.billId}</td>
									</tr>
									<tr>
										<th><spring:message code="account.rooms"/></th>
									</tr>
									<c:forEach var="roomType" items="${roomTypeSet}">
										<tr>
											<td>${roomType.roomTypeName}</td>
										</tr>
									</c:forEach>
									<tr>
										<th><spring:message code="account.checkOutDate"/></th>
										<td>${loc:getDate(bill.checkInDate)}</td>
									</tr>
									<tr>
										<th><spring:message code="account.checkInDate"/></th>
										<td>${loc:getDate(bill.checkOutDate)}</td>
									</tr>
									<tr>
										<th><spring:message code="hotelInfo.totalPrice"/>:</th>
										<td><strong>${loc:getMoney(bill.paymentAmount)}</strong></td>
									</tr>
								</table>
							</div>

							<c:set var="billStatus" value="${bill.billStatus}"/>
							<div class="actions">
								<c:if test="${billStatus eq ('notPaid' or 'paid')}">
									<a href="${context}/cancelBooking?billId=${bill.billId}" class="gradient-button">
										<spring:message code="hotelInfo.cancelBooking"/>
									</a>
									<a href="${context}/hotel?hotelId=${hotel.hotelId}" class="gradient-button">
										<spring:message code="hotelInfo.viewInformation"/>
									</a>
									<c:if test="${billStatus eq 'notPaid'}">
										<a href="${context}/payBill?billId=${bill.billId}" class="gradient-button">
											<spring:message code="hotelInfo.pay"/>
										</a>
									</c:if>
								</c:if>
								<c:if test="${billStatus eq 'canceled'}">
									<a href="${context}/removeBill?billId=${bill.billId}" class="gradient-button">
										<spring:message code="hotelInfo.removeFromList"/>
									</a>
								</c:if>
							</div>
						</article>
						<!--//booking-->
					</c:forEach>
				</section>
				<!--//My Bookings-->


				<!--MyReviews-->
				<section id="MyReviews" class="tab-content">
					<article class="myreviews">
						<h1>Your review of hotel Lorem ipsum hotel and spa</h1>
						<div class="score">
							<span class="achieved">8 </span>
							<span> / 10</span>
						</div>
						<div class="reviews">
							<div class="pro"><p>It was a warm friendly hotel. Very easy access to shops and underground stations. Staff very welcoming.</p></div>
							<div class="con"><p>noisy neigbourghs spoilt the rather calm environment</p></div>
						</div>
					</article>

					<article class="myreviews">
						<h1>Your review of hotel Lorem ipsum hotel and spa</h1>
						<div class="score">
							<span class="achieved">8 </span>
							<span> / 10</span>
						</div>
						<div class="reviews">
							<div class="pro"><p>It was a warm friendly hotel. Very easy access to shops and underground stations. Staff very welcoming.</p></div>
							<div class="con"><p>noisy neigbourghs spoilt the rather calm environment</p></div>
						</div>
					</article>

					<article class="myreviews">
						<h1>Your review of hotel Lorem ipsum hotel and spa</h1>
						<div class="score">
							<span class="achieved">8 </span>
							<span> / 10</span>
						</div>
						<div class="reviews">
							<div class="pro"><p>It was a warm friendly hotel. Very easy access to shops and underground stations. Staff very welcoming.</p></div>
							<div class="con"><p>noisy neigbourghs spoilt the rather calm environment</p></div>
						</div>
					</article>
				</section>
				<!--//MyReviews-->

				<!--MySettings-->
				<section id="MySettings" class="tab-content">
					<article class="mysettings">
						<h1>Personal details</h1>
						<table>
							<tr>
								<th>First name:</th>
								<td>John
									<!--edit fields-->
									<div class="edit_field" id="field1">
										<label for="new_name">Your new name:</label>
										<input type="text" id="new_name"/>
										<input type="submit" value="save" class="gradient-button" id="submit1"/>
										<a href="#">Cancel</a>
									</div>
									<!--//edit fields-->
								</td>
								<td><a href="#field1" class="gradient-button edit">Edit</a></td>
							</tr>
							<tr>
								<th>Last name:</th>
								<td>Livingston
									<!--edit fields-->
									<div class="edit_field" id="field2">
										<label for="new_last_name">Your new last name:</label>
										<input type="text" id="new_last_name"/>
										<input type="submit" value="save" class="gradient-button" id="submit2"/>
										<a href="#">Cancel</a>
									</div>
									<!--//edit fields-->
								</td>
								<td><a href="#field2" class="gradient-button edit">Edit</a></td>
							</tr>
							<tr>
								<th>E-mail address: </th>
								<td>mail@google.com
									<!--edit fields-->
									<div class="edit_field" id="field3">
										<label for="new_email">Your new email:</label>
										<input type="text" id="new_email"/>
										<input type="submit" value="save" class="gradient-button" id="submit3"/>
										<a href="#">Cancel</a>
									</div>
									<!--//edit fields-->
								</td>
								<td><a href="#field3" class="gradient-button edit">Edit</a></td>
							</tr>
							<tr>
								<th>Password: </th>
								<td>*********
									<!--edit fields-->
									<div class="edit_field" id="field4">
										<label for="new_password">Your new password:</label>
										<input type="password" id="new_password"/>
										<input type="submit" value="save" class="gradient-button" id="submit4"/>
										<a href="#">Cancel</a>
									</div>
									<!--//edit fields-->
								</td>
								<td><a href="#field4" class="gradient-button edit">Edit</a></td>
							</tr>
							<tr>
								<th>Street Address and number:</th>
								<td>Some street name 55
									<!--edit fields-->
									<div class="edit_field" id="field5">
										<label for="new_address">Your new address:</label>
										<input type="text" id="new_address"/>
										<input type="submit" value="save" class="gradient-button" id="submit5"/>
										<a href="#">Cancel</a>
									</div>
									<!--//edit fields-->
								</td>
								<td><a href="#field5" class="gradient-button edit">Edit</a></td>
							</tr>

							<tr>
								<th>Town / City: </th>
								<td>Sunnytown
									<!--edit fields-->
									<div class="edit_field" id="field6">
										<label for="new_city">Your new city:</label>
										<input type="text" id="new_city"/>
										<input type="submit" value="save" class="gradient-button" id="submit6"/>
										<a href="#">Cancel</a>
									</div>
									<!--//edit fields-->
								</td>
								<td><a href="#field6" class="gradient-button edit">Edit</a></td>
							</tr>

							<tr>
								<th>ZIP code:</th>
								<td>9500 - 100
									<!--edit fields-->
									<div class="edit_field" id="field7">
										<label for="new_zip">Your new ZIP code:</label>
										<input type="text" id="new_zip"/>
										<input type="submit" value="save" class="gradient-button" id="submit7"/>
										<a href="#">Cancel</a>
									</div>
									<!--//edit fields-->
								</td>
								<td><a href="#field7" class="gradient-button edit">Edit</a></td>
							</tr>

							<tr>
								<th>Country:</th>
								<td>Neverland
									<!--edit fields-->
									<div class="edit_field" id="field8">
										<label for="new_country">Your new country:</label>
										<input type="text" id="new_country"/>
										<input type="submit" value="save" class="gradient-button" id="submit8"/>
										<a href="#">Cancel</a>
									</div>
									<!--//edit fields-->
								</td>
								<td><a href="#field8" class="gradient-button edit">Edit</a></td>
							</tr>
						</table>

					</article>
				</section>
				<!--//MySettings-->

			</section>
			<!--//three-fourth content-->

			<!--sidebar-->
			<aside class="right-sidebar">

				<!--Need Help Booking?-->
				<article class="default clearfix">
					<h2>Need Help Booking?</h2>
					<p>Call our customer services team on the number below to speak to one of our advisors who will help you with all of your holiday needs.</p>
					<p class="number">1- 555 - 555 - 555</p>
				</article>
				<!--//Need Help Booking?-->

				<!--Why Book with us?-->
				<article class="default clearfix">
					<h2>Why Book with us?</h2>
					<h3>Low rates</h3>
					<p>Get the best rates, or get a refund.<br />No booking fees. Save money!</p>
					<h3>Largest Selection</h3>
					<p>140,000+ hotels worldwide<br />130+ airlines<br />Over 3 million guest reviews</p>
					<h3>We’re Always Here</h3>
					<p>Call or email us, anytime<br />Get 24-hour support before, during, and after your trip</p>
				</article>
				<!--//Why Book with us?-->

			</aside>
			<!--//sidebar-->
		</div>
		<!--//main content-->
	</div>
</div>
<!--//main-->
	