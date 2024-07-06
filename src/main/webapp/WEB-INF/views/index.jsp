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
        <button onclick="moveToDataTable2Page()">DATA TABLE 2 TEST PAGE</button>
        <button onclick="moveToRealTimeChartPage()">REAL-TIME CHART PAGE</button>
        <br>
        <br>
        <button onclick="logOut()">로그아웃</button>
    </div>
</div>

<script>
    const moveToDataTablePage = () => {
        location.href = "/data-table";
    }

    const moveToRealTimeChartPage = () => {
        location.href = "/realtime-chart";
    }

    const moveToDataTable2Page = () => {
        location.href = "/data-table-2";
    }

    const logOut = async () => {
        try {
            const response = await fetchUtil.postUrlEncoded('/logout', {});
            alert("로그아웃 되었습니다.");
            location.href = '/login';
        } catch (error) {
            console.error('Logout failed : ', error);
            alert(error);
        }
    }
</script>


<%@ include file="/WEB-INF/common/footer.jsp" %>