<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/common/header.jsp" %>

<style>

    .custom-input {
        width: 300px;
    }

    /* dataTable의 css를 직접 조작하는 부분 - START */

    /* pagenation과 lengthMenu를 한줄로 보여주기 위함 */
    .bottom{display: flex;flex-direction: row;justify-content: center;}

    /* lengthMenu도 pagenation 처럼 데이터가 렌더링 되었을 때만 보이도록 하기 위함 */
    .hidden-length-menu {display: none;}
    .visible-length-menu {display: block;}

    /* lengthMenu와 pagenation의 순서를 조작하기 위함 */
    .dt-length{order : 1;}
    .dt-paging {order : 0;}

    /* dataTable의 css를 직접 조작하는 부분 - END */


</style>

<div class="page-wrapper">
    <div class="page-content">
        <form id="search-form">

            <div class="col-sm-6">
                <input type="text" class="form-control datetimepicker-input" id="startDateTimePicker" data-toggle="datetimepicker" data-target="#startDateTimePicker"/>
            </div>

            <div class="col-sm-6">
                <input type="text" class="form-control datetimepicker-input" id="endDateTimePicker" data-toggle="datetimepicker" data-target="#endDateTimePicker"/>
            </div>

            <br>
            <input type="text" id="searchTestColumn1" class="custom-input" placeholder="TEST_COLUMN_1 값을 입력하세요">
            <br>
            <input type="text" id="searchTestColumn2" class="custom-input" placeholder="TEST_COLUMN_2 값을 입력하세요">
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
<%--        <button type="button" onclick="downloadExcel()">Excel 다운로드</button>--%>

    </div>
</div>

<script>

    $(document).ready(function() {

        const startDateTimePickerElement = $('#startDateTimePicker')[0];
        const endDateTimePickerElement = $('#endDateTimePicker')[0];

        const startDateTimePicker = new tempusDominus.TempusDominus(document.getElementById('startDateTimePicker'), {
            display: {
                components: {
                    calendar: true,
                    date: true,
                    month: true,
                    year: true,
                    decades: true,
                    clock: true,
                    hours: true,
                    minutes: true,
                    seconds: true,
                },
                icons: {
                    type: 'icons',
                    time: 'fa fa-solid fa-clock',
                    date: 'fa fa-solid fa-calendar',
                    up: 'fa fa-solid fa-arrow-up',
                    down: 'fa fa-solid fa-arrow-down',
                    previous: 'fa fa-solid fa-chevron-left',
                    next: 'fa fa-solid fa-chevron-right',
                    today: 'fa fa-solid fa-calendar-check',
                    clear: 'fa fa-solid fa-trash',
                    close: 'fas fa-solid fa-xmark'
                },
            },
            localization: {
                hourCycle: 'h23',
                format: 'yyyy-MM-dd HH:mm:ss',
                locale: 'ko-KR'
            },
            defaultDate: new Date()
        });


        const endDateTimePicker = new tempusDominus.TempusDominus(document.getElementById('endDateTimePicker'), {
            display: {
                components: {
                    calendar: true,
                    date: true,
                    month: true,
                    year: true,
                    decades: true,
                    clock: true,
                    hours: true,
                    minutes: true,
                    seconds: true,
                },
                icons: {
                    type: 'icons',
                    time: 'fa fa-solid fa-clock',
                    date: 'fa fa-solid fa-calendar',
                    up: 'fa fa-solid fa-arrow-up',
                    down: 'fa fa-solid fa-arrow-down',
                    previous: 'fa fa-solid fa-chevron-left',
                    next: 'fa fa-solid fa-chevron-right',
                    today: 'fa fa-solid fa-calendar-check',
                    clear: 'fa fa-solid fa-trash',
                    close: 'fas fa-solid fa-xmark'
                },
            },
            localization: {
                hourCycle: 'h23',
                format: 'yyyy-MM-dd HH:mm:ss',
                locale: 'ko-KR'
            },
            defaultDate: new Date()

        });


        // 초기 설정에서 endDateTimePicker가 startDateTimePicker 이전 날짜를 선택하지 못하게 함
        endDateTimePicker.updateOptions({ restrictions: { minDate: startDateTimePicker.dates.picked[0] } });

        startDateTimePickerElement.addEventListener('change.td', function (e) {
            endDateTimePicker.updateOptions({ restrictions: { minDate: e.detail.date } });

            const endDate = endDateTimePicker.dates.picked[0];
            if (endDate && e.detail.date > endDate) {
                endDateTimePicker.dates.setValue(e.detail.date);
            }
        });

        endDateTimePickerElement.addEventListener('change.td', function (e) {
            startDateTimePicker.updateOptions({ restrictions: { maxDate: e.detail.date } });

            const startDate = startDateTimePicker.dates.picked[0];
            if (startDate && e.detail.date < startDate) {
                startDateTimePicker.dates.setValue(e.detail.date);
            }
        });
    });



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
                            const response = await fetchUtil.getUrlEncoded('/api/user-data-2', params);
                            callback({
                                draw: response.data.draw,
                                recordsTotal: response.data.totalRecords,
                                recordsFiltered: response.data.totalRecords,
                                data: response.data.data
                            });
                            $('#search-data-count').text(response.data.totalRecords);
                            $('.dt-length').removeClass('hidden-length-menu').addClass('visible-length-menu');
                        } catch (error) {
                            console.error('Error fetching data:', error);
                        }
                    }
                },
                drawCallback: function(settings) {
                    if (settings.json && settings.json.data.length > 0) {
                        $('.dt-length').removeClass('hidden-length-menu').addClass('visible-length-menu');
                    } else {
                        $('.dt-length').removeClass('visible-length-menu').addClass('hidden-length-menu');
                    }
                }
            });
        }

        setSearchData(data) {
            return {
                draw: data.draw,     // dataTable의 serverSide 옵션을 활성화 하면 자동으로 보내지는 파라미터
                start: data.start,   // dataTable의 serverSide 옵션을 활성화 하면 자동으로 보내지는 파라미터
                length: data.length, // dataTable의 serverSide 옵션을 활성화 하면 자동으로 보내지는 파라미터
                testColumn1: $('#searchTestColumn1').val(),
                testColumn2: $('#searchTestColumn2').val(),
                startDate: $('#startDateTimePicker').val(),
                endDate: $('#endDateTimePicker').val()
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
            $('.dt-length').removeClass('visible-length-menu').addClass('hidden-length-menu');
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
            { title: "컬럼 1", data: 'testColumn1', width: "100px", className: "dt-center" },
            { title: "컬럼 2", data: 'testColumn2', width: "100px", className: "dt-center" },
            { title: "컬럼 3", data: 'testColumn3', width: "100px", className: "dt-center" },
            { title: "컬럼 4", data: 'testColumn4', width: "100px", className: "dt-center" },
            { title: "생성일", data: 'insertDate', width: "100px", className: "dt-center" },
            { title: "변경일", data: 'updateDate', width: "100px", className: "dt-center" }
        ],
        dom: '<"top"i>rt<"bottom"lp><"clear">',
        language: {
            emptyTable : "데이터가 없습니다.",
            lengthMenu : '<select class="form-select">' +
                '<option value="10">10</option>' +
                '<option value="25">25</option>' +
                '<option value="50">50</option>' +
                '<option value="100">100</option>' +
                '</select>'
        }
    });

    // Length menu를 처음에는 숨긴다.
    $('.dt-length').addClass('hidden-length-menu');

    const downloadExcel = async () => {
        const params = {
            column1: $('#searchColumn1').val(),
            column2: $('#searchColumn2').val(),
            excelHeader: JSON.stringify([
                { label: "컬럼 1", field: "testColumn1" },
                { label: "컬럼 2", field: "testColumn2" },
                { label: "컬럼 3", field: "testColumn3" },
                { label: "컬럼 4", field: "testColumn4" },
                { label: "생성일", field: "insertDate" },
                { label: "변경일", field: "updateDate" }
            ]),
            fileName: 'my_data_2.xlsx'
        };

        try {
            const blob = await fetchUtil.getBlob('/api/user-data-2/excel/download', params);
            const url = window.URL.createObjectURL(blob);
            const a = document.createElement('a');
            a.href = url;
            a.download = params.fileName;
            document.body.appendChild(a);
            a.click();
            a.remove();
        } catch (error) {
            console.error('Error downloading Excel file:', error);
        }
    }


</script>


<%@ include file="/WEB-INF/common/footer.jsp" %>