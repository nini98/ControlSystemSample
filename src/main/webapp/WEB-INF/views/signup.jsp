<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/common/header.jsp" %>

<style>
    .login-form-wrapper{
        width: 1000px;
    }

</style>

<div class="page-wrapper">
    <div class="login-form-wrapper">
        <div>
            <!-- Email input -->
            <div data-mdb-input-init class="form-outline mb-4">
                <input type="text" id="loginId" class="form-control" />
                <label class="form-label" for="loginId">Login ID</label>
            </div>

            <!-- Password input -->
            <div data-mdb-input-init class="form-outline mb-4">
                <input type="password" id="password" class="form-control" />
                <label class="form-label" for="password">Password</label>
            </div>

            <div data-mdb-input-init class="form-outline mb-4">
                <input type="email" id="email" class="form-control" />
                <label class="form-label" for="email">Email</label>
            </div>

            <div data-mdb-input-init class="form-outline mb-4">
                <input type="text" id="phone" class="form-control" />
                <label class="form-label" for="phone">Phone</label>
            </div>

            <div data-mdb-input-init class="form-outline mb-4">
                <input type="text" id="name" class="form-control" />
                <label class="form-label" for="name">Name</label>
            </div>

            <div data-mdb-input-init class="form-outline mb-4">
                <select id="role" class="form-control">
                    <option value="0">ADMIN</option>
                    <option value="1">USER</option>
                    <option value="2">GUEST</option>
                </select>
            </div>

            <!-- Submit button -->
            <button  type="button" data-mdb-button-init data-mdb-ripple-init class="btn btn-primary btn-block mb-4" onclick="signUp()">TEST 버튼</button>
        </div>

    </div>
</div>

<script>

    const signUp = () => {

        const signupData = {
            loginId: $('#loginId').val(),
            password: $('#password').val(),
            email: $('#email').val(),
            phone: $('#phone').val(),
            name: $('#name').val(),
            role: $('#role').val()
        };

        fetchUtil.post('/api/signup', signupData)
            .then(response => {
                console.log("JAY TEST : " + response);
                alert('회원가입 되었습니다.');
                location.href = "/login";
            })
            .catch(error => {
                console.log(error.data);
                alert(error.header.resultMessage);
            });


    }

</script>

<%@ include file="/WEB-INF/common/footer.jsp" %>