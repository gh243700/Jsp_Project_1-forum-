<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>header</title>
    <title>Title</title>
  </head>
  <header>
    <nav class="navbar navbar-expand-sm row bg-light" style="height: 4rem">
      <ul class="navbar-nav ">
        <h1 class="display-4">TITLE</h1>
      </ul>
      <div class="form-group ml-auto">
        <input type="text" class="form-control " placeholder="Search" />
      </div>
    </nav>
    <nav class="navbar navbar-expand-sm navbar-dark bg-dark">
      <ul class="navbar-nav">
        <li class="nav-item">
          <a href="/project_web/main" class="nav-link">Forums</a>
        </li>
        <li class="nav-item">
          <a href="#" class="nav-link">All Activity</a>
        </li>
        <li class="nav-item dropdown">
          <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown"
            >My Activity Streams</a
          >
          <div class="dropdown-menu">
            <a href="" class="dropdown-item">Status Updates</a>
          </div>
        </li>
      </ul>
      <c:if test="${sessionScope.userId == null}">
        <ul class="navbar-nav ml-auto ">
          <li class="nav-item dropdown">
            <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown"
              >Sign In</a
            >
            <div class="dropdown-menu dropdown-menu-right">
              <form
                action="/project_web/login"
                class=" px-4 py-3"
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
              </form>
            </div>
          </li>
        </ul>
        <a href="/project_web/register" class="btn btn-danger">SIGN UP</a>
      </c:if>
      <c:if test="${sessionScope.userId!=null}">
        <ul class="navbar-nav ml-auto">
          <li class="nav-item dropdown">
            <button
              data-toggle="dropdown"
              class="btn nav-link dropdown-toggle"
              style="border-radius: 40px;
                  letter-spacing: 1px;
                  line-height: 15px; background-color: #545b62"
            >
              ${sessionScope.username}
            </button>
            <div
              class="container dropdown-menu dropdown-menu-right"
              style="width: 250px;"
            >
              <a href="/project_web/profile" class="dropdown-item py-2"
                >Profile</a
              >
              <a href="#" class="dropdown-item py-2">My Activity</a>
              <a href="#" class="dropdown-item py-2">Update Status</a>
              <div class="dropdown-divider"></div>
              <a href="/project_web/logout" class="dropdown-item py-3"
                >Logout</a
              >
            </div>
          </li>
        </ul>
      </c:if>
    </nav>
  </header>
</html>
