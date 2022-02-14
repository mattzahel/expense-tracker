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

const getData = async () => {
    return await fetch('http://localhost:8080/expenses')
        .then(response => response.json())
        .then(data => {
            const dataset = data.map(function(el) {
                return {
                    value: el[0],
                    date: el[1]
                };
            });

            drawChart(dataset);
        });
}

getData();
