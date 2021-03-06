<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="context" value="${pageContext.request.contextPath}"/>

<!--main-->
<div class="main" role="main">
    <div class="wrap clearfix">
        <!--main content-->
        <div class="content clearfix">
            <section class="error">
                <!--Error type-->
                <div class="error-type">
                    <h1>404</h1>
                    <p>Page not found</p>
                </div>
                <!--//Error type-->

                <!--Error content-->
                <div class="error-content">
                    <h2>Whoops, you are in the middle of nowhere.</h2>
                    <h3>Don’t worry. You’ve probably made a wrong turn somewhwere.</h3>
                    <h4><spring:message code="error.sorry"/> <br/></h4>
                    <ul>
                        <li>If you typed in the address, check your spelling. Could just be a typo.</li>
                        <li>If you followed a link, it’s probably broken. Please <a href="#">contact us</a> and we’ll fix it.</li>
                        <li>If you’re not sure what you’re looking for, go back to <a href="${context}/index"><spring:message code="error.goToMain"/></a>.</li>
                    </ul>
                </div>
                <!--//Error content-->
            </section>
        </div>
        <!--//main content-->
    </div>
</div>
<!--//main-->
