//  created by Raj Manu on 01.05.2023


const notificationButton = document.getElementById("notificationButton");
let xValues = [];
let yValues = [];

$(document).ready(function () {
    $("#submitButton").on("click", function () {
        let isValid = true;
        xValues.splice(0, xValues.length);
        yValues.splice(0, yValues.length);

        $(".x-val").each(function () {
            let value = $(this).val().replaceAll(',', '.');

            if (value === null || value === undefined || isNaN(value) || value === "") {
                console.log("INVALID:" + value)
                isValid = false;
                return;
            }

            console.log("x valid:" + value)
            xValues.push(parseFloat(value));
        });

        $(".y-val").each(function () {
            let value = $(this).val().replaceAll(',', '.');

            if (value === null || value === undefined || isNaN(value) || value === "") {
                console.log("INVALID:" + value)
                isValid = false;
                return;
            }

            console.log("y valid:" + value)
            yValues.push(parseFloat(value));
        });

        console.log(new Set(xValues), xValues)
        if (new Set(xValues).size !== xValues.length) {
            isValid = false;
        }

        if (!isValid) {
            document.getElementById("submitHelper").style.display = "block";
            return;
        } else {
            document.getElementById("submitHelper").style.display = "none";
        }

        let data = {
            x: xValues,
            y: yValues
        }

        $.ajax({
            type: "POST",
            url: "points/solve",
            data: JSON.stringify(data),
            contentType: "application/json",
            dataType: "json",
            success: function (result) {
                let name = result['name'];
                let x = result['x'][0];
                let phy = result['phy'][0];
                let y = result['y'][0];
                let epsilons = result['epsilons'][0];
                let a = result['a'];
                let b = result['b'];
                let c = result['c'];
                let S = result['S']
                let r = result['r'];
                let deviation = result['deviation']

                let text = `name: ${name}\n` +
                    `x: ${x}\n` +
                    `phy: ${phy}\n` +
                    `y: ${y}\n` +
                    `epsilons: ${epsilons}\n` +
                    `a: ${a}\n` +
                    `b: ${b}\n` +
                    `c: ${c}\n` +
                    `S: ${S}\n` +
                    `r: ${r}\n` +
                    `deviation: ${deviation}\n`;

                document.getElementById("result").value = text;

                let chart = $('#graphic')[0].getContext('2d');

                const oldData = chart.data.datasets[0]; // сохраняем старый датасет

                const newData = {
                    label: 'Априки',
                    data: phy, // аппроксимированные значения y
                    borderColor: 'red',
                    backgroundColor: 'transparent',
                    pointRadius: 4,
                    pointHoverRadius: 6
                };

                chart.data.datasets = [oldData, newData]; // заменяем массив датасетов на новый, содержащий старый и новый датасеты
                chart.update(); // обновляем график

            },
            error: function (xhr, status, error) {
                document.getElementById("result").value = error;
            }
        })
    });
});

notificationButton.addEventListener("click", () => {
    submitHelper.style.display = "none";
})

