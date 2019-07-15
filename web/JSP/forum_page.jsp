<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="a" uri="http://java.sun.com/jsp/jstl/core" %>
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
</head>
<body>
<header>
    <jsp:include page="header.jsp"></jsp:include>
</header>

<article>
    <section class="container-fluid ">
        <div class="card border-0">

            <div class="card-body">
                <h2 class="card-title">
                    ${requestScope.title}
                </h2>
                <p class="card-text">
                    ${requestScope.description}
                </p>
            </div>

        </div>
    </section>


    <form action="forum" method="get" class="container-fluid row">
        <span class="col-11"></span>
        <%--        <input name="category" value="${requestScope.title.replace(" ","-")}" hidden>--%>
        <input hidden name="category"
               value="${requestScope.title.replace(" ","-")}">
        <button
                name="do"
                value="add"
                type="submit"
                class="button-inline btn btn-danger btn-sm col-1"
        >
            <%--            <%request.setAttribute("category",request.getAttribute("title"));%>--%>
            Start New Topic
        </button>
    </form>
    <section class="container-fluid">
        <div class="btn-group bg-light">

            <c:if test="${requestScope.PAGE_INDEX != 0}">
                <a href="?page=${requestScope.PAGE_INDEX}" class="btn btn-danger btn-sm"
                   type="button">PREV</a>
                <a href="?page=${requestScope.PAGE_INDEX}" class="btn btn-danger btn-sm"
                   type="button">${requestScope.PAGE_INDEX}</a>
            </c:if>
            <button class="btn btn-danger btn-sm" type="button"
                    disabled>${requestScope.PAGE_INDEX+1}</button>
            <c:forEach begin="${requestScope.PAGE_INDEX+2}" end="${requestScope.PAGE_INDEX+5}"
                       varStatus="loop">
                <a href="?page=${loop.index}" class="btn btn-danger btn-sm"
                   type="button">${loop.index} </a>
            </c:forEach>
            <a href="?page=${requestScope.PAGE_INDEX+6}" class="btn btn-danger btn-sm"
               type="button">next</a>

        </div>
    </section>
    <c:if test="${requestScope.topics != null}">
    <section class="container-fluid ">
        <div class="list-group">
            <c:forEach var="topic" items="${requestScope.topics}">
                <div class="list-group-item">
                    <a href="<%=application.getInitParameter("uri")%>/topic?attr=${topic.id}-${topic.title.replace(" ","-")}">${topic.title}</a>
                    <br>
                    <small class="text-muted">
                        by ${topic.user.username}
                    </small>
                </div>
            </c:forEach>
        </div>
    </section>
</article>
</c:if>
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
</body>
</html>
