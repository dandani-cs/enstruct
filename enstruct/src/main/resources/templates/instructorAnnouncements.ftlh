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
                        <a class="nav-link"><i class="bi bi-megaphone-fill"></i>Announcements</a>
                    </li>
            
                    <li class="nav-item">
                        <a class="nav-link logout"><i class="bi bi-box-arrow-left"></i>Log Out</a>
                    </li>
                </ul>
            </div>
            <!-- Main content -->
            <div style="background: #fff;" class="flex-grow-1 font-inter">
                <div class="container my-5 mw-100 mx-0 px-5">
                    <div>
                        <span class="highlight">
                            <h1 class="fw-bold color-prim-red display-6">Announcements</h1>
                        </span>
                    </div>

                    <!-- Announcements -->
                    <div class="mt-3 mb-4">
                        <p class="lead">Create a new announcement</p>
                        <form action="/instructor/postAnnouncement" method = "POST">
                            <div class="form-group mb-2">
                                <label class="mb-1">Course Section</label>
                                <select class="form-control" id="courseCode" name="courseCode">
                                    <#list courses as course>
                                        <option value="${course.courseCode}">${course.courseCode} - ${course.courseName}</option>
                                    </#list>
                                </select>
                            </div>
                            <div class="form-group mb-2">
                                <label class="mb-1">Announcement message</label>
                                <textarea class="form-control" rows="3" id="text" name="text" required></textarea>
                            </div>
                            <div class="text-center">
                                <button type="submit" class="btn btn-primary">Post announcement</button>
                            </div>
                        </form>
                    </div>
                    
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
