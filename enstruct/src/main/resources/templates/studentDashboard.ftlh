<!doctype html>
<html lang="en">
<head>
  <!-- Required meta tags -->
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">

  <!-- Bootstrap CSS -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css">
  <link rel="stylesheet" href="/css/master_samonte.css">

  <title>E-nstruct</title>
</head>
<body>

  <div class="container-fluid">
    <div class="row gx-5">
      <div class="col-sm-2 nav-col">
        <h1 class="title text-center">E-NSTRUCT</h1>
        <div class="profile-mini text-center">
          <img src="img/person-pic.jpg" class="rounded mx-auto d-block profile-pic">
          <p id="user-fullname">${user.lastName?capitalize}, ${user.firstName?capitalize}</p>
          <p id="user-role">STUDENT</p>
        </div>
        <ul class="nav flex-column nav-pills nav-justified">
          <li class="nav-item">
            <a class="nav-link active" aria-current="page" href="#"><i class="bi bi-house-fill"></i>Dashboard</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="/student/classes"><i class="bi bi-door-closed-fill"></i>Classes</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="/student/repositories"><i class="bi bi-archive-fill"></i>Repository</a>
          </li>
          <li class="nav-item">
            <a class="nav-link"><i class="bi bi-clipboard-data-fill"></i>Grades</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="/addUser"><i class="bi bi-person-badge-fill"></i>Add User</a>
          </li>

          <li class="nav-item">
            <a class="nav-link logout" onclick="location.href='/userLogout'"><i class="bi bi-box-arrow-left"></i>Log Out</a>
          </li>


        </ul>
      </div>

      <div class="col-sm-6 main-content">
        <h1 class="heading">DASHBOARD</h1>
        <div class="row">
          <div class="col-md-6">
            <div class="box-info">
              <h1 class="float-start mt-3" id="courses-enrolled">1</h1>
              <div class="float-end float-parent align-middle">
                <div class="float-start align-middle mt-3">
                  <h2>COURSES</h2>
                  <p>enrolled</p>
                </div>
                <div class="float-end">
                  <img src="img/courses-img.png" alt="">
                </div>
              </div>
            </div>
          </div>

          <div class="col-md-6">
            <div class="box-info" style="background-color: #D4EFDF !important">
              <h1 class="float-start mt-3" id="assignments-ongoing">0</h1>
              <div class="float-end float-parent align-middle">
                <div class="float-start align-middle mt-3">
                  <h2>EXERCISES</h2>
                  <p>ongoing</p>
                </div>
                <div class="float-end">
                  <img src="img/assignments-img.png" alt="">
                </div>
              </div>
            </div>
          </div>
        </div>

        <div class="row mt-5">
          <div class="col-sm-6">
            <div class="img-links">
              <a href="/instructor/classes">
              <img src="img/classes-img.png" alt="">
              </a>
            </div>
          </div>

          <div class="col-sm-6">
            <div class="row">
              <div class="col-sm-12">
                <a href="/instructor/grades">
                <img src="img/grades-img.png" alt="">
                </a>
              </div>
            </div>
            <div class="row mt-5">
              <div class="col-sm-12">
                <a href="/instructor/repositories">
                <img src="img/repo-img.png" alt="">
                </a>
              </div>
            </div>
          </div>
        </div>
      </div>

      <div class="col-sm-4 side-content">
        <div class="assignments-tab">
          <div class="row">
            <div class="col-sm-12">
              <h4> AGENDA </h4>
            </div>
          </div>
          <#list agenda as a>
          <div class="agenda-item">
            <div class="row">
              <div class="col-sm-12">
                <h5> ${a.name} </h5>
              </div>
            </div>
            <div class="row">
              <div class="col-sm-6">
                <p>${courses[a?index]}</p>
              </div>
              <div class="col-sm-6">
                  Due: <#if a.deadline_date??>
                      ${a.deadline_date}
                  <#elseif a.deadline??>
                      ${a.deadline}
                  <#else>
                      No deadline set
                  </#if>

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
