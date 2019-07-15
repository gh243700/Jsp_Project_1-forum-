<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>Document</title>
    <link
            rel="stylesheet"
            href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
            integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
            crossorigin="anonymous"
    />
    <link
            rel="stylesheet"
            href="${pageContext.request.contextPath}/css/style.css"
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

<% String category = (String) request.getAttribute("category");
%>
<header>
    <%@include file="header.jsp" %>
</header>
<section class="container">
    <form action="${requestScope.title}/Add_Topic" class="form-group" method="POST">
        <input name="category" value="${requestScope.title}" hidden>
        <label for="">Title</label>
        <input name="title" type="text" class="form-control">
        <label for="tags">Tags</label>
        <input name="tags" type="text" class="form-control"/>
        <small class="form-text text-muted"
        >Type tags separated by commas. <br/>
            Each tag should be no longer than 25 characters.
        </small
        >
        <label for="textarea"></label>
        <textarea
                name="content"
                class="form-control textarea-autosize"
                style="min-height: 8rem"
                id="floating"
                rows="5"
        ></textarea>
        <div class=" text-center mt-3">
            <button value="CANCEL" class="btn btn-inline">CANCEL</button>
            <button type="submit" class="btn btn-danger btn-inline">
                SUBMIT-TOPIC
            </button>
        </div>
    </form>
</section>

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
