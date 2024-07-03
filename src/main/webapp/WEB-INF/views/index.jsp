<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/common/header.jsp" %>

<style>
    /*.login-form-wrapper{*/
    /*    width: 1000px;*/
    /*}*/

</style>

<div class="page-wrapper">
    <div class="page-content">
        <div>HELLO WORLD</div>
        <br>
        <button onclick="moveToDataTablePage()">DATA TABLE TEST PAGE</button>
    </div>
</div>

<script>
    const moveToDataTablePage = () => {
        location.href = "/data-table";
    }
</script>


<%@ include file="/WEB-INF/common/footer.jsp" %>