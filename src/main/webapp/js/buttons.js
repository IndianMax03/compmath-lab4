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
            if (value === "" || isNaN(value) || !parseFloat(value)) {
                console.log("INVALID:" + value)
                isValid = false;
                return;
            }
            console.log("x valid:" + value)
            xValues.push(parseFloat(value));
        });

        $(".y-val").each(function () {
            let value = $(this).val().replaceAll(',', '.');
            if (value === "" || isNaN(value) || !parseFloat(value)) {
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
                document.getElementById("result").value = result['got'];
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

