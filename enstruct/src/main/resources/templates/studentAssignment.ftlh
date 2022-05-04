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
  <link rel="stylesheet" href="/css/passignment.css">

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
          <a class="nav-link" href="/student/classes"><i class="bi bi-door-closed-fill"></i>Classes</a>
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
          <a class="nav-link logout" onclick="location.href='/userLogout'"><i class="bi bi-box-arrow-left"></i>Log Out</a>
        </li>


      </ul>
    </div>

      <div class="col-sm-10 main-content" >
        <div style="display:flex; flex-direction: row; align-items: center;">
          <div style="display:flex; flex-direction: column; flex-grow: 1;">
            <h1 class="heading" style="margin-bottom: 10px;">${assignmentName}</h1>
            <h2 style="color: #A93226; margin-bottom: 20px;">
                  <#if submitted == true>
                      Already Submitted
                  </#if>
                  <#if available == false>
                    <#if submitted == false>
                        Deadline Passed
                    </#if>
                  </#if>
            </h2>
        </div>
        </div>

        <div class="row prow">
          <div class="col-lg-8 pinstructions">
            <span style="font-size: 1.6vw; flex-grow:1"><b>Instructions:</b></span>
                                <p style="font-size: 1.1vw">
                                    <#if instructions??>
                                        ${instructions}
                                    </#if>
                                </p>
                                <span style="font-size:1.2vw; display: block"><b>Deadline:</b>&nbsp;&nbsp;${deadline}</span>
                                <span style="font-size:1.2vw; display: block""><b>Total Points:</b>&nbsp;&nbsp;${maxScore}</span>
          </div>
          <div class="col-lg-1"></div>
          <div class="col-lg-3 pbuttonholder">
            <div class="pbutton" onclick="location.href='/student/repositories/${assignmentId}'">
              <img class="pbuttonimg" src="/img/pfolder.svg" style="height: 4vw; ">
              <span style="font-size: 1.8vw; font-weight: bold">Repository Link</span>
            </div>
            <div style="height:15px"></div>
            <div class="pbutton" onclick="triggerFile()">
               <img class="pbuttonimg" src="/img/pupload.svg" style="height: 4vw; ">
              <span style="font-size: 1.8vw; font-weight: bold;">Upload File</span>
              <form style="display: none" id="uploadAssignmentForm" name="uploadAssignmentForm" method="post" enctype="multipart/form-data">
                  <input type="file" id="upload" name="upload" onchange="openModal()"
                      <#if submitted == true>
                          disabled
                      </#if>
                      <#if available == false>
                        <#if submitted == false>
                            disabled
                        </#if>
                      </#if>
                  >
              </form>
            </div>
          </div>
        </div>
      </div>


    </div>
  </div>

        <div id="pmodal" class="pmodal">
        <div class="pmodal-contents">
            <div class="pmodal-body">
                <span class="pmodal-span" id="pmodaltext">
                Submit "name" as assignment?<br>
                This action cannot be undone.
            </span>
            </div>
            <div class="pmodal-buttons">
                <input class="pmodal-input" type="button" value="Cancel" onClick="removeAssignment()">
                <input class="pmodal-input" type="button" value="Submit" onClick="submitAssignment()">
            </div>
            </div>
        </div>


  <!-- Option 1: Bootstrap Bundle with Popper -->
  <script>
      window.onclick = function(event) {
        if (event.target == document.getElementById("pmodal")) {
          document.getElementById("pmodal").style.display = "none";
        }
      }

    function triggerFile() {
        document.getElementById("upload").click();
    }

    function removeAssignment() {
        document.getElementById("upload").value = "";
        document.getElementById("pmodal").style.display = "none";
    }

    function submitAssignment() {
            document.uploadAssignmentForm.action = "/student/assignment/${assignmentId}/submit/" + document.getElementById("upload").files.item(0).name;
            document.getElementById("uploadAssignmentForm").submit();
        }

    function openModal() {
        var upload = document.getElementById("upload");

        document.getElementById("pmodaltext").innerHTML = "Submit " + upload.files.item(0).name + " as assignment? <br> This action cannot be undone.";

        var modal = document.getElementById("pmodal");
        modal.style.display = "block";
    }
  </script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

</body>
</html>
