<!doctype html>
<html lang="en">
    <head>
        <title>E-nstruct</title>

        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css">
        <link rel="stylesheet" href="/css/master.css">
        <link rel="stylesheet" href="/css/announcement.css">

        <!-- Google Fonts -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Inter:wght@100;200;300;400;500;600;700&display=swap" rel="stylesheet">
    </head>
    <body>
        <div class="d-flex flex-sm-column fill-height flex-lg-row">
            <!--Sidenav -->
            <div class="col-sm-3 nav-col d-none d-lg-block">
                <h1 class="title text-center">E-NSTRUCT</h1>
                <div class="profile-mini text-center">
                    <img src="/img/person-pic.jpg" class="rounded mx-auto d-block profile-pic">
                    <p id="user-fullname">ANDERSON, TIMOTHY</p>
                    <p id="user-role">STUDENT</p>
                </div>
                <ul class="nav flex-column nav-pills nav-justified">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="#"><i class="bi bi-house-fill"></i>Dashboard</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#"><i class="bi bi-door-closed-fill"></i>Classes</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#"><i class="bi bi-archive-fill"></i>Repository</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link"><i class="bi bi-clipboard-data-fill"></i>Grades</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link"><i class="bi bi-person-badge-fill"></i>Profile</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link"><i class="bi bi-megaphone-fill"></i>Announcements</a>
                    </li>
            
                    <li class="nav-item">
                        <a class="nav-link logout" onclick="location.href='/userLogout'"><i class="bi bi-box-arrow-left"></i>Log Out</a>
                    </li>
                </ul>
            </div>
            <!-- Main content -->
            <div style="background: #fff;" class="flex-grow-1    font-inter">
                <div class="container my-5 mw-100 mx-0 px-5">
                    <div>
                        <span class="highlight">
                            <h1 class="fw-bold color-prim-red display-6">Announcements</h1>
                        </span>
                    </div>

                    <div class="row row-cols-lg-2 row-cols-md-2">
                       
                        <div class="col-lg-8 col-md-12 h-100 mt-3 ">
                            <#list announcements as announce>
                                <div class="announcement">
                                    <p class="fs-4 mb-0" style="overflow: hidden; text-overflow: ellipsis;">Announcement for ${announce.courseCode.courseName}</p>
                                    <div class="d-flex flex-row align-items-start justify-content-between mb-0">
                                        <div class="w-50">
                                            <p class="small mb-0">${announce.courseCode.courseCode}</p>
                                        </div>

                                        <div class="text-end">
                                            <p class="small mb-0">Prof. ${announce.courseCode.teacherId.firstName} ${announce.courseCode.teacherId.lastName} </p>
                                            <p class="small mb-0">Posted on: ${announce.date}</p>
                                        </div>
                                    </div>
                                    <p class="announcement-content">${announce.text}</p>
                                </div>
                            </#list>

                            <table class="table">

                            </table>

                            <!-- Calendar -->
                            <div class="" id="calendar-container">
                                <h5 class="mb-3">Calendar </h5>
                                <div class="container">
                                    <div class="row text-center">
                                        <div class="col"><p class="small date-num">Sun</p></div>
                                        <div class="col"><p class="small date-num">Mon</p></div>
                                        <div class="col"><p class="small date-num">Tue</p></div>
                                        <div class="col"><p class="small date-num">Wed</p></div>
                                        <div class="col"><p class="small date-num">Thu</p></div>
                                        <div class="col"><p class="small date-num">Fri</p></div>
                                        <div class="col"><p class="small date-num">Sat</p></div>
                                    </div>
                                    <#list calendar_table as row>
                                    <div class="row">
                                        <#list row as days>
                                        <div class="col date-cell border" style="width: 1rem;">
                                            <#assign day_idx = row?index * 7 + days?index + 1>
                                            <#if day_idx gt weekday_start - 1 && day_idx <= days_in_mo + weekday_start - 1>
                                                <p class="small date-num text-muted text-end">${day_idx - weekday_start + 1}</p>
                                            </#if>

                                            <#if days?has_content>
                                                <#list days as assignment>
                                                <div class="date-announcement mb-1">
                                                    <div class="fw-light" style="white-space: nowrap; overflow: hidden; text-overflow: ellipsis;">
                                                        <small>${assignment.name}</small>
                                                    </div>
                                                </div>
                                                </#list>
                                            </#if>

                                        </div>
                                        </#list>
                                    </div>
                                    </#list>
                                </div>
                            </div>
                        </div>
                        
                        <!-- Calendar -->
                        <div class="col-lg-4 col-md-12 h-100 mt-3 ">
                            <div class="agenda-container">
                                <h5 class="mb-3 agenda-head">Agenda</h5>
                                <#list assignments as assignment>
                                    <div class="agenda">
                                        <div class="d-flex flex-column px-4 py-3">
                                            <p class="mb-0 fw-bold"><a href="/student/assignment/${assignment.assignmentId}">${assignment.name}</a></p>
                                            <div class="d-flex flex-column justify-content-between">
                                                <p class="mb-0 fs-6 small">${assignment.course.courseName}</p>
                                                <p class="mb-0 fs-7 small">Due: ${assignment.deadline_date}</p>
                                            </div>
                                        </div>
                                    </div>
                                </#list>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script type="text/javascript">

        </script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" 
                integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" 
                crossorigin="anonymous"></script>
    </body>
</html>
