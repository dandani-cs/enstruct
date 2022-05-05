<!doctype html>
<html lang="en">
<head>
  <!-- Required meta tags -->
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">

  <!-- Bootstrap CSS -->
  <link rel="stylesheet" href="//code.jquery.com/ui/1.13.1/themes/base/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">

  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css">
  <link rel="stylesheet" href="/css/master_samonte.css">

  <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
  <script src="https://code.jquery.com/ui/1.13.1/jquery-ui.js"></script>

  <script>
  </script>

  <title>E-nstruct</title>
</head>
<body>

  <div class="container-fluid">
    <div class="row">
      <div class="col-sm-2 nav-col">
        <h1 class="title text-center">E-NSTRUCT</h1>
        <div class="profile-mini text-center">
          <img src="img/person-pic.jpg" class="rounded mx-auto d-block profile-pic">
          <p id="user-role">INSTRUCTOR</p>
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
            <a class="nav-link logout" onclick="location.href='/userLogout'"><i class="bi bi-box-arrow-left"></i>Log Out</a>
          </li>


        </ul>
      </div>

      <div class="col-sm-10 main-content">
        <h1 class="heading">ADD ASSIGNMENT</h1>
        <div class="row">
          <form method="POST" class="add-form">
            <div class="row g-3 align-items-center">
              <div class="col-2">
                <label for="name" class="col-form-label">Name</label>
              </div>
              <div class="col-4">
                <input type="text" id="name" name="name" class="form-control">
              </div>
              <div class="col-2">
                <label for="course" class="col-form-label">Course Code</label>
              </div>
              <div class="col-4">
                <select class="form-select" name="course">
                  <#list courses as c>
                  <option value="${c.courseCode}">${c.courseCode}</option>
                  </#list>
                </select>
              </div>

            </div>
            <div class="row mt-3">
              <label for="instruction" class="form-label">Instruction</label>
              <textarea name="instruction" class="form-control" rows="8" cols="80"></textarea>
            </div>
            <div class="row mt-3">
              <div class="col-sm-4">
                <p>Deadline: <input type="date" name="deadline_date" class="form-control" id="deadline"></p>
              </div>
              <div class="col-sm-4">
                <p>Availability start: <input type="date" name="availabilityStart" class="form-control" id="availabilityStart"></p>
              </div>
              <div class="col-sm-4">
                <p>Max score: <input type="number" class="form-control" name="maxScore" value=""> </p>
                <!--  -->
              </div>
            </div>
            <div class="row mt-3">
              <div class="col-sm-4">
                <p>Deadline Time: <input type="time" name="deadline_time" class="form-control" value=""> </p>
              </div>
              <div class="col-sm-4">
                <p>Availability end: <input type="date" name="availabilityEnd" class="form-control" id="availabilityEnd"></p>
              </div>
              <div class="col-sm-4">
                <button type="submit" class="btn btn-primary maxwidth" >Submit</button>
              </div>
            </div>

          </form>
        </div>
        </div>

      </div>

    </div>
  </div>



  <!-- Option 1: Bootstrap Bundle with Popper -->
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

</body>
</html>
