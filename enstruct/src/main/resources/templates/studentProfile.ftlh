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
            <div class="col-sm-2 nav-col d-none d-lg-block">
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
                        <a class="nav-link"><i class="bi bi-megaphone"></i>Announcements</a>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link logout" onclick="location.href='/userLogout'"><i class="bi bi-box-arrow-left"></i>Log Out</a>
                    </li>
                </ul>
            </div>
            <!-- Main content -->
            <div style="background: #fff;" class="flex-grow-1 font-inter">
                <div class="container-lg mw-100 mx-0 mh-100">
                    <div class="row border-bottom">
                        <div class="col-3 p-5"><img src="/img/person-pic.jpg" class="my-3 rounded mx-auto d-block profile-pic" ></div>
                        <div class="col-9 p-5 d-flex flex-column justify-content-center">
                            <h1 class="mb-1 fw-bold">${user.lastName?upper_case!""}, ${user.firstName?capitalize!""}
                                ${user.middleName[0]!""}.
                            </h1>
                            <p class="lead">Student</p>
                            <div class="d-flex flex-row">
                                <button class="btn btn-primary">Edit </button>
                                <button class="btn btn-secondary mx-1">Reset</button>
                            </div>
                        </div>
                    </div>
                    <div class="row p-5">
                        <div class="col">
                            <h5 class="text-muted lead mb-3">General Information</h5>

                            <div class="mb-5">

                                <h3 class="fw-bold mb-1">${user.email!"No email address"}</h3>
                                <p class="lead text-muted">E-mail address</p>

                            </div>
                            <div class="mb-5">
                                <h3 class="fw-bold mb-1">${user.contactNumber!"No contact number"}</h3>
                                <p class="lead text-muted">Contact number</p>

                            </div>
                            <div class="mb-5">
                                <h3 class="fw-bold mb-1">${user.birthDate?date!"No birthdate"}</h3>
                                <p class="lead text-muted">Birthdate</p>
                            </div>
                        </div>
                        <div class="col">
                            <h5 class="text-muted lead mb-3">Courses Enrolled</h5>
                            <#if enrollments?has_content>
                                <#list enrollments as enrollment>
                                    <div class="d-flex flex-column p-3 mb-3" style="border-left: 5px solid rgb(249, 215, 115)">
                                        <h3 class="fw-bold mb-1">${enrollment.courseCode.courseName}</h3>
                                        <p class="mb-0">${enrollment.courseCode.courseCode}</p>
                                    </div>
                                </#list>
                            </div>
                            </#if>
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
