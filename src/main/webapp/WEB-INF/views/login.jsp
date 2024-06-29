<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/common/header.jsp" %>

<style>
    .login-form-wrapper{
        width: 1000px;
    }

</style>

<div class="page-wrapper">
    <div class="login-form-wrapper">
        <form id="loginForm">
            <!-- LogIn ID input -->
            <div data-mdb-input-init class="form-outline mb-4">
                <input type="text" id="loginId" name="loginId"  class="form-control" required/>
                <label class="form-label" for="loginId">LogIn ID</label>
            </div>

            <!-- Password input -->
            <div data-mdb-input-init class="form-outline mb-4">
                <input type="password" id="password" name="password"  class="form-control" required/>
                <label class="form-label" for="password">Password</label>
            </div>

            <button type="submit" data-mdb-button-init data-mdb-ripple-init class="btn btn-primary btn-block mb-4">로그인</button>

        </form>

    </div>
</div>

<script>

    $('#loginForm').submit(function(e) {
        e.preventDefault();

        const loginId = $('#loginId').val();
        const password = $('#password').val();
        const payload = { loginId, password };

        fetchUtil.postUrlEncoded('/login', payload)
            .then(() => {
                location.href = '/';
            })
            .catch(error => {
                console.log(error.data);
                alert(error.header.resultMessage);
            });
    });

</script>




<%@ include file="/WEB-INF/common/footer.jsp" %>