const getData = async () => {
    return await fetch('http://localhost:8080/expenses')
        .then(response => response.json())
}

const drawChart = (data) => {
    const ctx = document.getElementById('expensesChart');

    new Chart(ctx, {
        type: 'line',
        data: {
            datasets: [{
                data,
                backgroundColor: 'rgba(255,99,132,0.2)',
                borderColor: 'rgb(255,159,50)',
            }]
        },
        options: {
            plugins: {
                legend: {
                    display: false
                },
            },
            parsing: {
                xAxisKey: 'date',
                yAxisKey: 'value'
            },
            scales: {
                y: {
                    beginAtZero: true,
                },
            }
        }
    });
}

const setInfoVisibility = (data) => {
    const noDataInfo = document.querySelector('.no-data-info');
    const chart = document.querySelector('#expensesChart');

    if (data.length > 0) {
        noDataInfo.style.display = 'none';
        chart.style.display = 'block';
    } else {
        noDataInfo.style.display = 'block';
        chart.style.display = 'none';
    }
}


function init() {
    getData().then(data => {
        const dataset = data.map(function(el) {
            return {
                value: el[0],
                date: el[1]
            };
        });

        setInfoVisibility(dataset);
        drawChart(dataset);
    });
}

init();