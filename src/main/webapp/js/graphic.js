//  created by Raj Manu on 01.05.2023

const ctx = $('#graphic')[0].getContext('2d');
let chart;

document.addEventListener('DOMContentLoaded', function () {
    chart = new Chart(ctx, {
        type: 'line',
        data: {
            labels: [],
            datasets: [{
                label: 'Ваши точки из таблицы слева',
                data: [],
                backgroundColor: 'rgba(255, 99, 132, 0.2)',
                borderColor: 'rgba(255, 99, 132, 1)',
                borderWidth: 1
            }]
        },
        options: {
            responsive: true,
            maintainAspectRatio: false,
            scales: {
                yAxes: [{
                    ticks: {
                        beginAtZero: false
                    }
                }]
            }
        }
    });
})
document.getElementById('dataFile').addEventListener('change', handleFileSelect, false);


$('.x-val').each(function () {
    $(this).on('change', redrawGraphic);
});

$('.y-val').each(function () {
    $(this).on('change', redrawGraphic);
});

function redrawGraphic() {
    const xFields = $('.x-val');

    const yFields = $('.y-val');

    console.log(xFields, yFields)

    const values = [];
    for (let i = 0; i < xFields.length; i++) {
        const xVal = parseFloat($(xFields[i]).val().replaceAll(',', '.'));
        const yVal = parseFloat($(yFields[i]).val().replaceAll(',', '.'));
        values.push({x: xVal, y: yVal});
    }

    for (let i = 0; i < values.length; i++) {
        if (isNaN(values[i].x) || isNaN(values[i].y)) {
            values[i].x = 0;
            values[i].y = 0;
        }
    }

    values.sort(function (a, b) {
        return a.x - b.x;
    });

    // Get unique x values
    let uniqueXValues = [];
    let uniqueYValues = [];
    for (let i = 0; i < values.length; i++) {
        if (!uniqueXValues.includes(values[i].x)) {
            uniqueXValues.push(values[i].x);
            uniqueYValues.push(values[i].y);
        }
    }

    const data = {
        labels: uniqueXValues,
        datasets: [{
            label: 'Ваши точки из таблицы слева',
            data: uniqueYValues,
            backgroundColor: 'rgba(255, 99, 132, 0.2)',
            borderColor: 'rgba(255, 99, 132, 1)',
            borderWidth: 3
        }]
    };

    const options = {
        responsive: true,
        maintainAspectRatio: false,
        scales: {
            yAxes: [{
                ticks: {
                    beginAtZero: false,
                    fontSize: 10,
                    max: Math.max(...uniqueYValues),
                    min: Math.min(...uniqueYValues)
                }
            }],
            xAxes: [{
                ticks: {
                    fontSize: 10,
                    max: Math.max(...uniqueXValues),
                    min: Math.min(...uniqueXValues)
                }
            }]
        },
        legend: {
            display: true,
            labels: {
                fontSize: 10,
            }
        }
    };

    chart.data = data;
    chart.options = options;

    chart.update();
}

function handleFileSelect(evt) {
    let files = evt.target.files; // список выбранных файлов
    let file = files[0];

    let reader = new FileReader();
    reader.onload = function (e) {
        // читаем содержимое файла
        let contents = e.target.result;
        let lines = contents.split('\n'); // разделяем содержимое файла на строки
        let xValues = lines[0].split(';'); // разделяем первую строку на массив значений X
        let yValues = lines[1].split(';'); // разделяем вторую строку на массив значений Y

        // заполняем ячейки таблицы
        let tableRows = document.querySelectorAll("#data table tbody tr");
        for (let i = 0; i < tableRows.length; i++) {
            let xInput = tableRows[i].querySelector("td:first-child input");
            let yInput = tableRows[i].querySelector("td:nth-child(2) input");
            if (xValues[i] === undefined || yValues[i] === undefined) continue
            let xVal = xValues[i].replaceAll(',', '.');
            let yVal = yValues[i].replaceAll(',', '.');
            let x = parseFloat(xVal) && !isNaN(xVal) && xVal !== null ? parseFloat(xVal) : undefined;
            let y = parseFloat(yVal) && !isNaN(yVal) && xVal !== null ? parseFloat(yVal) : undefined;
            console.log(x, y)
            if (x !== undefined && y !== undefined) { // проверяем, что значения X и Y существуют
                xInput.value = x;
                yInput.value = y;
            }
        }
        redrawGraphic();
    };
    reader.readAsText(file);
}
