<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<br/>
<br/>
<div class="container" style="width: 50%">
    <form action="register" method="POST">
        <div class="form-group">
            <label for="username">Username</label>
            <input
                    name="username"
                    placeholder="Username"
                    type="text"
                    class="form-control"
                    value="${requestScope.data.username}"
            />
            <small class=" form-text text-muted">${requestScope.checkUsernameMessage}</small>
        </div>
        <div class="form-group">
            <label for="email">Email</label>
            <input
                    name="email"
                    type="text"
                    class="form-control"
                    placeholder="Enter Email"
                    value="${requestScope.data.email}"
            />
            <small class=" form-text text-muted">${requestScope.checkEmailMessage}</small>
        </div>
        <div class="row">
            <div class="col form-group">
                <label for="first_name">First Name</label>
                <input
                        name="firstname"
                        type="text"
                        class="form-control"
                        placeholder="Enter First Name"
                        value="${requestScope.data.firstname}"
                />
            </div>
            <div class="col form-group">
                <label for="Last Name">Last Name</label>
                <input
                        name="lastname"
                        type="text"
                        class="form-control"
                        placeholder="Enter Last Name"
                        value="${requestScope.data.lastname}"
                />
            </div>
        </div>
        <div class="form-group">
            <label for="password">Password</label>
            <input
                    name="password"
                    type="text"
                    class="form-control"
                    placeholder="Enter Password"
                    value="${requestScope.data.password}"

            />
        </div>
        <div class="form-gorup">
            <label for="age">Age</label>
            <input
                    name="age"
                    type="text"
                    class="form-control"
                    placeholder="Enter Age"
                    value="${requestScope.data.age}"
            />
        </div>
        <button
                name="regisger"
                type="submit"
                class="btn btn-danger btn-block mt-3"
        >
            SUBMIT
        </button>
    </form>
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
</body>
</html>
