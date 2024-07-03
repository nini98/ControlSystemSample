<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/common/header.jsp" %>

<style>
    /*.login-form-wrapper{*/
    /*    width: 1000px;*/
    /*}*/
    .tdCenter { text-align: center; }
    .tdRight { text-align: right; }
    .tdLeft { text-align: left; }

    .custom-input {
        width: 300px;
    }

</style>

<div class="page-wrapper">
    <div class="page-content">
        <form id="search-form">
            <input type="text" id="searchColumn1" class="custom-input" placeholder="COLUMN_1 값을 입력하세요">
            <br>
            <input type="text" id="searchColumn2" class="custom-input" placeholder="COLUMN_2 값을 입력하세요">
        </form>

        <!-- 테이블 / 결과영역 -->
        <div class="mt-4">
            <div class="mt-2 table-responsive">
                <table class="table table-bordered table-hover border-top search-list-table bg-white display responsive" id="data-table">
                    <thead class="table-light"></thead>
                    <tbody></tbody>
                </table>
                <div class="mt-2">
                        검색결과 : <strong id="search-data-count">0</strong>건
                </div>
            </div>
        </div>

        <br/>
        <br/>
        <br/>
        <button type="button" onclick="dataTable.searchTable()">데이터 조회</button>
        <button type="button" onclick="dataTable.searchReset()">검색 조건 초기화</button>

    </div>
</div>

<script>

    class setDataTable {
        constructor (ele, opt) {
            this.ele = ele;
            this.opt = opt;
        }
        createTable () {
            this.tableInit = $(this.ele).DataTable(this.opt);
        }
        async getTableData() {
            try{
                await fetchUtil.getUrlEncoded('/api/user-data-1',this.setSearchData()).then(response=>{
                    console.log(JSON.stringify(response));
                    this.renderTable(response.data);
                    $('#search-data-count').text(response.data.length);
                })
            }catch(err){
                console.log(err);
                return err;
            }
        }
        setSearchData() {
            return {
                column1 : $('#searchColumn1').val(),
                column2 : $('#searchColumn2').val()
            }
        }
        setDataArr (data) {
            let arr = [];
            data.forEach(data => {
                arr.push(data.value)
            });
            return arr.join();
        }
        renderTable (data) {
            let dataJson = [];
            data.forEach(d => {
                dataJson.push([
                    d.column1|| "",
                    d.column2|| "",
                    d.column3|| "",
                    d.column4|| "",
                    d.column5|| "",
                    d.column6|| "",
                ])
            });
            this.tableInit.rows.add(dataJson).draw();
        }
        searchTable () {
            this.tableInit.clear();
            this.getTableData();
        }
        searchReset () {
            $('#search-form')[0].reset();
        }

    }

    const dataTable = new setDataTable('#data-table', {
        paging: true,
        // displayLength: 25,
        searching: false,
        responsive: true,
        info: false,
        scrollCollapse: true,
        responsive: {
            details: {
                type: 'column',
            },
        },
        // order: [[0, 'asc']],
        ordering : false,
        columns: [
            { title : "컬럼 1", width : "100px" ,className : "dt-center"},
            { title : "컬럼 2", width : "100px" ,className : "dt-center"},
            { title : "컬럼 3", width : "100px" ,className : "dt-center"},
            { title : "컬럼 4", width : "100px" ,className : "dt-center"},
            { title : "컬럼 5", width : "100px" ,className : "dt-center"},
            { title : "컬럼 6", width : "100px" ,className : "dt-left"},
            // { title : "컬럼 6", width : "0%" ,className :"dt-left",bVisible :false},
        ],
        createdRow (row, data) {
            // 클래스설정하기
            // $(row).addClass($statusUtil.qaStatusData(data[6]).className);
        },
        columnDefs : [
            <%--{--%>
            <%--    targets : 2,--%>
            <%--    data : null,--%>
            <%--    render (data, type, row, meta){--%>
            <%--        return `<a href="#" onclick="modifySmsNotiGroupConfig.openModal('${'${data[0]}'}','${'${data[1]}'}','${'${data[2]}'}','${'${data[3]}'}', '${'${data[4]}'}', '${'${data[5]}'}')">${'${data[2]}'}</a>`;--%>
            <%--    }--%>
            <%--},--%>

        ],
        language : {
            emptyTable: "dasdasadsads"
        }
    })
    dataTable.createTable();

</script>


<%@ include file="/WEB-INF/common/footer.jsp" %>