<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/common/header.jsp" %>

<style>

    .custom-input {
        width: 300px;
    }


</style>

<div class="page-wrapper">
    <div class="page-content">

        <div id="userChart"></div>

    </div>
</div>

<script>

    const maxDataPoints = 20; // 최대 데이터 포인트 수
    let dataSeries = []; // 데이터 시리즈 배열

    const chartOptions = {
        chart: {
            type: 'line',
            height: 350,
            animations: {
                enabled: false,
                easing: 'linear',
                dynamicAnimation: {
                    speed: 1000
                }
            },
            toolbar: { show: false },
            zoom: { enabled: false }
        },
        dataLabels: { enabled: false },
        stroke: { curve: 'straight', width: 2 },
        grid: { borderColor: '#363636', show: true },
        markers: { size: 0 },
        legend: { show: false },
        series: [{
            name: 'Real-time Data',
            data: dataSeries
        }],
        xaxis: {
            type: 'datetime',
            labels: { datetimeUTC: false } // 한국 시간으로 표시되도록 설정
        }
    };

    const chart = new ApexCharts(document.querySelector("#userChart"), chartOptions);
    chart.render();

    // 임의의 값을 생성하는 함수
    const getRandomValue = () => {
        const value = Math.floor(Math.random() * 100);
        return value;
    };

    // 실시간 데이터 업데이트 함수
    const updateData = () => {
        const newData = {
            x: getKoreanTime().getTime(),
            y: getRandomValue()
        };

        // 데이터 추가
        dataSeries.push(newData);

        // 데이터가 최대 포인트 수를 넘으면 오래된 데이터 제거
        if (dataSeries.length > maxDataPoints) {
            dataSeries.shift();
        }

        // 차트 업데이트
        chart.updateSeries([{
            name: 'Real-time Data',
            data: dataSeries
        }]);
    };

    // 1초마다 데이터 업데이트
    setInterval(updateData, 1000);

</script>


<%@ include file="/WEB-INF/common/footer.jsp" %>