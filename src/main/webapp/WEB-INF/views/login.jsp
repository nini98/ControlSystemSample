<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/common/header.jsp" %>

<style>
    .login-form-wrapper{
        width: 1000px;
    }

</style>

<div class="page-wrapper">
    <div class="login-form-wrapper">
        <form action="/login" method="post">
            <!-- LogIn ID input -->
            <div data-mdb-input-init class="form-outline mb-4">
                <input type="text" id="username" class="form-control" />
                <label class="form-label" for="username">LogIn ID</label>
            </div>

            <!-- Password input -->
            <div data-mdb-input-init class="form-outline mb-4">
                <input type="password" id="password" class="form-control" />
                <label class="form-label" for="password">Password</label>
            </div>

            <button type="submit" data-mdb-button-init data-mdb-ripple-init class="btn btn-primary btn-block mb-4">로그인</button>

        </form>

    </div>
</div>




<%@ include file="/WEB-INF/common/footer.jsp" %>