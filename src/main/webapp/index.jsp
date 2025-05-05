<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.time.LocalDateTime, java.time.format.DateTimeFormatter" %>
<%
    // Get current session information
    String username = (String) session.getAttribute("username");
    String role = (String) session.getAttribute("role");
    boolean isLoggedIn = (username != null && !username.isEmpty());
    
    // Generate timestamp for analytics
    String accessTimestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    
    // Current date and time for documentation
    String currentDateTime = "2025-04-30 18:48:03";
    String currentUser = "IT24102137";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Marry Mate - Your complete wedding planning and vendor booking platform">
    <title>Marry Mate | Wedding Planning Made Simple</title>
    
    <!-- Favicon -->
    <link rel="shortcut icon" href="https://img.icons8.com/color/48/wedding-rings.png" type="image/png">
    
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    
    <!-- FontAwesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    
    <!-- AOS - Animate On Scroll -->
    <link rel="stylesheet" href="https://unpkg.com/aos@next/dist/aos.css" />
    
    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css2?family=Playfair+Display:wght@400;500;600;700&family=Montserrat:wght@300;400;500;600;700&display=swap" rel="stylesheet">
    
    <!-- Swiper CSS for Carousels -->
    <link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.min.css">
    
    <!-- Custom CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/index.css">
</head>

<body>
    <!-- Navigation Bar -->
    <nav class="navbar navbar-expand-lg navbar-light fixed-top">
        <div class="container">
            <!-- Logo -->
            <a class="navbar-brand" href="${pageContext.request.contextPath}/">
                <div class="logo-container">
                    <i class="fas fa-heart"></i>
                    <i class="fas fa-ring"></i>
                </div>
                <span>Marry Mate</span>
            </a>
            
            <!-- Mobile Toggle Button -->
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarContent" aria-controls="navbarContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            
            <!-- Navigation Links -->
            <div class="collapse navbar-collapse" id="navbarContent">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <a class="nav-link active" href="${pageContext.request.contextPath}/">Home</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/views/vendor-categories.jsp">Vendors</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/views/wedding-guides.jsp">Wedding Guides</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/views/about.jsp">About Us</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/views/contact.jsp">Contact</a>
                    </li>
                </ul>
                
                <!-- Authentication Buttons -->
                <div class="auth-buttons">
                    <% if (isLoggedIn) { %>
                        <div class="dropdown">
                            <button class="btn btn-outline-primary dropdown-toggle" type="button" id="userDropdown" data-bs-toggle="dropdown" aria-expanded="false">
                                <i class="fas fa-user-circle me-1"></i> <%= username %>
                            </button>
                            <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="userDropdown">
                                <% if ("admin".equals(role)) { %>
                                    <li><a class="dropdown-item" href="${pageContext.request.contextPath}/admin/dashboard.jsp">Admin Dashboard</a></li>
                                <% } else if ("vendor".equals(role)) { %>
                                    <li><a class="dropdown-item" href="${pageContext.request.contextPath}/vendor/dashboard.jsp">Vendor Dashboard</a></li>
                                <% } else { %>
                                    <li><a class="dropdown-item" href="${pageContext.request.contextPath}/user/dashboard.jsp">My Dashboard</a></li>
                                <% } %>
                                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/user/profile.jsp">Profile Settings</a></li>
                                <li><hr class="dropdown-divider"></li>
                                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/LogoutServlet">Sign Out</a></li>
                            </ul>
                        </div>
                    <% } else { %>
                        <a href="${pageContext.request.contextPath}/login.jsp" class="btn btn-outline-primary me-2">Sign In</a>
                        <a href="${pageContext.request.contextPath}/signup.jsp" class="btn btn-primary">Join Now</a>
                    <% } %>
                </div>
            </div>
        </div>
    </nav>

    <!-- Hero Section -->
    <section class="hero">
        <div class="overlay"></div>
        <div class="container">
            <div class="row align-items-center min-vh-100">
                <div class="col-lg-6" data-aos="fade-right" data-aos-delay="200">
                    <h1>Your Dream Wedding Starts Here</h1>
                    <p class="lead">Find perfect vendors, plan your special day, and create memories that last a lifetime with Marry Mate.</p>
                    <div class="hero-buttons">
                        <a href="${pageContext.request.contextPath}/views/vendor-categories.jsp" class="btn btn-primary btn-lg">Find Vendors</a>
                        <a href="${pageContext.request.contextPath}/views/signup.jsp" class="btn btn-outline-light btn-lg">Start Planning</a>
                    </div>
                </div>
                <div class="col-lg-6 position-relative d-none d-lg-block" data-aos="fade-left" data-aos-delay="400">
                    <div class="floating-card card-1">
                        <img src="https://images.unsplash.com/photo-1519225421980-715cb0215aed?ixlib=rb-1.2.1&auto=format&fit=crop&w=700&q=80" alt="Wedding Venue">
                        <div class="card-content">
                            <h4>Perfect Venues</h4>
                            <p>Find your dream location</p>
                        </div>
                    </div>
                    <div class="floating-card card-2">
                        <img src="https://images.unsplash.com/photo-1502301197179-65228ab57f78?ixlib=rb-1.2.1&auto=format&fit=crop&w=700&q=80" alt="Wedding Photographer">
                        <div class="card-content">
                            <h4>Talented Photographers</h4>
                            <p>Capture timeless moments</p>
                        </div>
                    </div>
                    <div class="floating-card card-3">
                        <img src="https://images.unsplash.com/photo-1464979681340-bdd28a61699e?ixlib=rb-1.2.1&auto=format&fit=crop&w=700&q=80" alt="Wedding Catering">
                        <div class="card-content">
                            <h4>Exquisite Catering</h4>
                            <p>Delight your guests</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        
        <div class="wave-container">
            <svg class="wave" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1440 320">
                <path fill="#ffffff" fill-opacity="1" d="M0,128L48,117.3C96,107,192,85,288,90.7C384,96,480,128,576,149.3C672,171,768,181,864,165.3C960,149,1056,107,1152,96C1248,85,1344,107,1392,117.3L1440,128L1440,320L1392,320C1344,320,1248,320,1152,320C1056,320,960,320,864,320C768,320,672,320,576,320C480,320,384,320,288,320C192,320,96,320,48,320L0,320Z"></path>
            </svg>
        </div>
    </section>

    <!-- Key Features Section -->
    <section class="features section-padding">
        <div class="container">
            <div class="section-header text-center" data-aos="fade-up">
                <h2>Everything You Need for Your Special Day</h2>
                <p>Plan your perfect wedding with our comprehensive features</p>
            </div>
            
            <div class="row g-4 mt-4">
                <div class="col-md-4" data-aos="fade-up" data-aos-delay="100">
                    <div class="feature-card">
                        <div class="icon">
                            <i class="fas fa-search"></i>
                        </div>
                        <h3>Find Trusted Vendors</h3>
                        <p>Discover and book pre-screened local wedding professionals for every aspect of your celebration.</p>
                    </div>
                </div>
                
                <div class="col-md-4" data-aos="fade-up" data-aos-delay="200">
                    <div class="feature-card">
                        <div class="icon">
                            <i class="fas fa-tasks"></i>
                        </div>
                        <h3>Planning Tools</h3>
                        <p>Stay organized with customizable checklists, budgeting tools, and timelines for a stress-free experience.</p>
                    </div>
                </div>
                
                <div class="col-md-4" data-aos="fade-up" data-aos-delay="300">
                    <div class="feature-card">
                        <div class="icon">
                            <i class="fas fa-calendar-check"></i>
                        </div>
                        <h3>Easy Booking</h3>
                        <p>Compare availability, prices, and reviews before booking your vendors with just a few clicks.</p>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <!-- Vendor Categories Section -->
    <section class="vendor-categories section-padding bg-light">
        <div class="container">
            <div class="section-header text-center" data-aos="fade-up">
                <h2>Find Your Perfect Wedding Vendors</h2>
                <p>Explore our curated selection of top wedding professionals</p>
            </div>
            
            <div class="category-cards row g-4 mt-4">
                <div class="col-6 col-md-4 col-lg-3" data-aos="fade-up" data-aos-delay="100">
                    <a href="${pageContext.request.contextPath}/views/vendor-categories.jsp?category=venue" class="category-card">
                        <div class="icon">
                            <i class="fas fa-landmark"></i>
                        </div>
                        <h3>Venues</h3>
                        <span>View All <i class="fas fa-arrow-right"></i></span>
                    </a>
                </div>
                
                <div class="col-6 col-md-4 col-lg-3" data-aos="fade-up" data-aos-delay="200">
                    <a href="${pageContext.request.contextPath}/views/vendor-categories.jsp?category=photography" class="category-card">
                        <div class="icon">
                            <i class="fas fa-camera"></i>
                        </div>
                        <h3>Photographers</h3>
                        <span>View All <i class="fas fa-arrow-right"></i></span>
                    </a>
                </div>
                
                <div class="col-6 col-md-4 col-lg-3" data-aos="fade-up" data-aos-delay="300">
                    <a href="${pageContext.request.contextPath}/views/vendor-categories.jsp?category=catering" class="category-card">
                        <div class="icon">
                            <i class="fas fa-utensils"></i>
                        </div>
                        <h3>Catering</h3>
                        <span>View All <i class="fas fa-arrow-right"></i></span>
                    </a>
                </div>
                
                <div class="col-6 col-md-4 col-lg-3" data-aos="fade-up" data-aos-delay="400">
                    <a href="${pageContext.request.contextPath}/views/vendor-categories.jsp?category=florist" class="category-card">
                        <div class="icon">
                            <i class="fas fa-seedling"></i>
                        </div>
                        <h3>Florists</h3>
                        <span>View All <i class="fas fa-arrow-right"></i></span>
                    </a>
                </div>
                
                <div class="col-6 col-md-4 col-lg-3" data-aos="fade-up" data-aos-delay="500">
                    <a href="${pageContext.request.contextPath}/views/vendor-categories.jsp?category=music" class="category-card">
                        <div class="icon">
                            <i class="fas fa-music"></i>
                        </div>
                        <h3>Musicians & DJs</h3>
                        <span>View All <i class="fas fa-arrow-right"></i></span>
                    </a>
                </div>
                
                <div class="col-6 col-md-4 col-lg-3" data-aos="fade-up" data-aos-delay="600">
                    <a href="${pageContext.request.contextPath}/views/vendor-categories.jsp?category=cake" class="category-card">
                        <div class="icon">
                            <i class="fas fa-birthday-cake"></i>
                        </div>
                        <h3>Cake Designers</h3>
                        <span>View All <i class="fas fa-arrow-right"></i></span>
                    </a>
                </div>
                
                <div class="col-6 col-md-4 col-lg-3" data-aos="fade-up" data-aos-delay="700">
                    <a href="${pageContext.request.contextPath}/views/vendor-categories.jsp?category=dress" class="category-card">
                        <div class="icon">
                            <i class="fas fa-female"></i>
                        </div>
                        <h3>Bridal Wear</h3>
                        <span>View All <i class="fas fa-arrow-right"></i></span>
                    </a>
                </div>
                
                <div class="col-6 col-md-4 col-lg-3" data-aos="fade-up" data-aos-delay="800">
                    <a href="${pageContext.request.contextPath}/views/vendor-categories.jsp" class="category-card view-all">
                        <div class="icon">
                            <i class="fas fa-th-large"></i>
                        </div>
                        <h3>All Categories</h3>
                        <span>Browse All <i class="fas fa-arrow-right"></i></span>
                    </a>
                </div>
            </div>
        </div>
    </section>

    <!-- Featured Vendors Section -->
    <section class="featured-vendors section-padding">
        <div class="container">
            <div class="section-header text-center" data-aos="fade-up">
                <h2>Top-Rated Wedding Vendors</h2>
                <p>Discover our most loved wedding professionals</p>
            </div>
            
            <div class="swiper vendor-swiper mt-5">
                <div class="swiper-wrapper">
                    <!-- Vendor Card 1 -->
                    <div class="swiper-slide" data-aos="fade-up" data-aos-delay="100">
                        <div class="vendor-card">
                            <div class="vendor-img">
                                <img src="https://images.unsplash.com/photo-1519167758481-83f550bb49b3?ixlib=rb-1.2.1&auto=format&fit=crop&w=700&q=80" alt="Enchanted Gardens Venue">
                                <div class="vendor-category">Venue</div>
                            </div>
                            <div class="vendor-info">
                                <div class="vendor-rating">
                                    <i class="fas fa-star"></i>
                                    <i class="fas fa-star"></i>
                                    <i class="fas fa-star"></i>
                                    <i class="fas fa-star"></i>
                                    <i class="fas fa-star"></i>
                                    <span>(48)</span>
                                </div>
                                <h3>Enchanted Gardens</h3>
                                <p class="location"><i class="fas fa-map-marker-alt"></i> New York, NY</p>
                                <p class="price">Starting from $5,000</p>
                                <a href="${pageContext.request.contextPath}/views/vendor-details.jsp?id=1" class="btn btn-outline-primary">View Details</a>
                            </div>
                        </div>
                    </div>
                    
                    <!-- Vendor Card 2 -->
                    <div class="swiper-slide" data-aos="fade-up" data-aos-delay="200">
                        <div class="vendor-card">
                            <div class="vendor-img">
                                <img src="https://images.unsplash.com/photo-1527529482837-4698179dc6ce?ixlib=rb-1.2.1&auto=format&fit=crop&w=700&q=80" alt="Artistic Moments Photography">
                                <div class="vendor-category">Photography</div>
                            </div>
                            <div class="vendor-info">
                                <div class="vendor-rating">
                                    <i class="fas fa-star"></i>
                                    <i class="fas fa-star"></i>
                                    <i class="fas fa-star"></i>
                                    <i class="fas fa-star"></i>
                                    <i class="fas fa-star-half-alt"></i>
                                    <span>(36)</span>
                                </div>
                                <h3>Artistic Moments</h3>
                                <p class="location"><i class="fas fa-map-marker-alt"></i> Los Angeles, CA</p>
                                <p class="price">Starting from $2,500</p>
                                <a href="${pageContext.request.contextPath}/views/vendor-details.jsp?id=2" class="btn btn-outline-primary">View Details</a>
                            </div>
                        </div>
                    </div>
                    
                    <!-- Vendor Card 3 -->
                    <div class="swiper-slide" data-aos="fade-up" data-aos-delay="300">
                        <div class="vendor-card">
                            <div class="vendor-img">
                                <img src="https://images.unsplash.com/photo-1414235077428-338989a2e8c0?ixlib=rb-1.2.1&auto=format&fit=crop&w=700&q=80" alt="Divine Catering Services">
                                <div class="vendor-category">Catering</div>
                            </div>
                            <div class="vendor-info">
                                <div class="vendor-rating">
                                    <i class="fas fa-star"></i>
                                    <i class="fas fa-star"></i>
                                    <i class="fas fa-star"></i>
                                    <i class="fas fa-star"></i>
                                    <i class="far fa-star"></i>
                                    <span>(27)</span>
                                </div>
                                <h3>Divine Catering</h3>
                                <p class="location"><i class="fas fa-map-marker-alt"></i> Chicago, IL</p>
                                <p class="price">Starting from $75 per person</p>
                                <a href="${pageContext.request.contextPath}/views/vendor-details.jsp?id=3" class="btn btn-outline-primary">View Details</a>
                            </div>
                        </div>
                    </div>
                    
                    <!-- Vendor Card 4 -->
                    <div class="swiper-slide" data-aos="fade-up" data-aos-delay="400">
                        <div class="vendor-card">
                            <div class="vendor-img">
                                <img src="https://images.unsplash.com/photo-1470225620780-dba8ba36b745?ixlib=rb-1.2.1&auto=format&fit=crop&w=700&q=80" alt="Melody Makers Band">
                                <div class="vendor-category">Music</div>
                            </div>
                            <div class="vendor-info">
                                <div class="vendor-rating">
                                    <i class="fas fa-star"></i>
                                    <i class="fas fa-star"></i>
                                    <i class="fas fa-star"></i>
                                    <i class="fas fa-star"></i>
                                    <i class="fas fa-star"></i>
                                    <span>(52)</span>
                                </div>
                                <h3>Melody Makers</h3>
                                <p class="location"><i class="fas fa-map-marker-alt"></i> Miami, FL</p>
                                <p class="price">Starting from $1,800</p>
                                <a href="${pageContext.request.contextPath}/views/vendor-details.jsp?id=4" class="btn btn-outline-primary">View Details</a>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Swiper Navigation -->
                <div class="swiper-button-next"></div>
                <div class="swiper-button-prev"></div>
                <!-- Swiper Pagination -->
                <div class="swiper-pagination"></div>
            </div>
            
            <div class="text-center mt-4" data-aos="fade-up">
                <a href="${pageContext.request.contextPath}/views/vendor-categories.jsp" class="btn btn-primary">View All Vendors</a>
            </div>
        </div>
    </section>

    <!-- Testimonials Section -->
    <section class="testimonials section-padding bg-light">
        <div class="container">
            <div class="section-header text-center" data-aos="fade-up">
                <h2>What Happy Couples Say</h2>
                <p>Hear from couples who planned their perfect wedding with us</p>
            </div>
            
            <div class="swiper testimonial-swiper mt-5">
                <div class="swiper-wrapper">
                    <!-- Testimonial 1 -->
                    <div class="swiper-slide" data-aos="fade-up" data-aos-delay="100">
                        <div class="testimonial-card">
                            <div class="quote"><i class="fas fa-quote-left"></i></div>
                            <div class="testimonial-content">
                                <p>"Marry Mate made our wedding planning so much easier! We found amazing vendors that fit our budget and style. The planning tools kept us organized throughout the whole process."</p>
                            </div>
                            <div class="testimonial-author">
                                <img src="https://images.unsplash.com/photo-1543610892-0b1f7e6d8ac1?ixlib=rb-1.2.1&auto=format&fit=crop&w=128&q=80" alt="Sarah and Michael">
                                <div class="author-info">
                                    <h4>Sarah & Michael</h4>
                                    <p>Married June 2024</p>
                                </div>
                            </div>
                        </div>
                    </div>
                    
                    <!-- Testimonial 2 -->
                    <div class="swiper-slide" data-aos="fade-up" data-aos-delay="200">
                        <div class="testimonial-card">
                            <div class="quote"><i class="fas fa-quote-left"></i></div>
                            <div class="testimonial-content">
                                <p>"As a busy couple with full-time jobs, we didn't have much time for planning. Marry Mate connected us with vendors who understood our vision and made our dream wedding come true!"</p>
                            </div>
                            <div class="testimonial-author">
                                <img src="https://images.unsplash.com/photo-1535295972055-1c762f4483e5?ixlib=rb-1.2.1&auto=format&fit=crop&w=128&q=80" alt="Jessica and David">
                                <div class="author-info">
                                    <h4>Jessica & David</h4>
                                    <p>Married August 2024</p>
                                </div>
                            </div>
                        </div>
                    </div>
                    
                    <!-- Testimonial 3 -->
                    <div class="swiper-slide" data-aos="fade-up" data-aos-delay="300">
                        <div class="testimonial-card">
                            <div class="quote"><i class="fas fa-quote-left"></i></div>
                            <div class="testimonial-content">
                                <p>"The vendor reviews were spot-on! We booked our photographer and caterer through Marry Mate, and they exceeded our expectations. Our wedding day was absolutely perfect."</p>
                            </div>
                            <div class="testimonial-author">
                                <img src="https://images.unsplash.com/photo-1524623252636-db510bfb4128?ixlib=rb-1.2.1&auto=format&fit=crop&w=128&q=80" alt="Emma and James">
                                <div class="author-info">
                                    <h4>Emma & James</h4>
                                    <p>Married May 2024</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Swiper Pagination -->
                <div class="swiper-pagination"></div>
            </div>
        </div>
    </section>

    <!-- How It Works Section -->
    <section class="how-it-works section-padding">
        <div class="container">
            <div class="section-header text-center" data-aos="fade-up">
                <h2>How Marry Mate Works</h2>
                <p>Your wedding planning journey made simple</p>
            </div>
            
            <div class="timeline mt-5">
                <div class="row">
                    <div class="col-md-3" data-aos="fade-up" data-aos-delay="100">
                        <div class="step">
                            <div class="step-number">1</div>
                            <div class="step-icon"><i class="fas fa-user-plus"></i></div>
                            <h3>Create Account</h3>
                            <p>Sign up for free and tell us about your wedding vision and preferences.</p>
                        </div>
                    </div>
                    
                    <div class="col-md-3" data-aos="fade-up" data-aos-delay="200">
                        <div class="step">
                            <div class="step-number">2</div>
                            <div class="step-icon"><i class="fas fa-search"></i></div>
                            <h3>Discover Vendors</h3>
                            <p>Browse pre-screened vendors and filter by location, price, and availability.</p>
                        </div>
                    </div>
                    
                    <div class="col-md-3" data-aos="fade-up" data-aos-delay="300">
                        <div class="step">
                            <div class="step-number">3</div>
                            <div class="step-icon"><i class="fas fa-calendar-check"></i></div>
                            <h3>Book Services</h3>
                            <p>Compare options, read reviews, and book your favorite vendors securely.</p>
                        </div>
                    </div>
                    
                    <div class="col-md-3" data-aos="fade-up" data-aos-delay="400">
                        <div class="step">
                            <div class="step-number">4</div>
                            <div class="step-icon"><i class="fas fa-glass-cheers"></i></div>
                            <h3>Enjoy Your Day</h3>
                            <p>Relax and celebrate while your carefully selected vendors do what they do best.</p>
                        </div>
                    </div>
                </div>
            </div>
            
            <div class="text-center mt-5" data-aos="fade-up">
                <a href="${pageContext.request.contextPath}/views/signup.jsp" class="btn btn-primary btn-lg">Start Planning Today</a>
            </div>
        </div>
    </section>

    <!-- Join Banner Section -->
    <section class="join-banner">
        <div class="container">
            <div class="row align-items-center">
                <div class="col-lg-7" data-aos="fade-right">
                    <h2>Are You a Wedding Vendor?</h2>
                    <p>Join our platform to showcase your services to thousands of engaged couples. Expand your business and book more weddings.</p>
                </div>
                <div class="col-lg-5 text-lg-end" data-aos="fade-left">
                    <a href="${pageContext.request.contextPath}/views/signup.jsp?role=vendor" class="btn btn-light btn-lg">Join as Vendor</a>
                    <a href="${pageContext.request.contextPath}/views/vendor-info.jsp" class="btn btn-outline-light btn-lg ms-2">Learn More</a>
                </div>
            </div>
        </div>
    </section>

    <!-- Footer -->
    <footer class="main-footer">
        <div class="container">
            <div class="footer-content">
                <div class="row">
                    <div class="col-md-4 mb-4">
                        <div class="footer-logo">
                            <div class="logo-icon">
                                <i class="fas fa-heart"></i>
                                <i class="fas fa-ring"></i>
                            </div>
                            <h3>Marry Mate</h3>
                        </div>
                        <p>Your complete wedding planning and vendor booking platform. Making dream weddings come true since 2023.</p>
                        <div class="social-icons">
                            <a href="#"><i class="fab fa-facebook-f"></i></a>
                            <a href="#"><i class="fab fa-instagram"></i></a>
                            <a href="#"><i class="fab fa-pinterest-p"></i></a>
                            <a href="#"><i class="fab fa-twitter"></i></a>
                        </div>
                    </div>
                    
                    <div class="col-md-2 col-6 mb-4">
                        <h4>Quick Links</h4>
                        <ul class="footer-links">
                            <li><a href="${pageContext.request.contextPath}/views/vendor-categories.jsp">Find Vendors</a></li>
                            <li><a href="${pageContext.request.contextPath}/views/wedding-guides.jsp">Wedding Guides</a></li>
                            <li><a href="${pageContext.request.contextPath}/views/about.jsp">About Us</a></li>
                            <li><a href="${pageContext.request.contextPath}/views/contact.jsp">Contact</a></li>
                            <li><a href="${pageContext.request.contextPath}/views/blog.jsp">Blog</a></li>
                        </ul>
                    </div>
                    
                    <div class="col-md-2 col-6 mb-4">
                        <h4>For Couples</h4>
                        <ul class="footer-links">
                            <li><a href="${pageContext.request.contextPath}/views/signup.jsp">Sign Up</a></li>
                            <li><a href="${pageContext.request.contextPath}/user/dashboard.jsp">Wedding Dashboard</a></li>
                            <li><a href="${pageContext.request.contextPath}/user/checklist.jsp">Planning Checklist</a></li>
                            <li><a href="${pageContext.request.contextPath}/user/budget.jsp">Budget Planner</a></li>
                            <li><a href="${pageContext.request.contextPath}/user/guest-list.jsp">Guest List</a></li>
                        </ul>
                    </div>
                    
                    <div class="col-md-2 col-6 mb-4">
                        <h4>For Vendors</h4>
                        <ul class="footer-links">
                            <li><a href="${pageContext.request.contextPath}/views/signup.jsp?role=vendor">Join as Vendor</a></li>
                            <li><a href="${pageContext.request.contextPath}/vendor/dashboard.jsp">Vendor Dashboard</a></li>
                            <li><a href="${pageContext.request.contextPath}/vendor/pricing.jsp">Pricing</a></li>
                            <li><a href="${pageContext.request.contextPath}/vendor/success-stories.jsp">Success Stories</a></li>
                            <li><a href="${pageContext.request.contextPath}/vendor/faq.jsp">Vendor FAQ</a></li>
                        </ul>
                    </div>
                    
                    <div class="col-md-2 col-6 mb-4">
                        <h4>Support</h4>
                        <ul class="footer-links">
                            <li><a href="${pageContext.request.contextPath}/views/help-center.jsp">Help Center</a></li>
                            <li><a href="${pageContext.request.contextPath}/views/faq.jsp">FAQ</a></li>
                            <li><a href="${pageContext.request.contextPath}/views/terms.jsp">Terms of Service</a></li>
                            <li><a href="${pageContext.request.contextPath}/views/privacy.jsp">Privacy Policy</a></li>
                            <li><a href="${pageContext.request.contextPath}/views/contact.jsp">Contact Support</a></li>
                        </ul>
                    </div>
                </div>
            </div>
            
            <div class="footer-bottom">
                <div class="row align-items-center">
                    <div class="col-md-6">
                        <p>&copy; 2025 Marry Mate. All rights reserved. | Current Date: <%= currentDateTime %> | Developer: <%= currentUser %></p>
                    </div>
                    <div class="col-md-6 text-md-end">
                        <ul class="footer-bottom-links">
                            <li><a href="${pageContext.request.contextPath}/views/terms.jsp">Terms</a></li>
                            <li><a href="${pageContext.request.contextPath}/views/privacy.jsp">Privacy</a></li>
                            <li><a href="${pageContext.request.contextPath}/views/cookies.jsp">Cookies</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </footer>

    <!-- Back to Top Button -->
    <button id="back-to-top" class="back-to-top">
        <i class="fas fa-arrow-up"></i>
    </button>

    <!-- jQuery -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    
    <!-- Bootstrap Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    
    <!-- AOS - Animate On Scroll -->
    <script src="https://unpkg.com/aos@next/dist/aos.js"></script>
    
    <!-- Swiper JS -->
    <script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>
    
    <!-- Custom JS -->
    <script src="${pageContext.request.contextPath}/assets/js/index.js"></script>
</body>
</html>