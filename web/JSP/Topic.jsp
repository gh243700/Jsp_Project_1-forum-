<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <title>Topic</title>
    <link
            rel="stylesheet"
            href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
            integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
            crossorigin="anonymous"
    />
    <script type="text/javascript">
      function stopRKey(evt) {
        var evt = evt ? evt : event ? event : null;
        var node = evt.target
            ? evt.target
            : evt.srcElement
                ? evt.srcElement
                : null;
        if (evt.keyCode == 13 && node.type == 'text') {
          return false;
        }
      }

      document.onkeypress = stopRKey;
    </script>
</head>
<body>
<header>
    <jsp:include page="header.jsp"></jsp:include>
</header>
<div class="row">
    <div class="container-fluid p-5 col-10">
        <section class="row mb-4">
            <!-- <img class="col-md-1" src="" alt="" /> -->
            <div class="col-md-12">
                <h1 class="">${requestScope.topic.title}</h1>
                <p>By ${requestScope.topic.user_id}, ${requestScope.topic.post_date} in
                    ${requestScope.topic.getCatagory()}  ${requestScope.topic.replies} replies</p>
            </div>
        </section>
        <section class="row mb-4 justify-content-center">
            <div class="col-1">
                <img src="" alt=""/>
                <h3>${requestScope.topic.user_id}</h3>
                <p>302 posts</p>
            </div>
            <div class="col-11">
                <blackquote class="blackquote">
                    ${requestScope.topic.main_content}
                </blackquote>
            </div>
            <label for="textarea"></label>
        </section>
        <c:if test="${sessionScope.userId != null}">
            <section class=" mt-4 row align-items-center">
                <div class="col-1 ">
                    <p>${sessionScope.username}</p>
                </div>
                <textarea
                        name="content"
                        class="col-11 form-control textarea-autosize "
                        style="min-height: 8rem"
                        id="floating"
                        rows="5"
                ></textarea>
            </section>
        </c:if>
    </div>
    <div class="col-2"></div>
</div>

<script
        src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"
></script>
<script
        src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"
></script>
<script
        src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"
></script>
<script src="../js/script.js"></script>
</body>
</html>
