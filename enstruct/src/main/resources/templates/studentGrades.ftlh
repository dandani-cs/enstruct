<!doctype html>
<html lang="en">
<head>
  <!-- Required meta tags -->
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">

  <!-- Bootstrap CSS -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css">
  <link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet">
  <link rel="stylesheet" href="/css/master_samonte.css">

  <title>E-nstruct</title>
</head>
<body>

  <div class="container-fluid">
    <div class="row gx-5">
      <div class="col-sm-2 nav-col">
        <h1 class="title text-center">E-NSTRUCT</h1>
        <div class="profile-mini text-center">
          <img src="person-pic.jpg" class="rounded mx-auto d-block profile-pic">
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
            <a class="nav-link logout"><i class="bi bi-box-arrow-left"></i>Log Out</a>
          </li>


        </ul>
      </div>

      <div class="col-sm-4 side-content">
        <div class="assignments-tab">
          <div class="row">
            <div class="col-sm-12">
              <h1 class="heading">GRADES</h1>
            </div>
          </div>
          <#list assignments as a>
            <div class="grades-item grades-active">
              <div class="row">
                <div class="col-sm-2">
                  <h2> E${a?index} </h2>
                </div>
                 <div class="col-sm-10 align-middle">
                   <h4 class="align-middle"> ${a.name} </h4>
                 </div>
              </div>
            </div>
          </#list>
          <div class="grades-item grades-active">
            <div class="row">
              <div class="col-sm-2">
                <h2> E1 </h2>
              </div>
              <div class="col-sm-10 align-middle">
                <h4 class="align-middle"> Exercise 1 </h4>
              </div>
            </div>
          </div>

          <div class="grades-item">
            <div class="row">
              <div class="col-sm-2">
                <h2> E2 </h2>
              </div>
              <div class="col-sm-10 align-middle">
                <h4 class="align-middle"> Exercise 2 </h4>
              </div>
            </div>
          </div>
        </div>


      </div>

      <div class="col-sm-6 main-content">
        <h1 class="heading">RESULTS</h1>
        <div class="results-content">
          <div class="row justify-content-between">
            <div class="col-md-4">
              <h3 class="heading-lbl">EXERCISE 1</h3>
            </div>

            <div class="col-md-4">
              <h3 class="">16/20</h3>
            </div>
          </div>

          <div class="row grades-details text-center">
            <h2>DETAILS</h2>
            <div class="row grade-detail">
              <div class="col-sm-6">
                <h5>DEADLINE</h5>
              </div>
              <div class="col-sm-6">
                <p>04/05/22</p>
              </div>
            </div>
            <div class="row grade-detail">
              <div class="col-sm-6">
                <h5>DATE SUBMITTED</h5>
              </div>
              <div class="col-sm-6">
                <p>04/05/22</p>
              </div>
            </div>
          </div>
        </div>

      </div>


    </div>
  </div>



  <!-- Option 1: Bootstrap Bundle with Popper -->
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

</body>
</html>
