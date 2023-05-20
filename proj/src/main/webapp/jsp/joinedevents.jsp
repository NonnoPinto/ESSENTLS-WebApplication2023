<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ include file="../html/cdn.html"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta name="description" content="ESN Padova application">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Event List</title>
    </head>
    <body>
        <%@include file="navbar.jsp"%>
        <div class="container">
            <h1 class="page-title text-center p-2">Your events!</h1>
            <c:choose>
                <c:when test="${message.isError()}">
                    <ul>
                        <li>message: <c:out value="${message.message}"/></li>
                    </ul>
                </c:when>
                <c:otherwise>
                    <div class="row justify-content-start">
                        <c:forEach items="${events}" var="event" varStatus="loop">
                            <div class="col-lg-3 col-md-6 col-sm-12">
                                <div class="card border-black event-preview">
                                    <img class="card-img-top" src="" alt="Event thumbnail">
                                    <div class="card-body">
                                        <div class="row">
                                                <!-- <img class="w-100 mh-10rem" src="${event.thumbnail}" alt="${event.name} thumbnail"> -->
                                            <div class="col-3 event-date"><c:out value="${event.eventStart}"/></div>
                                            <div class="col-9 event-infos">
                                                <h4 class="event title">
                                                    <c:out value="${event.name}"/>
                                                </h4>
                                                <h6 class="event-price">
                                                    €: <c:out value="${event.price}"/>
                                                </h6>
                                                <h6 class="event-location">
                                                    <!-- <c:out value="${event.location}"/> -->
                                                    <p>
                                                        <i class="fa-solid fa-map-marker"></i>
                                                        <span style="text-transform: capitalize">
                                                            <c:out value="${event.location.street}"/>,
                                                        </span>
                                                        <span style="text-transform: capitalize">
                                                            <c:out value="${event.location.number}"/>
                                                        </span>
                                                        <span style="text-transform: capitalize">
                                                            <c:out value="${event.location.zip}"/>
                                                        </span>
                                                        <span style="text-transform: capitalize">
                                                            <c:out value="${event.location.city}"/>
                                                        </span>
                                                        <span style="text-transform: capitalize">
                                                            <c:out value="${event.location.country}"/>
                                                        </span>
                                                    </p>
                                                </h6>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <button name="id" value="${event.id}" class="button bg-orange text-white border-orange px-4 py-2">Details</button>
                                <a href="confirmEvent?id=${event.id}"><button class="button bg-orange text-white border-orange px-4 py-2">Additional info</button></a>
                            </div>
                        </c:forEach>
                    </div>
                </c:otherwise>
            </c:choose>
            </div>
            <!-- <c:choose>
                <c:when test="${message.isError()}">
                    <ul>
                        <li>message: <c:out value="${message.message}"/></li>
                    </ul>
                </c:when>
                <c:otherwise>
                    <div class="row">
                        <c:forEach items="${events}" var="event" varStatus="loop">
                            <div class="col-lg-3 col-md-6 col-sm-12">
                                <div class="event-preview card">

                                    <div class="row">
                                        <div class="col-12 event-image">
                                            <img class="w-100 mh-10rem" src="${event.thumbnail}" alt="${event.name} thumbnail">
                                        </div>
                                        <div class="col-3 event-date">
                                            <c:out value="${event.dateStart}"/>
                                        </div>
                                        <div class="col-9 event-infos">
                                            <h4 class=""><c:out value="${event.name}"/></h4>
                                            <h6 class="event-price">€: <c:out value="${event.price}"/></h6>
                                            <h6 class="event-location"><c:out value="${event.description}"/></h6>
                                        </div>
                                    </div>
                                </div>
                                <li>Event ID: <c:out value="${event.id}"/></li>
                                <li>Event Name: <c:out value="${event.name}"/></li>
                                <li>Event Location: <c:out value="${event.location}"/></li>
                                <li>Event Price: <c:out value="${event.price}"/></li>
                                <li>Event Description: <c:out value="${event.description}"/></li>
                                <form action="eventdetail">
                                    <button name="id" value="${event.id}" class="button bg-orange text-white border-orange px-4 py-2">Details</button>
                                </form>
                                <a href="confirmEvent?id=${event.id}"><button class="button bg-orange text-white border-orange px-4 py-2">Additional info</button></a>
                            </div>
                        </c:forEach>
                    </div>
                    
                </c:otherwise>
            </c:choose> -->
        </div>
        <%@include file="/html/footer.html"%>
    </body>
</html>


<!-- <div class="container">
    <div class="row">
        <div class="col"></div>
        <div class="col"></div>
        <div class="col"></div>
        <div class="col"></div>
    </div>
</div>

<div class="container">
    <div class="row">
      <div class="col-sm-12 col-md-6 col-2">auhwqiuhw</div>
      <div class="col-sm-12 col-md-6 col-2">qweqwe</div>
      <div class="col-sm-12 col-md-12 col-8">qwqwe</div>
    </div>
  </div>
  

  xs
  576
  sm
  768
  md
  992
  lg
  1200
  xl -->