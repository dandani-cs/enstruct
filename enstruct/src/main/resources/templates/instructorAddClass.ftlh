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
            <a class="nav-link active" href="/instructor/classes"><i class="bi bi-door-closed-fill"></i>Classes</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="/instructor/repositories"><i class="bi bi-archive-fill"></i>Repository</a>
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

      <div class="col-sm-10 main-content" style="display:flex; flex-direction: column;">
        <div style="display:flex; flex-direction: row; align-items: center;">
          <div style="display:flex; flex-direction: column; flex-grow: 1;">
            <h1 class="heading" style="margin-bottom: 10px;">CLASSES</h1>
            <h2 style="color: #A93226; margin-bottom: 20px;">Add New Class</h2>
        </div>
        </div>


        <div class="row prow" style="flex-grow: 1;">
          
            <form method="post" name="addClassForm" id="addClassForm">
                            <div class="row">
                          <div class="pautoFilled col-lg-4">

                          <label for="fname">CourseCode:</label>
                          <input type="text" id="CourseCode" name="CourseCode" oninput="checkCourse()" required>
                        </div>
                        <div class="pautoFilled col-lg-4">

                          <label for="fname">Section:</label>
                          <input type="text" id="Section" name="Section" oninput="checkCourse()" required>
                        </div>
                        <div class="pautoFilled col-lg-4">

                          <label for="fname">Term:</label>
                          <input type="text" id="Term" name="Term" oninput="checkCourse()" required>
                        </div>
                      </div>
                      <div class="row">
                        <div class=col>
                        <span id="pinputError" class="pinputError">Course with given details already exists!</span>
                        </div>
                      </div>
                        <div class="row">
                          <div class="col-lg-11">
                        <label for="fname" style="margin-top: 20px;">Course Name:</label>
                        <input type="text" id="courseName" name="courseName">
                          </div>
                          <div class="col-lg-1">
                            <label for="fname" style="margin-top: 20px;">Enabled:</label>
                            <input type="checkbox" id="courseEnabled" name="courseEnabled"  style="height:20px; margin-top: 10px;">
                              </div>
                      </div>

                      <div class="row" style="padding-left: 15px; padding-right: 15px; flex-grow: 1; display: flex; flex-direction: column;">
                        <label for="students" style="margin-top: 20px; margin-left: -15px;">Students:</label>
                        <input class="searchInput" type="text" id="tableinput" onkeyup="tableSearcher()" placeholder="Search by Full Name">
                        <div class="tableholder">
                          <table id="ptable" class="table table-hover" >
                            <thead>
                              <tr>
                                <th scope="col">Id</th>
                                <th scope="col">First Name</th>
                                <th scope="col">Middle Name</th>
                                <th scope="col">Last Name</th>
                                <th scope="col">Email Address</th>
                                <th scope="col">Include</th>
                              </tr>
                            </thead>
                            <tbody>

                            <#list students as s>
                            <tr>
                                <td scope="row">${s.userId}</td>
                                <td><#if s.firstName??>
                                    ${s.firstName}
                                    </#if>
                                </td>
                                <td><#if s.middleName??>
                                    ${s.middleName}
                                    </#if>
                                <td><#if s.lastName??>
                                    ${s.lastName}
                                    </#if>
                                <td><#if s.email??>
                                    ${s.email}
                                    </#if>
                                </td>
                                <td><input id="${s.userId}ck" class="checkbox" type="checkbox" />&nbsp;</td>
                            </tr>
                            </#list>
                            </tbody>
                          </table>
                        </div>
                      </div>
                      <div class="row" style="padding-left: 15px; padding-right: 15px;">
                        <input type="button" id="psubmit" value="Create Class" onClick="submitForm()">

                      </div>
                      </form>

                    </div>



                    </div>
                  </div>


                </div>
              </div>


              <script>
                function checkCourse() {
                  var courseCode = document.getElementById("CourseCode").value;
                  var section = document.getElementById("Section").value;
                  var term = document.getElementById("Term").value;

                  var courseId = courseCode + "_" + section + "_" + term;
                  courseId = courseId.toUpperCase();
                  const existing = [
                  <#list classes as c>
                  "${c.courseCode}",
                  </#list>];

                  for (let i = 0; i < existing.length; i++) {
                    if(courseId == existing[i].toUpperCase()) {
                      document.getElementById("pinputError").style.display = "inline-block";
                      document.getElementById("psubmit").disabled = true;
                      document.getElementById("psubmit").style.backgroundColor = "#BCBDBF";
                      break;
                    } else {
                      document.getElementById("pinputError").style.display = "none";
                      document.getElementById("psubmit").disabled = false;
                      document.getElementById("psubmit").style.backgroundColor = "#A93226";
                    }
                  }
                }

                function tableSearcher() {
                  var input, filter, table, tr, td, i, txtValue;
                  input = document.getElementById("tableinput");
                  console.log("input: " + input.value);

                  filter = input.value.toUpperCase();
                  table = document.getElementById("ptable");
                  tr = table.getElementsByTagName("tr");
                  for (i = 1; i < tr.length; i++) {

                    txtValue = (tr[i].getElementsByTagName("td")[1].innerHTML).trim();
                    txtValue = (txtValue).trim() + " " + (tr[i].getElementsByTagName("td")[2].innerHTML).trim();
                    txtValue = (txtValue).trim() + " " + (tr[i].getElementsByTagName("td")[3].innerHTML).trim();

                    td = tr[i].getElementsByTagName("td")[0];

                    console.log("td: " + txtValue);
                    if (td) {
                      if (txtValue.toUpperCase().indexOf(filter) > -1) {
                        tr[i].style.display = "";
                      } else {
                        tr[i].style.display = "none";
                      }
                    }
                  }
                }

                function submitForm() {
                      var courseCode = document.getElementById("CourseCode").value;
                      var section = document.getElementById("Section").value;
                      var term = document.getElementById("Term").value;


                      var courseId = courseCode + "_" + section + "_" + term;
                      courseId = courseId.toUpperCase();

                      var courseEnabled = document.getElementById("courseEnabled").checked;

                      var courseName = document.getElementById("courseName").value;

                      table = document.getElementById("ptable");
                      tr = table.getElementsByTagName("tr");

                      var includedStudents = "";
                    for (i = 1; i < tr.length; i++) {

                      id = (tr[i].getElementsByTagName("td")[0].innerHTML).trim();

                      checkboxId = id + "ck";

                      checked = document.getElementById(checkboxId).checked;
                      if(checked == true) {
                        includedStudents = includedStudents + id + ".";
                        }
                      }

                        if(includedStudents == "") {
                            includedStudents = "-1";
                        }

                        document.addClassForm.action = "/instructor/classes/addClass/"+ courseId + "/" + courseName + "/" + courseEnabled + "/" + includedStudents;
                        document.getElementById("addClassForm").submit();

}
              </script>

    <!-- Option 1: Bootstrap Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

  </body>
</html>
