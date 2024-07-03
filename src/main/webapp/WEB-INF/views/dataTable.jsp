<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/common/header.jsp" %>

<style>

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
        constructor(ele, opt) {
            this.ele = ele;
            this.opt = opt;
            this.searchInitiated = false;
            this.createTable();
        }

        createTable() {
            this.tableInit = $(this.ele).DataTable({
                ...this.opt,
                serverSide: true,
                ajax: async (data, callback, settings) => {
                    // 이 함수는 데이터 조회 버튼을 클릭했을 때만 실행된다.
                    if (this.searchInitiated) {
                        const params = this.setSearchData(data);
                        try {
                            const response = await fetchUtil.getUrlEncoded('/api/user-data-1', params);
                            callback({
                                draw: response.data.draw,
                                recordsTotal: response.data.totalRecords,
                                recordsFiltered: response.data.totalRecords,
                                data: response.data.data
                            });
                            $('#search-data-count').text(response.data.totalRecords);
                        } catch (error) {
                            console.error('Error fetching data:', error);
                        }
                    }
                }
            });
        }

        setSearchData(data) {
            return {
                draw: data.draw,     // dataTable의 serverSide 옵션을 활성화 하면 자동으로 보내지는 파라미터
                start: data.start,   // dataTable의 serverSide 옵션을 활성화 하면 자동으로 보내지는 파라미터
                length: data.length, // dataTable의 serverSide 옵션을 활성화 하면 자동으로 보내지는 파라미터
                column1: $('#searchColumn1').val(),
                column2: $('#searchColumn2').val()
            };
        }

        searchTable() {
            this.searchInitiated = true;
            this.tableInit.ajax.reload();
        }

        searchReset() {
            $('#search-form')[0].reset();
            this.searchInitiated = false;
            this.tableInit.clear().draw();
            $('#search-data-count').text(0);
        }
    }

    const dataTable = new setDataTable('#data-table', {
        paging: true,
        searching: false,
        responsive: true,
        info: false,
        scrollCollapse: true,
        ordering: false,
        columns: [
            { title: "컬럼 1", data: 'column1', width: "100px", className: "dt-center" },
            { title: "컬럼 2", data: 'column2', width: "100px", className: "dt-center" },
            { title: "컬럼 3", data: 'column3', width: "100px", className: "dt-center" },
            { title: "컬럼 4", data: 'column4', width: "100px", className: "dt-center" },
            { title: "컬럼 5", data: 'column5', width: "100px", className: "dt-center" },
            { title: "컬럼 6", data: 'column6', width: "100px", className: "dt-left" }
        ],
        language: {
            emptyTable: "데이터가 없습니다."
        }
    });

</script>


<%@ include file="/WEB-INF/common/footer.jsp" %>