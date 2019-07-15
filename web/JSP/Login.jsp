<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <title>Title</title>
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
    <section class="container">
      <div class="row justify-content-center">
        <form
          action="<%=application.getInitParameter("uri")%>/login"
          class="col-6 px-4 py-3"
          style="width: 300px"
          method="post"
        >
          <h4 class="card-title mb-4">Sign In</h4>
          <div class="form-group">
            <input
              type="text"
              class="form-control"
              placeholder="Display Name or Email"
              name="nameOrEmail"
            />
          </div>

          <div class="form-group">
            <input
              type="password"
              placeholder="Enter Password"
              class="form-control"
              name="password"
            />
          </div>
          <div class="row">
            <div class="form-group inline">
              <input type="checkbox" value="true" name="rememberUser" />
              <span for="">remember me</span>
            </div>
            <a class="col" href="#"><p>forgot password</p></a>
          </div>
          <button type="submit" class="btn btn-danger btn-block">
            SIGN IN
          </button>
          <a href="<%=application.getInitParameter("uri")%>/register" class="block">Create an Account</a>
        </form>
      </div>
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
  </body>
</html>
