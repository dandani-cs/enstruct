<!doctype html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css">
    <link rel="stylesheet" href="/css/pmaster.css">
    <link rel="stylesheet" href="/css/classes.css">

    <title>E-nstruct</title>
  </head>
  <body>

    <div class="container-fluid">
      <div class="row" style="height: 100vh">
        <div class="col-sm-2 nav-col">
          <h1 class="title text-center">E-NSTRUCT</h1>
        <div class="profile-mini text-center">
          <img src="/img/person-pic.jpg" class="rounded mx-auto d-block profile-pic">
          <p id="user-fullname">ANDERSON, TIMOTHY</p>
          <p id="user-role">STUDENT</p>
        </div>
        <ul class="nav flex-column nav-pills nav-justified">
          <li class="nav-item">
            <a class="nav-link" aria-current="page" href="#"><i class="bi bi-house-fill"></i>Dashboard</a>
          </li>
          <li class="nav-item">
            <a class="nav-link active" href="/student/classes"><i class="bi bi-door-closed-fill"></i>Classes</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="/student/repositories"><i class="bi bi-archive-fill"></i>Repository</a>
          </li>
          <li class="nav-item">
            <a class="nav-link"><i class="bi bi-clipboard-data-fill"></i>Grades</a>
          </li>
          <li class="nav-item">
            <a class="nav-link"><i class="bi bi-person-badge-fill"></i>Profile</a>
          </li>

          <li class="nav-item">
            <a class="nav-link logout"><i class="bi bi-box-arrow-left"></i>Log Out</a>
          </li>


        </ul>
        </div>

        <div class="col-sm-10 main-content">
          <h1 class="heading">CLASSES</h1>
          <div class="row">
          
            <#list classes as c>
              <div class="col-3">
                <div class="card
                    <#if c.enabled == false>
                        disabled
                    </#if>
                ">
                    <div class="card-body">
                  <div onclick="window.location.href='/student/classes/${c.courseCode}'">
                    <h5 class="card-title lancpp" >C++ </h5>
                    &nbsp;
                    <h5 class="card-title" >${courseCode[c?index]}</h5>
                    </div>
                    <div style="display:flex; flex-direction: column; height: 100%;">
                    <p class="card-text" style="flex-grow:1">${c.courseName}</p>
                    <p class="card-text">${section[c?index]}
                        <!--<#if professor[c?index]??>
                            &middot; ${professor[c?index]}
                        </#if>-->
                    </p>
                    </div>
                  </div>
                </div>
              </div>
            </#list>
          </div>
        </div>

      </div>
    </div>
  
    <!-- Option 1: Bootstrap Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

  </body>
</html>
