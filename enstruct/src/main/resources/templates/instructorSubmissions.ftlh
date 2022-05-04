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
    <link rel="stylesheet" href="/css/submissions.css">

    <title>E-nstruct</title>
  </head>
  <body onload="loadComp()">

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
            <h1 class="heading" style="margin-bottom: 10px;">SUBMISSION: ${courseCode} ${section}</h1>
            <h2 style="color: #A93226; margin-bottom: 20px;">${name}</h2>
          </div>
        </div>
        <div class="row">

          <div class="col borders studname">
            <span class="arrows" onclick="location.href='/student/submissions/${previous}'"><</span>
            <div class="selectholder">
              <select onchange="location.href = this.value;">
                <option selected hidden>${student}</option>
                <#list submissions as s>
                <option value="/student/submissions/${s.submissionId}">${students[s?index]}</option>
                </#list>

              </select>
            </div>
            <span class="arrows" onclick="location.href='/student/submissions/${next}'">></span>
          </div>
        </div>

        <div class="borders exer">
          <div class="ideSide">
              <iframe
              id="oc-editor"
              height="100%"
              src="https://onecompiler.com/embed/cpp?listenToEvents=true&hideNew=true&hideTitle=true"
              width="100%"
            ></iframe>
          </div>
           <form class="scoreSide" >
            <div style="display:flex; flex-direction: row; justify-content: space-between;">
              <span>Score:</span>
              <input placeholder="${maxScore}" type="number" min="1" max="${maxScore}" value="${grade}">
            </div>
            <span>LATE SUBMISSION</span>
            <div style="flex-grow:1"></div>
            <button type="submit" class="save">Save Score</button>
          </form>
        </div>
    
      </div>
    </div>
  </div>



  <!-- Option 1: Bootstrap Bundle with Popper -->
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

  <script>
    function loadComp() {
        var iFrame = document.getElementById('oc-editor'); // add an ID for the <iframe tag
        iFrame.contentWindow.postMessage({
            eventType: 'populateCode',
            language: 'cpp',
            files: [
            {
                "name": "${filename}",
                "content": "${code?no_esc}"
            }
        ]
        }, "*");
        //iFrame.contentWindow.postMessage({
        //    eventType: 'triggerRun'
        //}, "*");
    }
</script>
</body>
</html>
