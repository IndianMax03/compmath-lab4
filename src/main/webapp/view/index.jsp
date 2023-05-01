<%-- created by Raj Manu on 01.05.2023 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Вычмат лаба#4</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.4/css/bulma-rtl.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/KaTeX/0.7.1/katex.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/KaTeX/0.7.1/katex.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>
<body>

<section class="section">

    <div class="columns">
        <div id="data" class="column is-half">
            <table class="table is-hoverable is-bordered">
                <thead>
                <tr>
                    <th>X</th>
                    <th>Y</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td><input class="input is-small x-val"></td>
                    <td><input class="input is-small y-val"></td>
                </tr>
                <tr>
                    <td><input class="input is-small x-val"></td>
                    <td><input class="input is-small y-val"></td>
                </tr>
                <tr>
                    <td><input class="input is-small x-val"></td>
                    <td><input class="input is-small y-val"></td>
                </tr>
                <tr>
                    <td><input class="input is-small x-val"></td>
                    <td><input class="input is-small y-val"></td>
                </tr>
                <tr>
                    <td><input class="input is-small x-val"></td>
                    <td><input class="input is-small y-val"></td>
                </tr>
                <tr>
                    <td><input class="input is-small x-val"></td>
                    <td><input class="input is-small y-val"></td>
                </tr>
                <tr>
                    <td><input class="input is-small x-val"></td>
                    <td><input class="input is-small y-val"></td>
                </tr>
                <tr>
                    <td><input class="input is-small x-val"></td>
                    <td><input class="input is-small y-val"></td>
                </tr>
                </tbody>
            </table>

            <div class="file is-info is-small is-centered">
                <label class="file-label">
                    <input id="dataFile" class="file-input" type="file" name="userData" multiple>
                    <span class="file-cta">
                                    <span class="file-label">Выбрать файл…</span>
                                </span>
                </label>
            </div>
        </div>
        <div id="graphicFrame" class="column is-half">
            <canvas id="graphic"></canvas>
        </div>
    </div>

</section>
<section>
    <div id="submitHelper" class="notification is-danger has-text-centered" style="display: none; position: absolute">
        <button id="notificationButton" class="delete"></button>
        <h1>Чел, учти: <b><u>иксы должны быть уникальными !цифрами!</u></b>, ну а <b><u>пустое поле соответствует
            значению 0</u></b>, усёк? Если нет, я ничего никуда не отправлю и ты ничего не получишь.</h1>
    </div>
</section>


<section class="section">
    <div class="columns is-vcentered">
        <div class="column has-text-centered">
            <div class="my-4">
                <button id="submitButton" class="button is-medium is-primary">
                    ЛЕСГО
                </button>
            </div>
            <div>
                <a id="anekdot" class="is-link">
                    анекдот для улыбочки
                </a>
            </div>
        </div>
        <div class="column is-three-fifths has-text-centered">
            <div class="field">
                <label class="label" for="result">Результат:</label>
                <div class="control">
                    <textarea id="result" class="textarea" rows="10" cols="10" readonly></textarea>
                </div>
            </div>
        </div>
    </div>
</section>


<script src="<c:url value="/js/graphic.js"/>"></script>
<script src="<c:url value="/js/buttons.js"/>"></script>
<script src="<c:url value="/js/anekdots.js"/>"></script>
</body>
</html>
