<%-- 
    Document   : home
    Created on : Jan 19, 2024, 10:10:24 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

        <link rel="stylesheet" href="css/header.css" />
        <link rel="stylesheet" href="css/home.css" />
        <link rel="stylesheet" href="css/footer.css" />
        <link rel="stylesheet" href="css/boarding-process.css" />
        <link rel="stylesheet" href="css/cabin-type.css" />

        <link rel="preconnect" href="https://fonts.googleapis.com" />
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
        <link
            href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,500;1,200&family=Quicksand:wght@500&display=swap"
            rel="stylesheet"
            />
    </head>
    <body>
        <!-- HEADER -->
        <header class="container">
            <div class="logo"></div>
            <div class="menu">
                <ul>
                    <li><a href="home.html">Home</a></li>
                    <li><a href="about.html">About</a></li>
                    <li class="dropdown">
                        <a href="">Cat Boarding</a>
                        <ul>
                            <li><a href="requirement.jsp">Boarding Requirements</a></li>
                            <li><a href="Guide.jsp">Boarding Guide</a></li>
                            <li><a href="boarding.jsp">Boarding Rates</a></li>
                        </ul>
                    </li>
                    <li><a href="news.html">Service</a></li>
                    <li><a href="contact.html">Contact</a></li>
                </ul>
            </div>
            <div class="booking">
                <input class="bookingbtn" type="button" value="Book Now" />
            </div>
            <div class="user">
<!--                <a href="login.jsp" class="login">Hello, Sign in</a>-->
                <a href="login.jsp" class="login">Hello, ${sessionScope.username != null ? sessionScope.username : "Sign in"}</a>
                <a href="order.jsp" class="order">Order</a>
                <a href="cart.jsp" class="cart"
                   ><i class="fa-regular fa-cart-shopping"></i> Cart</a
                >
            </div>
        </header>
        <!-- BODY -->
        <div class="body">
            <div class="image">
                <img
                    src="picture/z5077342167975_2837d168fa8646f56ed1e385320a9aff.jpg"
                    alt="pe Hy"
                    />
                <h1 class="title">WELCOME TO NEKO HEE</h1>
            </div>
            <!-- BOOKING FORM -->
            <div class="booking-form">
                <div class="search">
                    <form action="DispatchServlet">
                        <div class="checkin">
                            <p>Check In</p>
                            <input type="date" name="checkin" />
                        </div>
                        <div class="checkout">
                            <p>Check Out</p>
                            <input type="date" name="checkout" />
                        </div>
                        <div class="btn-search">
                            <input type="submit" value="SEARCH" name="btAction" />
                        </div>
                    </form>

                    <!-- search result -->
                    <div class="search-result">
                        <h2>Search results</h2>
                        <div class="container">
                            <div>
                                <a href="cabin.html">
                                    <img
                                        src="picture/CC_C3-scaled-e1695366218592.jpg"
                                        alt="classic cabin"
                                        />
                                    <h3>Classic Cabin</h3>
                                    <button>VIEW</button>
                                </a>
                            </div>

                            <div>
                                <a href="cabin.html">
                                    <img
                                        src="picture/CC_C3-scaled-e1695366218592.jpg"
                                        alt="classic cabin"
                                        />
                                    <h3>Classic Cabin</h3>
                                    <button>VIEW</button>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- CABIN TYPE -->
            <center class="cabin-type">
                <h2>Cabin Types</h2>
                <div class="container">
                    <div>
                        <img
                            src="picture/Nekoya-JK-5-qcrxvja7k3idl9mw0dinxm2k6e72oxqc5tb814efnc.jpg"
                            alt="Classic Cabin"
                            />
                        <h3>Classic Cabin</h3>
                        <p>From 49$</p>
                    </div>
                    <div>
                        <img
                            src="picture/deluxe-cabin-qgzbu5iy2iml1i72vt2ih2pk2m6k53n7at6q8or5x4.jpg"
                            alt="Duluxe Cabin"
                            />
                        <h3>Duluxe Cabin</h3>
                        <p>From 53$</p>
                    </div>
                    <div>
                        <img
                            src="picture/double-deluxe-qgzbu6gs9cnvd45pqbh51kh0o01xcsqxmxu7pyprqw.jpg"
                            alt="Double Duluxe Cabin"
                            />
                        <h3>Double Duluxe Cabin</h3>
                        <p>From 88$</p>
                    </div>
                    <div>
                        <img
                            src="picture/premiere-suite-qgzbu7emg6p5oq4cktvrm28h9dxakhunz2hp78odko.jpg"
                            alt="Premier Suit"
                            />
                        <h3>Premier Suit</h3>
                        <p>From 108$</p>
                    </div>
                </div>
                <button><a href="boarding_rates.html">See Full Pricing</a></button>
            </center>
            <!-- BOARDING PROCCESS -->
            <center class="boarding-process">
                <h2>Our Boarding Process</h2>
                <div class="container">
                    <img src="picture/CatGuest06.jpg" alt="" />
                    <div class="steps">
                        <div class="step">
                            <p>Step 1</p>
                            <h3>Check Eligibility</h3>
                            <p>Check boarding eligibility here!</p>
                        </div>
                        <div class="step">
                            <p>Step 2</p>
                            <h3>Book a Viewing (Optional)</h3>
                        </div>
                        <div class="step">
                            <p>Step 3</p>
                            <h3>Book Date & Cabin Type</h3>
                        </div>
                        <div class="step">
                            <p>Step 4</p>
                            <h3>Pack Cat's Boarding Bag</h3>
                        </div>
                        <div class="step">
                            <p>Step 5</p>
                            <h3>Check in/out</h3>
                            <p>Check in: 2PM – 5.30PM</p>
                            <br />
                            <p>Check out: 11AM – 12.30PM</p>
                        </div>
                    </div>
                </div>
            </center>
            <!-- DESCRIPTION -->
            <!-- SERVICE -->
        </div>
        <!-- FOOTER -->
        <footer>
            <div class="container">
                <div>
                    <h2>Contact</h2>
                    <p>Hotline: 012345678</p>
                    <p>Email: eochanghee@gmail.com</p>
                </div>
                <div>
                    <h2>Location</h2>
                    <p>S201 - Vinhome Grand Park - District 9 - Ho Chi Minh</p>
                    <p>S105 - Vinhome Grand Park - District 9 - Ho Chi Minh</p>
                </div>
                <div>
                    <h2>Opening hour</h2>
                    <p>8 am - 6 pm (7 days)</p>
                </div>
            </div>
            <div class="copyright">
                <p>Copyright © 2024 Neko Hee Hotel</p>
            </div>
        </footer>
    </body>
</html>
